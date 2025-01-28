package com.example.apiproject.core.ads.admob

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.apiproject.R
import com.example.apiproject.util.ApplicationConstants.appOpenAndInterstitialTimeElapsed
import com.example.apiproject.util.ApplicationConstants.isAppOpenAdShowing
import com.example.apiproject.util.ApplicationConstants.isInterstitialAdShowing
import com.example.apiproject.util.ApplicationConstants.isSplash
import com.google.android.gms.ads.AdActivity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.appopen.AppOpenAd.AppOpenAdLoadCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit
import com.example.apiproject.DownloadApplication

class AppOpenManager(private val myApplication: DownloadApplication) : LifecycleObserver, ActivityLifecycleCallbacks {
    private var appOpenAd: AppOpenAd? = null
    private lateinit var loadCallback: AppOpenAdLoadCallback
    private var currentActivity: Activity? = null
    private var loadTime: Long = 0
    private var appOpenAdTimeElapsed = 0L

    //*LifecycleObserver methods
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {

        showAdIfAvailable()

        Log.d(LOG_TAG, "onStart")
    }

    fun fetchAd() {
        if (true) {
            if (isAdAvailable) {
                return
            }
            Log.i(LOG_TAG, "fetchAd: Loading AppOpen Ad")
            loadCallback = object : AppOpenAdLoadCallback() {
                override fun onAdLoaded(appOpenAd: AppOpenAd) {
                    //   super.onAdLoaded(appOpenAd);
                    this@AppOpenManager.appOpenAd = appOpenAd
                    loadTime = Date().time
                    Log.i(LOG_TAG, "onAdLoaded: ")
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    // super.onAdFailedToLoad(loadAdError);
                    Log.i(LOG_TAG, "onAdFailedToLoad: app open ad ${loadAdError.message}")
                }
            }
            try {
                val request = adRequest
                AppOpenAd.load(myApplication,myApplication.getString(R.string.admob_app_open), request, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback)
            } catch (e: Exception) {
                Log.i("exception", "fetchAd: \$e")
            }
        }
    }

    private fun wasLoadTimeLessThanNHoursAgo(): Boolean {
        val dateDifference = Date().time - loadTime
        val numMilliSecondsPerHour: Long = 3600000
        return dateDifference < numMilliSecondsPerHour * 4
    }

    private val isAdAvailable: Boolean
        get() = appOpenAd != null && wasLoadTimeLessThanNHoursAgo()

     private fun showAdIfAvailable() {
        if (
            !isSplash
            /*&&
            (timeDifference(appOpenAdTimeElapsed) > app_open_ad_time) &&
            (timeDifference(appOpenAndInterstitialTimeElapsed) > app_open_and_interstitial_time)
             */
        ) {
            Log.d(LOG_TAG, "$isShowingAd, $isAdAvailable, $isInterstitialAdShowing")
            if (!isShowingAd && isAdAvailable && !isInterstitialAdShowing) {
                Log.d(LOG_TAG, "Will show ad.")
                val fullScreenContentCallback: FullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        appOpenAd = null
                        isShowingAd = false
                        isAppOpenAdShowing = false
                        appOpenAdTimeElapsed = Calendar.getInstance().timeInMillis
                        appOpenAndInterstitialTimeElapsed = Calendar.getInstance().timeInMillis
                        fetchAd()
                    }
                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {}
                    override fun onAdShowedFullScreenContent() {
                        isShowingAd = true
                        isAppOpenAdShowing = true
                    }
                }
                appOpenAd?.fullScreenContentCallback = fullScreenContentCallback
                if (currentActivity != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        delay(200)
                        withContext(Dispatchers.Main) {
                            if(currentActivity != null)
                                appOpenAd?.show(currentActivity!!)
                        }
                    }

                }
            }
            else {
                Log.d(LOG_TAG, "Can not show ad.")
                fetchAd()
            }
        }
    }

    private fun timeDifference(millis: Long): Int {
        val current = Calendar.getInstance().timeInMillis
        val elapsedTime = current - millis
        return TimeUnit.MILLISECONDS.toSeconds(elapsedTime).toInt()
    }

    private val adRequest: AdRequest
        get() = AdRequest.Builder().build()

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        if (activity !is AdActivity) currentActivity = activity
    }

    override fun onActivityStarted(activity: Activity) {
        if (activity !is AdActivity) currentActivity = activity
    }

    override fun onActivityResumed(activity: Activity) {
        if (activity !is AdActivity) currentActivity = activity
    }

    override fun onActivityPaused(activity: Activity) {
        if (activity !is AdActivity) currentActivity = activity
    }

    override fun onActivityStopped(activity: Activity) {
        if (activity !is AdActivity) currentActivity = activity
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        if (activity !is AdActivity) currentActivity = activity
    }

    override fun onActivityDestroyed(activity: Activity) {
        currentActivity = null
    }

    companion object {
        private const val LOG_TAG = "app_open_ad_log"
        private var isShowingAd = false
    }

    init {
        Log.d(LOG_TAG, "intializing: here ")
        myApplication.registerActivityLifecycleCallbacks(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }


}