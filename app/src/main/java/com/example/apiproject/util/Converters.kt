package com.example.apiproject.util

import android.util.Log
import com.example.apiproject.data.api.ExtractedData
import com.example.apiproject.data.api.Video
import com.google.gson.JsonObject

object Converters {
    fun convertToExtractedData(apiResponse: JsonObject?): ExtractedData? {
        Log.d("Converter", "convertToExtractedData: $apiResponse")
        if (apiResponse == null) return null
        val title = apiResponse.get("title")?.asString ?: "Unknown Title"
        val imageUrl = apiResponse.get("thumbnail")?.asString ?: "Unknown Image URL"
        val videoFormats = mutableListOf<JsonObject>()
        val audioFormats = mutableListOf<JsonObject>()
        Log.d("Converter", "convertToExtractedData2: $videoFormats")
        val formatsArray = apiResponse.get("formats")?.asJsonArray
        var cookie:String?=null
        Log.d("Converter", "convertToExtractedData2.1: $videoFormats")

        if (formatsArray != null) {
            Log.d("Converter", "convertToExtractedData2.11: $formatsArray")
            for (format in formatsArray) {
                Log.d("Converter", "convertToExtractedData2.12: $format")
                val formatObj = format?.asJsonObject
                Log.d("Converter", "convertToExtractedData2.110: $formatObj")
                if(cookie==null) {
                    cookie = formatObj?.get("cookies")?.asString
                }
                Log.d("Converter", "convertToExtractedData2.111: $cookie")
                var vcodec = try{formatObj?.get("vcodec")?.asString}catch (e:Exception){"none"}
                var acodec = try{formatObj?.get("acodec")?.asString}catch (e:Exception){"none"}
                if(vcodec==null){
                    vcodec="none"
                }
                if(acodec==null){
                    acodec="none"
                }
                Log.d("Converter", "convertToExtractedData2.112: $vcodec $acodec")
                when {
                    vcodec == "none" && acodec == "none" -> {
                        // Video-only format
                        formatObj?.let { videoFormats.add(it) }
                    }
                    vcodec != "none" && acodec != "none" -> {
                        // Video-only format
                        formatObj?.let { videoFormats.add(it) }
                    }
                    vcodec == "none" && acodec != "none" -> {
                        // Audio-only format
                        formatObj?.let { audioFormats.add(it) }
                    }
                }
            }
            Log.d("Converter", "convertToExtractedData2.13:  $videoFormats")
        }
        Log.d("Converter", "convertToExtractedData2.2: $videoFormats")

        val videoList = mutableListOf<Video>()
        for (i in videoFormats) {
            val height = i.get("height")?.asInt ?: 0
            val formatId = i.get("format_id")?.asString ?: "Unknown Format ID"
            val url = i.get("url")?.asString ?: null
            val width = i.get("width")?.asInt ?: 0
            if(url!=null) {
                if(height==0 &&  width==0){
                    Log.d("noheight", "convertToExtractedData: $i")
                    if(i?.get("format_id")?.asString=="sd"){
                        val video = Video(
                            height = 640,
                            formatId,
                            1,
                            url = url,
                            width = 350,
                            imageUrl
                        )
                        videoList.add(video)
                    }
                    else{
                        val video = Video(
                            height = 720,
                            formatId,
                            1,
                            url = url,
                            width = 1024,
                            imageUrl
                        )
                        videoList.add(video)
                    }
                }
                else {
                    val video = Video(
                        height = height,
                        formatId,
                        1,
                        url = url,
                        width = width,
                        imageUrl
                    )
                    videoList.add(video)
                }
            }
        }
        Log.d("Converter", "convertToExtractedData3: $videoList")
        if(videoList.size==0){
            return null
        }
        val audioUrl = audioFormats.getOrNull(0)?.get("url")?.asString ?: null
        val extractedData = ExtractedData(
             videoList,
             imageUrl,
            title = title,
             audioUrl,
            cookie = cookie
        )


        Log.d("Converter", "convertToExtractedData: $title $imageUrl")

        return extractedData
    }

}