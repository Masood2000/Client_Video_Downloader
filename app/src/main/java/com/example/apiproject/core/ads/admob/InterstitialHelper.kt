package com.example.apiproject.core.ads.admob

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.CountDownTimer
import android.util.Log
import com.example.apiproject.util.ApplicationConstants.appOpenAndInterstitialTimeElapsed
import com.example.apiproject.util.ApplicationConstants.app_open_and_interstitial_time
import com.example.apiproject.util.ApplicationConstants.capping_time
import com.example.apiproject.util.ApplicationConstants.isInterstitialAdShowing
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.concurrent.TimeUnit

object InterstitialHelper {

    private const val TAG = "interstitial_ad_log"
    private var interstitialTimeElapsed = 0L
    var mInterstitialAd: InterstitialAd? = null
    private var isAdLoading = false
    private var timerShowAndLoad: CountDownTimer? = null
    private var mInterstitialLoadAndShowAd: InterstitialAd? = null

    fun showAndLoadInterstitial(
        activity: Activity,
        adId: String,
        canShowLoading: Boolean = true,
        useCapping: Boolean = true,
        screenName: String,
        interstitialAdShowListener: InterstitialAdShowListener
    ) {

        fun runInterstitial() {
            if (canShowLoading) {
                LoadingDialog.showLoadingDialog(activity)
            }
            if (mInterstitialAd != null) {

                CoroutineScope(Dispatchers.IO).launch {
                    if (canShowLoading) {
                        delay(1000)
                    } else {
                        delay(100)
                    }
                    withContext(Dispatchers.Main) {
                        LoadingDialog.hideLoadingDialog(activity)
                        Log.i(
                            TAG,
                            "showAndLoadInterstitial: Showing Interstitial Ad with ID: ${mInterstitialAd?.adUnitId}"
                        )
                        mInterstitialAd?.fullScreenContentCallback =
                            object : FullScreenContentCallback() {
                                override fun onAdImpression() {
                                    super.onAdImpression()
                                    mInterstitialAd = null
                                    LoadingDialog.hideLoadingDialog(activity)
                                    interstitialAdShowListener?.onInterstitialAdImpression()
                                    // postAnalytic(activity, "${screenName.lowercase()}_inter_impression")
                                    Log.i(
                                        TAG,
                                        "onAdImpression: Interstitial Ad Impression Received"
                                    )
                                }

                                override fun onAdDismissedFullScreenContent() {
                                    super.onAdDismissedFullScreenContent()
                                    LoadingDialog.hideLoadingDialog(activity)
                                    interstitialTimeElapsed = Calendar.getInstance().timeInMillis
                                    appOpenAndInterstitialTimeElapsed =
                                        Calendar.getInstance().timeInMillis
                                    interstitialAdShowListener?.onInterstitialAdDismissed()
                                    Log.i(
                                        TAG,
                                        "onAdDismissedFullScreenContent: Interstitial Ad is Dismissed"
                                    )
                                    //postAnalytic(activity)
                                    loadInterstitialAd(activity, adId, screenName)
                                    isInterstitialAdShowing = false
                                }

                                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                                    super.onAdFailedToShowFullScreenContent(p0)
                                    LoadingDialog.hideLoadingDialog(activity)

                                    isInterstitialAdShowing = false
                                    Log.i(
                                        TAG,
                                        "onAdFailedToShowFullScreenContent: Interstitial Ad Failed to SHOW with error: ${p0.message}"
                                    )
                                }
                            }
                        mInterstitialAd?.show(activity)
                        isInterstitialAdShowing = true

                        LoadingDialog.hideLoadingDialog(activity)
                    }
                }
            }
            else {
                LoadingDialog.hideLoadingDialog(activity)
                interstitialAdShowListener?.onInterstitialAdNull()
                Log.i(TAG, "showAndLoadInterstitial: Unable to show Interstitial AD : Ad is null")
                //loadInterstitialAd(activity, adId, screenName)
            }
        }

