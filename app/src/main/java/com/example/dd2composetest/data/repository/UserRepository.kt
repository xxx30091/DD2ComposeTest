package com.example.dd2composetest.data.repository

import com.example.dd2composetest.data.service.UserApi
import retrofit2.Retrofit

class UserRepository(retrofit: Retrofit) {
    private val service = retrofit.create(UserApi::class.java)

    suspend fun getMyFollowingVideos(token: String, date: String, page: Int, pageSize: Int) =
        service.getMyFollowingVideos(token, date, page, pageSize)

    suspend fun getMyFollowingTopics(token: String, date: String, page: Int, pageSize: Int) =
        service.getMyFollowingTopics(token, date, page, pageSize)

    suspend fun getMyFollowingArticles(token: String, date: String, page: Int, pageSize: Int) =
        service.getMyFollowingArticles(token, date, page, pageSize)

    suspend fun getMyFollowingLatest(token: String, videoFrom: String, topicFrom: String, articleFrom: String) =
        service.getMyFollowingLatest(token, videoFrom, topicFrom, articleFrom)

    suspend fun getDetail(token: String) =
        service.getDetail(token)

//    suspend fun canSendCSMessage(token: String) =
//        service.canSendCSMessage(token)

    suspend fun getRedCoinRevenueTrendDaily(
        token: String, date: String,
    ) = service.getRedCoinRevenueTrendDaily(token, date)

    suspend fun getRedCoinRevenueTrendWeekly(
        token: String, date: String,
    ) = service.getRedCoinRevenueTrendWeekly(token, date)

    suspend fun getRedCoinRevenueTrendMonthly(
        token: String, date: String,
    ) = service.getRedCoinRevenueTrendMonthly(token, date)

    suspend fun getVideoUnlockTrendDaily(
        token: String, date: String,
    ) = service.getVideoUnlockTrendDaily(token, date)

    suspend fun getVideoUnlockTrendWeekly(
        token: String, date: String,
    ) = service.getVideoUnlockTrendWeekly(token, date)

    suspend fun getVideoUnlockTrendMonthly(
        token: String, date: String,
    ) = service.getVideoUnlockTrendMonthly(token, date)

    suspend fun getArticleUnlockTrendDaily(
        token: String, date: String,
    ) = service.getArticleUnlockTrendDaily(token, date)

    suspend fun getArticleUnlockTrendWeekly(
        token: String, date: String,
    ) = service.getArticleUnlockTrendWeekly(token, date)

    suspend fun getArticleUnlockTrendMonthly(
        token: String, date: String,
    ) = service.getArticleUnlockTrendMonthly(token, date)

    suspend fun getTopicUnlockTrendDaily(
        token: String, date: String,
    ) = service.getTopicUnlockTrendDaily(token, date)

    suspend fun getTopicUnlockTrendWeekly(
        token: String, date: String,
    ) = service.getTopicUnlockTrendWeekly(token, date)

    suspend fun getTopicUnlockTrendMonthly(
        token: String, date: String,
    ) = service.getTopicUnlockTrendMonthly(token, date)
}