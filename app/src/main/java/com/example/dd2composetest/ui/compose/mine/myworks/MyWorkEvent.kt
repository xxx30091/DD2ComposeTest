package com.example.dd2composetest.ui.compose.mine.myworks

import com.example.dd2composetest.data.bean.MyVideoBean

sealed class MyWorkEvent() {

    class SelectDate(val selectedDate: Pair<String, String>) : MyWorkEvent()

    class AddVideo() : MyWorkEvent()

    class EditVideo() : MyWorkEvent()

    class SetVideoTop(val video: MyVideoBean) : MyWorkEvent()

    class SetVideoNotTop(val id: Int) : MyWorkEvent()

    class RemoveVideo(val id: Int) : MyWorkEvent()

    class AddNewTopic() : MyWorkEvent()

    class RemoveTopic(val id: Int) : MyWorkEvent()

    class AddNewRequest() : MyWorkEvent()

    class RemoveRequest(val id: Int) : MyWorkEvent()

    class AddNewArticle() : MyWorkEvent()

    class EditArticle() : MyWorkEvent()

    class RemoveArticle(val id: Int) : MyWorkEvent()

    class AddNewQuestion() : MyWorkEvent()

}
