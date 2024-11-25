package com.example.apiproject.ui.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apiproject.data.api.ExtractedData
import com.example.apiproject.data.database.entity.DownloadedVideo
import com.example.apiproject.data.database.entity.DownloadingVideo
import com.example.apiproject.data.di.AppModule
import com.example.apiproject.data.interfaces.ClickHandler
import com.example.apiproject.data.interfaces.DeleteClickInterface
import com.example.apiproject.databinding.DownloadedItemBinding
import com.example.apiproject.databinding.DownloadingItemBinding
import com.example.apiproject.databinding.SelectionItemBinding
import com.example.apiproject.domain.events.DownloadingVideos
import com.example.apiproject.ui.activity.MainActivity
import com.example.apiproject.util.NetworkHelper
import com.liulishuo.filedownloader.FileDownloadList
import com.liulishuo.filedownloader.FileDownloader
import com.liulishuo.filedownloader.model.FileDownloadStatus
import com.liulishuo.filedownloader.util.FileDownloadUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DownloadingAdapter():RecyclerView.Adapter<DownloadingAdapter.ViewHolder>() {

     var downloadedData:List<DownloadingVideo> = listOf()

    var deleteClick: DeleteClickInterface?=null
     class ViewHolder(val binding: DownloadingItemBinding) : RecyclerView.ViewHolder(binding.root)

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         return ViewHolder(DownloadingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
     }

     override fun getItemCount(): Int {
         return downloadedData.size
     }
    private fun generateId(url: String?, path: String?, pathAsDirectory: Boolean): Int {
        return if (pathAsDirectory) {
            FileDownloadUtils.md5(FileDownloadUtils.formatString("%sp%s@dir", url, path)).hashCode()
        } else {
            FileDownloadUtils.md5(FileDownloadUtils.formatString("%sp%s", url, path)).hashCode()
        }
    }


     override fun onBindViewHolder(holder: ViewHolder, position: Int) {

         if(downloadedData[position].audioPath==downloadedData[position].path){
             holder.binding.type.text= "Audio"
         }
         else {
             holder.binding.type.text = "Video"
         }
         holder.binding.videoTitle.text=downloadedData[position].title
         if(NetworkHelper.isInternetConnectionAvailable(holder.itemView.context)) {
             Glide.with(holder.itemView.context).load(downloadedData[position].image)
                 .into(holder.binding.image)
         }
         else{
                Glide.with(holder.itemView.context).load(downloadedData[position].path)
                    .into(holder.binding.image)
         }

         var id=generateId(downloadedData[position].url,downloadedData[position].path,false)

         CoroutineScope(Dispatchers.IO).launch {
             while (true){
                 var status = FileDownloader.getImpl().getStatus(id, downloadedData[position].path)
                 Log.d("DownloadingVideoAdapter", "onBindViewHolder:status $status")
                 if(status.toInt() !=0) {
                     when (status) {
                         FileDownloadStatus.completed -> {
//                         holder.binding.videoProgress.progress=100
                             break
                         }

                         FileDownloadStatus.paused -> {
//
                             break
                         }

                         FileDownloadStatus.error -> {
                             withContext(Dispatchers.Main) {
                                 holder.binding.state.text = "Error Occurred"

                             }


                             break
                         }

                         FileDownloadStatus.progress -> {

                             withContext(Dispatchers.Main) {
                                 holder.binding.state.text = "Downloading..."
                                 var completed = FileDownloader.getImpl().getSoFar(id)
                                 Log.d(
                                     "DownloadingVideoAdapter",
                                     "onBindViewHolder:progress $completed"
                                 )
                                 CoroutineScope(Dispatchers.IO).launch {
                                     withContext(Dispatchers.Main) {
                                         var max = FileDownloader.getImpl().getTotal(id)
                                         holder.binding.details.text =
                                             "${completed / 1000000}MB/${max / 1000000}MB"
                                         holder.binding.videoProgress.setProgress(
                                             (completed.toFloat() / max.toFloat() * 100).toInt(),
                                             true
                                         )
                                     }
                                 }
                             }
                         }
                     }
                 }
                 else{
                     withContext(Dispatchers.Main) {
                         holder.binding.state.text = "Error Occurred"
                         holder.binding.videoProgress.progress=0
                         holder.binding.details.text="0MB/0MB"

                     }
                        break
                 }
             }
         }
             holder.binding.delete.setOnClickListener {
                 Log.d("DownloadingAdapter", "onBindViewHolder: ${downloadedData[position].path}")
                 //stop downloading
                    FileDownloader.getImpl().pause(id)
                    FileDownloader.getImpl().clear(id, downloadedData[position].path)
                deleteClick?.deleteDownloadingVideo(downloadedData[position].path)
             }
         }

    fun setData(data:List<DownloadingVideo>){
        downloadedData=data
        notifyDataSetChanged()
    }

 }

