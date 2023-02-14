package com.example.dd2composetest.data.bean

data class ArticleData(
    var totalCount: Int = 0,
    var items: List<ArticleItem> = listOf()
)

data class ArticleItem(
    val id: Int = 0,
    val title: String = "",
    val content: String = "",
    val imageUrls: List<String> = listOf(),
    val user: User = User(),
    val favoriteCount: Int = 0,
    val likeCount: Int = 0,
    val commentCount: Int = 0,
    var unlockCount: Int = 0,
    var isUnlocked: Boolean = false,
    val isQuality: Boolean = false,
    var isLiked: Boolean = false,
    val isFavorite: Boolean = false,
    val tags: List<String> = listOf(),
    val videos: List<ArticleVideos> = listOf(),
    val creationDate: String = "",
    val isMyArticle: Boolean = false,
    var isLastSeen: Boolean = false
)

data class ArticleVideos(
    val coverUrl: String = "",
    val playCount: Int = 0,
    val isUnlocked: Boolean = false,
)