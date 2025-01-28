package com.example.apiproject.ui.fragments.onboarding


import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apiproject.R
import com.example.apiproject.core.ads.admob.AdLoadListener
import com.example.apiproject.core.ads.admob.NativePreLoadAdManager
import com.example.apiproject.core.ads.admob.NativePreLoadAdManager.getFeatureOneAd
import com.example.apiproject.core.ads.admob.NativePreLoadAdManager.getFeatureTwoAd
import com.example.apiproject.core.ads.admob.NativePreLoadAdManager.getFullScreenAd
import com.example.apiproject.core.remoteconfig.RemoteConfig
import com.example.apiproject.databinding.AdLayoutOnboardingBinding
import com.example.apiproject.databinding.AnimationLayoutOnboardingBinding
import com.example.apiproject.ui.activity.MainActivity

import com.example.videodownloader.core.ads.NativeLayout
import com.google.android.gms.ads.nativead.NativeAd
import javax.inject.Inject

class OnboardingAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val list = mutableListOf<Any>()
    var context: Context? = null
    var isAdItemAvailable = false

    var fragmentClickListener: OnboardingFragment.FragmentClickListener? = null


    class AnimViewHolder(val binding: AnimationLayoutOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root)

    class ADViewHolder(val binding: AdLayoutOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            ITEM_TYPE_ANIMATION -> {
                val binding = AnimationLayoutOnboardingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                AnimViewHolder(binding)
            }

            ITEM_TYPE_AD -> {
                val binding = AdLayoutOnboardingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ADViewHolder(binding)
            }

            else -> {
                val binding = AnimationLayoutOnboardingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                AnimViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        val ctx = holder.itemView.context
        when (holder) {
            is AnimViewHolder -> {
                with(holder.binding) {
                    Log.d(TAG, "onBindViewHolder: Anim view called")


                    if (isAdItemAvailable){
                        when (position) {
                            0 -> {
                                Glide.with(ctx).load(R.drawable.onboarding_img_1).into(lottieAnimationView)
                                onboardingTitle.text = ctx.getString(R.string.onboarding_title)
                                onboardingDescription.text = ctx.getString(R.string.onboarding_description)
                                showFeatureOneNativeAd(this)

                                tvNext.setText(R.string.next)
                                tvNext.setOnClickListener {
                                    fragmentClickListener?.onNextButtonClick()
                                }

                            }

                            2 -> {
                                Glide.with(ctx).load(R.drawable.onboarding_img_2).into(lottieAnimationView)
                                onboardingTitle.text = ctx.getString(R.string.onboarding_title_2)
                                onboardingDescription.text = ctx.getString(R.string.onboarding_description_2)
                                showFeatureTwoNativeAd(this)
                                tvNext.setText(R.string.next)
                                tvNext.setOnClickListener {
                                    fragmentClickListener?.onNextButtonClick()
                                }
                            }

                            3 -> {
                                Glide.with(ctx).load(R.drawable.onboarding_img_3).into(lottieAnimationView)
                                onboardingTitle.text = ctx.getString(R.string.onboarding_title_3)
                                onboardingDescription.text = ctx.getString(R.string.onboarding_description_3)
                                layoutNativeContainer.root.isVisible = false
                                tvNext.text = ""
                                tvNext.setOnClickListener {
                                    fragmentClickListener?.onNextButtonClick()
                                }

                            }
                        }
                    }
                    else {
                        when (position) {
                            0 -> {
                                Glide.with(ctx).load(R.drawable.onboarding_img_1).into(lottieAnimationView)
                                onboardingTitle.text = ctx.getString(R.string.onboarding_title)
                                onboardingDescription.text = ctx.getString(R.string.onboarding_description)
                                showFeatureOneNativeAd(this)

                                tvNext.setText(R.string.next)
                                tvNext.setOnClickListener {
                                    fragmentClickListener?.onNextButtonClick()
                                }

                            }

                            1 -> {
                                Glide.with(ctx).load(R.drawable.onboarding_img_2).into(lottieAnimationView)
                                onboardingTitle.text = ctx.getString(R.string.onboarding_title_2)
                                onboardingDescription.text = ctx.getString(R.string.onboarding_description_2)
                                showFeatureTwoNativeAd(this)

                                tvNext.setText(R.string.next)
                                tvNext.setOnClickListener {
                                    fragmentClickListener?.onNextButtonClick()
                                }
                            }

                            2 -> {
                                Glide.with(ctx).load(R.drawable.onboarding_img_3).into(lottieAnimationView)
                                onboardingTitle.text = ctx.getString(R.string.onboarding_title_3)
                                onboardingDescription.text = ctx.getString(R.string.onboarding_description_3)
                                layoutNativeContainer.root.isVisible = false

                                tvNext.text = ""
                                tvNext.setOnClickListener {
                                    fragmentClickListener?.onNextButtonClick()
                                }
                            }
                        }
                    }
                }
            }

            is ADViewHolder -> {
                with(holder.binding) {
                    Log.d(TAG, "onBindViewHolder: Full Screen Ad View called")
                    showFullScreenNativeAd(this)
                }
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is Int -> ITEM_TYPE_ANIMATION
            else -> ITEM_TYPE_AD
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Any>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }


    private fun showFullScreenNativeAd(binding: AdLayoutOnboardingBinding) {

        Log.d(TAG, "show Full Screen NativeAd: pre load ")

        if(RemoteConfig.show_full_screen_native_ad){

            context?.let {
                if (it is MainActivity) {
                    val myAdLoadListener = object : AdLoadListener {

                        override fun onFullScreenAdLoaded(nativeAd: NativeAd?) {
                            super.onFullScreenAdLoaded(nativeAd)



                            NativePreLoadAdManager.populateUnifiedNativeAdView(
                                NativePreLoadAdManager.nativeFullScreenAd,
                                it,
                                binding.parentNativeContainer,
                                binding.admobNativeContainer,
                                NativeLayout.FULL_SCREEN,
                                "onboarding_full_screen",
                                RemoteConfig.admob_native_full_screen_cta_round,
                                RemoteConfig.admob_native_full_screen_cta_color,
                                RemoteConfig.admob_native_full_screen_cta_text_color,
                            )

                        }


                        override fun onFullScreenAdFailedToLoad(error: String) {
                            super.onFullScreenAdFailedToLoad(error)

                            binding.parentNativeContainer.visibility = View.GONE
                        }

                    }
                    if (NativePreLoadAdManager.getFullScreenAd() != null) {

                        NativePreLoadAdManager.populateUnifiedNativeAdView(
                            getFullScreenAd(),
                            context as MainActivity,
                            binding.parentNativeContainer,
                            binding.admobNativeContainer,
                            NativeLayout.FULL_SCREEN,
                            "onboarding_full_screen",
                            RemoteConfig.admob_native_full_screen_cta_round,
                            RemoteConfig.admob_native_full_screen_cta_color,
                            RemoteConfig.admob_native_full_screen_cta_text_color,
                        )
                    }
                    else if (NativePreLoadAdManager.isFullScreenAdLoading()) {

                        NativePreLoadAdManager.adLoadListener = myAdLoadListener
                    } else {

                        binding.parentNativeContainer.visibility = View.GONE
                    }
                }
            }

        }
        else{
            binding.parentNativeContainer.isVisible = false
        }
    }

    private fun showFeatureOneNativeAd(binding: AnimationLayoutOnboardingBinding) {

        Log.d(TAG, "show Feature One NativeAd: pre load ")

        if(RemoteConfig.show_feature_one_native_ad){

            context?.let {
                if (it is MainActivity) {


                    val myAdLoadListener = object : AdLoadListener {

                        override fun onFeatureOneAdLoaded(nativeAd: NativeAd?) {
                            super.onFeatureOneAdLoaded(nativeAd)



                            NativePreLoadAdManager.populateUnifiedNativeAdView(
                                NativePreLoadAdManager.nativeFeatureOneAd,
                                it,
                                binding.layoutNativeContainer.parentNativeContainer,
                                binding.layoutNativeContainer.admobNativeContainer,
                                NativeLayout.NATIVE_6A,
                                "onboarding_feature_one",
                                RemoteConfig.admob_native_feature_one_cta_round,
                                RemoteConfig.admob_native_feature_one_cta_color,
                                RemoteConfig.admob_native_feature_one_cta_text_color,
                            )

                        }


                        override fun onFeatureOneAdFailedToLoad(error: String) {
                            super.onFeatureOneAdFailedToLoad(error)

                            binding.layoutNativeContainer.parentNativeContainer.visibility = View.GONE
                        }

                    }



                    if (NativePreLoadAdManager.getFeatureOneAd() != null) {

                        NativePreLoadAdManager.populateUnifiedNativeAdView(
                            getFeatureOneAd(),
                            context as MainActivity,
                            binding.layoutNativeContainer.parentNativeContainer,
                            binding.layoutNativeContainer.admobNativeContainer,
                            NativeLayout.NATIVE_6A,
                            "onboarding_feature_one",
                            RemoteConfig.admob_native_feature_one_cta_round,
                            RemoteConfig.admob_native_feature_one_cta_color,
                            RemoteConfig.admob_native_feature_one_cta_text_color,
                        )
                    }
                    else if (NativePreLoadAdManager.isFeatureOneAdLoading()) {

                        NativePreLoadAdManager.adLoadListener = myAdLoadListener
                    } else {

                        binding.layoutNativeContainer.parentNativeContainer.visibility = View.GONE
                    }
                }
            }

        }
        else{
            binding.layoutNativeContainer.parentNativeContainer.visibility = View.GONE
        }


    }

    private fun showFeatureTwoNativeAd(binding: AnimationLayoutOnboardingBinding) {

        Log.d(TAG, "show Feature Two NativeAd: pre load ")

        if(RemoteConfig.show_feature_two_native_ad){
            context?.let {
                if (it is MainActivity) {

                    val myAdLoadListener = object : AdLoadListener {
                        override fun onFeatureTwoAdLoaded(nativeAd: NativeAd?) {
                            super.onFeatureTwoAdLoaded(nativeAd)
                            NativePreLoadAdManager.populateUnifiedNativeAdView(
                                NativePreLoadAdManager.nativeFeatureTwoAd,
                                it,
                                binding.layoutNativeContainer.parentNativeContainer,
                                binding.layoutNativeContainer.admobNativeContainer,
                                NativeLayout.NATIVE_6A,
                                "onboarding_feature_two",
                                RemoteConfig.admob_native_feature_two_cta_round,
                                RemoteConfig.admob_native_feature_two_cta_color,
                                RemoteConfig.admob_native_feature_two_cta_text_color,
                            )

                        }


                        override fun onFeatureTwoAdFailedToLoad(error: String) {
                            super.onFeatureTwoAdFailedToLoad(error)

                            binding.layoutNativeContainer.parentNativeContainer.visibility = View.GONE
                        }

                    }



                    if (NativePreLoadAdManager.getFeatureTwoAd() != null) {

                        NativePreLoadAdManager.populateUnifiedNativeAdView(
                            getFeatureTwoAd(),
                            context as MainActivity,
                            binding.layoutNativeContainer.parentNativeContainer,
                            binding.layoutNativeContainer.admobNativeContainer,
                            NativeLayout.NATIVE_6A,
                            "onboarding_feature_two",
                            RemoteConfig.admob_native_feature_two_cta_round,
                            RemoteConfig.admob_native_feature_two_cta_color,
                            RemoteConfig.admob_native_feature_two_cta_text_color,
                        )
                    }
                    else if (NativePreLoadAdManager.isFeatureTwoAdLoading()) {

                        NativePreLoadAdManager.adLoadListener = myAdLoadListener
                    } else {

                        binding.layoutNativeContainer.parentNativeContainer.visibility = View.GONE
                    }
                }
            }

        }else{
            binding.layoutNativeContainer.parentNativeContainer.visibility = View.GONE
        }



    }


    companion object {
        const val ITEM_TYPE_ANIMATION = 0
        const val ITEM_TYPE_AD = 1
        private const val TAG = "on_boarding_adapter_tag"
    }

}
