package com.example.apiproject.data.preferences

import android.content.Context

import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


class SharedPreference ( context: Context) {


    private val mPreference by lazy { context.getSharedPreferences(APP_PREFS_NAME, Context.MODE_PRIVATE) }



    fun setLastAutoFetchLink(link: String) {
        mPreference.edit().putString(LAST_AUTO_FETCH_LINK, link).apply()
    }

    fun getLastAutoFetchLink(): String? {
        return mPreference.getString(LAST_AUTO_FETCH_LINK, null)
    }


    fun isNewUser(): Boolean {
        return mPreference.getBoolean(BOOL_NEW_USER, true)
    }

    fun setIsNewUser(value:Boolean) {
        mPreference.edit().putBoolean(BOOL_NEW_USER, value).apply()
    }


    companion object {
        private const val APP_PREFS_NAME = "OneTapDownloaderPreference"

        private const val LAST_AUTO_FETCH_LINK = "lastlink"
        val BOOL_NEW_USER = "bool_new_user"
    }
}