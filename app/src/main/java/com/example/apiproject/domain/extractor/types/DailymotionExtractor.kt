package com.example.apiproject.domain.extractor.types

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.example.apiproject.data.api.ExtractedData
import com.example.apiproject.domain.extractor.Extractor
import com.example.apiproject.util.NetworkHelper
import kotlinx.coroutines.runBlocking
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.util.regex.Pattern

class XModel : Comparable<XModel?> {
    var quality: String? = null
    var url: String? = null
    var cookie: String? = null
    override fun compareTo(other: XModel?): Int {
        if (startWithNumber(other?.quality)) {
            return quality!!.replace("\\D+".toRegex(), "")
                .toInt() - other?.quality!!.replace("\\D+".toRegex(), "").toInt()
        }
        return quality!!.length - other?.quality!!.length
    }


    override fun toString(): String {
        return quality!!
    }


   private fun startWithNumber(string: String?): Boolean {
        //final String regex = "^[0-9][A-Za-z0-9-]*$";
       val regex =
               "^[0-9][A-Za-z0-9-\\s,]*$" // start with number and can contain space or comma ( 480p , ENG)
        val pattern = Pattern.compile(regex, Pattern.MULTILINE)
        val matcher = pattern.matcher(string)
        return matcher.find()
    }
}
//
@SuppressLint("StaticFieldLeak")
class DailyMotionUtils {
    var xModels: ArrayList<XModel> = ArrayList<XModel>()
    var onDone: OnDone? = null
    fun fetch(response: String, onDone: OnDone?) {
        this.onDone = onDone
        try {
            val json = getJson(response)
            val jsonObject = JSONObject(json).getJSONObject("metadata").getJSONObject("qualities")
            val iterator = jsonObject.keys()
            while (iterator.hasNext()) {
                var key = iterator.next()
                if (!key.equals("auto", ignoreCase = true)) {
                    println("This is Direct")
                    val array = jsonObject.getJSONArray(key)
                    for (i in 0 until array.length()) {
                        val temp = array.getJSONObject(i)
                        val type = temp.getString("type")
                        val url = temp.getString("url")
                        if (key.contains("@")) {
                            key = key.replace("@", "-")
                        }
                        val quality = key + "p"
                        if (type.contains("mp4")) {
                            putModel(url, quality, xModels)
                        }
                    }
                    showResult()
                } else {
                    println("This is HLS")
                    try {
                        val url = jsonObject.getJSONArray(key).getJSONObject(0).getString("url")

                        object : AsyncTask<Void?, Void?, Void?>() {
                            override fun onPostExecute(aVoid: Void?) {
                                super.onPostExecute(aVoid)
                                showResult()
                            }
                            override fun doInBackground(vararg params: Void?): Void? {
                                try {
                                    val reader =
                                        BufferedReader(InputStreamReader(URL(url).openStream()))
                                    var line: String
                                    var s = ""
                                    while ((reader.readLine().also { line = it }) != null) {
                                        s += line
                                    }
                                    for (a in s.split("#EXT-X-STREAM-INF".toRegex())
                                        .dropLastWhile { it.isEmpty() }
                                        .toTypedArray()) {
                                        val mUrl = query("PROGRESSIVE-URI", a)
                                        val mName = query("NAME", a) + "p"
                                        if (mUrl != null) {
                                            putModel(mUrl, mName, xModels)
                                        }
                                    }
                                }
                                catch (e: IOException) {
                                    e.printStackTrace()
                                }
                                return null
                            }
                        }.execute()
                    } catch (e: Exception) {
                        showResult()
                        e.printStackTrace()
                    }
                }
            }
        } catch (e: JSONException) {
            showResult()
            e.printStackTrace()
        }
    }

    private fun showResult() {
        if (xModels.size > 0) {
            onDone!!.onResult(xModels)
        } else {
            onDone!!.onResult(null)
        }
    }

    interface OnDone {
        fun onResult(xModels: ArrayList<XModel>?)
    }

    private fun query(key: String, string: String): String? {
        val regex = "$key=\"(.*?)\""
        val pattern = Pattern.compile(regex, Pattern.MULTILINE)
        val matcher = pattern.matcher(string)
        if (matcher.find()) {
            return matcher.group(1)
        }
        return null
    }

    private fun getJson(html: String): String? {
        val regex = "var ?config ?=(.*);"
        val pattern = Pattern.compile(regex, Pattern.MULTILINE)
        val matcher = pattern.matcher(html)
        if (matcher.find()) {
            return matcher.group(1)
        }
        return null
    }

    companion object {
        fun getDailyMotionID(link: String?): String? {
            val regex =
                "^.+dailymotion.com\\/(embed)?\\/?(video|hub)\\/([^_]+)[^#]*(#video=([^_&]+))?"
            val pattern = Pattern.compile(regex)
            val matcher = pattern.matcher(link)
            if (matcher.find()) {
                return if (matcher.group(5) != null && matcher.group(5) != "null") {
                    removeSlash(matcher.group(5))
                } else removeSlash(matcher.group(3))
            }
            return null
        }

        private fun putModel(url: String?, quality: String?, model: ArrayList<XModel>) {
            val xModel = XModel()
            xModel.url = url
            xModel.quality = quality
            model.add(xModel)

        }

       private fun removeSlash(ogay: String): String {
           if (ogay.contains("/")) {
               return ogay.replace("/", "")
           }
           return ogay
       }
   }
}
class DailymotionExtractor: Extractor {
    companion object {
        val instance by lazy { DailymotionExtractor() }
        var TAG="DailymotionExtractor"
    }
    override suspend fun getVideoLink(context: Context, url: String): ExtractedData? {


        Log.d(TAG, "getVideoLink: ${url}")
        
        var response = NetworkHelper.sendGetRequest(url, null, null)

        Log.d(TAG, "getVideoLink: ${response.length}")
        runBlocking {


            DailyMotionUtils().fetch(response, object : DailyMotionUtils.OnDone {
                override fun onResult(xModels: ArrayList<XModel>?) {
                    Log.d(TAG, "onResult: ${xModels}")
                    if (xModels != null) {
                        for (xModel in xModels) {
                            println("Quality : " + xModel.quality + " URL : " + xModel.url)
                            Log.d(TAG, "onResult: Quality : " + xModel.quality + " URL : " + xModel.url)
                        }

                        return
                    }
                }


            }

            )
        }
        return null
    }
}
