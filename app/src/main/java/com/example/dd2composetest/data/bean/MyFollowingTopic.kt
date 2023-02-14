package com.example.dd2composetest.data.bean

data class MyFollowingTopic(
    val totalCount: Int = 0,
    val item: TopicItem?
)

data class MyFollowingTopicList(
    val totalCount: Int = 0,
    val items: List<TopicItem> = listOf()
)
