package com.example.apiproject.core.ads.admob

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.apiproject.R

import com.example.videodownloader.core.ads.NativeLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView


class NativeAdManager(private val context: Activity) {

    private val TAG = "native_ads"
    private var bannerAdView: AdView? = null

    fun loadAdsWithConfiguration(
        nativeContainer: ConstraintLayout,
        adMobContainer: FrameLayout,
        nativeLayout: NativeLayout,
        adMobId: String,
        config: Int,
        screenName: String,
        showBanner: Boolean,
        round: Float,
        color: String,
        textColor: String,
        callback: (NativeAd?) -> Unit = {}
    ) {
        if (/*!AppConstants.isPremium && */isNetworkAvailable(context)) {
            if (config.toDouble() == 0.0) { //No Ads
                nativeContainer.visibility = View.GONE
                return
            } else if (config.toDouble() == 1.0) {
                checkView(
                    nativeContainer,
                    adMobContainer,
                    nativeLayout,
                    adMobId,
                    screenName,
                    showBanner,
                    round,
                    color,
                    textColor
                ) { nativeAd ->
                    callback.invoke(nativeAd)
                }
            }
        } else {
            nativeContainer.visibility = View.GONE
        }
    }

    private fun checkView(
        nativeContainer: ConstraintLayout,
        adMobContainer: FrameLayout,
        nativeLayout: NativeLayout,
        adMobId: String,
        screenName: String,
        showBanner: Boolean,
        round: Float,
        color: String,
        textColor: String,
        callback: (NativeAd?) -> Unit
    ) {
        val vto = nativeContainer.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val heightPx = nativeContainer.measuredHeight
                val heightDp = (heightPx / context.resources.displayMetrics.density).toInt()
                nativeContainer.viewTreeObserver.removeOnGlobalLayoutListener(this)
                if (heightDp < 49) {
                    nativeContainer.visibility = View.GONE
                } else {
                    loadNative(
                        nativeContainer,
                        adMobContainer,
                        nativeLayout,
                        adMobId,
                        screenName,
                        showBanner,
                        round,
                        color,
                        textColor,
                    ) { nativeAd ->
                        callback.invoke(nativeAd)
                    }
                }
            }
        })
    }

    private fun loadNative(
        nativeContainer: ConstraintLayout,
        adMobContainer: FrameLayout,
        nativeLayout: NativeLayout,
        adMobId: String,
        screenName: String,
        showBanner: Boolean,
        round:Float,
        color:String,
        textColor:String,
        callback: (NativeAd?) -> Unit
    ) {
        if (adMobNativeAd == null) {

            val builder: AdLoader.Builder = AdLoader.Builder(context, adMobId)
            val adLoader = builder.forNativeAd { unifiedNativeAd: NativeAd? ->
                adMobNativeAd = unifiedNativeAd
            }.withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(i: LoadAdError) {
                    super.onAdFailedToLoad(i)
                    Log.i(
                        TAG,
                        "Native Ad Failed to load; code: " + i.code + ", message: " + i.message
                    )
                    postAnalytic("${screenName.lowercase()}_native_fail")
                    if (showBanner)
                        nativeContainer.visibility = View.GONE
                       /* loadBannerAd(
                            nativeContainer,
                            adMobContainer,
                            context.getString(R.string.adaptive_banner),
                            screenName
                        )*/
                    else
                        nativeContainer.visibility = View.GONE
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    val privateAD = adMobNativeAd
                    nativeContainer.visibility = View.VISIBLE
                    adMobContainer.visibility = View.VISIBLE
                    Log.i(TAG, "onAdLoaded: Native Ad Loaded")
                    populateUnifiedNativeAdView(
                        privateAD,
                        nativeContainer,
                        adMobContainer,
                        nativeLayout,
                        round,
                        color,
                        textColor
                    )
                    adMobNativeAd = null
                    postAnalytic("${screenName.lowercase()}_native_loaded")
                    callback.invoke(privateAD)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    postAnalytic("${screenName.lowercase()}_native_impression")

                }

                override fun onAdClicked() {
                    super.onAdClicked()
                    postAnalytic("${screenName.lowercase()}_native_click")
                }


            }).withNativeAdOptions(
                com.google.android.gms.ads.nativead.NativeAdOptions.Builder()
                    .setAdChoicesPlacement(com.google.android.gms.ads.nativead.NativeAdOptions.ADCHOICES_TOP_RIGHT)
                    .build()
            ).build()
            adLoader.loadAd(AdRequest.Builder().build())
            Log.i(TAG, "loadNative: Loading Native Ad with id: $adMobId")
        } else {
            val privateAD = adMobNativeAd
            nativeContainer.visibility = View.VISIBLE
            adMobContainer.visibility = View.VISIBLE
            populateUnifiedNativeAdView(privateAD, nativeContainer, adMobContainer, nativeLayout,round,color,textColor)
            adMobNativeAd = null
        }
    }

    @SuppressLint("InflateParams")
    fun populateUnifiedNativeAdView(
        nativeAd: NativeAd?,
        nativeContainer: ConstraintLayout,
        adMobNativeContainer: FrameLayout,
        nativeLayout: NativeLayout,
        round:Float,
        color:String,
        textColor:String,
    ) {
        Log.i(TAG, "populateUnifiedNativeAdView: Showing Native Ad")
        if (nativeAd != null) {
            val textView = nativeContainer.findViewById<ShimmerFrameLayout>(R.id.loading_ad)
            if (textView != null) textView.visibility = View.GONE
            val inflater = LayoutInflater.from(context)

            val adView: NativeAdView = when (nativeLayout) {
                NativeLayout.NATIVE_7A -> inflater.inflate(
                    R.layout.admob_native_7b,
                    null
                ) as NativeAdView

                NativeLayout.NATIVE_7B -> inflater.inflate(
                    R.layout.admob_native_7b,
                    null
                ) as NativeAdView

                NativeLayout.NATIVE_6A -> inflater.inflate(
                    R.layout.admob_native_6a,
                    null
                ) as NativeAdView

                NativeLayout.NATIVE_2A -> inflater.inflate(
                    R.layout.admob_native_7b,
                    null
                ) as NativeAdView  //need to be changed
                NativeLayout.NATIVE_3A -> inflater.inflate(
                    R.layout.admob_native_7b,
                    null
                ) as NativeAdView

                NativeLayout.NATIVE_3B -> inflater.inflate(
                    R.layout.admob_native_3_b,
                    null
                ) as NativeAdView

                NativeLayout.NATIVE_5A -> inflater.inflate(
                    R.layout.admob_native_7b,
                    null
                ) as NativeAdView

                NativeLayout.NATIVE_1A -> inflater.inflate(
                    R.layout.admob_native_7b,
                    null
                ) as NativeAdView

                NativeLayout.NATIVE_1B -> inflater.inflate(
                    R.layout.admob_native_7b,
                    null
                ) as NativeAdView

                NativeLayout.NATIVE_8B -> inflater.inflate(
                    R.layout.admob_native_8b,
                    null
                ) as NativeAdView

                else -> inflater.inflate(
                    R.layout.admob_native_7b,
                    null
                ) as NativeAdView
            }
            adMobNativeContainer.visibility = View.VISIBLE
            adMobNativeContainer.removeAllViews()
            adMobNativeContainer.addView(adView)


            setRoundAndColor(adView, round, color, textColor)

            // Media
            if (adView.findViewById<MediaView>(R.id.ad_media) != null) {
                val mediaView: MediaView = adView.findViewById(R.id.ad_media)
                adView.mediaView = mediaView
            }

            // Set other ad assets.
            adView.headlineView = adView.findViewById(R.id.ad_headline)
            adView.bodyView = adView.findViewById(R.id.ad_body)
            adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)
            adView.iconView = adView.findViewById(R.id.ad_app_icon)

            //Headline
            if (adView.headlineView != null) {
                if (nativeAd.headline != null) {
                    (adView.headlineView as TextView).visibility = View.VISIBLE
                    (adView.headlineView as TextView).text = nativeAd.headline
                    (adView.headlineView as TextView).isSelected = true
                } else {
                    (adView.headlineView as TextView).visibility = View.INVISIBLE
                }
            }

            //Body
            if (adView.bodyView != null) {
                if (nativeAd.body == null) {
                    adView.bodyView?.visibility = View.GONE
                } else {
                    adView.bodyView?.visibility = View.VISIBLE
                    (adView.bodyView as TextView).text = nativeAd.body
                }
            }

            //Call to Action
            if (adView.callToActionView != null) {
                if (nativeAd.callToAction == null) {
                    adView.callToActionView?.visibility = View.INVISIBLE
                } else {
                    adView.callToActionView?.visibility = View.VISIBLE
                    (adView.callToActionView as TextView).text = nativeAd.callToAction
                }
            }

            //Icon
            if (adView.iconView != null) {
                if (nativeAd.icon == null) {
                    adView.iconView?.visibility = View.GONE
                } else {
                    (adView.iconView as ImageView).setImageDrawable(nativeAd.icon?.drawable)
                    (adView.iconView as ImageView).visibility = View.VISIBLE
                }
            }

            //price
            if (adView.priceView != null) {
                if (nativeAd.price == null) {
                    adView.priceView?.visibility = View.GONE
                } else {
                    adView.priceView?.visibility = View.GONE
                    (adView.priceView as TextView).text = nativeAd.price
                }
            }

            //Store
            if (adView.storeView != null) {
                if (nativeAd.store == null) {
                    adView.storeView?.visibility = View.GONE
                } else {
                    adView.storeView?.visibility = View.GONE
                    (adView.storeView as TextView).text = nativeAd.store
                }
            }

            //Rating
            if (adView.starRatingView != null) {
                if (nativeAd.starRating != null) {
                    (adView.starRatingView as RatingBar).rating = nativeAd.starRating!!.toFloat()
                }
                adView.starRatingView?.visibility = View.GONE
            }

            //Advertiser
            if (adView.advertiserView != null) {
                if (nativeAd.advertiser != null) {
                    (adView.advertiserView as TextView).text = nativeAd.advertiser
                }
                adView.advertiserView?.visibility = View.GONE
            }
            adView.setNativeAd(nativeAd)
        } else {
            nativeContainer.visibility = View.GONE
        }
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    private fun setRoundAndColor(
        adView: NativeAdView,
        round: Float,
        color: String,
        textColor: String
    ) {
        val callToActionBg = adView.findViewById<ImageFilterView>(R.id.ifv_ad_call_to_action)
        if (callToActionBg != null) {
            callToActionBg.roundPercent = round
            if (color.isNotBlank()) {
                try {

                    val c = color.replace("\"","")
                    callToActionBg.setBackgroundColor(Color.parseColor(c))

                } catch (e: Exception) {

                    callToActionBg.setBackgroundColor(Color.parseColor("#00BCD4"))
                } catch (e: java.lang.Exception) {

                    callToActionBg.setBackgroundColor(Color.parseColor("#00BCD4"))
                }
            }
        }
        val callToActionText = adView.findViewById<TextView>(R.id.ad_call_to_action)
        if(callToActionText != null){
            if (textColor.isNotBlank()) {
                try {
                    val c = textColor.replace("\"","")

                    callToActionText.setTextColor(Color.parseColor(c))
                } catch (e: Exception) {
                    callToActionText.setTextColor(Color.parseColor("#FFFFFF"))
                } catch (e: java.lang.Exception) {
                    callToActionText.setTextColor(Color.parseColor("#FFFFFF"))
                }
            }
        }
    }

    /*

    private fun loadBannerAd(
        parentContainer: ConstraintLayout,
        adMobContainer: FrameLayout,
        bannerAdId: String,
        screenName: String
    ) {
        Log.i(TAG, "loadBannerAd: Loading Banner Ad")
        bannerAdView?.destroy()
        bannerAdView = null
        bannerAdView = AdView(context)
        bannerAdView?.adUnitId = bannerAdId
        adMobContainer.removeAllViews()
        adMobContainer.addView(bannerAdView)
        getAdSize(adMobContainer) { adSize ->
            bannerAdView?.setAdSize(adSize)
            val adRequest = AdRequest.Builder().build()
            bannerAdView?.loadAd(adRequest)
            bannerAdView?.adListener = object : AdListener() {

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    super.onAdFailedToLoad(loadAdError)
                    parentContainer.isVisible = false
                    adMobContainer.isVisible = false
                    postAnalytic("${screenName.lowercase()}_banner_fail")
                    Log.i(TAG, "Banner ad onAdFailedToLoad: " + loadAdError.message)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    postAnalytic("${screenName.lowercase()}_banner_impression")
                    Log.i(TAG, "Banner ad onAdImpression: ")
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    val textView = parentContainer.findViewById<TextView>(R.id.loading_ad)
                    if (textView != null) textView.visibility = View.GONE
                    postAnalytic("${screenName.lowercase()}_banner_loaded")
                    Log.i(TAG, "Banner onAdLoaded: ")
                }

                override fun onAdClicked() {
                    super.onAdClicked()
                    postAnalytic("${screenName.lowercase()}_banner_click")
                    Log.i(TAG, "Banner onAdClicked: ")
                }
            }
        }
    }

    */


    private fun getAdSize(adMobContainer: FrameLayout, callback: (AdSize) -> Unit) {
        val display: Display = context.windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)
        val density = outMetrics.density
        adMobContainer.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                adMobContainer.viewTreeObserver.removeOnGlobalLayoutListener(this)
                var adWidthPixels: Float = adMobContainer.width.toFloat()
                var adHeightPixels: Float = adMobContainer.height.toFloat()
                if (adWidthPixels == 0f) {
                    adWidthPixels = outMetrics.widthPixels.toFloat()
                }
                if (adHeightPixels == 0f) {
                    adHeightPixels = outMetrics.heightPixels.toFloat()
                }
                val adWidth = (adWidthPixels / density).toInt()
                val adHeight = (adHeightPixels / density).toInt()
                callback.invoke(AdSize.getInlineAdaptiveBannerAdSize(adWidth, adHeight))
            }
        })
    }

    private fun postAnalytic(text: String) {
        try {
            // todo add post analytic code here
        } catch (_: Exception) {
        } catch (_: java.lang.Exception) {
        }
    }

    companion object {
        private var adMobNativeAd: NativeAd? = null
    }
}