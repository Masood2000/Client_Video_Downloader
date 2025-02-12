package com.example.apiproject.core.remoteconfig

import android.util.Log
import com.example.apiproject.BuildConfig
import com.example.apiproject.R
import com.example.apiproject.util.ApplicationConstants
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings


class RemoteConfig {
    companion object {
        private const val TAG = "RemoteConfig"


        //Listener
        var onRemoteFetched: ((Boolean) -> Unit)? = null

        var test = "not configured"



        /***
         * On Boarding native ad....
         */

        var show_on_boarding_native_ad = true
        var admob_native_on_boarding_id = if(BuildConfig.DEBUG){"ca-app-pub-3940256099942544/2247696110"} else{"ca-app-pub-1396902447261798/6734847627"}
        var admob_native_on_boarding_cta_round = 0.5f
        var admob_native_on_boarding_cta_color = "#0085FF"
        var admob_native_on_boarding_cta_text_color = "#FFFFFF"



        /***
         * Full screen native ad....
         */

        var show_full_screen_native_ad = true
        var admob_native_full_screen_id = if(BuildConfig.DEBUG){"ca-app-pub-3940256099942544/2247696110"} else{"ca-app-pub-1396902447261798/6941935029"}
        var admob_native_full_screen_cta_round = 0.5f
        var admob_native_full_screen_cta_color = "#0085FF"
        var admob_native_full_screen_cta_text_color = "#FFFFFF"



        /***
         * Feature One native ad....
         */

        var show_feature_one_native_ad = true
        var admob_native_feature_one_id = if(BuildConfig.DEBUG){"ca-app-pub-3940256099942544/2247696110"} else{"ca-app-pub-1396902447261798/3493644607"}
        var admob_native_feature_one_cta_round = 0.5f
        var admob_native_feature_one_cta_color = "#0085FF"
        var admob_native_feature_one_cta_text_color = "#FFFFFF"


        /***
         * Feature Two native ad....
         */

        var show_feature_two_native_ad = true
        var admob_native_feature_two_id = if(BuildConfig.DEBUG){"ca-app-pub-3940256099942544/2247696110"} else{"ca-app-pub-1396902447261798/5924366824"}
        var admob_native_feature_two_cta_round = 0.5f
        var admob_native_feature_two_cta_color = "#0085FF"
        var admob_native_feature_two_cta_text_color = "#FFFFFF"

        /***
         * Home native ad....
         */

        var show_home_native_ad = true
        var admob_native_home_id = if(BuildConfig.DEBUG){"ca-app-pub-3940256099942544/2247696110"} else{"ca-app-pub-1396902447261798/7237448491"}
        var admob_native_home_cta_round = 0.5f
        var admob_native_home_cta_color = "#0085FF"
        var admob_native_home_cta_text_color = "#FFFFFF"



        /***
         * downloaded videos native ad....
         */

        var show_downloaded_videos_native_ad = true
        var admob_native_downloaded_videos_id = if(BuildConfig.DEBUG){"ca-app-pub-3940256099942544/2247696110"} else{"ca-app-pub-1396902447261798/4236676796"}
        var admob_native_downloaded_videos_cta_round = 0.5f
        var admob_native_downloaded_videos_cta_color = "#0085FF"
        var admob_native_downloaded_videos_cta_text_color = "#FFFFFF"




        /***
         * Exit native ad....
         */

        var show_exit_native_ad = true
        var admob_exit_native_id = if(BuildConfig.DEBUG){"ca-app-pub-3940256099942544/2247696110"} else{"ca-app-pub-1396902447261798/8012270279"}
        var admob_native_exit_cta_round = 1f
        var admob_native_exit_cta_color = "#0085FF"
        var admob_native_exit_cta_text_color = "#FFFFFF"
        var admob_exit_interstitial_capping = true


        /***
         * Exit Interstitial ad....
         */

        var show_exit_Interstitial_ad = true


        /***
         * onboarding use cases
         */

        var show_on_boarding_always = true


        /***
         * downloading interstitial ad
         */

        var show_downloading_Interstitial_ad = true
        var admob_downloading_interstitial_capping = true

        /***
         * downloaded interstitial ad
         */

        var show_downloaded_Interstitial_ad = true
        var admob_downloaded_interstitial_capping = true

        /***
         * reels interstitial ad
         */

        var show_reels_Interstitial_ad = true
        var admob_reels_interstitial_capping = true


        /***
         * download_option_sheet interstitial ad
         */

        var show_download_option_sheet_Interstitial_ad = true
        var admob_download_option_sheet_interstitial_capping = true



        /***
         * banner ad
         */

        var admob_banner_id = if(BuildConfig.DEBUG){"ca-app-pub-3940256099942544/2014213617"} else{"ca-app-pub-1396902447261798/6862840137"}



    }



