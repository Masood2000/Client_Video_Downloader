package com.example.apiproject.domain.extractor.types

import android.content.Context
import android.util.Log
import com.example.apiproject.data.api.ExtractedData
import com.example.apiproject.data.api.Video
import com.example.apiproject.domain.extractor.Extractor
import com.example.apiproject.util.NetworkHelper


class TikTokExtractor: Extractor {
    var TAG="Tiktok Extractor"
    companion object{
        val instance by lazy { TikTokExtractor() }
        var cookie:String?= null
    }
    override suspend fun getVideoLink(context: Context, url: String): ExtractedData? {

        Log.d(TAG, "getVideoLink: ${url}")
        var firstHeaders= mapOf(
            "Accept" to "*/*",
            "User-Agent" to "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36",

       )
        var responseMap= NetworkHelper.sendGetRequestResponseWithCookie(url,null,firstHeaders)
         cookie =responseMap?.get("cookie")?.substringBefore(";")
        var returnCookie= cookie
        if(cookie ==null){
            return null
        }
        Log.d(TAG, "getVideoLink: COokie "+ cookie)

        var page= responseMap?.get("page") ?: return null


        var tempDetailsStart= page.indexOf("webapp.video-detail")

         var imageStart=page.indexOf("\"cover\":\"",tempDetailsStart)
        var imageEnd=page.indexOf("\"",imageStart+9)
        var image=page.substring(imageStart+9,imageEnd).replace("\\u002F","/")
        Log.d(TAG, "getVideoLink: Image : "+image)


        var startIndex= page.indexOf("bitrateInfo")
        Log.d(TAG, "getVideoLink: Start Index : " + startIndex.toString())
        if(startIndex==-1){
            return null
        }
        else{
            startIndex +="bitrateInfo".length+2
        }

        var videoDataMap=getVideoDataMap(page.slice(IntRange(startIndex,page.length-1)))

        var answer= mutableListOf<Video>()
        for (i in videoDataMap){
            Log.d(TAG, "getVideoLink: "+i.key)
           /* for (j in i.value){
                Log.d(TAG, "getVideoLink: "+j.replace("\"",""))
            }*/

            Log.d(TAG, i.value.toString())
            if(i.key.contains("lowes")){
                answer.add(Video(0,i.key,0,i.value[0],0))
            }
            else{
                answer.add(Video(0,i.key,1,i.value[0],0))

            }

        }
        var extractedData= ExtractedData(answer,image,"Tiktok Video",null, returnCookie)
        return extractedData
    }
    fun getVideoDataMap(data: String): Map<String, List<String>> {
        val map = mutableMapOf<String, List<String>>()
        var startIndex = 0
        var endIndex: Int
        while (startIndex >= 0) {
            startIndex = data.indexOf("GearName", startIndex)
            if (startIndex < 0) {
                break
            }
            startIndex += "GearName".length + 3
            endIndex = data.indexOf(",", startIndex)
            if (endIndex == -1) endIndex = data.length
            val gearName = data.substring(startIndex, endIndex-1).trim()
            startIndex = data.indexOf("UrlList", startIndex)
            startIndex += "UrlList".length + 4
            endIndex = data.indexOf("UrlKey", startIndex) - 4
            if (endIndex == -1) endIndex = data.length
            var urlList = data.substring(startIndex, endIndex).trim()
            urlList = urlList.replace("\\u002F", "/")
            map[gearName] = urlList.split(",")
            println("GearName: $gearName, UrlList: $urlList")
            startIndex = endIndex
        }
        return map
    }


}
