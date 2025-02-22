package com.example.apiproject.ui.fragments.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.apiproject.R
import com.example.apiproject.core.ads.admob.NativePreLoadAdManager
import com.example.apiproject.core.remoteconfig.RemoteConfig
import com.example.apiproject.databinding.FragmentOnboardingBinding
import com.example.apiproject.ui.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class OnboardingFragment : Fragment() {

    @Inject
    lateinit var onboardingAdapter: OnboardingAdapter

    val binding by lazy { FragmentOnboardingBinding.inflate(layoutInflater) }

    private var adapterList = listOf(
        R.drawable.uk_flag,
        "AD",
        R.drawable.uk_flag,
        R.drawable.uk_flag
    )

    private var isLastPageSwiped = false
    private var indexPageScroll = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (NativePreLoadAdManager.getFullScreenAd() == null || NativePreLoadAdManager.isFullScreenAdLoading()){
            adapterList = listOf(

                R.drawable.uk_flag,
                R.drawable.uk_flag,
                R.drawable.uk_flag

            )
        }


        init()
        initListeners()

        loadHomeNativeAd()

    }

    private fun init(){

        onboardingAdapter.fragmentClickListener = object : FragmentClickListener {
            override fun onNextButtonClick() {
                nextButtonClick()
            }
        }

        binding.onboardingVp.adapter = onboardingAdapter
        activity?.let{
            if (it is MainActivity) {
                onboardingAdapter.context = it
            }
        }
        binding.dotsIndicator.attachTo(binding.onboardingVp)
        onboardingAdapter.isAdItemAvailable = adapterList.size != 3
        onboardingAdapter.setList(adapterList)
    }

    private fun initListeners(){
        binding.onboardingVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (adapterList.size == 3){
                    when (position){
                        0 -> {
                            binding.dotsIndicator.isVisible = true

                            binding.backBtn.isVisible = false
                            binding.skipBtn.isVisible = false
                        }
                        1 -> {
                            binding.dotsIndicator.isVisible = true

                            binding.backBtn.isVisible = true
                            binding.skipBtn.isVisible = false
                        }
                        2 -> {
                            binding.dotsIndicator.isVisible = true

                            binding.backBtn.isVisible = true
                            binding.skipBtn.isVisible = true
                        }
                    }
                }

                else {

                    when(position){
                        0 -> {
                            binding.dotsIndicator.isVisible = true

                            binding.backBtn.isVisible = false
                            binding.skipBtn.isVisible = false
                        }
                        1 -> {
                            binding.dotsIndicator.isVisible = false

                            binding.backBtn.isVisible = false
                            binding.skipBtn.isVisible = false
                        }
                        2 -> {
                            binding.dotsIndicator.isVisible = true

                            binding.backBtn.isVisible = true
                            binding.skipBtn.isVisible = false
                        }
                        3 -> {
                            binding.dotsIndicator.isVisible = true

                            binding.backBtn.isVisible = true
                            binding.skipBtn.isVisible = true
                        }
                    }
                }

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                if (adapterList.size == 3){
                    if (position == 2 && positionOffset == 0f && !isLastPageSwiped) {
                        if (indexPageScroll != 0) {
                            isLastPageSwiped = true
                            //You can perform your action on last page scroll.
                            changeScreen()
                        }
                        indexPageScroll++
                    } else {
                        indexPageScroll = 0
                    }
                } else {
                    if (position == 3 && positionOffset == 0f && !isLastPageSwiped) {
                        if (indexPageScroll != 0) {
                            isLastPageSwiped = true
                            //You can perform your action on last page scroll.
                            changeScreen()
                        }
                        indexPageScroll++
                    } else {
                        indexPageScroll = 0
                    }
                }


            }
        })

        activity?.let {
            it.onBackPressedDispatcher.addCallback(viewLifecycleOwner){
                if (binding.onboardingVp.currentItem == 0){
                    // move to home
                    if (findNavController().currentDestination?.id == R.id.onboardingFragment){
                        findNavController().navigate(R.id.action_onboardingFragment_to_homeFragment)
                    }
                } else {
                    binding.onboardingVp.currentItem -= 1
                }
            }
        }



        binding.backBtn.setOnClickListener {
            if (binding.onboardingVp.currentItem != 0){
                binding.onboardingVp.currentItem -= 1
            }
        }

        binding.skipBtn.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.onboardingFragment){
                findNavController().navigate(R.id.action_onboardingFragment_to_homeFragment)
            }
        }

    }

    private fun changeScreen() {
        if (findNavController().currentDestination?.id == R.id.onboardingFragment){
            findNavController().navigate(R.id.action_onboardingFragment_to_homeFragment)
        }
    }


    fun nextButtonClick(){
        val size = adapterList.size
        val currentItem = binding.onboardingVp.currentItem
        if (currentItem != size - 1){
            binding.onboardingVp.currentItem = currentItem + 1
        } else {
            findNavController().navigate(R.id.action_onboardingFragment_to_homeFragment)
        }
    }

    /***
     * Ads Related Functions
     */
    private fun loadHomeNativeAd() {

        if(RemoteConfig.show_home_native_ad){
            activity.let {
                if (it is MainActivity) {

                    NativePreLoadAdManager.loadHomeAd(it, RemoteConfig.admob_native_home_id, "home")

                }
            }
        }

    }



    interface FragmentClickListener{
        fun onNextButtonClick()
    }

}