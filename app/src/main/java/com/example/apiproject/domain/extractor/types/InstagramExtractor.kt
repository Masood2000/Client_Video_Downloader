package com.example.apiproject.domain.extractor.types
import android.content.Context
import android.util.Log
import com.example.apiproject.data.api.ExtractedData
import com.example.apiproject.data.api.Video
import com.example.apiproject.domain.extractor.Extractor
import com.example.apiproject.util.NetworkHelper


class InstagramExtractor : Extractor {
    var TAG = "InstagramExtractor"
    companion object {
        val instance: InstagramExtractor by lazy { InstagramExtractor() }
    }
    override suspend fun getVideoLink(context: Context, link: String): ExtractedData? {
        try {
            var data: String? = null
            var shortCode: String = ""
            //Extracting short code from url
            if (link.contains("instagram.com/p/")) {
                shortCode = link.split("instagram.com/p/")[1].split("/")[0]
            } else if (link.contains("instagram.com/tv/")) {
                shortCode = link.split("instagram.com/tv/")[1].split("/")[0]
            } else if (link.contains("instagram.com/reel/")) {
                shortCode = link.split("instagram.com/reel/")[1].split("/")[0]
            } else if (link.contains("instagram.com/stories/")) {
                shortCode = link.split("instagram.com/stories/")[1].split("/")[0]
            } else {
                return null
            }
            var baseUrl =
                "https://www.instagram.com/graphql/query/?hl=en&query_hash=b3055c01b4b222b8a47dc12b090e4e64&variables=%7B%22shortcode%22%3A%22${shortCode}%22%7D"

            var headers = mapOf(
                "Accept" to "*/*",
                "User-Agent" to "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3"
            )

            var response = NetworkHelper.sendGetRequest(baseUrl, null, headers)

            Log.d(TAG, "getVideoLink: Response : " + response)
            if (response.contains("video_url")) {
                var startIndex = response.indexOf("video_url")
                startIndex += 12
                var endIndex = response.indexOf("\"", startIndex)
                var videoUrl = response.substring(startIndex, endIndex)
                var image = ""
                var imageStart = response.indexOf("display_url") + 14
                var imageEnd = response.indexOf("\"", imageStart)
                image = response.substring(imageStart, imageEnd)
                var title = ""
                var titleStart = response.indexOf("title") + 9
                var titleEnd = response.indexOf("\"", titleStart)
                title = response.substring(titleStart, titleEnd)
                if (title.length<5) {
                    title = "Instagram Video"
                }
                Log.d(TAG, "getVideoLink: Video Url : " + videoUrl)
                Log.d(TAG, "getVideoLink: Image Url : " + image)
                Log.d(TAG, "getVideoLink: Title : " + title)


                return ExtractedData(listOf(Video(0, "HD", 0, videoUrl.replace("\\u0026","&"), 0)), image.replace("\\u0026","&"), title)
            }

            return null
        }
        catch (e:Exception){
            Log.d(TAG, "getVideoLink: Error "+e.message)
            return null
        }
    }
}





