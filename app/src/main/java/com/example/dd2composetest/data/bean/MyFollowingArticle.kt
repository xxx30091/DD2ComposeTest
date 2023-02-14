package com.example.dd2composetest.data.bean

data class MyFollowingArticle(
    val totalCount: Int = 0,
    val item: ArticleItem?
)

data class MyFollowingArticleList(
    val totalCount: Int = 0,
    val items: List<ArticleItem> = listOf()
)