        if (useCapping) {
            if (

                isNetworkAvailable(activity) &&
                (timeDifference(interstitialTimeElapsed) > capping_time) &&
                (timeDifference(appOpenAndInterstitialTimeElapsed) > app_open_and_interstitial_time)
            ) {
                runInterstitial()
            } else {
                interstitialAdShowListener.onInterstitialAdNull()
            }
        } else {
            if (
                isNetworkAvailable(activity)
            ) {
                runInterstitial()
            } else {
                interstitialAdShowListener.onInterstitialAdNull()
            }
        }
    }

    fun loadAndShowInterstitial(
        activity: Activity,
        adId: String,
        screenName: String,
        interstitialAdShowListener: InterstitialAdShowListener
    ) {
        if (isNetworkAvailable(activity)) {
            LoadingDialog.showLoadingDialog(activity)
            timerShowAndLoad = object : CountDownTimer(12000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    if (millisUntilFinished >= 3000L && mInterstitialLoadAndShowAd != null) {
                        isInterstitialAdShowing = true
                        LoadingDialog.hideLoadingDialog(activity)
                        Log.i(
                            TAG,
                            "loadAndShowInterstitial: Showing Interstitial Ad with ID: $mInterstitialLoadAndShowAd"
                        )
                        mInterstitialLoadAndShowAd?.show(activity)
                        try {
                            timerShowAndLoad?.cancel()
                        } catch (_: Exception) {
                        } catch (_: java.lang.Exception) {
                        }

                    }
                }

                override fun onFinish() {
                    LoadingDialog.hideLoadingDialog(activity)
                    isInterstitialAdShowing = false
                    Log.i(TAG, "loadAndShowInterstitial: Timer for loading Interstitial ad ended")
                    interstitialAdShowListener.onInterstitialAdNull()
                }

            }

            if (mInterstitialLoadAndShowAd == null) {
                Log.i(TAG, "loadAndShowInterstitial: Loading Interstitial Ad with ID: $adId")
                InterstitialAd.load(activity, adId, AdRequest.Builder().build(),
                    object : InterstitialAdLoadCallback() {

                        override fun onAdFailedToLoad(adError: LoadAdError) {
                            mInterstitialLoadAndShowAd = null
                            isInterstitialAdShowing = false
                            LoadingDialog.hideLoadingDialog(activity)
                            interstitialAdShowListener.onInterstitialAdNull()
                            Log.i(
                                TAG,
                                "loadAndShowInterstitial: Interstitial ad failed to load with error: ${adError.message}"
                            )
                            postAnalytic("${screenName.lowercase()}_inter_fail")
                            try {
                                timerShowAndLoad?.cancel()
                            } catch (e: Exception) {
                                e.printStackTrace()
                            } catch (e: java.lang.Exception) {
                                e.printStackTrace()
                            }
                        }

                        override fun onAdLoaded(interstitialAd: InterstitialAd) {

                            Log.i(TAG, "loadAndShowInterstitial: Interstitial Ad loaded")
                            postAnalytic("${screenName.lowercase()}_inter_loaded")
                            mInterstitialLoadAndShowAd = interstitialAd

                            mInterstitialLoadAndShowAd?.fullScreenContentCallback =
                                object : FullScreenContentCallback() {
                                    override fun onAdDismissedFullScreenContent() {
                                        super.onAdDismissedFullScreenContent()
                                        isInterstitialAdShowing = false
                                        LoadingDialog.hideLoadingDialog(activity)
                                        postAnalytic("${screenName.lowercase()}_inter_dismiss")
                                        interstitialAdShowListener.onInterstitialAdDismissed()
                                        Log.i(
                                            TAG,
                                            "onAdDismissedFullScreenContent: Interstitial Ad is Dismissed"
                                        )
                                    }

                                    override fun onAdImpression() {
                                        super.onAdImpression()
                                        LoadingDialog.hideLoadingDialog(activity)
                                        mInterstitialLoadAndShowAd = null
                                        interstitialAdShowListener.onInterstitialAdImpression()
                                        postAnalytic("${screenName.lowercase()}_inter_impression")
                                        Log.i(
                                            TAG,
                                            "onAdImpression: Interstitial AD Impression received"
                                        )
                                    }

                                    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                                        super.onAdFailedToShowFullScreenContent(p0)
                                        LoadingDialog.hideLoadingDialog(activity)
                                        mInterstitialLoadAndShowAd = null
                                        isInterstitialAdShowing = false
                                        Log.i(
                                            TAG,
                                            "onAdFailedToShowFullScreenContent: Interstitial Ad failed to show with error: ${p0.message}"
                                        )
                                    }
                                }
                        }
                    })
            }
            timerShowAndLoad?.start()
        } else {
            isInterstitialAdShowing = false
            interstitialAdShowListener.onInterstitialAdNull()
        }
    }

    fun loadInterstitialAd(activity: Activity, adId: String, screenName: String) {
        if (mInterstitialAd == null && !isAdLoading) {
            isAdLoading = true
            Log.i(TAG, "loadInterstitialAd: Loading Interstitial Ad with ID: $adId")
            InterstitialAd.load(
                activity,
                adId,
                AdRequest.Builder().build(),
                object : InterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        mInterstitialAd = null
                        isAdLoading = false
                        postAnalytic("${screenName.lowercase()}_inter_fail")
                        Log.i(
                            TAG,
                            "onAdFailedToLoad: Interstitial Ad Failed to load with error ${adError.message}"
                        )
                    }

                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        mInterstitialAd = interstitialAd
                        isAdLoading = false
                        postAnalytic("${screenName.lowercase()}_inter_loaded")
                        Log.i(TAG, "onAdLoaded: Interstitial AD Loaded")
                    }
                })
        }
    }

    /**
     * Interstitial Ad Listener Enables you to override methods to fetch Ad states while showing an Interstitial
     * Ad. onInterstitialAdNull is required to be implemented in order to handle Ad check Fails.
     */

    interface InterstitialAdShowListener {
        fun onInterstitialAdImpression() {
            Log.d(
                "AD_LISTENER_TAG",
                "onInterstitialAdImpression: Interstitial Ad Impression received"
            )
        }

        fun onInterstitialAdDismissed() {
            Log.d("AD_LISTENER_TAG", "onInterstitialAdDismissed: Interstitial Ad Dismissed")
        }

        // Make sure to add navigation code on this method
        fun onInterstitialAdNull()

    }

    private fun postAnalytic(text: String) {
        try {
            // todo add post analytic code here
        } catch (_: Exception) {
        } catch (_: java.lang.Exception) {
        }
    }

    private fun timeDifference(millis: Long): Int {
        val current = Calendar.getInstance().timeInMillis
        val elapsedTime = current - millis
        return TimeUnit.MILLISECONDS.toSeconds(elapsedTime).toInt()
    }

    fun isNetworkAvailable(context: Context): Boolean {
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

}