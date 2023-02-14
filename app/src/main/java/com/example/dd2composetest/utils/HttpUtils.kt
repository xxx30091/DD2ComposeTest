package com.example.dd2composetest.utils

import com.example.dd2composetest.ThisApp
import com.example.dd2composetest.preference.Preference
import okhttp3.HttpUrl
import okhttp3.MultipartBody
import okhttp3.Request

object HttpUtils {

    fun getRequestUseFormUrlencoded(body: MultipartBody, url: String): Request {
        val token = Preference.getInstance(ThisApp.instance).getUserToken()
        val header = if (token == "") Request.Builder().addHeader("Accept", "application/json")
        else Request.Builder().addHeader("Accept", "application/json")
            .addHeader("Authorization", "Bearer $token")

        return header
            .url(url)
            .post(body)
            .build()
    }



}