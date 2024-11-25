package com.example.apiproject.domain.extractor.types

import android.content.Context
import android.util.Log
import com.example.apiproject.data.api.ExtractedData

object ExtractorManager {

    suspend fun getVideo(context: Context,url:String): ExtractedData?{
        Log.d("HOME_FRAGMENT", "getVideo: here")
        if (url.contains("tiktok.com")) {
            println("TikTok link detected")
            return TikTokExtractor.instance.getVideoLink(context,url)
        }
        else if (url.contains("facebook.com")||url.contains("fb.com")
            ||url.contains("fb.watch")||url.contains("fb.me")) {
            println("Facebook link detected")
            val video= FacebookExtractor.instance.getVideoLink(context,url)
            if(video==null){
                return FacebookExtractor.instance.getVideoLink(context,url)
            }
            else{
                return video
            }
        }
        else if (url.contains("instagram.com")) {
            Log.d("HOME_FRAGMENT", "getVideo: here")
            println("Instagram link detected")
            return InstagramExtractor.instance.getVideoLink(context,url)
        }
        else if(url.contains("tiktok")){
            println("TikTok Link detected")
            return TikTokExtractor.instance.getVideoLink(context,url)
        }



        else if(url.contains("9gag.com")){
            println("9Gag Link detected")
            return Gag9Extractor.instance.getVideoLink(context,url)
        }

        else if(url.contains("reddit.com")){
            println("Reddit Link detected")
            return RedditExtractor.instance.getVideoLink(context,url)
        }
        else if(url.contains("dailymotion.com")  || url.contains("dai.ly") ){

            Log.d("EXTRACTOR_TAG", "getVideo: ${url}")
            println("daily motion link detected")
            return DailymotionExtractor.instance.getVideoLink(context,url)
        }


        else {
            println("Unknown link detected" + url)
            return null
        }
    }

}