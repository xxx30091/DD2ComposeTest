package com.example.dd2composetest.ui.compose.article

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.dd2composetest.data.bean.EditArticleData
import com.example.dd2composetest.data.bean.TopicArticleDetailItem
import com.example.dd2composetest.data.mock.MockData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditArticleViewModel @Inject constructor() : ViewModel() {

    var article by mutableStateOf(TopicArticleDetailItem())

    init {
        getArticle()
    }

    fun getArticle() {
        article = MockData.getMockEditArticle()
    }

    fun onEvent(event: EditArticleEvent) {
        when (event) {
            is EditArticleEvent.SetTitle -> {
                article = article.copy(title = event.title)
            }
            is EditArticleEvent.RemoveTag -> {
                if (event.index in article.tags.indices) {
                    val tags = ArrayList(article.tags)
                    tags.removeAt(event.index)
                    article = article.copy(tags = tags)
                }
            }
            is EditArticleEvent.AddTag -> {
                val tags = ArrayList(article.tags)
                if (tags.size < 3) tags.add(event.tag)
                article = article.copy(tags = tags)
            }
            is EditArticleEvent.SetOrientation -> {
                article = article.copy(sexType = event.orientations)
            }
        }
    }

}

sealed class EditArticleEvent() {

    class SetTitle(val title: String) : EditArticleEvent()

    class RemoveTag(val index: Int) : EditArticleEvent()

    class AddTag(val tag: String) : EditArticleEvent()

    class SetOrientation(val orientations: ArrayList<Int>) : EditArticleEvent()


}