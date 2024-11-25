package com.example.apiproject.data.Preferences

import android.content.Context
import android.content.SharedPreferences



class SharedPreference(private val context: Context) {
    private val mPreference: SharedPreferences
    private val mPrefEditor: SharedPreferences.Editor

    init {
        this.mPreference = context.getSharedPreferences(APP_PREFS_NAME, Context.MODE_PRIVATE)
        this.mPrefEditor = mPreference.edit()
    }

    fun setLastAutoFetchLink(link: String) {
        mPrefEditor.putString(LAST_AUTO_FETCH_LINK, link)
        mPrefEditor.apply()
    }

    fun getLastAutoFetchLink(): String? {
        return mPreference.getString(LAST_AUTO_FETCH_LINK, null)
    }

    companion object {
        private const val APP_PREFS_NAME = "OneTapDownloaderPreference"

        private const val LAST_AUTO_FETCH_LINK = "lastlink"
    }
}