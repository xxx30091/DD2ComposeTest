package com.example.dd2composetest.data.bean

data class LoginData(
    var userId: Int = 0,
    var accessToken: String = "",
    var refreshToken: String = "",
    var rongCloudToken: String = "",
)
