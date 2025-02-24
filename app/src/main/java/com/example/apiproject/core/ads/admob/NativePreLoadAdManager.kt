package com.example.apiproject.core.ads.admob

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.apiproject.R
import com.example.apiproject.ui.activity.MainActivity

import com.example.videodownloader.core.ads.NativeLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView

object NativePreLoadAdManager {

    /**
     * You can create multiple native ads instances according to your preload requirements
     * to ensure scalability. Right now this class will make sure not to load next preloaded ad until
     * or unless the current native ad impression is received
     **/

    var nativeHomeAd: NativeAd? = null
    private var isHomeAdLoading = false

    var nativeOnBoardingAd: NativeAd? = null
    private var isOnBoardingAdLoading = false


    var nativeExitAd: NativeAd? = null
    private var isExitAdLoading = false


    /***
     * how to use onboarding ads...
     */

    var nativeFeatureOneAd: NativeAd? = null
    private var isFeatureOneAdLoading = false

    var nativeFeatureTwoAd: NativeAd? = null
    private var isFeatureTwoAdLoading = false

    var nativeFullScreenAd: NativeAd? = null
    private var isFullScreenAdLoading = false





    // For Multiple Native Ads Calls
    private val nativeAds: MutableList<NativeAd> = mutableListOf()

    var adLoadListener: AdLoadListener? = null

    const val TAG = "native_ad_log"

    /**
     * You can create separate functions for different preloaded native ads for
     * separation on concern
     **/

