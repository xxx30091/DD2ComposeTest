package com.example.dd2composetest.data.bean

data class TopicAsk(
    var totalCount: Int = 0,
    var items: List<TopicAskItem> = arrayListOf()
)

data class TopicAskItem (
    var id: Int = 0,
    var name: String = "",
    var coverUrl: String = "",
//    var coverUrl: Int = 0,
    var favoriteCount: Int = 0,
    var videoCount: Int = 0, // 目前 api 沒有傳這個值回來
    var creationDateTime: String = ""
)
