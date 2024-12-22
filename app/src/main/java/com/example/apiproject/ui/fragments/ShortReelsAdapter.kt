package com.example.apiproject.ui.fragments

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.core.view.isVisible
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.RecyclerView
import com.example.apiproject.R
import com.example.apiproject.data.models.gag.Post
import com.example.apiproject.data.models.gag.PostUrl
import com.example.apiproject.data.models.gag.VideoShorts
import com.example.apiproject.databinding.ItemReelsPlayerBinding

class ShortReelsAdapter : RecyclerView.Adapter<ShortReelsAdapter.ViewHolder>() {

    private var videos: MutableList<PostUrl> = arrayListOf()
    var onShortSelectListener: OnShortSelectListener? = null
    var exoPlayer: ExoPlayer? = null

    private var shortLoaded: Boolean = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemReelsPlayerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SimpleDateFormat", "NotifyDataSetChanged", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = videos[position]

        shortLoaded = false
        Log.d("ONBIND", "onBindViewHolder: called")
        with(holder.binding) {
            exoplayerview.setOnClickListener {
                if (shortLoaded) {

                    val a: Animation = AlphaAnimation(1.00f, 0.00f)
                    a.duration = 1000
                    a.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {
                            // TODO Auto-generated method stub
                        }

                        override fun onAnimationRepeat(animation: Animation?) {
                            // TODO Auto-generated method stub
                        }

                        override fun onAnimationEnd(animation: Animation?) {
                            pause.setVisibility(View.GONE)



                        }
                    })

                    if (exoPlayer?.isPlaying == true) {
                        exoPlayer?.pause()
                        pause.setImageResource(R.drawable.outline_play_arrow_24)
                        pause.isVisible = true

                        pause.startAnimation(a)
                    }
                    else {
                        exoPlayer?.play()
                        pause.setImageResource(R.drawable.pause_outline_24)
                        pause.isVisible = true

                        pause.startAnimation(a)
                    }




                }
            }


            downloadVid.setOnClickListener {

                onShortSelectListener?.downloadShort(item)


            }


        }
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        val videoItem = videos[holder.absoluteAdapterPosition]
        exoPlayer?.stop()
        exoPlayer?.release()
        exoPlayer = null
        holder.binding.pause.isVisible = false
        holder.binding.shortsPlayerProgress.isVisible = true
        exoPlayer = ExoPlayer.Builder(holder.itemView.context).build().also { exoPlayer ->
            holder.binding.exoplayerview.player = exoPlayer
            val url = videoItem.url
            val mediaItem = MediaItem.fromUri(url ?: "")
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.repeatMode = ExoPlayer.REPEAT_MODE_ALL
            exoPlayer.playWhenReady = true
            exoPlayer.addListener(object : Player.Listener {
                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    super.onIsPlayingChanged(isPlaying)
                    Log.i("EXO_TAG", "onIsPlayingChanged: $isPlaying")
                    if (isPlaying) {
                        holder.binding.shortsPlayerProgress.isVisible = false
                        holder.binding.btnDownload.isVisible = true
                    }
//                    holder.binding.holderProgress.isVisible = !isPlaying
//                    holder.binding.shortsShimmer.isVisible = !isPlaying
//                    holder.binding.shortsPlayerProgress.isVisible = !isPlaying
                }
                override fun onPlayerError(error: PlaybackException) {
                    super.onPlayerError(error)
                    Log.i("EXO_TAG", "onPlayerError: ${error.message}")
                    Log.d("EXO_TAG", "onPlayerError: ${videoItem.url}")
//                    holder.videoBinding.holderProgress.isVisible = false
//                    holder.binding.shortsShimmer.isVisible = false
//                    holder.binding.shortsPlayerProgress.isVisible = false
                }
            })
            exoPlayer.prepare()
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        //holder.binding.rippleBg.stopRippleAnimation()
        holder.binding.exoplayerview.player?.stop()
        holder.binding.exoplayerview.player?.release()
        holder.binding.pause.isVisible = false


    }

    override fun getItemCount(): Int {
        return videos.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(list: MutableList<PostUrl>) {
        this.videos.clear()
        this.videos.addAll(list)
        notifyDataSetChanged()
    }




    class ViewHolder(val binding: ItemReelsPlayerBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnShortSelectListener{
        fun downloadShort(item:PostUrl)
    }

}