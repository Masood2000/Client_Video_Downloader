package com.example.apiproject.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.addCallback
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.apiproject.R
import com.example.apiproject.core.ads.admob.InterstitialHelper
import com.example.apiproject.core.remoteconfig.RemoteConfig
import com.example.apiproject.data.interfaces.DeleteClickInterface
import com.example.apiproject.ui.activity.MainActivity
import com.example.apiproject.databinding.FragmentVideoDownloadingBinding
import com.example.apiproject.domain.events.DownloadingVideos
import com.example.apiproject.ui.adapters.DownloadingAdapter
import com.example.apiproject.ui.base.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class VideoDownloadingFragment : BaseFragment() {
    val TAG = "VideoDownloadingFragment"
    val binding by lazy { FragmentVideoDownloadingBinding.inflate(layoutInflater) }

    val adapter by lazy { DownloadingAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            if (it is MainActivity) {
                it.viewModel.getDownloadingVideos()
            }

        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                activity?.let {
                    if (it is MainActivity) {
                        it.viewModel.downloadingVideos.collect { state ->
                            Log.d(TAG, "State: $state")
                            when (state) {
                                is DownloadingVideos.Success -> {
                                    Log.d(TAG, "DownloadingVideosSize: ${state.data.size}")
                                    if(state.data.isEmpty()){
                                        Log.d(TAG, "Empty")
                                        binding.noVid.visibility = View.VISIBLE
                                        adapter.setData(state.data)

                                    }
                                    else {
                                        binding.noVid.visibility = View.GONE
                                        adapter.setData(state.data)
                                    }
                                }
                                is DownloadingVideos.Empty -> {
                                    Log.d(TAG, "Empty")
                                }
                                else->{

                                }
                            }
                        }
                    }

                }

            }
        }
    }

    override fun setViewBinding(): View {
        return binding.root
    }

    override fun initView() {


        binding.recycleView.adapter = adapter
        adapter.deleteClick = object : DeleteClickInterface {
            override fun deleteDownloadingVideo(path: String) {
                activity?.let {
                    if (it is MainActivity) {
                        CoroutineScope(Dispatchers.IO).launch {
                            it.viewModel.deleteDownloadingVideo(path)
                            MainActivity.updateData?.update()
                        }
                    }
                }
            }

        }

        activity?.let {
            if (it is MainActivity){
                it.onBackPressedDispatcher.addCallback (viewLifecycleOwner){
                    it.moveToFirstItem()
                }
            }
        }


    }

    override fun lazyLoad() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity.let{
            if(it is MainActivity){
                if(RemoteConfig.show_downloading_Interstitial_ad){
                    InterstitialHelper.showAndLoadInterstitial(
                        it,
                        it.getString(R.string.interstitial_inner),
                        true,
                        useCapping = RemoteConfig.admob_downloading_interstitial_capping,
                        "downloading",
                        object : InterstitialHelper.InterstitialAdShowListener {
                            override fun onInterstitialAdImpression() {
                                super.onInterstitialAdImpression()

                            }

                            override fun onInterstitialAdNull() {
                                //postAnalytic("exit_interstitial_null")

                            }
                        }
                    )

                }
            }

        }

    }

}