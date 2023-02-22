package com.example.dd2composetest.data.bean

data class EditArticleData(
    var title: String = "",
    var content: String = "",
    var tagList: ArrayList<String>? = null,
    var imageUrls: ArrayList<Image> = arrayListOf(),
    var videos: ArrayList<Video> = arrayListOf()
) {
    data class Image(
        val id: Int,
        val url: String
    )
    data class Video(
        val id: Int,
        var coverUrl: String,
        var previewUrl: String,
        var isUnlocked: Boolean
    )
}
