package com.example.dd2composetest.data.mock

import com.example.dd2composetest.data.bean.*

class MockData {



    companion object {
        val url1 = "https://i1.jueshifan.com/2f5221d51dedbc4f9f/205a28/7c077a81/204328d017a8fa4d8c26.png"

        //    val url2 = "https://dd2-content.dev-st.ax2tw.net/video/cover/2020/01/22/143409312-1ea86deb209744ca8865b608b002542f.jpeg?Expires=1660726345&Signature=C83E~cGf2Dhrg24fQePJnEW7HzGAnaHm2NvU~WHgY5-SeHKGe-Lw3QhVH9kC98O0yvCFm0rjf28Jw0LRaFA-QpDGyuKyBRQ4dHywknbWb5KDLh1eTPsJNLhInnrlHION6MkGHlx3YsldNh2l1itvkAIrikMcS7cu6lyy-ZTEUyGtJc41b~OPf-ZINbk-4OciYNv8sn9kjFXVd2SPpePhouCeNawelTNFpY0CR-QrmmHJjrtEb0WiRkBvIlEHvgHXxnmnYQYD4njN2nnXcFCr82vTXyyCDqWC~01zvY9hsOtbQGxoWVpcV6l2wYeEXTrYJSsteaGwvXu~haMWjS5wXA__&Key-Pair-Id=KN12ZFNTZFF71"
//    val url3 = "https://dd2-content.dev-st.ax2tw.net/video/cover/2022/06/06/095715702-169acc8c118449a6ae1994c5d3c60d73.jpg?Expires=1660726345&Signature=o6kk4hLiXXEzgGFnZm4mlv2thGUk5jXhPQ8HWR9DIg0QgfLdQbLRIpFRr8Vf4jcGKTOfL12y0VsFzonKXyQ~mhzFhMPfEv50QK8cSKVqmgax2AXznOn0NBB2k2I840lmgQYwAhebVdKK3nZ9ciZ7BLJus-HK42aswt3xPu2E0MgHEgx799fJuY8kYbM4Rx6ZydwIQJAMYyTVOLlb5WlNaO~K1tIbpG3ZDO3VEsCXcWfPrV~gk~4EHdoaC-NGpN-7fSp4YKlbedM-CbCeB8c8H3pxq1x5NLGbbZPTzlRznE1SvM4pOWM8B3dIM5B2usOmSNWCMX830zHEk8KBw1~CyQ__&Key-Pair-Id=KN12ZFNTZFF71"
        val url2 = "https://media.istockphoto.com/photos/happy-shiba-inu-dog-on-yellow-redhaired-japanese-dog-smile-portrait-picture-id1197121742?k=20&m=1197121742&s=612x612&w=0&h=HX4DoFCL1RDlegj3P9w4O2H64sgwKvMP0VSki7sBEtE="
        val url3 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSBudgZFJVI2VWPK8MDOFqmlcOzkyRoEkmq5bfit7O8kCB-58NAaAk1lMPpUb3ND72mlGU&usqp=CAU"
        val url4 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSG-Gh8kWM80mbSJli32sL26KpxuinRm_KQyg&usqp=CAU"
        val url5 = "https://cdn2.ettoday.net/images/413/d413480.jpg"
        val url6 = "https://www.twreporter.org/images/20220325174929-4e7afb26ee06fde2884862a84ceb9703-mobile.jpg"
        val url7 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRtvFCbJ-DNkdnWuEKes19HDiLYHpMXeXercPlVaouroksfW6jDJ2m9lFAR12Dqr2S25cc&usqp=CAU"
        val url8 = "https://www.animal-ethics.org/wp-content/uploads/new-veganism-section-thumb.jpg"
        val url9 = "https://media.nownews.com/nn_media/thumbnail/2021/04/1618278792552-ec17499ce00c41c4b582666a63fa32da-800x533.jpg?unShow=false"

        // my work
        fun getMockMyVideos() = listOf(
            MyVideoBean(
                id = 0,
                title = "Video_1",
                coverUrl = url1,
                likeCount = 12,
                playCount = 36,
                creationDateTime = "2023-06-06 12:24",
                duration = 69,
                isUnlocked = true,
                creationDate = "2023-06-06",
            ),
            MyVideoBean(
                id = 1,
                title = "Video_2",
                coverUrl = url2,
                likeCount = 51,
                playCount = 72,
                creationDateTime = "2022-03-06 12:24",
                duration = 99,
                isUnlocked = false,
                creationDate = "2022-03-06",
            ),
            MyVideoBean(
                id = 2,
                title = "Video_3",
                coverUrl = url3,
                likeCount = 21,
                playCount = 18,
                creationDateTime = "2023-06-06 12:24",
                duration = 66,
                isUnlocked = true,
                creationDate = "2023-06-06",
            ),
            MyVideoBean(
                id = 3,
                title = "Video_4",
                coverUrl = url4,
                likeCount = 12,
                playCount = 36,
                creationDateTime = "2023-06-06 12:24",
                duration = 69,
                isUnlocked = false,
                creationDate = "2023-06-06",
            ),
            MyVideoBean(
                id = 4,
                title = "Video_5",
                coverUrl = url5,
                likeCount = 51,
                playCount = 72,
                creationDateTime = "2022-03-06 12:24",
                duration = 99,
                isUnlocked = false,
                creationDate = "2022-03-06",
            ),
            MyVideoBean(
                id = 5,
                title = "Video_6",
                coverUrl = url6,
                likeCount = 21,
                playCount = 18,
                creationDateTime = "2023-06-06 12:24",
                duration = 66,
                isUnlocked = true,
                creationDate = "2023-06-06",
            ),
            MyVideoBean(
                id = 6,
                title = "Video_7",
                coverUrl = url7,
                likeCount = 12,
                playCount = 36,
                creationDateTime = "2023-06-06 12:24",
                duration = 69,
                isUnlocked = false,
                creationDate = "2023-06-06",
            ),
            MyVideoBean(
                id = 7,
                title = "Video_8",
                coverUrl = url8,
                likeCount = 51,
                playCount = 72,
                creationDateTime = "2022-03-06 12:24",
                duration = 99,
                isUnlocked = false,
                creationDate = "2022-03-06",
            ),
            MyVideoBean(
                id = 8,
                title = "Video_9",
                coverUrl = url9,
                likeCount = 21,
                playCount = 18,
                creationDateTime = "2023-06-06 12:24",
                duration = 66,
                isUnlocked = true,
                creationDate = "2023-06-06",
            )
        )

        fun getMockMyTopVideos() = listOf(
            MyVideoBean(
                id = 3,
                title = "Video_4",
                coverUrl = url4,
                likeCount = 12,
                playCount = 36,
                creationDateTime = "2023-06-06 12:24",
                duration = 69,
                isUnlocked = false,
                creationDate = "2023-06-06",
            ),
            MyVideoBean(
                id = 4,
                title = "Video_5",
                coverUrl = url5,
                likeCount = 51,
                playCount = 72,
                creationDateTime = "2022-03-06 12:24",
                duration = 99,
                isUnlocked = false,
                creationDate = "2022-03-06",
            ),
            MyVideoBean(
                id = 5,
                title = "Video_6",
                coverUrl = url6,
                likeCount = 21,
                playCount = 18,
                creationDateTime = "2023-06-06 12:24",
                duration = 66,
                isUnlocked = true,
                creationDate = "2023-06-06",
            ),
            MyVideoBean(
                id = 6,
                title = "Video_7",
                coverUrl = url7,
                likeCount = 12,
                playCount = 36,
                creationDateTime = "2023-06-06 12:24",
                duration = 69,
                isUnlocked = false,
                creationDate = "2023-06-06",
            ),
            MyVideoBean(
                id = 7,
                title = "Video_8",
                coverUrl = url8,
                likeCount = 51,
                playCount = 72,
                creationDateTime = "2022-03-06 12:24",
                duration = 99,
                isUnlocked = false,
                creationDate = "2022-03-06",
            ),
            MyVideoBean(
                id = 8,
                title = "Video_9",
                coverUrl = url9,
                likeCount = 21,
                playCount = 18,
                creationDateTime = "2023-06-06 12:24",
                duration = 66,
                isUnlocked = true,
                creationDate = "2023-06-06",
            )
        )

        fun getMockMyTopics() = listOf(
            TopicItem(
                id = 0,
                name = "Topic_1",
                videos = listOf(
                    TopicVideos(coverUrl = url6, userImageUrl = ""),
                    TopicVideos(coverUrl = url7, userImageUrl = ""),
                    TopicVideos(coverUrl = url8, userImageUrl = ""),
                    TopicVideos(coverUrl = url9, userImageUrl = ""),
                ),
                playCount = 36,
                videoCount = 3,
                likeCount = 12,
                videoUserCount = 9,
                creationDateTime = "2023-06-06 12:24"
            ),
            TopicItem(
                id = 1,
                name = "Topic_2",
                videos = listOf(
                    TopicVideos(coverUrl = url1, userImageUrl = ""),
                    TopicVideos(coverUrl = url2, userImageUrl = ""),
                    TopicVideos(coverUrl = url3, userImageUrl = ""),
                ),
                playCount = 36,
                videoCount = 3,
                likeCount = 12,
                videoUserCount = 3,
                creationDateTime = "2023-06-06 12:24"
            ),
            TopicItem(
                id = 2,
                name = "Topic_3",
                videos = listOf(
                    TopicVideos(coverUrl = url4, userImageUrl = ""),
                    TopicVideos(coverUrl = url5, userImageUrl = ""),
                ),
                playCount = 36,
                videoCount = 3,
                likeCount = 12,
                videoUserCount = 6,
                creationDateTime = "2023-06-06 12:24"
            ),
            TopicItem(
                id = 3,
                name = "Topic_4",
                videos = listOf(
                    TopicVideos(coverUrl = url6, userImageUrl = ""),
                    TopicVideos(coverUrl = url7, userImageUrl = ""),
                    TopicVideos(coverUrl = url8, userImageUrl = ""),
                    TopicVideos(coverUrl = url9, userImageUrl = ""),
                ),
                playCount = 36,
                videoCount = 3,
                likeCount = 12,
                videoUserCount = 12,
                creationDateTime = "2023-06-06 12:24"
            ),
            TopicItem(
                id = 4,
                name = "Topic_5",
                videos = listOf(
                    TopicVideos(coverUrl = url1, userImageUrl = ""),
                    TopicVideos(coverUrl = url2, userImageUrl = ""),
                    TopicVideos(coverUrl = url3, userImageUrl = ""),
                ),
                playCount = 36,
                videoCount = 3,
                likeCount = 12,
                videoUserCount = 21,
                creationDateTime = "2023-06-06 12:24"
            ),
            TopicItem(
                id = 5,
                name = "Topic_6",
                videos = listOf(
                    TopicVideos(coverUrl = url4, userImageUrl = ""),
                    TopicVideos(coverUrl = url5, userImageUrl = ""),
                ),
                playCount = 36,
                videoCount = 3,
                likeCount = 12,
                videoUserCount = 39,
                creationDateTime = "2023-06-06 12:24"
            ),
        )

        fun getMockMyMockAskTopics() = listOf(
            TopicAskItem(
                id = 1,
                name = "Request_Topic_1",
                coverUrl = url3,
                favoriteCount = 6,
                creationDateTime = "2023-01-31 11:04"
            ),
            TopicAskItem(
                id = 2,
                name = "Request_Topic_2",
                coverUrl = url6,
                favoriteCount = 3,
                creationDateTime = "2023-02-28 09:04"
            ),
            TopicAskItem(
                id = 3,
                name = "Request_Topic_3",
                coverUrl = url9,
                favoriteCount = 24,
                creationDateTime = "2023-03-31 11:04"
            ),
        )

        fun getMockMyArticles() = listOf<TopicArticleItem>(
            TopicArticleItem(
                id = 1,
                title = "My_Topic_1",
                content = "This is a 3-pictures article",
                imageUrls = listOf(url1, url2, url3),
                videos = arrayListOf(),
                favoriteCount = 12,
                likeCount = 0,
                commentCount = 9,
                isQuality = true,
                tags = arrayListOf("動物圖片", "毛孩", "柴犬"),
                creationDate = "2023-01-14"
            ),
            TopicArticleItem(
                id = 2,
                title = "My_Topic_2",
                content = "This is an article with 3-pictures and 1 locked video",
                imageUrls = listOf(url9, url6, url3),
                videos = arrayListOf(MyArticleVideo(coverUrl = url4, playCount = 33, isUnlocked = false)),
                favoriteCount = 12,
                likeCount = 999,
                commentCount = 9,
                isQuality = false,
                tags = arrayListOf("幼柴"),
                creationDate = "2023-02-14"
            ),
            TopicArticleItem(
                id = 3,
                title = "My_Topic_3",
                content = "This is a 2-pictures article",
                imageUrls = listOf(url5, url6),
                videos = arrayListOf(),
                favoriteCount = 12,
                likeCount = 0,
                commentCount = 9,
                isQuality = true,
                tags = arrayListOf("動物圖片", "企鵝", "貓咪"),
                creationDate = "2023-03-14"
            ),
            TopicArticleItem(
                id = 4,
                title = "My_Topic_4",
                content = "This is an article with 1 unlocked video",
                imageUrls = listOf(),
                videos = arrayListOf(MyArticleVideo(coverUrl = url7, playCount = 36, isUnlocked = true)),
                favoriteCount = 12,
                likeCount = 0,
                commentCount = 9,
                isQuality = true,
                tags = arrayListOf("森林之王", "霸氣"),
                creationDate = "2023-04-14"
            ),
            TopicArticleItem(
                id = 5,
                title = "My_Topic_5",
                content = "This is a one picture article",
                imageUrls = listOf(url8),
                videos = arrayListOf(),
                favoriteCount = 12,
                likeCount = 0,
                commentCount = 9,
                isQuality = true,
                tags = arrayListOf("動物圖片", "毛孩"),
                creationDate = "2023-05-14"
            ),
            TopicArticleItem(
                id = 6,
                title = "My_Topic_6",
                content = "This is a article without any picture and video",
                imageUrls = listOf(),
                videos = arrayListOf(),
                favoriteCount = 3,
                likeCount = 0,
                commentCount = 9,
                isQuality = false,
                tags = arrayListOf("動物圖片", "賣萌"),
                creationDate = "2023-06-14"
            ),
        )

        fun getMockMyQuestions() = listOf<MyQuestionItem>(
            MyQuestionItem(
                id = 1,
                content = "My_Question_1",
                favoriteCount = 9,
                answerCount = 3,
                creationDate = "2023-02-14"
            ),
            MyQuestionItem(
                id = 2,
                content = "My_Question_2",
                favoriteCount = 15,
                answerCount = 42,
                qualityAnswer = QualityAnswer(
                    title = "Quality Answer",
                    likeCount = 6,
                    unlockCount = 9,
                    coverUrl = url4
                ),
                creationDate = "2023-02-14"
            ),
            MyQuestionItem(
                id = 3,
                content = "My_Question_3",
                favoriteCount = 3,
                answerCount = 3,
                creationDate = "2023-02-14"
            ),
        )

        // edit article
        fun getMockEditArticle() = TopicArticleDetailItem(
            user = User(

            ),
            questionCount = 0,
            images = arrayListOf(
                EditArticleData.Image(
                    id = 1,
                    url = url1
                ),
                EditArticleData.Image(
                    id = 2,
                    url = url2
                ),
                EditArticleData.Image(
                    id = 4,
                    url = url4
                ),
            ),
            videos = arrayListOf(
                EditArticleData.Video(
                    id = 0,
                    coverUrl = url3,
                    previewUrl = "",
                    isUnlocked = true
                )
            ),
            id = 0,
            title = "動物圖片",
            content = "<article>氣候變遷帶來的災害...... \n  <image id=\"1\" />\n 這是一隻柴犬<video id=\"100\" />\n 這是也一隻柴犬" +
                    "<image id=\"2\" />\n 滿滿的柴<image id=\"4\" /></article>",
            favoriteCount = 99,
            likeCount = 999,
            commentCount = 69,
            unlockCount = 120,
            isUnlocked = false,
            isQuality = true,
            isLiked = false,
            isFavorite = false,
            tags = arrayListOf("柴犬" ,"有趣"),
            creationDate = "2023-02-20",
            isMyArticle = true,
            sexType = listOf(0, 1, 2)
        )

        fun getMockEditArticles() = listOf(
            TopicArticleDetailItem(
                user = User(),
                questionCount = 0,
                images = arrayListOf(
                    EditArticleData.Image(
                        id = 1,
                        url = url1
                    ),
                    EditArticleData.Image(
                        id = 2,
                        url = url2
                    ),
                    EditArticleData.Image(
                        id = 4,
                        url = url3
                    ),
                ),
                videos = arrayListOf(),
                id = 1,
                title = "My_Topic_1",
                content = "<article>This is a 3-pictures article \n \n1 <image id=\"1\" />\n 2 <image id=\"2\" />\n 3 <image id=\"3\" /></article>",
                favoriteCount = 12,
                likeCount = 0,
                commentCount = 9,
                unlockCount = 0,
                isUnlocked = false,
                isQuality = true,
                isLiked = false,
                isFavorite = false,
                tags = arrayListOf("動物圖片", "毛孩", "柴犬"),
                creationDate = "2023-01-14",
                isMyArticle = true,
                sexType = listOf(0)
            ),
            TopicArticleDetailItem(
                user = User(),
                questionCount = 0,
                images = arrayListOf(
                    EditArticleData.Image(
                        id = 9,
                        url = url9
                    ),
                    EditArticleData.Image(
                        id = 6,
                        url = url6
                    ),
                    EditArticleData.Image(
                        id = 3,
                        url = url3
                    ),
                ),
                videos = arrayListOf(
                    EditArticleData.Video(
                        id = 0,
                        coverUrl = url4,
                        previewUrl = url4,
                        isUnlocked = false
                    )
                ),
                id = 2,
                title = "My_Topic_2",
                content = "<article>This is an article with 3-pictures \n <image id=\"1\" />\n" +
                        " <image id=\"2\" />\n" +
                        " <image id=\"3\" /> \nand 1 locked video \n<video id=\"100\" />\n  </article>",
                favoriteCount = 12,
                likeCount = 999,
                commentCount = 9,
                unlockCount = 0,
                isUnlocked = false,
                isQuality = false,
                isLiked = false,
                isFavorite = false,
                tags = arrayListOf("幼柴", "小笨貓"),
                creationDate = "2023-02-14",
                isMyArticle = true,
                sexType = listOf(0, 1, 2)
            ),
            TopicArticleDetailItem(
                user = User(),
                questionCount = 0,
                images = arrayListOf(
                    EditArticleData.Image(
                        id = 5,
                        url = url5
                    ),
                    EditArticleData.Image(
                        id = 6,
                        url = url6
                    ),
                ),
                videos = arrayListOf(),
                id = 3,
                title = "My_Topic_3",
                content = "<article>This is a 2-pictures article \n <image id=\"5\" />\n" +
                        " <image id=\"6\" /> </article>",
                favoriteCount = 12,
                likeCount = 0,
                commentCount = 9,
                unlockCount = 0,
                isUnlocked = false,
                isQuality = true,
                isLiked = false,
                isFavorite = false,
                tags = arrayListOf("動物圖片", "企鵝", "貓咪"),
                creationDate = "2023-03-14",
                isMyArticle = true,
                sexType = listOf(1)
            ),
            TopicArticleDetailItem(
                user = User(),
                questionCount = 0,
                images = arrayListOf(),
                videos = arrayListOf(
                    EditArticleData.Video(
                        id = 0,
                        coverUrl = url7,
                        previewUrl = url7,
                        isUnlocked = true
                    )
                ),
                id = 4,
                title = "My_Topic_4",
                content = "<article>This is an article with 1 unlocked video \n <video id=\"100\" /> </article>",
                favoriteCount = 12,
                likeCount = 0,
                commentCount = 9,
                unlockCount = 0,
                isUnlocked = false,
                isQuality = true,
                isLiked = false,
                isFavorite = false,
                tags = arrayListOf("森林之王", "霸氣"),
                creationDate = "2023-04-14",
                isMyArticle = true,
                sexType = listOf(2)
            ),
            TopicArticleDetailItem(
                user = User(),
                questionCount = 0,
                images = arrayListOf(
                    EditArticleData.Image(
                        id = 8,
                        url = url8
                    ),
                ),
                videos = arrayListOf(),
                id = 5,
                title = "My_Topic_5",
                content = "<article>This is a one picture article \n <image id=\"5\" /> </article>",
                favoriteCount = 12,
                likeCount = 0,
                commentCount = 9,
                unlockCount = 0,
                isUnlocked = false,
                isQuality = true,
                isLiked = false,
                isFavorite = false,
                tags = arrayListOf("動物圖片", "毛孩"),
                creationDate = "2023-05-14",
                isMyArticle = true,
                sexType = listOf(0, 1)
            ),
            TopicArticleDetailItem(
                user = User(),
                questionCount = 0,
                images = arrayListOf(),
                videos = arrayListOf(),
                id = 6,
                title = "My_Topic_6",
                content = "<article>This is a article without any picture and video</article>",
                favoriteCount = 12,
                likeCount = 0,
                commentCount = 9,
                unlockCount = 0,
                isUnlocked = false,
                isQuality = true,
                isLiked = false,
                isFavorite = false,
                tags = arrayListOf("動物圖片", "賣萌"),
                creationDate = "2023-06-14",
                isMyArticle = true,
                sexType = listOf(1, 2)
            ),
        )


        fun getMockChooseVideoData() = listOf(
            ChooseVideoData(
                id = 1,
                title = "呆柴",
                previewUrl = url1,
                coverUrl = url1,
                duration = 90,
                playCount = 90,
                likeCount = 81,
                creationDateTime = "2023-02-22",
                isSelect = false
            ),
            ChooseVideoData(
                id = 2,
                title = "笑柴",
                previewUrl = url2,
                coverUrl = url2,
                duration = 90,
                playCount = 90,
                likeCount = 81,
                creationDateTime = "2023-02-22",
                isSelect = false
            ),
            ChooseVideoData(
                id = 3,
                title = "不可以色色",
                previewUrl = url3,
                coverUrl = url3,
                duration = 90,
                playCount = 90,
                likeCount = 81,
                creationDateTime = "2023-02-22",
                isSelect = false
            ),
            ChooseVideoData(
                id = 4,
                title = "萌柴",
                previewUrl = url4,
                coverUrl = url4,
                duration = 90,
                playCount = 90,
                likeCount = 81,
                creationDateTime = "2023-02-22",
                isSelect = false
            ),
            ChooseVideoData(
                id = 5,
                title = "企鵝",
                previewUrl = url5,
                coverUrl = url5,
                duration = 90,
                playCount = 90,
                likeCount = 81,
                creationDateTime = "2023-02-22",
                isSelect = false
            ),
            ChooseVideoData(
                id = 6,
                title = "想出來",
                previewUrl = url6,
                coverUrl = url6,
                duration = 90,
                playCount = 90,
                likeCount = 81,
                creationDateTime = "2023-02-22",
                isSelect = false
            ),
            ChooseVideoData(
                id = 7,
                title = "大貓",
                previewUrl = url7,
                coverUrl = url7,
                duration = 90,
                playCount = 90,
                likeCount = 81,
                creationDateTime = "2023-02-22",
                isSelect = false
            ),
            ChooseVideoData(
                id = 8,
                title = "小鴨",
                previewUrl = url8,
                coverUrl = url8,
                duration = 90,
                playCount = 90,
                likeCount = 81,
                creationDateTime = "2023-02-22",
                isSelect = false
            ),
            ChooseVideoData(
                id = 9,
                title = "小小貓",
                previewUrl = url9,
                coverUrl = url9,
                duration = 90,
                playCount = 90,
                likeCount = 81,
                creationDateTime = "2023-02-22",
                isSelect = false
            ),
            ChooseVideoData(
                id = 10,
                title = "呆柴",
                previewUrl = url1,
                coverUrl = url1,
                duration = 90,
                playCount = 90,
                likeCount = 81,
                creationDateTime = "2023-02-22",
                isSelect = false
            ),
            ChooseVideoData(
                id = 11,
                title = "笑柴",
                previewUrl = url2,
                coverUrl = url2,
                duration = 90,
                playCount = 90,
                likeCount = 81,
                creationDateTime = "2023-02-22",
                isSelect = false
            ),
            ChooseVideoData(
                id = 12,
                title = "不可以色色",
                previewUrl = url3,
                coverUrl = url3,
                duration = 90,
                playCount = 90,
                likeCount = 81,
                creationDateTime = "2023-02-22",
                isSelect = false
            ),
            ChooseVideoData(
                id = 13,
                title = "萌柴",
                previewUrl = url4,
                coverUrl = url4,
                duration = 90,
                playCount = 90,
                likeCount = 81,
                creationDateTime = "2023-02-22",
                isSelect = false
            ),
            ChooseVideoData(
                id = 14,
                title = "企鵝",
                previewUrl = url5,
                coverUrl = url5,
                duration = 90,
                playCount = 90,
                likeCount = 81,
                creationDateTime = "2023-02-22",
                isSelect = false
            ),
            ChooseVideoData(
                id = 15,
                title = "想出來",
                previewUrl = url6,
                coverUrl = url6,
                duration = 90,
                playCount = 90,
                likeCount = 81,
                creationDateTime = "2023-02-22",
                isSelect = false
            ),
            ChooseVideoData(
                id = 16,
                title = "大貓",
                previewUrl = url7,
                coverUrl = url7,
                duration = 90,
                playCount = 90,
                likeCount = 81,
                creationDateTime = "2023-02-22",
                isSelect = false
            ),
            ChooseVideoData(
                id = 17,
                title = "小鴨",
                previewUrl = url8,
                coverUrl = url8,
                duration = 90,
                playCount = 90,
                likeCount = 81,
                creationDateTime = "2023-02-22",
                isSelect = false
            ),
            ChooseVideoData(
                id = 18,
                title = "小小貓",
                previewUrl = url9,
                coverUrl = url9,
                duration = 90,
                playCount = 90,
                likeCount = 81,
                creationDateTime = "2023-02-22",
                isSelect = false
            ),
        )
    }

