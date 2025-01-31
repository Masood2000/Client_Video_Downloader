package com.example.apiproject.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController

import com.example.apiproject.R
import com.example.apiproject.core.ads.admob.InterstitialHelper
import com.example.apiproject.core.ads.admob.NativePreLoadAdManager
import com.example.apiproject.core.remoteconfig.RemoteConfig
import com.example.apiproject.data.preferences.SharedPreference
import com.example.apiproject.databinding.FragmentSplashBinding
import com.example.apiproject.ui.activity.MainActivity
import com.example.apiproject.ui.base.BaseFragment
import com.example.apiproject.util.ApplicationConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.system.exitProcess

// heelo world
class SplashFragment : BaseFragment() {

    private val binding by lazy { FragmentSplashBinding.inflate(layoutInflater) }


    private var loadingDelay: Long = 100
    private var boolNewUser = false


    lateinit var preferences: SharedPreference

    override fun lazyLoad() {

    }

    override fun setViewBinding(): View {
        return binding.root
    }

    override fun initView() {

        activity?.let {
            it.onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                it.finish()
                exitProcess(0)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ApplicationConstants.isSplash = true

        preferences = SharedPreference(requireContext())

        boolNewUser = preferences.isNewUser()

        Log.d(TAG, "$TAG onCreate: Called")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RemoteConfig().remoteConfig()
        RemoteConfig.onRemoteFetched = {
            startFragment()
        }


    }


    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
        if (binding.progress.progress == 100 || InterstitialHelper.mInterstitialAd != null) {
            binding.progress.progress = 101
            navigateFragment()
        }

    }

    private fun startFragment() {

        Log.d(TAG, "$TAG startFragment: Called")

        binding.splashTitle.animation =
            android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)

