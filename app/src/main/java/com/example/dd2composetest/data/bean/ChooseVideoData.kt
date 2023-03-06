package com.example.dd2composetest.data.bean

data class ChooseVideoData(
    var id: Int = 0,
    var title: String = "",
    var previewUrl: String = "",
    var coverUrl: String = "",
    var duration: Int = 0,
    var playCount: Int = 0,
    var likeCount: Int = 0,
    var creationDateTime: String = "",
    var isSelect: Boolean = false
)
