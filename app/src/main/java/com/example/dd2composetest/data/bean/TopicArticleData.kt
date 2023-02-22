package com.example.dd2composetest.data.bean

data class TopicArticleData(
    val articles: List<TopicArticleItem>,
    val totalCount: Int
)

data class TopicArticleItem(
    var id: Int = 0,
    var title: String = "",
    var content: String = "",
//    var imageUrls: List<Int> = listOf(),
    var imageUrls: List<String>? = listOf(),
    var videos: ArrayList<MyArticleVideo>? = arrayListOf(),
    var favoriteCount: Int = 0,
    var likeCount: Int = 0,
    var commentCount: Int = 0,
    var isQuality: Boolean = false,
    var tags: java.util.ArrayList<String> = arrayListOf(),
    var creationDate: String = "",
    // 我的文章只用到以上


    var isFavorite: Boolean = false,
    var isLiked: Boolean = false,
    var isUnlocked: Boolean = false,
    var unlockCount: Int = 0,
    var user: User = User(),
    var isMyArticle: Boolean = false,
    val coverUrl: String = "",
    var hasVideo: Boolean = false,
    val unlockDateTime: String = "",
    val favoriteDate: String = "",//收藏日期
    val isLike: Boolean = false,
    var checked: Boolean = false
)

data class MyArticleVideo(
//    var coverUrl: Int = 0,
    var coverUrl: String = "",
    var playCount: Int = 0,
    var isUnlocked: Boolean = false
)

// article/getArticle 回傳資料
data class TopicArticleDetailItem(
    var user: User = User(),
    var questionCount: Int = 0,
    var images: ArrayList<EditArticleData.Image> = arrayListOf(),
    var videos: ArrayList<EditArticleData.Video>? = arrayListOf(),
    var id: Int = 0,
    var title: String = "",
    var content: String = "",
    var favoriteCount: Int = 0,
    var likeCount: Int = 0,
    var commentCount: Int = 0,
    var unlockCount: Int = 0,
    var isUnlocked: Boolean = false,
    var isQuality: Boolean = false,
    var isLiked: Boolean = false,
    var isFavorite: Boolean = false,
    var tags: ArrayList<String> = arrayListOf(),
    var creationDate: String = "",
    var isMyArticle: Boolean = false,
    var sexType: List<Int> = listOf(),
)

