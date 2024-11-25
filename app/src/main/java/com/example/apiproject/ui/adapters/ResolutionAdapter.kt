package com.example.apiproject.ui.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apiproject.data.api.ExtractedData
import com.example.apiproject.databinding.SelectionItemBinding

 class ResolutionAdapter():RecyclerView.Adapter<ResolutionAdapter.ViewHolder>() {
     var extractedData:ExtractedData?=null
     var selectedCell=0
    class ViewHolder(val binding: SelectionItemBinding) : RecyclerView.ViewHolder(binding.root)

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         return ViewHolder(SelectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
     }

     override fun getItemCount(): Int {
         if(extractedData?.audio!=null){
             return extractedData?.video?.size!!+1
         }
         return extractedData?.video?.size!!
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         if(position<extractedData?.video?.size!!){
             holder.binding.resolutionText.text=extractedData?.video?.get(position)?.width.toString()+" X "+extractedData?.video?.get(position)?.height.toString()
             holder.binding.videoType.text="Video"
             holder.binding.typeImage.setImageResource(com.example.apiproject.R.drawable.video_svgrepo_com)
         }
         else{
             holder.binding.resolutionText.text="Mp3 Audio"
             holder.binding.videoType.text="Audio"
             holder.binding.typeImage.setImageResource(com.example.apiproject.R.drawable.audio_svgrepo_com)
         }
         if(position==selectedCell){
               holder.binding.root.background=holder.itemView.context.resources.getDrawable(com.example.apiproject.R.drawable.selection_bg)
            }
            else{
             holder.binding.root.background=holder.itemView.context.resources.getDrawable(com.example.apiproject.R.drawable.non_selection_bg)

         }

        try {
            if (position< extractedData?.video?.size!! &&extractedData?.video?.get(position)?.height == 0 && extractedData?.video?.get(
                    position
                )?.width == 0
            ) {
                holder.binding.resolutionText.text = "Source ${position + 1}"
            }
        }
        catch (e:Exception){}
         holder.binding.root.setOnClickListener {
             selectedCell=position
//             Log.d("Adapter", "onBindViewHolder: ${extractedData?.video?.get(position)?.url}")
             notifyDataSetChanged()
         }




     }

 }

