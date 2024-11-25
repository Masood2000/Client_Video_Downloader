package com.example.apiproject.ui.adapters

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apiproject.data.api.ExtractedData
import com.example.apiproject.data.database.entity.DownloadedVideo
import com.example.apiproject.data.interfaces.ClickBundleHandler
import com.example.apiproject.data.interfaces.ClickHandler
import com.example.apiproject.databinding.DownloadedItemBinding
import com.example.apiproject.databinding.SelectionItemBinding
import com.example.apiproject.util.NetworkHelper

class DownloadedAdapter() : RecyclerView.Adapter<DownloadedAdapter.ViewHolder>() {


    var onVideoSelectedListener: OnVideoSelectedListener? = null

    var downloadedData: List<DownloadedVideo> = listOf()
    var clickHandler: ClickBundleHandler? = null

    class ViewHolder(val binding: DownloadedItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DownloadedItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return downloadedData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (downloadedData[position].audioPath == downloadedData[position].path) {
            holder.binding.type.text = "Audio"

        }
        else {
            holder.binding.type.text = "Video"
        }

        holder.binding.videoTitle.text = downloadedData[position].title
        if (NetworkHelper.isInternetConnectionAvailable(holder.itemView.context)) {
            Glide.with(holder.itemView.context).load(downloadedData[position].image)
                .into(holder.binding.image)
        } else {
            Glide.with(holder.itemView.context).load(downloadedData[position].path)
                .into(holder.binding.image)
        }

        holder.binding.root.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("videoUrl", downloadedData[position].path)
            bundle.putString("videoTitle", downloadedData[position].title)
            bundle.putBoolean(
                "isAudio",
                downloadedData[position].audioPath == downloadedData[position].path
            )
            clickHandler?.onClickPressed(bundle)
        }

        holder.binding.options.setOnClickListener(){
            onVideoSelectedListener?.onOptionsClicked(holder.binding.options, position, downloadedData[position])
        }

    }

    fun setData(data: List<DownloadedVideo>) {
        downloadedData = data
        notifyDataSetChanged()
    }


    interface OnVideoSelectedListener {
        fun onOptionsClicked(view: View, position: Int, download: DownloadedVideo)
        fun toggleSelectionMode(isSelection: Boolean, allSelected: Boolean)
        fun onItemClicked(position: Int, media: DownloadedVideo)
        fun dummyItemClicked(url: String)
    }


}

