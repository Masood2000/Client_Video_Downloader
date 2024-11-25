package com.example.apiproject.data.interfaces

import android.os.Bundle

interface ClickHandler {
    fun onClickPressed()
}

interface ClickBundleHandler {
    fun onClickPressed(bundle: Bundle)
}