    fun remoteConfig() {

        Log.i(TAG, "remoteConfig: Called")

        val mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3).setFetchTimeoutInSeconds(10)
            .build()

        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_default)

        mFirebaseRemoteConfig.fetchAndActivate()
            .addOnSuccessListener {

                Log.i(TAG, "remoteConfig: Success ")


                //capping time
                ApplicationConstants.capping_time = mFirebaseRemoteConfig.getLong("capping_time").toInt()

                test = mFirebaseRemoteConfig.getString("test")



                //On Boarding Native

                show_on_boarding_native_ad = mFirebaseRemoteConfig.getBoolean("show_on_boarding_native_ad")
                admob_native_on_boarding_id = if(!BuildConfig.DEBUG) {mFirebaseRemoteConfig.getString("admob_native_on_boarding_id")} else{"ca-app-pub-3940256099942544/2247696110"}
                admob_native_on_boarding_cta_round = mFirebaseRemoteConfig.getDouble("admob_native_on_boarding_cta_round").toFloat()
                admob_native_on_boarding_cta_color = mFirebaseRemoteConfig.getString("admob_native_on_boarding_cta_color")
                admob_native_on_boarding_cta_text_color = mFirebaseRemoteConfig.getString("admob_native_on_boarding_cta_text_color")



                //Full Screen Native

                show_full_screen_native_ad = mFirebaseRemoteConfig.getBoolean("show_full_screen_native_ad")
                admob_native_full_screen_id = if(!BuildConfig.DEBUG) {mFirebaseRemoteConfig.getString("admob_native_full_screen_id")} else{"ca-app-pub-3940256099942544/2247696110"}
                admob_native_full_screen_cta_round = mFirebaseRemoteConfig.getDouble("admob_native_full_screen_cta_round").toFloat()
                admob_native_full_screen_cta_color = mFirebaseRemoteConfig.getString("admob_native_full_screen_cta_color")
                admob_native_full_screen_cta_text_color = mFirebaseRemoteConfig.getString("admob_native_full_screen_cta_text_color")


                //Feature One Native

                show_feature_one_native_ad = mFirebaseRemoteConfig.getBoolean("show_feature_one_native_ad")
                admob_native_feature_one_id = if(!BuildConfig.DEBUG) {mFirebaseRemoteConfig.getString("admob_native_feature_one_id")} else{"ca-app-pub-3940256099942544/2247696110"}
                admob_native_feature_one_cta_round = mFirebaseRemoteConfig.getDouble("admob_native_feature_one_cta_round").toFloat()
                admob_native_feature_one_cta_color = mFirebaseRemoteConfig.getString("admob_native_feature_one_cta_color")
                admob_native_feature_one_cta_text_color = mFirebaseRemoteConfig.getString("admob_native_feature_one_cta_text_color")



                //Feature Two Native

                show_feature_two_native_ad = mFirebaseRemoteConfig.getBoolean("show_feature_two_native_ad")
                admob_native_feature_two_id = if(!BuildConfig.DEBUG) {mFirebaseRemoteConfig.getString("admob_native_feature_two_id")} else{"ca-app-pub-3940256099942544/2247696110"}
                admob_native_feature_two_cta_round = mFirebaseRemoteConfig.getDouble("admob_native_feature_two_cta_round").toFloat()
                admob_native_feature_two_cta_color = mFirebaseRemoteConfig.getString("admob_native_feature_two_cta_color")
                admob_native_feature_two_cta_text_color = mFirebaseRemoteConfig.getString("admob_native_feature_two_cta_text_color")


                //Home Native

                show_home_native_ad = mFirebaseRemoteConfig.getBoolean("show_home_native_ad")
                admob_native_home_id = if(!BuildConfig.DEBUG) {mFirebaseRemoteConfig.getString("admob_native_home_id")} else{"ca-app-pub-3940256099942544/2247696110"}
                admob_native_home_cta_round = mFirebaseRemoteConfig.getDouble("admob_native_home_cta_round").toFloat()
                admob_native_home_cta_color = mFirebaseRemoteConfig.getString("admob_native_home_cta_color")
                admob_native_home_cta_text_color = mFirebaseRemoteConfig.getString("admob_native_home_cta_text_color")




                //exit Native

                show_exit_native_ad = mFirebaseRemoteConfig.getBoolean("show_exit_native_ad")
                admob_exit_native_id = if(!BuildConfig.DEBUG) {mFirebaseRemoteConfig.getString("admob_exit_native_id")} else{"ca-app-pub-3940256099942544/2247696110"}
                admob_native_exit_cta_round = mFirebaseRemoteConfig.getDouble("admob_native_exit_cta_round").toFloat()
                admob_native_exit_cta_color = mFirebaseRemoteConfig.getString("admob_native_exit_cta_color")
                admob_native_exit_cta_text_color = mFirebaseRemoteConfig.getString("admob_native_exit_cta_text_color")


                //exit Interstitial ad

                show_exit_Interstitial_ad = mFirebaseRemoteConfig.getBoolean("show_exit_interstitial_ad")

                //on boarding use cases

                show_on_boarding_always = mFirebaseRemoteConfig.getBoolean("show_on_boarding_always")


                //banner ad


                admob_banner_id = if(!BuildConfig.DEBUG) {mFirebaseRemoteConfig.getString("admob_banner_id")} else{"ca-app-pub-3940256099942544/2014213617"}

                //all capping values
                admob_exit_interstitial_capping = mFirebaseRemoteConfig.getBoolean("admob_exit_interstitial_capping")
                admob_downloading_interstitial_capping = mFirebaseRemoteConfig.getBoolean("admob_downloading_interstitial_capping")
                admob_downloaded_interstitial_capping = mFirebaseRemoteConfig.getBoolean("admob_downloaded_interstitial_capping")
                admob_reels_interstitial_capping = mFirebaseRemoteConfig.getBoolean("admob_reels_interstitial_capping")
                admob_download_option_sheet_interstitial_capping = mFirebaseRemoteConfig.getBoolean("admob_download_option_sheet_interstitial_capping")


                admob_native_downloaded_videos_id = if(!BuildConfig.DEBUG) {mFirebaseRemoteConfig.getString("admob_native_downloaded_videos_id")} else{"ca-app-pub-3940256099942544/2247696110"}
                onRemoteFetched?.invoke(true)

            }

            .addOnFailureListener {
                Log.i(TAG, "remoteConfig: Failure ")

                onRemoteFetched?.invoke(true)

            }
            .addOnCanceledListener { Log.i(TAG, "remoteConfig: Cancel ") }
            .addOnCompleteListener { Log.i(TAG, "remoteConfig: Complete ") }

    }

}

