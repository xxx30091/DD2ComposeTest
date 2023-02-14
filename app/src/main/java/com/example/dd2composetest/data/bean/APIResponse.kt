package com.example.dd2composetest.data.bean

class ApiResponseBean<T>(t: T) {
    var code: Int = 0
    var message: String? = ""
    var date: T? = t
}

data class APIResponse<T> (
    val code: Int,
    val message: String,
    val data: T?
)


