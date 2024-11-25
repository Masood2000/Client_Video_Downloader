package com.example.apiproject.data.interfaces

import com.example.apiproject.data.models.ReelVideo

interface ReelClickHandler {
    fun onClickPressed(reelVideo: ReelVideo)
}