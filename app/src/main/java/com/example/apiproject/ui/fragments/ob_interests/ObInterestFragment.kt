package com.example.apiproject.ui.fragments.ob_interests

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.apiproject.R
import com.example.apiproject.core.ads.admob.AdLoadListener
import com.example.apiproject.core.ads.admob.NativePreLoadAdManager
import com.example.apiproject.core.remoteconfig.RemoteConfig
import com.example.apiproject.databinding.ObFragmentInterestBinding
import com.example.apiproject.domain.models.InterestModel
import com.example.apiproject.ui.activity.MainActivity
import com.example.videodownloader.core.ads.NativeLayout
import com.google.android.gms.ads.nativead.NativeAd
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class ObInterestFragment : Fragment() {


    private val binding by lazy { ObFragmentInterestBinding.inflate(layoutInflater) }

    @Inject
    lateinit var adapter: ObInterestAdapter

    private val viewModel: ObLanguageViewModel by viewModels()

    var backPressedCallback: OnBackPressedCallback? = null


    /***
     * Life cycle Related Functions....
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.allLanguages.collect {
                    when {
                        it.isEmpty() -> {
                            Log.d("Lang_tag", "onCreate: No languages found")
                        }

                        else -> {
                            withContext(Dispatchers.Main) {
                                setUpRecyclerView(it)
                            }
                        }
                    }
                }
            }
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        Log.d(TAG, "onCreateView: called")
        configureBackPress()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated: called")


        init()
        inflateUI()
        initListeners()

        preLoadOnBoardingAds()
        showPreLoadAd()


    }

    override fun onDestroyView() {
        super.onDestroyView()

        backPressedCallback?.remove()

        backPressedCallback = null
    }


    private fun init() {
        viewModel.getLanguages()
        // adapter.selectedLocale = preferences.getLanguageCode()
        binding.obRecyclerView.adapter = adapter
        if (viewModel.selectedLanguages.isEmpty()) {
            //binding.nextIv.isEnabled = false
        }
    }

    private fun inflateUI() {


        binding.back.isVisible = false


    }

    private fun initListeners() {

        binding.nextIv.setOnClickListener {

            if(ObInterestAdapter.selected) {

                if (findNavController().currentDestination?.id == R.id.obInterestFragment) {
                    findNavController().navigate(R.id.action_obInterestFragment_to_onboardingFragment)
                }
            }
            else{
                Log.d("adp_tag", "initListeners: not selected")
                Toast.makeText(requireContext(), "Please select at least one interest", Toast.LENGTH_SHORT).show()
            }

        }

        // code pushed......

        adapter.onInterestClickListener = object : ObInterestAdapter.OnInterestClickListener {
            override fun onInterestClick(interest: InterestModel) {
                if (viewModel.selectedLanguages.contains(interest)) {
                    viewModel.selectedLanguages.remove(interest)
                } else {
                    viewModel.selectedLanguages.add(interest)
                }
               // binding.nextIv.isEnabled = ObInterestAdapter.selected
            }
        }
    }

    private fun setUpRecyclerView(it: MutableList<InterestModel>) {

        adapter.setAllInterests(it)

    }


    /***
     * Ads related functions
     */


    fun showPreLoadAd() {


        if (RemoteConfig.show_on_boarding_native_ad) {
            Log.d(TAG, "loadAndShowHomeNativeAd: pre load ")
            activity?.let {
                if (it is MainActivity) {

                    Log.d(TAG, "onOnBoardingAdLoaded: 0")

                    val myAdLoadListener = object : AdLoadListener {

                        override fun onOnBoardingAdLoaded(nativeAd: NativeAd?) {
                            super.onOnBoardingAdLoaded(nativeAd)

                            Log.d(TAG, "onOnBoardingAdLoaded: 1")

                            NativePreLoadAdManager.populateUnifiedNativeAdView(
                                NativePreLoadAdManager.nativeOnBoardingAd,
                                it,
                                binding.admobParentContainer,
                                binding.admobNativeContainer,
                                NativeLayout.NATIVE_7B,
                                "ob_language",
                                RemoteConfig.admob_native_on_boarding_cta_round,
                                RemoteConfig.admob_native_on_boarding_cta_color,
                                RemoteConfig.admob_native_on_boarding_cta_text_color,
                            )

                        }


                        override fun onOnBoardingAdFailedToLoad(error: String) {
                            super.onOnBoardingAdFailedToLoad(error)
                            Log.d(TAG, "onOnBoardingAdLoaded: 2")
                            binding.admobParentContainer.visibility = View.GONE
                        }

                    }

                    Log.d(TAG, "onOnBoardingAdLoaded: 3")

                    if (NativePreLoadAdManager.getOnBoardingAd() != null) {
                        Log.d(TAG, "onOnBoardingAdLoaded: 4")
                        NativePreLoadAdManager.populateUnifiedNativeAdView(
                            NativePreLoadAdManager.nativeOnBoardingAd,
                            it,
                            binding.admobParentContainer,
                            binding.admobNativeContainer,
                            NativeLayout.NATIVE_7B,
                            "ob_language",
                            RemoteConfig.admob_native_on_boarding_cta_round,
                            RemoteConfig.admob_native_on_boarding_cta_color,
                            RemoteConfig.admob_native_on_boarding_cta_text_color,
                        )
                    } else if (NativePreLoadAdManager.isOnBoardingAdLoading()) {
                        Log.d(TAG, "onOnBoardingAdLoaded: 5")
                        NativePreLoadAdManager.adLoadListener = myAdLoadListener
                    } else {
                        Log.d(TAG, "onOnBoardingAdLoaded: 6")
                        binding.admobParentContainer.visibility = View.GONE
                    }
                }
            }
        } else {
            binding.admobParentContainer.isVisible = false
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
                        "feature_one"
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
                        "feature_two"
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
                        "full_screen"
                    )

                }
            }

        }

    }

    private fun configureBackPress() {
        backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {


            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, backPressedCallback!!
        )
    }

    companion object {
        private const val TAG = "OB_INTEREST_FRAGMENT"
    }
}