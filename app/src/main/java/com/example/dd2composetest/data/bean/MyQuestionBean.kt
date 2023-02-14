package com.example.dd2composetest.data.bean

data class MyQuestionBean(
    val totalCount: Int,
    val questions: List<MyQuestion>
)

data class MyQuestion(
    val id: Int = 0,
    val content: String = "",
    val favoriteCount: Int = 0,
    val answerCount: Int = 0,
    var creationDate: String? = "",
    // 我的作品只用到以上5個
    var isAnswered: Boolean? = false, //当前用户是否已回答
    var favoriteDate: String? = "",
    var qualityAnswer: QualityAnswer? = null,
    var user: User? = null
)

data class QualityAnswer(
    var title: String = "",
    var likeCount: Int = 0,
    var unlockCount: Int = 0,
    var imageUrl: String = "",
    var coverUrl: String = ""
)

// 我的收藏
//{"id":62,
//    "content":"性与爱可以分离么？",
//    "user":{
//        "id":28,
//        "nickName":"牵着蜗牛去散步i",
//        "imageUrl":"https://dd2-content.dev-st.ax2tw.net/image/a3fb1b07c9b841f2a98586478ac8c068.jpg?Expires=1676357335&Signature=FiAVLBFqEWZNjwGM61~VZrjnXato6hIUWaMRgAD0tOpAdXLBRSMOTju3Y83UhTYcz-Z7QVfW6dj8SNt7ipggdzW9pEKawYsVjGSjgahIvr~vv6LvXcW3T2M67udGkDnMALLYJESrGIO6Oh1sNRbEMsVk-D2MDp0xCA6guPFfRieHCp4nOhDSVDRuMlSAL9-ERAhIxfzCXQBgttvanb1drbcPYu64mIBt7hnWXlowQPOFqE6aJvILcyzSRSvdfyE~CABW8qPTcMoDmrGqu7MXqo4vSWIIRuQjk7XtVMzco7AYmMs8mA1hALJuCUeU9j9Disg0p0s-GOlcDg7kVAL35g__&Key-Pair-Id=KN12ZFNTZFF71"
//           },
//    "favoriteCount":1,
//    "answerCount":1,
//    "isAnswered":false,
//    "favoriteDate":"2022-03-24"
//}