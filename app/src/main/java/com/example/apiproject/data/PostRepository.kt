package com.example.apiproject.data

import android.util.Log
import com.example.apiproject.data.gag.RetrofitClient
import com.example.apiproject.data.models.gag.VideoShorts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun fetchFreshPosts(nextApiHit: String): Result<VideoShorts> {
        return try {
            val (after, count) = parseApiParams(nextApiHit)
            Log.i("WatchReelsFragment", "fetchFreshPosts: after is $after and count is $count")
            val response = withContext(Dispatchers.IO) {
                apiService.getFreshPosts(after, count)
            }
            Log.i("WatchReelsFragment", "fetchFreshPosts: response is $response")
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun parseApiParams(apiParams: String): Pair<String, Int> {
        val params = apiParams.split("&").associate {
            val (key, value) = it.split("=")
            key to value
        }
        val after = params["after"] ?: ""
        val count = params["c"]?.toInt() ?: 25
        return Pair(after, count)
    }
}
