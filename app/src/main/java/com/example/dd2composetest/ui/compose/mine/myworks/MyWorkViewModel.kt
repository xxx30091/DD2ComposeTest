package com.example.dd2composetest.ui.compose.mine.myworks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.dd2composetest.data.bean.*
import com.example.dd2composetest.data.mock.MockData
import com.example.dd2composetest.utils.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class MyWorkViewModel @Inject constructor() : ViewModel() {

    var transcodingCount by mutableStateOf(0)
    var topVideos by mutableStateOf(listOf<MyVideoBean>())
    var videos by mutableStateOf(listOf<MyVideoBean>())

    var myTopics by mutableStateOf(listOf<TopicItem?>())
    var myAskTopic by mutableStateOf(listOf<TopicAskItem>())

    var myArticles by mutableStateOf(listOf<TopicArticleItem?>())
    var myQuestions by mutableStateOf(listOf<MyQuestionItem>())

    var startDate by mutableStateOf("")
    var endDate by mutableStateOf("")

    init {
        initDate()
        getMyTranscodingCount()
//        getMyTopVideos()
//        getMyVideos()
//        getMyTopics()
//        getMyAskTopics()
//        getMyArticles()
//        getMyQuestions()
    }

    fun onEvent(event: MyWorkEvent) {
        when (event) {
            is MyWorkEvent.SelectDate -> {
                startDate = event.selectedDate.first
                endDate = event.selectedDate.second
            }
            is MyWorkEvent.EditVideo -> {

            }
            is MyWorkEvent.SetVideoTop -> {
                if (!topVideos.contains(event.video)) {
                    val arr = ArrayList(topVideos)
                    arr.add(event.video)
                    topVideos = arr
                }
            }
            is MyWorkEvent.SetVideoNotTop -> {
                val arr = ArrayList(topVideos)
                arr.remove(element = arr.find { it.id == event.id })
                topVideos = arr
            }
            is MyWorkEvent.RemoveVideo -> {
                val arrTop = ArrayList(topVideos)
                val arr = ArrayList(videos)
                arrTop.remove(element = arr.find { it.id == event.id })
                arr.remove(element = arr.find { it.id == event.id })
                topVideos = arrTop
                videos = arr
            }
            is MyWorkEvent.RemoveTopic -> {
                val arr = ArrayList(myTopics)
                arr.remove(element = arr.find { it?.id == event.id } )
                myTopics = arr
            }
            is MyWorkEvent.RemoveRequest -> {
                val arr = ArrayList(myAskTopic)
                arr.remove(element = arr.find { it.id == event.id } )
                myAskTopic = arr
            }
            is MyWorkEvent.RemoveArticle -> {
                val arr = ArrayList(myArticles)
                arr.remove(element = arr.find { it?.id == event.id } )
                myArticles = arr
            }
            else -> {}
        }
    }

    fun initDate() {
        val c = Calendar.getInstance()
        endDate = DateUtils.getDateString(c.timeInMillis)
        c.add(Calendar.MONTH, -3)
        startDate = DateUtils.getDateString(c.timeInMillis)
    }

    private fun getMyTranscodingCount() {
        transcodingCount = 1
    }

    fun getMyTopVideos() {
//        topVideos = listOf()
        topVideos = MockData().getMockMyTopVideos()
    }

    fun getMyVideos() {
//        videos = listOf()
        videos = MockData().getMockMyVideos()
    }

    fun getMyTopics() {
//        myTopics = listOf()
        myTopics = MockData().getMockMyTopics()
    }

    fun getMyAskTopics() {
//        myAskTopic = listOf()
        myAskTopic = MockData().getMockMyMockAskTopics()
    }

    fun getMyArticles() {
//        myArticles = listOf()
        myArticles = MockData().getMockMyArticles()
    }

    fun getMyQuestions() {
//        myQuestions = listOf()
        myQuestions = MockData().getMockMyQuestions()
    }

}