    val userUrl = "https://miro.medium.com/max/1400/1*FKlRYAU5z-74RYqsTYrOAQ@2x.png"

//    fun getFullScreenData(): List<FullScreenItem> {
//        return listOf(
//            FullScreenItem(id = 0, name = "呆柴1", imgUrl = url1, content = "瀑布流布局是一種新的佈局方式，項目可以自動匹配大小適應螢幕。"),
//            FullScreenItem(id = 1, name = "萌柴1", imgUrl = url2, content = "在RecycleView中，要呈現瀑布流樣式，需要用到StaggeredGridLayoutManager來實作。"),
//            FullScreenItem(id = 2, name = "不可以色色1", imgUrl = url3, content = "先上Acitvity"),
//            FullScreenItem(id = 3, name = "呆柴2", imgUrl = url1, content = "StaggeredGridLayoutManager其實跟之前的GridLayoutManager的用法一樣，要宣告spanCount以及方向。"),
//            FullScreenItem(id = 4, name = "萌柴2", imgUrl = url2, content = ""),
//            FullScreenItem(id = 5, name = "不可以色色2", imgUrl = url3, content = ""),
//            FullScreenItem(id = 6, name = "呆柴3", imgUrl = url1, content = ""),
//            FullScreenItem(id = 7, name = "萌柴3", imgUrl = url2, content = ""),
//            FullScreenItem(id = 8, name = "不可以色色3", imgUrl = url3, content = ""),
//        )
//    }

//    val myMockVideos =
//    val myMockTopics =
//    val myMockAskTopics =

}

data class FullScreenData(
    val item: List<FullScreenItem> = listOf()
)

data class FullScreenItem(
    val id: Int = 0,
    val name: String = "",
    val content: String = "",
    val imgUrl: String = ""
)