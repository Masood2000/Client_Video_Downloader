package com.example.apiproject.ui.adapters

import android.annotation.SuppressLint
import android.media.MediaMetadataRetriever
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.apiproject.R
import com.example.apiproject.data.api.ExtractedData
import com.example.apiproject.data.database.entity.DownloadedVideo
import com.example.apiproject.data.interfaces.ClickHandler
import com.example.apiproject.data.interfaces.ReelClickHandler
import com.example.apiproject.data.models.ReelVideo
import com.example.apiproject.databinding.DownloadedItemBinding
import com.example.apiproject.databinding.ReelItemBinding
import com.example.apiproject.databinding.SelectionItemBinding
import com.example.apiproject.util.NetworkHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReelsAdapter():RecyclerView.Adapter<ReelsAdapter.ViewHolder>() {

     private var reelsAdapter:List<ReelVideo> = listOf()

    var clickHandler: ReelClickHandler?=null
     class ViewHolder(val binding: ReelItemBinding) : RecyclerView.ViewHolder(binding.root)

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         return ViewHolder(ReelItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
     }

     override fun getItemCount(): Int {
         return reelsAdapter.size
     }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.binding.reeltitle.text=reelsAdapter[position].title
         holder.binding.reelchannel.text=reelsAdapter[position].channel.capitalize()
         CoroutineScope(Dispatchers.IO).launch {
            try {
                // Try to load a frame at 2 seconds
                val requestOptions = RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.film_reel_cinema_svgrepo_com) // Placeholder during loading
                    .override(600)
                val bitmap = Glide.with(holder.itemView.context)
                    .asBitmap()
                    .load("https://www.dailymotion.com/thumbnail/video/${reelsAdapter[position].id}")
                    .apply(requestOptions)
                    .submit()
                    .get()

                withContext(Dispatchers.Main) {
                    holder.binding.imageView.setImageBitmap(bitmap)
                }

            } catch (e: Exception) {
                Log.e("ReelsAdapter", "Error loading video frame: ${e.message}")

            }
        }
            holder.binding.root.setOnClickListener {
                clickHandler?.onClickPressed(reelsAdapter[position])
            }
     }

    fun setData(data:List<ReelVideo>){
        var newData=data.shuffled()
        reelsAdapter=newData.take(500)
        notifyDataSetChanged()
    }

 }