    fun loadHomeAd(context: Context, adUnitId: String, screenName: String) {

        if (nativeHomeAd != null || isHomeAdLoading) {
            Log.i(TAG, "PreLoadNative: Home Native ad is already loaded or loading")
            return  // Already loaded or loading
        }

        Log.i(TAG, "PreLoadNative: Loading Home Native Ad with ID: $adUnitId")
        isHomeAdLoading = true
        val adLoader = AdLoader.Builder(context, adUnitId)
            .forNativeAd { ad ->
                nativeHomeAd = ad
                isHomeAdLoading = false
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    isHomeAdLoading = false
                    nativeHomeAd = null
                    adLoadListener?.onHomeAdFailedToLoad(p0.message)
                    postAnalytic("${screenName.lowercase()}_native_fail",context)
                    postAnalytic("ad_failure",context)
                    Log.i(
                        TAG,
                        "PreLoadNative onAdFailedToLoad: Home  Native ad failed to load with error: ${p0.message}"
                    )
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    Log.i(TAG, "PreLoadNative onAdLoaded: Home native ad loaded")
                    postAnalytic("${screenName.lowercase()}_native_loaded",context)
                    adLoadListener?.onHomeAdLoaded(nativeHomeAd)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    Log.i(TAG, "PreLoadNative onAdLoaded: Home native ad Impression")
                    postAnalytic("ad_impression",context)
                    //nativeHomeAd = null
                }

                override fun onAdClicked() {
                    super.onAdClicked()
                    postAnalytic("${screenName.lowercase()}_native_click",context)
                }


            })
            .withNativeAdOptions(
                NativeAdOptions.Builder().setAdChoicesPlacement(NativeAdOptions.ADCHOICES_TOP_RIGHT)
                    .build()
            )
            .build()


        adLoader.loadAd(AdRequest.Builder().build())
    }

    fun loadOnBoardingAd(context: Context, adUnitId: String, screenName: String) {

        if (nativeOnBoardingAd != null || isOnBoardingAdLoading) {
            Log.i(TAG, "PreLoadNative: ob Native ad is already loaded or loading")
            return  // Already loaded or loading
        }

        Log.i(TAG, "PreLoadNative: Loading ob Native Ad with ID: $adUnitId")
        isOnBoardingAdLoading = true
        val adLoader = AdLoader.Builder(context, adUnitId)
            .forNativeAd { ad ->
                nativeOnBoardingAd = ad
                isOnBoardingAdLoading = false
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    isOnBoardingAdLoading = false
                    nativeOnBoardingAd = null
                    adLoadListener?.onOnBoardingAdFailedToLoad(p0.message)
                    postAnalytic("${screenName.lowercase()}_native_fail",context)
                    postAnalytic("ad_failure",context)
                    Log.i(
                        TAG,
                        "PreLoadNative onAdFailedToLoad: ob  Native ad failed to load with error: ${p0.message}"
                    )
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    Log.i(TAG, "PreLoadNative onAdLoaded: ob native ad loaded")
                    postAnalytic("${screenName.lowercase()}_native_loaded",context)

                    adLoadListener?.onOnBoardingAdLoaded(nativeOnBoardingAd)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    Log.i(TAG, "PreLoadNative onAdLoaded: OB native ad Impression")
                    postAnalytic("${screenName.lowercase()}_native_impression",context)
                    postAnalytic("ad_impression",context)
                    nativeOnBoardingAd = null
                }

                override fun onAdClicked() {
                    super.onAdClicked()
                    postAnalytic("${screenName.lowercase()}_native_click",context)
                }


            })
            .withNativeAdOptions(
                NativeAdOptions.Builder().setAdChoicesPlacement(NativeAdOptions.ADCHOICES_TOP_RIGHT)
                    .build()
            )
            .build()


        adLoader.loadAd(AdRequest.Builder().build())
    }

    fun loadExitAd(context: Context, adUnitId: String, screenName: String) {

        if (nativeExitAd != null || isExitAdLoading) {
            Log.i(TAG, "PreLoadNative: Exit Native ad is already loaded or loading")
            return  // Already loaded or loading
        }

        Log.i(TAG, "PreLoadNative: Loading Exit Native Ad with ID: $adUnitId")
        isExitAdLoading = true
        val adLoader = AdLoader.Builder(context, adUnitId)
            .forNativeAd { ad ->
                nativeExitAd = ad
                isExitAdLoading = false
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    isExitAdLoading = false
                    nativeExitAd = null
                    adLoadListener?.onExitAdFailedToLoad(p0.message)
                    postAnalytic("${screenName.lowercase()}_native_fail",context)
                    postAnalytic("ad_failure",context)
                    Log.i(
                        TAG,
                        "PreLoadNative onAdFailedToLoad: Exit  Native ad failed to load with error: ${p0.message}"
                    )
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    Log.i(TAG, "PreLoadNative onAdLoaded: Exit native ad loaded")
                    postAnalytic("${screenName.lowercase()}_native_loaded",context)
                    adLoadListener?.onExitAdLoaded(nativeExitAd)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    Log.i(TAG, "PreLoadNative onAdLoaded: Exit native ad Impression")
                    postAnalytic("${screenName.lowercase()}_native_impression",context)
                    postAnalytic("ad_failure",context)
                    //nativeHomeAd = null
                }

                override fun onAdClicked() {
                    super.onAdClicked()
                    postAnalytic("${screenName.lowercase()}_native_click",context)
                }


            })
            .withNativeAdOptions(
                NativeAdOptions.Builder().setAdChoicesPlacement(NativeAdOptions.ADCHOICES_TOP_RIGHT)
                    .build()
            )
            .build()


        adLoader.loadAd(AdRequest.Builder().build())
    }


    fun loadFeatureOneAd(context: Context, adUnitId: String, screenName: String) {


        if (nativeFeatureOneAd != null || isFeatureOneAdLoading) {
            Log.i(TAG, "PreLoadNative: Feature One Native ad is already loaded or loading")
            return  // Already loaded or loading
        }

        Log.i(TAG, "PreLoadNative: Loading Feature One Native Ad with ID: $adUnitId")
        isFeatureOneAdLoading = true
        val adLoader = AdLoader.Builder(context, adUnitId)
            .forNativeAd { ad ->
                nativeFeatureOneAd = ad
                isFeatureOneAdLoading = false
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    isFeatureOneAdLoading = false
                    nativeFeatureOneAd = null
                    adLoadListener?.onFeatureOneAdFailedToLoad(p0.message)
                    postAnalytic("${screenName.lowercase()}_native_fail",context)
                    postAnalytic("ad_failure",context)
                    Log.i(
                        TAG,
                        "PreLoadNative onAdFailedToLoad: Feature One  Native ad failed to load with error: ${p0.message}"
                    )
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    Log.i(TAG, "PreLoadNative onAdLoaded: Feature One native ad loaded")
                    postAnalytic("${screenName.lowercase()}_native_loaded",context)
                    adLoadListener?.onFeatureOneAdLoaded(nativeFeatureOneAd)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    Log.i(TAG, "PreLoadNative onAdLoaded: Feature One native ad Impression")
                    postAnalytic("ad_impression",context)
                    postAnalytic("${screenName.lowercase()}_native_impression",context)
                    //nativeFeatureOneAd = null
                }

                override fun onAdClicked() {
                    super.onAdClicked()
                    postAnalytic("${screenName.lowercase()}_native_click",context)
                }


            })
            .withNativeAdOptions(
                NativeAdOptions.Builder().setAdChoicesPlacement(NativeAdOptions.ADCHOICES_TOP_RIGHT)
                    .build()
            )
            .build()


        adLoader.loadAd(AdRequest.Builder().build())
    }

    fun loadFeatureTwoAd(context: Context, adUnitId: String, screenName: String) {


        if (nativeFeatureTwoAd != null || isFeatureTwoAdLoading) {
            Log.i(TAG, "PreLoadNative: Feature Two Native ad is already loaded or loading")
            return  // Already loaded or loading
        }

        Log.i(TAG, "PreLoadNative: Loading Feature Two Native Ad with ID: $adUnitId")
        isFeatureTwoAdLoading = true
        val adLoader = AdLoader.Builder(context, adUnitId)
            .forNativeAd { ad ->
                nativeFeatureTwoAd = ad
                isFeatureTwoAdLoading = false
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    isFeatureTwoAdLoading = false
                    nativeFeatureTwoAd = null
                    adLoadListener?.onFeatureTwoAdFailedToLoad(p0.message)
                    postAnalytic("${screenName.lowercase()}_native_fail",context)
                    postAnalytic("ad_failure",context)
                    Log.i(
                        TAG,
                        "PreLoadNative onAdFailedToLoad: Feature Two  Native ad failed to load with error: ${p0.message}"
                    )
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    Log.i(TAG, "PreLoadNative onAdLoaded: Feature Two native ad loaded")
                    postAnalytic("${screenName.lowercase()}_native_loaded",context)
                    adLoadListener?.onFeatureTwoAdLoaded(nativeFeatureTwoAd)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    Log.i(TAG, "PreLoadNative onAdLoaded: Feature Two native ad Impression")
                    postAnalytic("ad_impression",context)
                    postAnalytic("${screenName.lowercase()}_native_impression",context)
                    //nativeFeatureTwoAd = null
                }

                override fun onAdClicked() {
                    super.onAdClicked()
                    postAnalytic("${screenName.lowercase()}_native_click",context)
                }


            })
            .withNativeAdOptions(
                NativeAdOptions.Builder().setAdChoicesPlacement(NativeAdOptions.ADCHOICES_TOP_RIGHT)
                    .build()
            )
            .build()


        adLoader.loadAd(AdRequest.Builder().build())
    }

    fun loadFullScreenAd(context: Context, adUnitId: String, screenName: String) {



        if (nativeFullScreenAd != null || isFullScreenAdLoading) {
            Log.i(TAG, "PreLoadNative: Full Screen Native ad is already loaded or loading")
            return  // Already loaded or loading
        }

        Log.i(TAG, "PreLoadNative: Loading Full Screen Native Ad with ID: $adUnitId")
        isFullScreenAdLoading = true
        val adLoader = AdLoader.Builder(context, adUnitId)
            .forNativeAd { ad ->
                nativeFullScreenAd = ad
                isFullScreenAdLoading = false
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    isFullScreenAdLoading = false
                    nativeFullScreenAd = null
                    adLoadListener?.onFullScreenAdFailedToLoad(p0.message)
                    postAnalytic("${screenName.lowercase()}_native_fail",context)
                    postAnalytic("ad_failure",context)
                    Log.i(
                        TAG,
                        "PreLoadNative onAdFailedToLoad: Full Screen Native ad failed to load with error: ${p0.message}"
                    )
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    Log.i(TAG, "PreLoadNative onAdLoaded: Full Screen native ad loaded")
                    postAnalytic("${screenName.lowercase()}_native_loaded",context)
                    adLoadListener?.onFullScreenAdLoaded(nativeFullScreenAd)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    Log.i(TAG, "PreLoadNative onAdLoaded: Full Screen native ad Impression")
                    postAnalytic("ad_impression",context)
                    postAnalytic("${screenName.lowercase()}_native_impression",context)
                    //nativeFullScreenAd = null
                }

                override fun onAdClicked() {
                    super.onAdClicked()
                    postAnalytic("${screenName.lowercase()}_native_click",context)
                }


            })
            .withNativeAdOptions(
                NativeAdOptions.Builder().setAdChoicesPlacement(NativeAdOptions.ADCHOICES_TOP_RIGHT)
                    .build()
            )
            .build()


        adLoader.loadAd(AdRequest.Builder().build())
    }


    @SuppressLint("InflateParams")
    fun populateUnifiedNativeAdView(
        nativeAd: NativeAd?,
        context: Context,
        nativeContainer: ConstraintLayout,
        adMobNativeContainer: FrameLayout,
        nativeLayout: NativeLayout,
        adName: String = "",
        round: Float = 0f,
        color: String = "",
        textColor: String = ""
    ) {

        if (nativeAd != null) {
            Log.i(TAG, "PreLoadNative populateUnifiedNativeAdView: Showing $adName Native Ad")
            val textView = nativeContainer.findViewById<ShimmerFrameLayout>(R.id.loading_ad)
            if (textView != null) textView.visibility = View.GONE
            val textView2 = nativeContainer.findViewById<TextView>(R.id.loading_ad_text)
            if (textView2 != null) textView2.visibility = View.GONE
            val inflater = LayoutInflater.from(context)

            val adView: NativeAdView = when (nativeLayout) {
                NativeLayout.NATIVE_7B -> inflater.inflate(
                    R.layout.admob_native_7b,
                    null
                ) as NativeAdView

                NativeLayout.NATIVE_6A -> inflater.inflate(
                    R.layout.admob_native_6a,
                    null
                ) as NativeAdView

                NativeLayout.FULL_SCREEN -> inflater.inflate(
                    R.layout.full_screen_native_ad_layout,
                    null
                ) as NativeAdView

                else -> inflater.inflate(R.layout.admob_native_7b, null) as NativeAdView
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


            // Media
            if (adView.findViewById<MediaView>(R.id.ad_media) != null) {
                val mediaView: MediaView = adView.findViewById(R.id.ad_media)
                if (adView.mediaView != null)
                    adView.mediaView = mediaView
                else {
                    (adView.mediaView as MediaView).visibility = View.INVISIBLE
                }
            }

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
                    adView.bodyView?.visibility = View.INVISIBLE
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
                    val c = color.replace("\"", "")
                    callToActionBg.setBackgroundColor(Color.parseColor(c))
                } catch (e: Exception) {
                    callToActionBg.setBackgroundColor(Color.parseColor("#00BCD4"))
                } catch (e: java.lang.Exception) {
                    callToActionBg.setBackgroundColor(Color.parseColor("#00BCD4"))
                }
            }
        }

        val callToActionText = adView.findViewById<TextView>(R.id.ad_call_to_action)
        if (callToActionText != null) {
            if (textColor.isNotBlank()) {
                try {
                    val c = textColor.replace("\"", "")
                    callToActionText.setTextColor(Color.parseColor(c))
                } catch (e: Exception) {
                    callToActionText.setTextColor(Color.parseColor("#FFFFFF"))
                } catch (e: java.lang.Exception) {
                    callToActionText.setTextColor(Color.parseColor("#FFFFFF"))
                }
            }
        }
    }


    fun loadNativeAdsList(
        context: Context,
        adUnitId: String,
        numberOfAds: Int,
        screenName: String
    ) {

        nativeAds.clear()

        val adLoader = AdLoader.Builder(context, adUnitId)
            .forNativeAd {
                nativeAds.add(it)
                adLoadListener?.onNativeAdsListLoaded(nativeAds)
            }.withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    postAnalytic("${screenName.lowercase()}_native_fail",context)
                    Log.i(
                        TAG,
                        "PreLoadNative onAdFailedToLoad: Native ads failed to load with error: ${p0.message}"
                    )
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    Log.i(TAG, "PreLoadNative onAdLoaded: native ads loaded")
                    postAnalytic("${screenName.lowercase()}_native_loaded",context)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    postAnalytic("${screenName.lowercase()}_native_impression",context)
                }

                override fun onAdClicked() {
                    super.onAdClicked()
                    postAnalytic("${screenName.lowercase()}_native_click",context)
                }
            })
            .withNativeAdOptions(
                NativeAdOptions.Builder().setAdChoicesPlacement(NativeAdOptions.ADCHOICES_TOP_RIGHT)
                    .build()
            )
            .build()
        adLoader.loadAds(AdRequest.Builder().build(), numberOfAds)
        Log.i(TAG, "PreLoadNative: Loading Native Ads with ID: $adUnitId")
    }

    fun getNativeAdList(): MutableList<NativeAd> {
        return nativeAds
    }









    fun getHomeAd(): NativeAd? {
        return nativeHomeAd
    }

    fun isHomeAdLoading(): Boolean {
        return isHomeAdLoading
    }

    fun clearHomeAd() {
        nativeHomeAd?.destroy()
        nativeHomeAd = null
    }

    fun getOnBoardingAd(): NativeAd? {
        return nativeOnBoardingAd
    }

    fun isOnBoardingAdLoading(): Boolean {
        return isOnBoardingAdLoading
    }

    fun clearOnBoardingAd() {
        nativeOnBoardingAd?.destroy()
        nativeOnBoardingAd = null
    }

    fun getFeatureOneAd(): NativeAd? {
        return nativeFeatureOneAd
    }

    fun isFeatureOneAdLoading(): Boolean {
        return isFeatureOneAdLoading
    }

    fun clearFeatureOneAd() {
        nativeFeatureOneAd?.destroy()
        nativeFeatureOneAd = null
    }


    fun getFeatureTwoAd(): NativeAd? {
        return nativeFeatureTwoAd
    }

    fun isFeatureTwoAdLoading(): Boolean {
        return isFeatureTwoAdLoading
    }

    fun clearFeatureTwoAd() {
        nativeFeatureTwoAd?.destroy()
        nativeFeatureTwoAd = null
    }


    fun getFullScreenAd(): NativeAd? {
        return nativeFullScreenAd
    }

    fun isFullScreenAdLoading(): Boolean {
        return isFullScreenAdLoading
    }

    fun clearFullScreenAd() {
        nativeFullScreenAd?.destroy()
        nativeFullScreenAd = null
    }

    fun getExitAd(): NativeAd? {
        return nativeExitAd
    }

    fun isExitAdLoading(): Boolean {
        return isExitAdLoading
    }

    fun clearExitAd() {
        nativeExitAd?.destroy()
        nativeExitAd = null
    }


    private fun postAnalytic(text: String,context: Context) {
        try {
            try {
                if (context is MainActivity){
                    context.postAnalytic(text)
                }
            } catch (_: Exception) {
            } catch (_: java.lang.Exception) {
            }        } catch (_: Exception) {
        } catch (_: java.lang.Exception) {
        }
    }
}

