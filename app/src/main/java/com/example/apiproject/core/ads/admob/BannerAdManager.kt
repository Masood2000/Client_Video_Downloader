package com.example.apiproject.core.ads.admob

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.View
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.apiproject.R

import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError


class BannerAdManager(private val activity: Activity) {

    val TAG = "banner_ad_log"
    private var bannerAdView: AdView? = null

    /**
     * Load and Show an Anchored Adaptive banner ad. Parent Container is used to Hide the
     * Ad container in case ad fails to load.
     **/

    fun isAdLoaded(): Boolean {
        return bannerAdView != null
    }

    fun isAdLoading(): Boolean {
        return bannerAdView?.isLoading == true
    }

    fun loadAnchoredAdaptiveBannerAd(
        parentContainer: ConstraintLayout,
        adMobContainer: FrameLayout,
        bannerAdId: String
    ) {


        bannerAdView?.destroy()
        bannerAdView = null

        //      Log.i(TAG, "loadBannerAd: Loading BannerAd with ID: ${R.string.admob_banner}")
        bannerAdView = AdView(activity)
        bannerAdView?.adUnitId = bannerAdId
        adMobContainer.removeAllViews()
        adMobContainer.addView(bannerAdView)

        val adRequest = AdRequest.Builder().build()
        bannerAdView?.loadAd(adRequest)
        bannerAdView?.adListener = object : AdListener() {

            override fun onAdClosed() {
                super.onAdClosed()
                Log.i(TAG, "banner onAdClosed: ")
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                super.onAdFailedToLoad(loadAdError)
                parentContainer.isVisible = false
                adMobContainer.isVisible = false
                Log.i(TAG, "Banner ad onAdFailedToLoad: " + loadAdError.message)
            }

            override fun onAdImpression() {
                super.onAdImpression()
                Log.i(TAG, "Banner ad onAdImpression: ")
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                val textView = parentContainer.findViewById<TextView>(R.id.loading_ad_tv_banner)
                if (textView != null) textView.visibility = View.GONE
                Log.i(TAG, "Banner onAdLoaded1: ")
            }

            override fun onAdClicked() {
                super.onAdClicked()
                Log.i(TAG, "Banner onAdClicked: ")

            }

        }
    }


    fun loadInlineBannerAd(
        parentContainer: ConstraintLayout,
        adMobContainer: FrameLayout,
        width: Int,
        height: Int,
        bannerAdId: String,
    ) {

        bannerAdView?.destroy()
        bannerAdView = null

        Log.i(TAG, "loadBannerAd: Loading Banner Ad with ID: $bannerAdId")
        bannerAdView = AdView(activity)
        bannerAdView?.adUnitId = bannerAdId
        adMobContainer.removeAllViews()
        adMobContainer.addView(bannerAdView)

        fun loadBanner(
            width: Int,
            height: Int,
            parentContainer: ConstraintLayout,
            adMobContainer: FrameLayout
        ) {

            try {
                val bannerAdSize =
                    AdSize.getInlineAdaptiveBannerAdSize(width, height)
                Log.i(TAG, "loadInlineBannerAd: $width , $height")
                // make banner collapsible
                bannerAdView?.setAdSize(bannerAdSize)
                val extras = Bundle()
                extras.putString("collapsible", "bottom")
                val adRequest =
                    AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter::class.java, extras)
                        .build()

                bannerAdView?.loadAd(adRequest)
                bannerAdView?.adListener = object : AdListener() {

                    override fun onAdClosed() {
                        super.onAdClosed()
                        Log.i(TAG, "banner onAdClosed: ")
                    }

                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                        super.onAdFailedToLoad(loadAdError)
                        parentContainer.isVisible = false
                        adMobContainer.isVisible = false
                        Log.i(TAG, "Banner ad onAdFailedToLoad: " + loadAdError.message)
                    }

                    override fun onAdImpression() {
                        super.onAdImpression()
                        val textView =
                            parentContainer.findViewById<TextView>(R.id.loading_ad_tv_banner)
                        if (textView != null) textView.visibility = View.GONE
                        Log.i(TAG, "Banner ad onAdImpression: ")
                    }

                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        val textView =
                            parentContainer.findViewById<TextView>(R.id.loading_ad_tv_banner)
                        if (textView != null)
                            textView.visibility = View.GONE
                        else
                            Log.i(TAG, "Banner onAdLoaded Text View is NULL: ")
                        Log.i(TAG, "Banner onAdLoaded1231: ")


                    }

                    override fun onAdClicked() {
                        super.onAdClicked()
                        Log.i(TAG, "Banner onAdClicked: ")
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "loadBannerAd: ${e.message}")
                e.printStackTrace()
            }


        }

        loadBanner(width, height, parentContainer, adMobContainer)
    }


    fun getViewDimensions(
        adMobContainer: FrameLayout,
        callback: (height: Int, width: Int) -> Unit
    ) {

        adMobContainer.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (adMobContainer.width > 0 && adMobContainer.height > 0) {
                    adMobContainer.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    Log.d(
                        TAG,
                        "onGlobalLayout: width is ${adMobContainer.width} and height is ${adMobContainer.height}"
                    )
                    val width = pxToDp(adMobContainer.width, activity)
                    val height = pxToDp(adMobContainer.height, activity)
                    Log.i(TAG, "onGlobalLayout: $width ----- $height")
                    callback.invoke(height, width)
                }
            }
        })

    }

    fun pxToDp(px: Int, context: Context): Int {
        val density = context.resources.displayMetrics.density
        return (px / density).toInt()
    }

    fun getInlineBannerAdSize(
        activity: Activity,
        adMobContainer: FrameLayout,
        callback: (AdSize) -> Unit
    ) {
        val display: Display = activity.windowManager.defaultDisplay
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
//                Log.i(TAG, "onGlobalLayout: $adHeight")
                callback.invoke(AdSize.getInlineAdaptiveBannerAdSize(adWidth, adHeight))
            }
        })
    }

    fun getAnchoredAdSize(activity: Activity, admobContainer: FrameLayout): AdSize {
        val display: Display = activity.windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)
        val density = outMetrics.density
        var adWidthPixels: Float = admobContainer.width.toFloat()
        if (adWidthPixels == 0f) {
            adWidthPixels = outMetrics.widthPixels.toFloat()
        }
        val adWidth = (adWidthPixels / density).toInt()
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth)
    }

}
