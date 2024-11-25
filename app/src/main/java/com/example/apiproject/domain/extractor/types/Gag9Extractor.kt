package com.example.apiproject.domain.extractor.types

import android.content.Context
import android.util.Log
import com.example.apiproject.data.api.ExtractedData
import com.example.apiproject.data.api.Video
import com.example.apiproject.domain.extractor.Extractor
import com.example.apiproject.util.NetworkHelper

class Gag9Extractor: Extractor
{
    companion object{
        val instance: Gag9Extractor by lazy { Gag9Extractor() }
    }
    override suspend fun getVideoLink(context: Context, url: String): ExtractedData? {

        var firstHeaders= mapOf(
            "Accept" to "*/*",
            "User-Agent" to "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36",

            )
        var response= NetworkHelper.sendGetRequest(url,null,firstHeaders)

        if (response.contains("contentUrl")){
            Log.d("Gag9Extractor", "getVideoLink: Video Found")
            var startIndex= response.indexOf("contentUrl")
            startIndex+=13
            var endIndex= response.indexOf("\"",startIndex)
            var videoUrl= response.substring(startIndex,endIndex)
            Log.d("Gag9Extractor", "getVideoLink: Video Url : "+videoUrl)
            var image= ""
            var imageStart= response.indexOf("property=\"og:image\" content=\"")+ "property=\"og:image\" content=\"".length
            var imageEnd= response.indexOf("\"",imageStart)
            image= response.substring(imageStart,imageEnd)
            Log.d("Gag9Extractor", "getVideoLink: Image Url : "+image)
            var title= ""
            var titleStart= response.indexOf("property=\"og:title\" content=\"")+ "property=\"og:title\" content=\"".length
            var titleEnd= response.indexOf("\"",titleStart)
            title= response.substring(titleStart,titleEnd)
            Log.d("Gag9Extractor", "getVideoLink: Title : "+title)
            var Video= listOf(Video(0,"HD",0,videoUrl,0))
            return ExtractedData(Video,image,title)

        }
        else{
            Log.d("Gag9Extractor", "getVideoLink:Video Not Found")
        }

        return null
    }
}