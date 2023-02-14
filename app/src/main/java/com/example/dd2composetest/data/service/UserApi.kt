package com.example.dd2composetest.data.service

import com.example.dd2composetest.data.bean.*
import retrofit2.Response
import retrofit2.http.*
import java.io.File

interface UserApi {

    // 1-1. 检查注册邮箱
    @POST("account/isEmailAvailable")
    @FormUrlEncoded
    suspend fun isEmailAvailable(@Field("email") email: String)

    // 1-2. 用户注册
    @POST("account/register")
    @Multipart
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("nickName") nickName: String,
        @Field("sexOrientation") sexOrientation: Int,
        @Field("gender") gender: Int,
        @Field("deviceToken") deviceToken: String,
        @Field("tags") tags: Array<String>,
        @Field("image") image: File
    )

    // 1-3. 刷新Token
    @POST("account/refreshToken")
    @FormUrlEncoded
    suspend fun refreshToken()

    // 1-4. 用户登入
    @POST("account/login")
    @FormUrlEncoded
    suspend fun login()

    // 1-5. 忘记密码-获取验证码
    @POST("account/forgetPassword")
    @FormUrlEncoded
    suspend fun forgetPassword()

    // 1-6. 忘记密码-重置密码
    @POST("account/resetPassword")
    @FormUrlEncoded
    suspend fun resetPassword()

    // 1-7. 获取当前用户资料
    @POST("user/getDetail")
//    @FormUrlEncoded
    suspend fun getDetail(
        @Header("Authorization") token: String,
    ): Response<APIResponse<UserData>>

    // 1-8. 修改用户信息
    @POST("user/update")
    @FormUrlEncoded
    suspend fun update()

    // 1-9. 修改用户头像
    @POST("user/updateImage")
    @Multipart
    suspend fun updateImage()

    // 1-10. 用户关注/取消关注
    @POST("user/follow")
    @FormUrlEncoded
    suspend fun follow()

    // 1-11. 用户喜欢/取消喜欢
    @POST("user/like")
    @FormUrlEncoded
    suspend fun like()

    // 1-12. 获取我收藏的视频列表
    @POST("user/getMyFavoriteVideos")
    @FormUrlEncoded
    suspend fun getMyFavoriteVideos()

    // 1-13. 获取我的作品（视频）列表
    @POST("user/getMyVideos")
    @FormUrlEncoded
    suspend fun getMyVideos()

    // 1-14. 获取当前用户置顶视频列表
    @POST("user/getMyTopVideos")
    @FormUrlEncoded
    suspend fun getMyTopVideos()

    // 1-15. 获取其他用户的置顶视频列表
    @POST("user/getUserTopVideos")
    @FormUrlEncoded
    suspend fun getUserTopVideos()

    // 1-16. 用户置顶/取消置顶视频
    @POST("user/topVideo")
    @FormUrlEncoded
    suspend fun topVideo()

    // 1-17. 获取其他用户详情信息
    @POST("user/getUserInfo")
    @FormUrlEncoded
    suspend fun getUserInfo()

    // 1-18. 获取我关注的用户列表
    @POST("user/getMyFollows")
    @FormUrlEncoded
    suspend fun getMyFollows()

    // 1-19. 获取我收藏的求片主题列表
    @POST("user/getMyFavoriteRequestTopics")
    @FormUrlEncoded
    suspend fun getMyFavoriteRequestTopics()

    // 1-20. 获取我的作品（求片主题）列表
    @POST("user/getMyRequestTopics")
    @FormUrlEncoded
    suspend fun getMyRequestTopics()

    // 1-21. 获取我的正在转码处理的视频总数
    @POST("user/getMyTranscodingCount")
    @FormUrlEncoded
    suspend fun getMyTranscodingCount()

    // 1-22. 获取用户视频列表
    @POST("user/getUserVideos")
    @FormUrlEncoded
    suspend fun getUserVideos()

    // 1-23. 获取用户求片主题列表
    @POST("user/getUserRequestTopics")
    @FormUrlEncoded
    suspend fun getUserRequestTopics()

    // 1-24. 获取当前用户金币红币账户余额信息
    @POST("user/getBalance")
    @FormUrlEncoded
    suspend fun getBalance()

    // 1-25. 获取用户分享主题列表
    @POST("user/getUserShareTopics")
    @FormUrlEncoded
    suspend fun getUserShareTopics()

    // 1-26. 获取我收藏的分享主题列表
    @POST("user/getMyFavoriteShareTopics")
    @FormUrlEncoded
    suspend fun getMyFavoriteShareTopics()

    // 1-27. 获取我的作品（分享主题）列表
    @POST("user/GetMyShareTopics")
    @FormUrlEncoded
    suspend fun getMyShareTopics()

    // 1-28. 获取我观看过（解锁）影片列表
    @POST("user/getMyPlayedVideos")
    @FormUrlEncoded
    suspend fun getMyPlayedVideos()

    // 1-29. 获取admin设置注册使用的Tag列表
    @POST("account/getRegisterTags")
    @FormUrlEncoded
    suspend fun getRegisterTags()

    // 1-30. 用户注销账号
    @POST("account/inactivate")
    @FormUrlEncoded
    suspend fun inactivate()

    // 1-31. 检查当前用户主动联系客服开启状态
