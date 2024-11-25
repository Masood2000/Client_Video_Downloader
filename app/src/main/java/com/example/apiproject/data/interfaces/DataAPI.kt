package com.example.apiproject.data.interfaces

import com.example.apiproject.data.models.ApiResponse
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DataAPI {
    @GET("getData/{encodedUrl}")
    fun getData(
        @Path("encodedUrl", encoded = true) encodedUrl: String
    ): Call<JsonObject>
}
