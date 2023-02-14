package com.example.dd2composetest.data.bean

data class MyFollowingVideo(
    val totalCount: Int = 0,
    val item: VideoItem?
)

data class MyFollowingVideoList(
    var totalCount: Int = 0,
    var items: List<VideoItem> = listOf()
)

data class VideoItem(
    val id: Int = 0,
    val title: String = "",
    val user: User,
    val coverUrl: String = "",
    val previewUrl: String = "",
    val duration: Int = 0,
    val likeCount: Int = 0,
    val playCount: Int = 0,
    val favoriteCount: Int = 0,
    var isUnlocked: Boolean = false,
    val creationDate: String = "",
    var isLastSeen: Boolean = false
)