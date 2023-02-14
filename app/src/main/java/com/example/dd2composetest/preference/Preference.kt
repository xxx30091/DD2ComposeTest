package com.example.dd2composetest.preference

import android.annotation.SuppressLint
import android.content.Context
import android.util.JsonToken
import androidx.compose.ui.tooling.preview.Device
import com.example.dd2composetest.data.bean.UserData

class Preference private constructor(private val context: Context) {
    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: Preference? = null
        fun getInstance(context: Context): Preference = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Preference(context)
        }

        var LOGIN_STATUS = "loginStatus"
        var TOKEN = "token"
        var DEVICE_TOKEN = "deviceToken"
        var REFRESH_TOKEN = "refreshToken"
        var USER_ID = "userId"
    }

    val userPreference by lazy {
        context.getSharedPreferences("user", Context.MODE_PRIVATE)
    }

    fun saveLoginStatus(isLogin: Boolean) =
        userPreference.edit().putBoolean(LOGIN_STATUS, isLogin).apply()
    fun getLoginStatus() = userPreference.getBoolean(LOGIN_STATUS, false)

    // token
    fun saveUserToken(token: String) =
        userPreference.edit().putString(TOKEN, token).apply()
    fun getUserToken() = userPreference.getString(TOKEN, "")

    // device token
    fun saveDeviceToken(deviceToken: String) =
        userPreference.edit().putString(DEVICE_TOKEN, deviceToken).apply()
    fun getDeviceToken() = userPreference.getString(DEVICE_TOKEN, "")

    // refresh token
    fun saveRefreshToken(refreshToken: String) =
        userPreference.edit().putString(REFRESH_TOKEN, refreshToken).apply()
    fun getRefreshToken() = userPreference.getString(REFRESH_TOKEN, "")

    // user id
    fun saveUserId(userId: Int) =
        userPreference.edit().putInt(USER_ID, userId).apply()
    fun getUserId() = userPreference.getInt(USER_ID, 0)

    // userInfo
//    fun saveUserInfo(info: UserData) =
//        userPreference.edit()
//    fun getUserInfo() = userPreference

    // clear token (for logout
    fun clear() {
        saveRefreshToken("")
        saveUserToken("")
        saveLoginStatus(false)
    }

}


