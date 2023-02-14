package com.example.dd2composetest.data.service

//import com.example.dd2composetest.BuildConfig
import com.google.gson.Gson

var URL_NEW = ""
var API_DOMAIN = "https://dd2-client-api.dev-st.ax2tw.net/"
//    when (BuildConfig.BUILD_TYPE) {
//    "st" -> "https://dd2-client-api.dev-st.ax2tw.net/"
//    "uat" -> "https://dd2-client-api.dev-uat.ax2tw.net/"
//    else -> "https://client.api.dd2.loveroot.club/"
//}

//var DEBUG: Boolean = BuildConfig.DEBUG


var GET_API_DOMAIN = "https://dd2-client-api.dev-st.ax2tw.net/"
//    when (BuildConfig.BUILD_TYPE) {
//    "st" -> "https://dd2-client-api.dev-st.ax2tw.net/"
//    "uat" -> "https://dd2-client-api.dev-uat.ax2tw.net/"
//    else -> "https://client.api.dd2.loveroot.club/"
//}

class ApiService {

    companion object {
        const val TAG = "ApiService"

        val gson = Gson()

        // user
        var REFRESH_TOKEN = "${URL_NEW}account/refreshToken"
        var LOGIN = "${URL_NEW}account/login"
        var REGISTER = "${URL_NEW}account/register"

        //        var CHECK_EMAIL = "${URL_NEW}account/canRegister"
        var CHECK_EMAIL = "${URL_NEW}account/isEmailAvailable"
        var USER_DETAIL = "${URL_NEW}user/getDetail"

        var USER_FOLLOWED_VIDEOS = "${URL_NEW}user/getMyFollowedUserVideos"
        var USER_FOLLOWED_TOPICS = "${URL_NEW}user/GetMyFollowedUserTopics"
        var USER_FOLLOWED_ARTICLES = "${URL_NEW}user/getMyFollowedUserArticles"

        var USER_RESETPASSWORD = "${URL_NEW}account/updatePassword"

        var UPDATE_DETAIL = "${URL_NEW}user/update"
        var UPDATE_USER_IMAGE = "${URL_NEW}user/updateImage"
        var USER_MY_VIDEO = "${URL_NEW}user/getMyVideos"
        var USER_MY_TOP_VIDEO = "${URL_NEW}user/getMyTopVideos"
        var USER_FAVORITE_VIDEO = "${URL_NEW}user/getMyFavoriteVideos"
        var GET_MY_TOPIC = "${URL_NEW}user/GetMyShareTopics"
        var GET_MY_ASK_TOPIC = "${URL_NEW}user/getMyRequestTopics"
        var GET_MY_FAVORITE_TOPIC = "${URL_NEW}user/getMyFavoriteShareTopics"
        var GET_MY_ASK_FAVORITE_TOPIC = "${URL_NEW}user/getMyFavoriteRequestTopics"
        var GET_USER_INFO = "${URL_NEW}user/getUserInfo"
        var USER_FOLLOW = "${URL_NEW}user/follow"
        var USER_LIKE = "${URL_NEW}user/like"
        var GET_USER_TOP_VIDEO = "${URL_NEW}user/getUserTopVideos"
        var GET_EMAIL_CODE = "${URL_NEW}account/forgetPassword"
        var RESET_PASSWORD = "${URL_NEW}account/resetPassword"
        var GET_MY_FOLLOWS = "${URL_NEW}user/getMyFollows"
        var GET_USER_TOPICS = "${URL_NEW}user/getUserShareTopics"
        var GET_USER_ASK_TOPIC = "${URL_NEW}user/getUserRequestTopics"
        var GET_HISTORY = "${URL_NEW}user/getMyPlayedVideos"
        var INACTIVATE = "${URL_NEW}account/inactivate"
        var GET_DEVICE_TOKEN = "${URL_NEW}system/registerDevice"

        //        var GET_MODIFY_EMAIL = "${URL_NEW}user/modifyEmail"
        var GET_MODIFY_EMAIL = "${URL_NEW}account/modifyEmail"

        //        var GET_UPDATE_EMAIL = "${URL_NEW}user/updateEmail"
        var GET_UPDATE_EMAIL = "${URL_NEW}account/updateEmail"

        var UPLOAD_USER_IMAGE = "${URL_NEW}account/uploadFile"
        var CHECK_TOKEN = "${URL_NEW}account/checkToken"
        var GET_COLLECTION_VIDEO = "${URL_NEW}video/getMyFavoritesVideos"

        // video
        var SYSTEM_MESSAAGE = "${URL_NEW}system/pullAnnouncements"

        var VIDEO_TOP = "${URL_NEW}user/topVideo"
        var GET_VIDEOS = "${URL_NEW}video/getVideos"
        var UNLOCK_VIDEO = "${URL_NEW}video/unlock"
        var LIKE = "${URL_NEW}video/like"
        var FAVORITES = "${URL_NEW}video/favorate"
        var COMPLAINT = "${URL_NEW}video/complain"
        var COMMENTS = "${URL_NEW}video/getComments"
        var ADD_COMMENT = "${URL_NEW}video/comment"
        var DELETE_COMMENT = "${URL_NEW}video/deleteComment"
        var UPLOAD_VIDEO = "${URL_NEW}video/upload"
        var DELETE_VIDEO = "${URL_NEW}video/delete"
        var UPLOAD_QUOTA = "${URL_NEW}video/getUploadQuota"
        var GET_USER_VIDEO = "${URL_NEW}user/getUserVideos"
        var GET_USER_GETBALANCE = "${URL_NEW}user/getBalance"
        var GET_MY_VIDEO = "${URL_NEW}topic/getAddableVideos"
        var GET_MY_VIDEO_NEW_TOPIC = "${URL_NEW}topic/GetAddableVideosForNewTopic"
        var TRANSCODING_COUNT = "${URL_NEW}user/getMyTranscodingCount"
        var UPDATE_VIDEO = "${URL_NEW}video/edit"
        var GET_VIDEOS_NOT_LOGIN = "${URL_NEW}video/getVideosForGuest"
        var GET_VIDEOS_CAN_UPLOAD = "${URL_NEW}video/canUplaod"

        var GET_VIDEO = "${URL_NEW}video/getVideo"
        var COMPLAINT_CHECK = "${URL_NEW}video/complain"
        var CHECK_COIN = "${URL_NEW}video/checkCanBePlay"

        // pay
        var WITHDRAW = "${URL_NEW}payment/withdraw"
        var PAY = "${URL_NEW}payment/pay"
        var RED_COIN_TRANSACTIONS = "${URL_NEW}payment/getRedCoinTransactions"
        var GOLD_COIN_TRANSACTIONS = "${URL_NEW}payment/getGoldCoinTransactions"
        var PAYMENT_PRICE = "${URL_NEW}payment/getPaymentPrices"
        var AMOUNT_INFO = "${URL_NEW}payment/getAmountInfo"
        var CHECK_PAY_STATUS = "${URL_NEW}payment/checkOrder"
        var CHECK_USER_PAIED = "${URL_NEW}payment/checkUserPaied"
        var CHECK_USER_REGISTERED = "${URL_NEW}user/checkUserRegistered"

        // finance
        var GET_GOLD_TRANSFERS = "${URL_NEW}/payment/getGoldCoinTransactions"
        var GET_RED_TRANSFERS = "${URL_NEW}payment/getRedCoinTransactions"
        var GET_BALANCE = "${URL_NEW}finance/getBalance"

        // topic
        var CAN_CREATE = "${URL_NEW}topic/canCreate"
        var GET_TOPICS = "${URL_NEW}topic/getShareTopics"
        var GET_TOPICS_GUEST = "${URL_NEW}topic/getShareTopicsForGuest"
        var GET_TOPIC = "${URL_NEW}topic/getShareTopic"
        var GET_SHARE_TOPIC_USERS = "${URL_NEW}topic/getShareTopicUsers"
        var GET_ASK_TOPICS = "${URL_NEW}topic/getRequestTopics"
        var GET_ASK_TOPICS_GUEST = "${URL_NEW}topic/getRequestTopicsForGuest"

        var GET_ASK_TOPIC = "${URL_NEW}topic/getRequestTopic"
        var GET_TOPIC_HASVIDEO = "${URL_NEW}topic/HasVideo"

        var GET_DELETE_TOPIC = "${URL_NEW}/topic/delete"

        var GET_ARTICLES = "${URL_NEW}article/getArticles"
        var GET_ARTICLES_GUEST = "${URL_NEW}article/getArticlesForGuest"


        var GET_ARTICLE = "${URL_NEW}article/getArticle"

        var GET_MY_ARTICLES = "${URL_NEW}user/getMyArticles"
        var GET_USER_ARTICLES = "${URL_NEW}user/getUserArticles"

        var GET_MY_FAVORITE_ARTICLES = "${URL_NEW}user/getMyfavoriteArticles"
        var GET_MY_READ_ARTICLES = "${URL_NEW}user/getMyReadArticles"

        var GET_QUESTIONS = "${URL_NEW}article/getQuestions"
        var GET_QUESTIONS_GUEST = "${URL_NEW}article/getQuestionsForGuest"


        var GET_QUESTION = "${URL_NEW}article/getQuestion"
        var GET_MY_QUESTION = "${URL_NEW}user/getMyQuestions"
        var GET_USER_QUESTION = "${URL_NEW}user/getUserQuestions"

        var GET_MY_FAVORITE_QUESTION = "${URL_NEW}user/getMyFavoriteQuestions"


        var ARTICLE_ADDABLEVIDEOS = "${URL_NEW}article/getAddableVideos"

        var ARTICLE_LIKE = "${URL_NEW}article/like"
        var ARTICLE_FAVORITE = "${URL_NEW}article/favorite"
        var ARTICLE_DELETE_COMMENT = "${URL_NEW}article/deleteComment"
        var ARTICLE_COMMENTS = "${URL_NEW}article/getComments"
        var ARTICLE_ADD_COMMENT = "${URL_NEW}article/comment"
        var CREATE_ARTICLE = "${URL_NEW}article/add"
        var ARTICLE_EDIT = "${URL_NEW}article/edit"
        var ARTICLE_UNLOCK = "${URL_NEW}article/unlock"
        var ARTICLE_DELETE = "${URL_NEW}article/delete"
        var ARTICLE_UNLOCK_COST = "${URL_NEW}article/getUnlockCost"

        var QUESTION_FAVORITE = "${URL_NEW}article/favorateQuestion"
        var CREATE_QUESTION = "${URL_NEW}article/addQuestion"
        var ANSWER_QUESTION = "${URL_NEW}article/answerQuestion"
        var QUESTION_ANSWERS = "${URL_NEW}article/getQuestionAnswers"

        var TOPIC_LIKE = "${URL_NEW}topic/like"
        var TOPIC_FAVORITE = "${URL_NEW}topic/favorite"
        var TOPIC_DELETE_COMMENT = "${URL_NEW}topic/deleteComment"
        var TOPIC_COMMENTS = "${URL_NEW}topic/getComments"
        var TOPIC_CAN_COMMENT = "${URL_NEW}topic/canComment"
        var TOPIC_ADD_COMMENT = "${URL_NEW}topic/comment"
        var CREATE_ASK_TOPIC = "${URL_NEW}topic/create"
        var CREATE_SHARE_TOPIC = "${URL_NEW}topic/createAndAddVideo"
        var TOPIC_VIDEO_RATE = "${URL_NEW}topic/rateVideo"

        var GET_TOPIC_VIDEOS = "${URL_NEW}topic/getVideos"
        var UPLOAD_TOPIC = "${URL_NEW}topic/upload"
        var ADD_TOPIC_VIDEOS = "${URL_NEW}topic/addVideo"
        var UNLOCK_TOPIC = "${URL_NEW}topic/unlock"

        var CAN_ADD_VIDEO = "${URL_NEW}topic/canAddVideo"

        // search
        var SEARCH_USER_TAGS = "${URL_NEW}search/userTags"
        var SEARCH_VIDEO = "${URL_NEW}search/videos"
        var SEARCH_AUTHOR = "${URL_NEW}search/users"
        var SEARCH_TOPIC = "${URL_NEW}search/shareTopics"
        var SEARCH_TOPIC_REQUEST = "${URL_NEW}search/requestTopics"
        var SEARCH_TAG_TOPIC_REQUEST = "${URL_NEW}search/tagRequestTopics"
        var SEARCH_ASK_TOPIC = "${URL_NEW}search/requestTopics"
        var SEARCH_TAGS = "${URL_NEW}search/tags"
        var SEARCH_HOT_TAGS = "${URL_NEW}search/hotTags"
        var SEARCH_TAG_VIDEO = "${URL_NEW}search/tagVideos"
        var SEARCH_TAG_ARTICLES = "${URL_NEW}search/tagArticles"
        var SEARCH_TAG_SHARETOPICS = "${URL_NEW}search/tagShareTopics"
        var SEARCH_TAG_USERS = "${URL_NEW}search/tagUsers"
        var SEARCH_ARTICLES = "${URL_NEW}search/articles"
        var SEARCH_QUESTIONS = "${URL_NEW}search/Questions"

        // tag
        var TAGS = "${URL_NEW}account/getRegisterTags"
        var ADMIN_TAGS = "${URL_NEW}search/hotTags"

        // hot
        var HOT_RANKING = "${URL_NEW}hot/getVideoRankings"
        var HOT_VIDEO = "${URL_NEW}hot/getRankingVideos"
        var HOT_TOPIC = "${URL_NEW}hot/topics"
        var HOT_USER_RANKING = "${URL_NEW}hot/getUserRankings"
        var MOST_REVENUE_USERS = "${URL_NEW}hot/getMostRevenueUsers"
        var POPULAR_USERS = "${URL_NEW}hot/getPopularUsers"
        var HOT_PAGE_TAG = "${URL_NEW}hot/hotTags"
        var HOT_TAG_VIDEO = "${URL_NEW}hot/getTagVideos"

        // message
        var MESSAGES = "${URL_NEW}system/pullSystemMessages"
        var RONG_MESSAGE = "${URL_NEW}rongcloud/sendPrivateMessage"
        var CHECK_FIRST_MESSAGE = "${URL_NEW}user/canSendCSMessage"

        // system
        var CHECK_UPDATE = "${GET_API_DOMAIN}system/getUpdate"
        var CREATE_ISSUE = "${URL_NEW}system/CreateIssue"

        const val UNKNOWN = "data error"
        const val ERROR401 = "登入凭证已过期，请重新登入。"
    }


}