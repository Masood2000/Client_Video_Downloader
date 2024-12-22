package com.example.apiproject.data.api.gag

import com.example.apiproject.data.models.gag.VideoShorts
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService9gag {
    @GET("v1/group-posts/group/video/type/fresh")
    suspend fun getFreshPosts(
        @Query("after") after: String,
        @Query("c") count: Int = 25
    ): VideoShorts

}