package com.example.apiproject.ui.adapters

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.media.MediaMetadataRetriever
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.apiproject.R
import com.example.apiproject.data.api.ExtractedData
import com.example.apiproject.data.database.entity.DownloadedVideo
import com.example.apiproject.data.interfaces.ClickHandler
import com.example.apiproject.data.models.ReelVideo
import com.example.apiproject.databinding.DownloadedItemBinding
import com.example.apiproject.databinding.ReelItemBinding
import com.example.apiproject.databinding.SelectionItemBinding
import com.example.apiproject.databinding.TablayoutBinding
import com.example.apiproject.util.NetworkHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TabsAdapter():RecyclerView.Adapter<TabsAdapter.ViewHolder>() {

     private var tabsData:List<String> = listOf()
     var clickHandler:ClickHandler?=null
     var selectedTab=0
     class ViewHolder(val binding: TablayoutBinding) : RecyclerView.ViewHolder(binding.root)

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         return ViewHolder(TablayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
     }

     override fun getItemCount(): Int {
         return tabsData.size
     }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(selectedTab==position){
            holder.binding.tabBg.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#F2573b")))
            holder.binding.tabHeading.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
        }else{
            holder.binding.tabBg.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#CCB5B3B3")))
            holder.binding.tabHeading.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.textHeading))
        }
       holder.binding.tabHeading.text=tabsData[position].capitalize()
         holder.binding.root.setOnClickListener {
             selectedTab=position
              clickHandler?.onClickPressed()
             notifyDataSetChanged()
         }
     }

    fun setData(data:List<String>){
        tabsData=data
        notifyDataSetChanged()
    }



 }

