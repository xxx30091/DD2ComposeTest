package com.example.dd2composetest.ui.compose.article

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.dd2composetest.data.bean.ChooseVideoData
import com.example.dd2composetest.data.bean.EditArticleData
import com.example.dd2composetest.data.bean.TopicArticleDetailItem
import com.example.dd2composetest.data.mock.MockData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditArticleViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private var currentArticleId = checkNotNull(savedStateHandle.get<Int>("articleId"))
    var articles by mutableStateOf(listOf(TopicArticleDetailItem()))
    var article by mutableStateOf(TopicArticleDetailItem())
    val route = MutableSharedFlow<EditArticleEvent>()

    var addableVideo by mutableStateOf(listOf<ChooseVideoData>())

    init {
        getArticles()
        getAddableVideo()
        getArticle(currentArticleId)
        Log.i("Arthur_test", "EditArticleViewModel: ${this.hashCode()}")
    }

    private fun getArticles() {
        articles = MockData.getMockEditArticles()
    }

    private fun getArticle(id: Int) {
        article = articles[id - 1]
//        Log.i("Arthur_test", "viewModel getArticle: id: $id, article: $article")
    }

    private fun getAddableVideo() {
        addableVideo = MockData.getMockChooseVideoData()
    }

    fun onEvent(event: EditArticleEvent) {
        viewModelScope.launch {
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
                    Log.i("Arthur_debug", "article1 = $article")
                }
//            is EditArticleEvent.SelectImages -> {
//
//            }
                is EditArticleEvent.ClickSelectVideo -> {
                    if (article.videos?.size != 0) route.emit(EditArticleEvent.SendToast("最多只能選擇1個影片"))
                    else event.onClick() // 去選擇影片頁面
                    Log.i("Arthur_debug", "article2 = $article")
                }
                is EditArticleEvent.AddVideo -> {
                    val content = article.content.replace("</article>", "<video id=\"100\" /></article>")
                    article = article.copy(videos = event.videos, content = content)

                    Log.i("Arthur_debug", "event content = ${article.content}")
                    Log.i("Arthur_debug", "event videos = ${article.videos}")
                    Log.i("Arthur_debug", "event orientation = ${article.sexType}")
                }
                is EditArticleEvent.SubmitArticle -> {
                    if (article.title.isEmpty()) route.emit(EditArticleEvent.SendToast("請輸入文章標題"))
                    else if (article.sexType.isEmpty()) route.emit(EditArticleEvent.SendToast("請正確選擇取向"))
                    else if (article.tags.size == 0) route.emit(EditArticleEvent.SendToast("請輸入文章標籤"))
                    else {
                        // 發送 api
                        event.onClick()
                    }
                }
            }
        }
    }


}

sealed class EditArticleEvent {

    class SetTitle(val title: String) : EditArticleEvent()

    class RemoveTag(val index: Int) : EditArticleEvent()

    class AddTag(val tag: String) : EditArticleEvent()

    class SetOrientation(val orientations: ArrayList<Int>) : EditArticleEvent()

//    class SelectImages() : EditArticleEvent()
//
    class ClickSelectVideo(val onClick: () -> Unit) : EditArticleEvent()

    class AddVideo(val videos: ArrayList<EditArticleData.Video>) : EditArticleEvent()

    class SetHtml(val html: String) : EditArticleEvent()

    class SubmitArticle(val onClick: () -> Unit) : EditArticleEvent()

    class SendToast(val message: String) : EditArticleEvent()

}