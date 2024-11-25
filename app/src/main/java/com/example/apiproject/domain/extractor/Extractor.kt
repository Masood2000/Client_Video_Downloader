package com.example.apiproject.domain.extractor

import android.content.Context
import com.example.apiproject.data.api.ExtractedData

interface Extractor {
    suspend fun getVideoLink(context: Context, url:String): ExtractedData?
}