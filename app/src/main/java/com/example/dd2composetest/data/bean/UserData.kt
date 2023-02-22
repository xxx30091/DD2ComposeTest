package com.example.dd2composetest.data.bean

data class UserData(
    var id: Int = 0,
    var imageUrl:String = "",
    var nickName:String = "",
    var gender: Int = 0, // 1 -> 男, 2 -> 女
    var sexOrientation: Int = 0, // 1 -> 同性, 2 -> 異性, 3 -> 通殺
    var goldCoinAmount: Int = 0,
    var redCoinAmount: Int = 0,
    var topicCount: Int = 0,
    var videoCount: Int = 0,
    var favoriteVideoCount: Int = 0,
    var favoriteTopicCount: Int = 0,
    var followerCount: Int = 0,
    var followingUserCount: Int = 0,
    var videoLikeCount: Int = 0,
    var userLikeCount: Int = 0,
    var planExpirationDateTime: String? = null,
    var hasNewSystemMessage: Boolean = false,
    var email:String = "",
    var tags: ArrayList<String> = arrayListOf(),
    var rongCloudToken: String? = ""
)

data class User(
    val id: Int = 0,
    val nickName: String = "",
    val imageUrl: String = "",
    val isFollowed: Boolean = false // article/getArticle
)

data class UserTags(
    val tags: List<String> = listOf()
)
