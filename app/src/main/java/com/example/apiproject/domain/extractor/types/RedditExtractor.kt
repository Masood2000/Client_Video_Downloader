package com.example.apiproject.domain.extractor.types

import android.content.Context
import android.util.Log
import com.example.apiproject.data.api.ExtractedData
import com.example.apiproject.data.api.Video
import com.example.apiproject.domain.extractor.Extractor
import com.example.apiproject.util.NetworkHelper
import com.example.apiproject.util.NetworkHelper.decodeHtmlEntities

class RedditExtractor: Extractor
{
    companion object {
        val instance by lazy { RedditExtractor() }
        var TAG="RedditExtractor"
    }
    override suspend fun getVideoLink(context: Context, url: String): ExtractedData? {
        try {
            var listVideos = mutableListOf<Video>()
            var pageResponse = NetworkHelper.sendGetRequest(url)
            if (pageResponse == null) {
                return null
            }
            var title: String? = null
            var titleStart = pageResponse.indexOf("</embed-snippet-share-button>") + 30
            if (titleStart != -1) {
                var titleEnd = pageResponse.indexOf("</h1>", titleStart)
                title = decodeHtmlEntities(pageResponse.substring(titleStart, titleEnd))
            }
            var image: String? = null
            var imageStart = pageResponse.indexOf("<link rel=\"preload\" href=\"", titleStart)
            if (imageStart != -1) {
                var imageEnd = pageResponse.indexOf("\"", imageStart + 27)
                image = pageResponse.substring(imageStart + 26, imageEnd).replace("amp;", "")
            }
            var playerStart = pageResponse.indexOf("<shreddit-player", imageStart)
            if (playerStart == -1) {
                return null
            }
            var srcStart = pageResponse.indexOf("src", playerStart) + 5
            var srcEnd = pageResponse.indexOf("\"", srcStart)
            var src = pageResponse.substring(srcStart, srcEnd)
            var audio:String?=null
            Log.d(TAG, "getVideoLink: $src")
            if (src.contains("HLSPlaylist.m3u8")) {
                var m3u8Data = NetworkHelper.sendGetRequest(src)
                if (m3u8Data == null) {
                    return null
                }
                var lines = m3u8Data.split("\n")
                var listOfUrlFormats = mutableListOf<String>()
                for (line in lines) {
                    if (!line.contains("AUDIO=")) {
                        Log.d(TAG, "getVideoLink: Audio line : $line")
                        try {
                            var formatStart = line.indexOf("HSL_") + 5
                            var formatEnd = line.indexOf(".m3u8")
                            var format = line.substring(formatStart, formatEnd)
                            if (!format.contains("AUDIO")) {
                                if (format == "576") {
                                    format = "720"
                                } else if (format == "540") {
                                    format = "480"
                                }
                                //check format only contains digits
                                if (format.matches(Regex("\\d+"))) {
                                    listOfUrlFormats.add(format)
                                }
                                // listOfUrlFormats.add(format)
                            }
                            if(format.contains("HLS_AUDIO")){
                                var audioStart = format.indexOf("HLS_")
                                var audioEnd = format.indexOf(".aac")+4
                                var audioFile=format.substring(audioStart,audioEnd)

                                Log.d(TAG, "getVideoLink: Audio File $audioFile")
                                if(audioFile.contains("AUDIO")){
                                    audio=audioFile
                                }
                            }

                        } catch (e: Exception) {
                            Log.d(TAG, "getVideoLink: Error in extracting format")
                        }
                    }
                    if(line.contains("HLS_AUDIO")){
                        var audioStart = line.indexOf("HLS_AUDIO")
                        if(audioStart==-1){
                           continue
                        }
                        var audioEnd = line.indexOf(".m3u8")
                        var audioFile=line.substring(audioStart,audioEnd)
                        Log.d(TAG, "getVideoLink: Audio File $audioFile")
                        if(audioFile.contains("AUDIO")){
                            audio=audioFile+".aac"
                        }
                    }

                }
                val baseUrl = src.substring(0, src.indexOf("HLSPlaylist.m3u8"))
                audio=baseUrl+audio
                listOfUrlFormats.forEach {
                    Log.d(TAG, "getVideoLink Format: $it")
                }




                for (format in listOfUrlFormats.toSet().toList()
                    .sortedByDescending { it.toInt() }) {
                    Log.d(TAG, "getVideoLink: $format P")
                    if (format.length > 20) {
                        continue
                    }
                    if (format[format.length - 1] != '0') {
                        var newformat = format.substring(0, format.length - 1) + "0"
                        listVideos.add(
                            Video(
                                0,
                                "$newformat" + " P",
                                0,
                                baseUrl + "DASH_${newformat}.mp4",
                                0,
                                image
                            )
                        )
                    } else {
                        listVideos.add(
                            Video(
                                0,
                                "$format" + " P",
                                0,
                                baseUrl + "DASH_${format}.mp4",
                                0,
                                image
                            )
                        )
                    }
                }
            } else {

            }

            Log.d(TAG, "getVideoLink: $listVideos")
            Log.d(TAG, "getVideoTitle: $title")
            Log.d(TAG, "getVideoImage: $image")

            Log.d(TAG, "getVideoLink:Audio $audio")
            return ExtractedData(listVideos, image, title,audio)
        }
        catch (e:Exception){
            Log.d(TAG, "getVideoLink: "+e.message)
            return null
        }
    }

}