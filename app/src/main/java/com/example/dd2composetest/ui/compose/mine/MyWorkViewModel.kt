package com.example.dd2composetest.ui.compose.mine

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

@HiltViewModel
class MyWorkViewModel @Inject constructor() : ViewModel() {

    var transcodingCount by mutableStateOf(0)
    var topVideos by mutableStateOf(listOf<MyVideoBean>())
    var videos by mutableStateOf(listOf<MyVideoBean>())

    var myTopics by mutableStateOf(listOf<TopicItem?>())
    var myAskTopic by mutableStateOf(listOf<TopicAskItem>())

    var myArticles by mutableStateOf(listOf<TopicArticleItem?>())
    var myQuestions by mutableStateOf(listOf<MyQuestion>())

    var startDate by mutableStateOf("")
    var endDate by mutableStateOf("")

    init {
        initDate()
        getMyTranscodingCount()
        getMyTopVideos()
        getMyVideos()
        getMyTopics()
        getMyAskTopics()
        getMyArticles()
        getMyQuestions()
    }

    fun onEvent(event: MyWorkEvent) {
        when (event) {
            is MyWorkEvent.SelectDate -> {
                startDate = event.selectedDate.first
                endDate = event.selectedDate.second
            }
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

    private fun getMyTopVideos() {
//        topVideos = listOf()
        topVideos = MockData().getMockMyVideos()
    }

    private fun getMyVideos() {
//        videos = listOf()
        videos = MockData().getMockMyVideos()
    }

    private fun getMyTopics() {
//        myTopics = listOf()
        myTopics = MockData().getMockMyTopics()
    }

    private fun getMyAskTopics() {
//        myAskTopic = listOf()
        myAskTopic = MockData().getMockMyMockAskTopics()
    }

    private fun getMyArticles() {
//        myArticles = listOf()
        myArticles = MockData().getMockMyArticles()
    }

    private fun getMyQuestions() {
//        myQuestions = listOf()
        myQuestions = MockData().getMockMyQuestions()
    }

}

sealed class MyWorkEvent() {

    class SelectDate(val selectedDate: Pair<String, String>) : MyWorkEvent()

}
