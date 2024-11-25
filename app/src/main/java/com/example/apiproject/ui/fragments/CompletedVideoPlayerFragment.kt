package com.example.apiproject.ui.fragments


import android.view.View
import android.view.ViewGroup
import androidx.annotation.OptIn
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import com.example.apiproject.R
import com.example.apiproject.databinding.FragmentCompletedVideoPlayerBinding
import com.example.apiproject.databinding.FragmentVideoCompletedBinding
import com.example.apiproject.ui.activity.MainActivity
import com.example.apiproject.ui.base.BaseFragment

class CompletedVideoPlayerFragment : BaseFragment(){
    var player:ExoPlayer?=null
    val binding by lazy { FragmentCompletedVideoPlayerBinding.inflate(layoutInflater) }

    override fun setViewBinding(): View {
       return binding.root
    }

    @OptIn(UnstableApi::class)
    override fun initView() {
        var bundle = arguments
        var videoUrl = bundle?.getString("videoUrl")
        var videoTitle = bundle?.getString("videoTitle")
        var isAudio = bundle?.getBoolean("isAudio")

        activity?.let {
            if(it is MainActivity){
                it.binding.tvTitle.text = videoTitle?.capitalize()
            }
        }

        if(isAudio==false){
         player = ExoPlayer.Builder(requireContext()).build()
        }
        else{
            // Initialize ExoPlayer
            val trackSelector = DefaultTrackSelector(requireContext()).apply {
                setParameters(
                    buildUponParameters().setRendererDisabled(C.TRACK_TYPE_VIDEO, true)
                )
            }
             player = ExoPlayer.Builder(requireContext())
                .setTrackSelector(trackSelector)
                .build()

        }

        binding.playerView.player = player
        MediaItem.fromUri(videoUrl?:"").let {
            player?.setMediaItem(it)
            player?.prepare()
            player?.play()
        }

    }

    override fun lazyLoad() {
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }

    override fun onPause() {
        super.onPause()
        player?.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        player?.release()
    }

}