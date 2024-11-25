package com.example.apiproject.util

import android.os.Environment
import android.os.SystemClock
import android.view.View
import java.io.File
import java.util.UUID

object Helper {
    var lastClickTime: Long = 0
    fun View.setOnOneClickListener(
        analytics: String = "",
        debounceTime: Long = 500L,
        action: () -> Unit
    ) {
        this.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
                else {
                    action()
                }
                lastClickTime = SystemClock.elapsedRealtime()
            }
        })
    }

    fun getNewDownloadPath():String{
        val dir =
            File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath.toString() + "/Downloaderspot/")
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dir.absolutePath.toString() +"/"+UUID.randomUUID().toString()+".mp4"
    }
    fun getNewAudioDownloadPath():String{
        val dir =
            File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath.toString() + "/Downloaderspot/")
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dir.absolutePath.toString() +"/"+UUID.randomUUID().toString()+".wav"
    }
}