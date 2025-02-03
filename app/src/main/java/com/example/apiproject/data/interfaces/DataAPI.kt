package com.example.apiproject.data.interfaces

import com.example.apiproject.data.models.ApiResponse
import com.google.errorprone.annotations.Keep
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

@Keep
interface DataAPI {
    @GET("getData/")
    suspend fun getData(
        @Query("url", encoded = true) encodedUrl: String
    ): Response<JsonObject>
}
