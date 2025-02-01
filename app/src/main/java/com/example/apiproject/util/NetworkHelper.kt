package com.example.apiproject.util
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.text.Html
import android.text.Spanned
import android.util.Log
import com.example.apiproject.data.interfaces.DataAPI
import com.example.apiproject.data.models.ApiResponse
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.net.URL

object NetworkHelper {
    var cookie: String? = null

    val retrofit = Retrofit.Builder()
        .baseUrl("http://13.60.183.120:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiService = retrofit.create(DataAPI::class.java)
    suspend fun getData(encodedUrl: String): JsonObject? {
        return try {
            val response = apiService.getData(encodedUrl)
            if (response.isSuccessful) {
                Log.d("ApiResponse", "getData: ${response.body()    }")
                response.body() // Return the data if successfulFF
            } else {
                Log.d("GetData", "Request failed with code: ${response.code()}")
                null // Return null if the request failed
            }
        } catch (e: Exception) {
            Log.e("GetData", "Error: ${e.message}")
            null // Return null if an exception occurs
        }
    }

    fun internetIsConnected(): Boolean {
        try {
            val command = "ping -c 1 google.com"
            return (Runtime.getRuntime().exec(command).waitFor() == 0)
        } catch (e: Exception) {
            return false
        }
    }
    fun isInternetConnectionAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities != null &&
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
    }
    fun decodeHtmlEntities(input: String): String {
        val spanned: Spanned = Html.fromHtml(input, Html.FROM_HTML_MODE_LEGACY)
        return spanned.toString()
    }
    fun sendGetRequest(url: String, params: Map<String, String>?=null, headers: Map<String, String>?=null): String {
        try {
            val urlString = StringBuilder(url)
            if (params?.isNotEmpty() == true) {
                urlString.append("?")
                params?.forEach { (key, value) ->
                    urlString.append("$key=$value&")
                }
                urlString.deleteCharAt(urlString.length - 1) // Remove the last '&'
            }
            val connection = URL(urlString.toString()).openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            headers?.forEach { (key, value) ->
                connection.setRequestProperty(key, value)
            }
            val hasHeaders = connection.headerFields != null

            if (hasHeaders) {
                println("Response contains headers.")
                for (i in connection.headerFields) {
                    Log.i("ResponseHeaders", "sendGetRequest: " + i.key + "  :   " + i.value)
                }
            } else {
                println("Response does not contain headers.")
            }


            val responseCode = connection.responseCode
            return if (responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = connection.inputStream
                val response = inputStream.bufferedReader().use { it.readText() }
                inputStream.close()
                println(response)

                response
            } else {
                "Error: $responseCode"
            }
        }
        catch(e:Exception){
            return "Error: ${e.message}"
        }
        catch (e:javax.net.ssl.SSLHandshakeException){
            return "Error: ${e.message}"
        }
    }
    fun sendGetRequestResponseWithCookie(url: String, params: Map<String, String>?=null, headers: Map<String, String>?=null): MutableMap<String,String>? {
        try {
            val urlString = StringBuilder(url)
            if (params?.isNotEmpty() == true) {
                urlString.append("?")
                params?.forEach { (key, value) ->
                    urlString.append("$key=$value&")
                }
                urlString.deleteCharAt(urlString.length - 1) // Remove the last '&'
            }

            val connection = URL(urlString.toString()).openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            headers?.forEach { (key, value) ->
                connection.setRequestProperty(key, value)
            }
            val hasHeaders = connection.headerFields != null
            var answer = mutableMapOf<String, String>()
            if (hasHeaders) {
                Log.i(
                    "ResponseHeaders",
                    "sendGetRequest: " + connection.headerFields["Set-Cookie"]?.get(2).toString()
                )
                answer["cookie"] = connection.headerFields["Set-Cookie"]?.get(2).toString()
            } else {
                println("Response does not contain headers.")
                return null
            }
            val responseCode = connection.responseCode
            return if (responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = connection.inputStream
                val response = inputStream.bufferedReader().use { it.readText() }
                inputStream.close()
                println(response)
                Log.d("RRR",
                    "sendGetRequestResponseWithCookie:  ${
                        response.find {
                            it.equals("bitrateInfo")
                        }
                    }"
                )
                Log.d("RRR", "sendGetRequestResponseWithCookie:  ${response}")
                Log.d("RRR", "sendGetRequestResponseWithCookie: ${response.indexOf("bitrateInfo")}")
                answer["page"] = response
                answer
            } else {
                "Error: $responseCode"
                null
            }
        }
        catch (e: Exception){
            return null
        }
    }


}