/**
 * Ad Listeners are only implemented in Fragment or activities where the ad is to
 * be shown. AdListeners can be separated according to App Preload requirements
 **/

interface AdLoadListener {

    fun onHomeAdLoaded(nativeAd: NativeAd?) {
        Log.i("native_ad_log", "onAdLoaded: Home Native Ad Loaded")
    }

    fun onHomeAdFailedToLoad(error: String) {
        Log.i("native_ad_log", "onAdFailedToLoad: Home -> $error")
    }

    fun onOnBoardingAdLoaded(nativeAd: NativeAd?) {
        Log.i("native_ad_log", "onAdLoaded: On Boarding Native Ad Loaded")
    }

    fun onOnBoardingAdFailedToLoad(error: String) {
        Log.i("native_ad_log", "onAdFailedToLoad: On Boarding -> $error")
    }

    fun onFeatureOneAdLoaded(nativeAd: NativeAd?) {
        Log.i("native_ad_log", "onAdLoaded: Feature One Native Ad Loaded")
    }

    fun onFeatureOneAdFailedToLoad(error: String) {
        Log.i("native_ad_log", "onAdFailedToLoad: Feature One -> $error")
    }


    fun onFeatureTwoAdLoaded(nativeAd: NativeAd?) {
        Log.i("native_ad_log", "onAdLoaded: Feature Two Native Ad Loaded")
    }

    fun onFeatureTwoAdFailedToLoad(error: String) {
        Log.i("native_ad_log", "onAdFailedToLoad: Feature Two -> $error")
    }


    fun onFullScreenAdLoaded(nativeAd: NativeAd?) {
        Log.i("native_ad_log", "onAdLoaded: Full Screen Native Ad Loaded")
    }

    fun onFullScreenAdFailedToLoad(error: String) {
        Log.i("native_ad_log", "onAdFailedToLoad: Full Screen -> $error")
    }


    fun onExitAdLoaded(nativeAd: NativeAd?) {
        Log.i("native_ad_log", "onAdLoaded: Exit Native Ad Loaded")
    }

    fun onExitAdFailedToLoad(error: String) {
        Log.i("native_ad_log", "onAdFailedToLoad: Exit -> $error")
    }


    fun onNativeAdsListLoaded(ads: MutableList<NativeAd>) {
        Log.i("native_ad_log", "onNativeAdsList: ")
    }

}