//    @POST("user/canSendCSMessage")
//    suspend fun canSendCSMessage(
//        @Header("Authorization") token: String,
//    ): Response<APIResponse<RongMessageData>>

    // 1-32. 获取我的文章列表
    @POST("user/getMyArticles")
    @FormUrlEncoded
    suspend fun getMyArticles()

    // 1-33. 获取我收藏的文章列表
    @POST("account/getMyfavoriteArticles")
    @FormUrlEncoded
    suspend fun getMyFavoriteArticles()

    // 1-34. 获取我看过的文章列表
    @POST("user/getMyReadArticles")
    @FormUrlEncoded
    suspend fun getMyReadArticles()

    // 1-35. 获取我的提问列表
    @POST("user/getMyQuestions")
    @FormUrlEncoded
    suspend fun getMyQuestions()

    // 1-36. 获取我收藏的提问列表
    @POST("user/getMyFavoriteQuestions")
    @FormUrlEncoded
    suspend fun getMyFavoriteQuestions()

    // 1-37. 修改邮箱-获取验证码
    @POST("account/modifyEmail")
    @FormUrlEncoded
    suspend fun modifyEmail()

    // 1-38. 修改用户邮箱
    @POST("account/updateEmail")
    @FormUrlEncoded
    suspend fun updateEmail()

    // 1-39. 获取用户的文章列表
    @POST("user/getUserArticles")
    @FormUrlEncoded
    suspend fun getUserArticles()

    // 1-40. 获取用户的提问列表
    @POST("user/getUserQuestions")
    @FormUrlEncoded
    suspend fun getUserQuestions()

    // 1-41. 检查当前用户是否已注册过TZ
    @POST("user/checkUserRegistered")
    @FormUrlEncoded
    suspend fun checkUserRegistered()

    // 1-42. 获取我的粉丝列表
    @POST("user/getMyFans")
    @FormUrlEncoded
    suspend fun getMyFans()

    // 1-43. 获取喜欢我的用户列表
    @POST("user/getLikeMes")
    @FormUrlEncoded
    suspend fun getLikeMes()

    // 1-44. 获取我关注的用户的视频
    @POST("user/getMyFollowedUserVideos")
    @FormUrlEncoded
    suspend fun getMyFollowedUserVideos()

    // 1-45. 获取我关注的用户的主题
    @POST("user/getMyFollowedUserTopics")
    @FormUrlEncoded
    suspend fun getMyFollowedUserTopics()

    // 1-46. 获取我关注的用户的文章
    @POST("user/getMyFollowedUserArticles")
    @FormUrlEncoded
    suspend fun getMyFollowedUserArticles()

    // 47 48 49 Page 為必填
    // 1-47 获取我关注的用户新上传的、以及我收藏的主题新加入的视频列表
    @POST("User/GetMyFollowingVideos")
    @FormUrlEncoded
    suspend fun getMyFollowingVideos(
        @Header("Authorization") token: String,
        @Field("From") date: String,
        @Field("Page") page: Int,
        @Field("PageSize") pageSize: Int
    ): Response<APIResponse<MyFollowingVideoList>>

    // 1-48 获取我关注的用户的主题
    @POST("User/GetMyFollowingTopics")
    @FormUrlEncoded
    suspend fun getMyFollowingTopics(
        @Header("Authorization") token: String,
        @Field("From") date: String,
        @Field("Page") page: Int,
        @Field("PageSize") pageSize: Int
    ): Response<APIResponse<MyFollowingTopicList>>

    // 1-49 获取我关注的用户的文章
    @POST("/User/GetMyFollowingArticles")
    @FormUrlEncoded
    suspend fun getMyFollowingArticles(
        @Header("Authorization") token: String,
        @Field("From") date: String,
        @Field("Page") page: Int,
        @Field("PageSize") pageSize: Int
    ): Response<APIResponse<MyFollowingArticleList>>

    // 1-50. 获取我关注的最新的视频、主题以及文章分别的总数量以及最新的一项
    @POST("User/GetMyFollowingLatest")
    @FormUrlEncoded
    suspend fun getMyFollowingLatest(
        @Header("Authorization") token: String,
        @Field("VideoFrom") videoFrom: String,
        @Field("TopicFrom") topicFrom: String,
        @Field("ArticleFrom") articleFrom: String
    ): Response<APIResponse<MyFollowingLatest>>

    // 1-51. 用户近7天红币收入的统计数据
    @POST("/User/GetRedCoinRevenueTrendDaily")
    @FormUrlEncoded
    suspend fun getRedCoinRevenueTrendDaily(
        @Header("Authorization") token: String,
        @Field("Date") date: String,
    ): Response<APIResponse<Trend>>

    // 1-52. 用户近7週红币收入的统计数据
    @POST("/User/GetRedCoinRevenueTrendWeekly")
    @FormUrlEncoded
    suspend fun getRedCoinRevenueTrendWeekly(
        @Header("Authorization") token: String,
        @Field("Date") date: String,
    ): Response<APIResponse<Trend>>

    // 1-53. 用户近7個月红币收入的统计数据
    @POST("/User/GetRedCoinRevenueTrendMonthly")
    @FormUrlEncoded
    suspend fun getRedCoinRevenueTrendMonthly(
        @Header("Authorization") token: String,
        @Field("Date") date: String,
    ): Response<APIResponse<Trend>>

    // 1-54. 用户近7天视频被解锁次数的统计数据
    @POST("/User/GetVideoUnlockTrendDaily")
    @FormUrlEncoded
    suspend fun getVideoUnlockTrendDaily(
        @Header("Authorization") token: String,
        @Field("Date") date: String,
    ): Response<APIResponse<Trend>>

    // 1-55. 用户近7週视频被解锁次数的统计数据
    @POST("/User/GetVideoUnlockTrendWeekly")
    @FormUrlEncoded
    suspend fun getVideoUnlockTrendWeekly(
        @Header("Authorization") token: String,
        @Field("Date") date: String,
    ): Response<APIResponse<Trend>>

    // 1-56. 用户近7個月视频被解锁次数的统计数据
    @POST("/User/GetVideoUnlockTrendMonthly")
    @FormUrlEncoded
    suspend fun getVideoUnlockTrendMonthly(
        @Header("Authorization") token: String,
        @Field("Date") date: String,
    ): Response<APIResponse<Trend>>
    //
    // 1-57. 用户近7天文章被解锁次数的统计数据
    @POST("/User/GetArticleUnlockTrendDaily")
    @FormUrlEncoded
    suspend fun getArticleUnlockTrendDaily(
        @Header("Authorization") token: String,
        @Field("Date") date: String,
    ): Response<APIResponse<Trend>>

    // 1-58. 用户近7周文章被解锁次数的统计数据
    @POST("/User/GetArticleUnlockTrendWeekly")
    @FormUrlEncoded
    suspend fun getArticleUnlockTrendWeekly(
        @Header("Authorization") token: String,
        @Field("Date") date: String,
    ): Response<APIResponse<Trend>>

    // 1-59. 用户近7个月文章被解锁次数的统计数据
    @POST("/User/GetArticleUnlockTrendMonthly")
    @FormUrlEncoded
    suspend fun getArticleUnlockTrendMonthly(
        @Header("Authorization") token: String,
        @Field("Date") date: String,
    ): Response<APIResponse<Trend>>

    // 1-60. 用户近7天主题被解锁次数的统计数据
    @POST("/User/GetTopicUnlockTrendDaily")
    @FormUrlEncoded
    suspend fun getTopicUnlockTrendDaily(
        @Header("Authorization") token: String,
        @Field("Date") date: String,
    ): Response<APIResponse<Trend>>

    // 1-61. 用户近7周主题被解锁次数的统计数据
    @POST("/User/GetTopicUnlockTrendWeekly")
    @FormUrlEncoded
    suspend fun getTopicUnlockTrendWeekly(
        @Header("Authorization") token: String,
        @Field("Date") date: String,
    ): Response<APIResponse<Trend>>

    // 1-62. 用户近7个月主题被解锁次数的统计数据
    @POST("/User/GetTopicUnlockTrendMonthly")
    @FormUrlEncoded
    suspend fun getTopicUnlockTrendMonthly(
        @Header("Authorization") token: String,
        @Field("Date") date: String,
    ): Response<APIResponse<Trend>>
}