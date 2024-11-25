package com.example.apiproject.domain.extractor.types

//import java.lang.Exception
import android.content.Context
import android.util.Log
import com.example.apiproject.data.api.ExtractedData
import com.example.apiproject.data.api.Video
import com.example.apiproject.domain.extractor.Extractor
import com.example.apiproject.util.NetworkHelper
import com.example.apiproject.util.NetworkHelper.decodeHtmlEntities
import com.example.apiproject.util.NetworkHelper.internetIsConnected
import java.util.regex.Pattern

class FacebookExtractor: Extractor {
    var TAG="FB_EXTRACTOR"
     companion object{
         val instance: FacebookExtractor by lazy { FacebookExtractor() }
     }
    private fun checkRedirectedUrl(response:String):String?{
        try {
            val pattern = """(?<=<link rel="canonical" href=")(.*?)(?=")"""
            val matcher = Pattern.compile(pattern).matcher(response)
            matcher.find()
            val link = matcher.group(1)
            println(link)
            Log.d(TAG, "checkRedirectedUrl: " + link)

            return link
        }
        catch (e:Exception){
            return null
        }
    }
    private fun findMediaId(input: String): Long? {
        val parts = input.split(":")
        try {
            Log.i(TAG, "findMediaId:2 "+parts[1].split(',')[0])
            var id=parts[1].split(',')[0].toLongOrNull()
            return id
        }
        catch(e:Exception){}
        return null
    }

    private fun getMediaId(context:Context, url:String, headers:Map<String,String>):Long?{
        var response= NetworkHelper.sendGetRequest(url,null,null)
        var tempUrl=checkRedirectedUrl(response)?:""
        if(tempUrl==""){
            return null
        }
        else{

            var response= NetworkHelper.sendGetRequest(tempUrl,null,headers)

            if(response.contains("media_id")){
                var start=response.indexOf("media_id")
                var slice=response.slice(IntRange(start,start+50))
                var id=findMediaId(slice)
                Log.d(TAG, "response size "+slice)

                Log.d(TAG, "getVideoLink: MediaId Found "+id.toString())
                return id

            }
            else{
                Log.d(TAG, "getVideoLink: MediaId not Found ")
            }
        }
        return null
    }
    override suspend fun getVideoLink(context: Context, inputUrl: String): ExtractedData? {

        //https://scontent.fisb17-1.fna.fbcdn.net/v/t15.5256-10/438249522_987815326279906_3661943224008333097_n.jpg?_nc_cat=105&amp;ccb=1-7&amp;_nc_sid=f4080e&amp;_nc_ohc=OCMEc9id0d8Q7kNvgGOL6HW&amp;_nc_ht=scontent.fisb17-1.fna&amp;oh=00_AYBJ6p5nK3_5w03ZZU3Ihz1GXgSn8gKkWkYhnyImcTP0dw&amp;oe=66442155
        try {
            if (internetIsConnected()) {
                Log.d(TAG, "getVideoLink: Internet Connected")
            } else {
                Log.d(TAG, "getVideoLink: Internet Not Connected")
                return null
            }
            var url = inputUrl
            val headers = mapOf<String, String>(
                "Accept" to "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
                "Accept-Language" to "en-US,en;q=0.9",
                "Dpr" to "1",
                "Sec-Ch-Prefers-Color-Scheme" to "light",
                "Sec-Ch-Ua" to "\"Chromium\";v=\"122\", \"Not(A:Brand\";v=\"24\", \"Google Chrome\";v=\"122\"",
                "Sec-Ch-Ua-Full-Version-List" to "\"Chromium\";v=\"122.0.6261.112\", \"Not(A:Brand\";v=\"24.0.0.0\", \"Google Chrome\";v=\"122.0.6261.112\"",
                "Sec-Ch-Ua-Mobile" to "?0",
                "Sec-Ch-Ua-Model" to "\"\"",
                "Sec-Ch-Ua-Platform" to "\"Windows\"",
                "Sec-Ch-Ua-Platform-Version" to "\"10.0.0\"",
                "Sec-Fetch-Dest" to "document",
                "Sec-Fetch-Mode" to "navigate",
                "Sec-Fetch-Site" to "none",
                "Sec-Fetch-User" to "?1",
                "Upgrade-Insecure-Requests" to "1",
                "User-Agent" to "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36",
                "Cache-Control" to "no-cache",
                "Postman-Token" to "ec8d1175-a906-4766-b859-44f5b366ab3f",
                "Host" to "www.facebook.com",
                "Connection" to "keep-alive",
//            "Cookie" to context.getString(R.string.cookie_fb),
            )
            var webPage = ""
            if (url.contains("fb.watch")) {
                var mediaId = getMediaId(context, url, headers)
                webPage = NetworkHelper.sendGetRequest(
                    "https://www.facebook.com/watch/?v=${mediaId}",
                    null,
                    headers
                )
            } else {
                webPage = NetworkHelper.sendGetRequest(url, null, headers)
            }
            Log.d(
                "FbWebPage",
                "getVideoLink: " + webPage.contains("VideoPlayerShakaPerformanceLoggerConfig")
            )

            val titleStart = webPage.indexOf("name=\"description\"") + "name=\"description\"".length
            var title = webPage.slice(IntRange(titleStart, titleStart + 200))
            val titleContentStart = title.indexOf("content") + 9
            val titleContentEnd = title.indexOf(">", titleContentStart) - 4
            title = title.slice(IntRange(titleContentStart, titleContentEnd))
            Log.d(TAG, "getVideoLink: Title " + title)

            val imageStart =
                webPage.indexOf("property=\"og:image\" content=\"") + "property=\"og:image\" content=\"".length
            var imgEnd = webPage.indexOf("\"", imageStart)
            var imageUrl = webPage.slice(IntRange(imageStart, imgEnd - 1)).replace("\\", "")
                .replace("amp;", "").replace("u0025", "%")
            Log.d(TAG, "getVideoLink: Image Url " + imageUrl)

            var start = webPage.indexOf("VideoPlayerShakaPerformanceLoggerConfig")
            var end = webPage.indexOf("</script>", start)
            if (start == -1 || end == -1) {
                return null
            }
            Log.d("fbExtractorDebug", "getVideoLink: Start: $start End: $end")
            var urlLine = webPage.slice(IntRange(start, end))
            var sdUrlStart =
                urlLine.indexOf("browser_native_sd_url") + "browser_native_sd_url".length + 3
            var sdUrlEnd = urlLine.indexOf("browser_native_hd_url", sdUrlStart)
            var sd_url = urlLine.slice(IntRange(sdUrlStart, sdUrlEnd - 4)).replace("\\", "")
            var hd_url = urlLine.slice(
                IntRange(
                    sdUrlEnd + "browser_native_hd_url".length + 3,
                    urlLine.indexOf("spherical_video_fallback_urls") - 4
                )
            ).replace("\\", "").replace("amp;", "").replace("u0025", "%")
            var answer = mutableListOf<Video>()
            if (hd_url.length < 10) {
                answer = mutableListOf(Video(720, "SD", 0, sd_url, 720))
            } else {
                answer = mutableListOf(

                    Video(1024, "HD", 1, hd_url, 1024),
                            Video(720, "SD", 0, sd_url, 720)
                )
            }


            var extractedData: ExtractedData = ExtractedData(answer.toList(), imageUrl,if(title.length>5) decodeHtmlEntities(title) else "Facebook Video")



            return extractedData
        }
        catch (e:Exception){
            Log.d(TAG, "getVideoLink: "+e.message)
            return null
        }
    }

}


