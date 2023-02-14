package com.example.dd2composetest.data.bean

data class TopicData(
    var totalCount: Int = 0,
    var items: List<TopicItem> = arrayListOf()
)

data class TopicItem(
    val id: Int = 0,
    val name: String = "",
    var authorImageUrl: String = "", // no use?
    var authorId: Int = 0, // no use?
    var favoriteCount: Int = 0,
    val user: User = User(), // 6-4, 10 沒用到
    var videos: List<TopicVideos> = listOf(),
    val likeCount: Int = 0,
    var playCount: Int = 0,
    val videoCount: Int = 0,
//    val coverUrl: Int = 0,
    val coverUrl: String = "",
    var isQuality: Boolean = false,
    var isUnlocked: Boolean = false,
    val videoUserCount: Int = 0,
    val creationDate: String = "",
    var isLastSeen: Boolean = false,
    val creationDateTime: String = ""
)

data class TopicVideos(
//    val coverUrl: Int = 0,
    val coverUrl: String = "",
    val userImageUrl: String = ""
)
