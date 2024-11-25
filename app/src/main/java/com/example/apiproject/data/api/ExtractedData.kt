package com.example.apiproject.data.api

data class ExtractedData(var video: List<Video>?,
                         var imageUrl: String? = null,
                         var title:String?=null,
                         var audio:String? = null,
                        var cookie:String? = null,
)