        //ads related functionality
        activity.let {
            if (it is MainActivity) {

                //interstitial ad
                InterstitialHelper.mInterstitialAd = null
                NativePreLoadAdManager.nativeHomeAd = null

                // load interstitial ad
                InterstitialHelper.loadInterstitialAd(
                    it,
                    it.getString(R.string.interstitial_splash),
                    "splash",
                )


                //preload native ads
                preLoadNativeAds()

            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                while (true) {

                    binding.progress.progress += 1



                    Log.d("progress", "startFragment: ${binding.progress.progress}")

                    if (InterstitialHelper.mInterstitialAd != null) {
                        // ad has been loaded, break the loop

                        Log.d(TAG, "ad loaded inter")
                        binding.progress.setProgress(100, true)

                    }

                    if ((binding.progress.progress == 100)) {

                        Log.d(TAG, "startFragment: navigating")
                        binding.progress.setProgress(101, true)
                        withContext(Dispatchers.Main) {
                            navigateFragment()
                        }
                        break
                    }

                    delay(loadingDelay)

                }

            }


        }


    }

    private fun navigateFragment() {


        Log.d(TAG, "navigateFragment: true")


        activity?.let {
            if (it is MainActivity) {
                InterstitialHelper.showAndLoadInterstitial(
                    it,
                    it.getString(R.string.interstitial_inner),
                    true,
                    useCapping = false,
                    "splash",
                    object : InterstitialHelper.InterstitialAdShowListener {
                        override fun onInterstitialAdImpression() {
                            super.onInterstitialAdImpression()


                            if (boolNewUser) {
                                if (findNavController().currentDestination?.id == R.id.splashFragment) {

                                    activity?.let {
                                        if (it is MainActivity) {
                                            findNavController().navigate(R.id.action_splashFragment_to_obInterestFragment)

                                        }
                                    }

                                }
                            }
                            else {

                                if(RemoteConfig.show_on_boarding_always){
                                    if (findNavController().currentDestination?.id == R.id.splashFragment) {

                                        activity?.let {
                                            if (it is MainActivity) {
                                                findNavController().navigate(R.id.action_splashFragment_to_onboardingFragment)

                                            }
                                        }

                                    }
                                }
                                else{
                                    activity?.let {
                                        if (it is MainActivity) {
                                            if (it.onSplashLinkDetected) {
                                                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                                                it.handleResume()

                                            } else {
                                                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                                            }
                                        }
                                    }
                                }

                            }


                        }

                        override fun onInterstitialAdNull() {
                            if (findNavController().currentDestination?.id == R.id.splashFragment) {

                                if (boolNewUser) {
                                    if (findNavController().currentDestination?.id == R.id.splashFragment) {

                                        activity?.let {
                                            if (it is MainActivity) {
                                                findNavController().navigate(R.id.action_splashFragment_to_obInterestFragment)

                                            }
                                        }

                                    }
                                }
                                else {

                                    if(RemoteConfig.show_on_boarding_always){
                                        if (findNavController().currentDestination?.id == R.id.splashFragment) {

                                            activity?.let {
                                                if (it is MainActivity) {
                                                    findNavController().navigate(R.id.action_splashFragment_to_onboardingFragment)

                                                }
                                            }

                                        }
                                    }
                                    else{
                                        activity?.let {
                                            if (it is MainActivity) {
                                                if (it.onSplashLinkDetected) {
                                                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                                                    it.handleResume()

                                                } else {
                                                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                                                }
                                            }
                                        }
                                    }

                                }

                            }
                        }
                    }
                )
            }
        }


    }


    private fun preLoadNativeAds() {

        /***
         * PreLoading ADs if new user
         */

        Log.d(TAG, "preLoadNativeAds: ${boolNewUser}")

        if (boolNewUser) {
            loadObNativeAd()
        }
        else {

            if (RemoteConfig.show_on_boarding_always) {
                preLoadOnBoardingAds()
            } else {
                loadHomeNativeAd()
            }

        }


    }


    //ads related functions
    private fun loadHomeNativeAd() {

        //check for the remote config value that is true or not...
        if (RemoteConfig.show_home_native_ad) {
            activity.let {
                if (it is MainActivity) {

                    NativePreLoadAdManager.loadHomeAd(
                        it,
                        RemoteConfig.admob_native_home_id,
                        "splash"
                    )

                }
            }
        }


    }


    private fun loadObNativeAd() {

        if (RemoteConfig.show_on_boarding_native_ad) {

            Log.d("dddd", "loadObNativeAd: called")
            activity.let {
                if (it is MainActivity) {

                    NativePreLoadAdManager.loadOnBoardingAd(
                        it,
                        RemoteConfig.admob_native_on_boarding_id,
                        "splash"
                    )


                }
            }

        }


    }

    fun preLoadOnBoardingAds() {

        loadFeatureOneNativeAd()
        loadFeatureTwoNativeAd()
        loadFullScreenNativeAd()


    }

    private fun loadFeatureOneNativeAd() {

        if (RemoteConfig.show_feature_one_native_ad) {
            activity.let {
                if (it is MainActivity) {

                    NativePreLoadAdManager.loadFeatureOneAd(
                        it,
                        RemoteConfig.admob_native_feature_one_id,
                        "splash"
                    )

                }
            }
        }


    }

    private fun loadFeatureTwoNativeAd() {

        if (RemoteConfig.show_feature_two_native_ad) {
            activity.let {
                if (it is MainActivity) {

                    NativePreLoadAdManager.loadFeatureTwoAd(
                        it,
                        RemoteConfig.admob_native_feature_two_id,
                        "splash"
                    )

                }
            }
        }


    }

    private fun loadFullScreenNativeAd() {

        if (RemoteConfig.show_full_screen_native_ad) {

            activity.let {
                if (it is MainActivity) {

                    NativePreLoadAdManager.loadFullScreenAd(
                        it,
                        RemoteConfig.admob_native_full_screen_id,
                        "splash"
                    )

                }
            }

        }

    }


    companion object {
        private const val TAG = "SPLASH_FRAGMENT"
    }
}