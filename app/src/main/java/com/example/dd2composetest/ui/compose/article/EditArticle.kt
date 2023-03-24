package com.example.dd2composetest.ui.compose.article

import android.util.Log
import android.util.Xml
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dd2composetest.R
import com.example.dd2composetest.data.bean.TopicArticleDetailItem
import com.example.dd2composetest.enum.BottomSheet
import com.example.dd2composetest.enum.Screen
import com.example.dd2composetest.ui.compose.components.*
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.navigation.material.bottomSheet
import jp.wasabeef.richeditor.RichEditor
import org.xmlpull.v1.XmlPullParser
import java.io.ByteArrayInputStream

/**
 * date: 2023.02.23
 */

fun NavGraphBuilder.editArticle(navController: NavHostController) {
    composable(
        route = "${Screen.EDIT_ARTICLE_SCREEN.route}/{articleId}",
        arguments = listOf(
            navArgument("articleId") {
                type = NavType.IntType
                defaultValue = 0
                nullable = false
            }
        )
    ) { backStackEntry ->
        val articleId by remember { mutableStateOf(backStackEntry.arguments?.getInt("articleId")) }
        val viewModel: EditArticleViewModel = hiltViewModel()

//        viewModel.getCurrentArticleId(articleId ?: 0)
//        viewModel.getArticle(articleId ?: 0)
        EditArticleScreen(
            navController = navController,
            viewModel = viewModel,
//            articleId = articleId
        )
    }

    bottomSheet(
        route = BottomSheet.ADD_ARTICLE_TAG.route,
    ) {
        val parent = remember(it) {
            navController.getBackStackEntry(Screen.EDIT_ARTICLE_SCREEN.route + "/{articleId}")
        }
        val viewModel: EditArticleViewModel = hiltViewModel(parent)
        AddTagSheet(
            navController = navController,
            onClick = { tag -> viewModel.onEvent(EditArticleEvent.AddTag(tag)) }
        )
    }
    composable(
        route = "add_article_video_screen",
     ) {
        val parent = remember(it) {
            navController.getBackStackEntry(Screen.EDIT_ARTICLE_SCREEN.route + "/{articleId}")
        }
        val viewModel: EditArticleViewModel = hiltViewModel(parent)
        AddTopicVideoScreen(
            navController = navController,
//            videos = viewModel.addableVideo,
            viewModel = viewModel,
            onNextClick = { video ->
                viewModel.onEvent(
                    EditArticleEvent.AddVideo(arrayListOf(video)))
                Log.i("Arthur_test", "videos: ${viewModel.article.videos?.get(0)?.id}")
            }
        )
    }
}

fun NavHostController.navigateToEditArticle(id: Int) {
    navigate("${Screen.EDIT_ARTICLE_SCREEN.route}/$id") {
        launchSingleTop = true
    }
}

@Composable
fun EditArticleScreen(
    navController: NavHostController,
    viewModel: EditArticleViewModel,
//    articleId: Int?
) {
//        viewModel.getArticle(articleId ?: 0)

    Column(
        modifier = Modifier.background(Color.White)
    ) {
        Toolbar(
            navController = navController,
            title = "編輯文章",
            toolbarType = ToolBarType.HAS_RIGHT_BTN_TOOLBAR,
            rightName = "發布"
        ) {
            viewModel.onEvent(EditArticleEvent.SubmitArticle {
                Log.i("Arthur_test", viewModel.article.content)
//                navController.popBackStack()
            })
        }
        EditArticleContent(navController = navController, viewModel = viewModel)
    }
}
//
//@Preview
//@Composable
//fun PreviewEditArticleScreen() {
//    EditArticleScreen(NavHostController(LocalContext.current), articleId = 0)
//}

@Composable
fun EditArticleContent(
    navController: NavHostController,
    viewModel: EditArticleViewModel,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val tags = viewModel.article.tags
    val context = LocalContext.current
    
    LaunchedEffect(key1 = context) {
        viewModel.route.collect { event ->
            when(event) {
                is EditArticleEvent.SendToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column {
        Column(
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp)
                .weight(1f)
        ) {
            BasicTextField(
                value = viewModel.article.title,
                onValueChange = { viewModel.onEvent(EditArticleEvent.SetTitle(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .indicatorLine(
                        enabled = true,
                        isError = false,
                        interactionSource = interactionSource,
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.LightGray,
                            backgroundColor = Color.White
                        ),
                    ),
                textStyle = TextStyle(
                    fontSize = 20.sp
                ),
                decorationBox = { innerTextField ->
                    TextFieldDefaults.TextFieldDecorationBox(
                        value = viewModel.article.title,
                        innerTextField = innerTextField,
                        enabled = true,
                        singleLine = true,
                        visualTransformation = VisualTransformation.None,
                        interactionSource = interactionSource,
                        placeholder = {
                            Text(
                                text = "請輸入文章標題",
                                color = Color.LightGray,
                                fontSize = 20.sp
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.LightGray,
                            backgroundColor = Color.White
                        ),
                        contentPadding = PaddingValues(top = 15.dp, bottom = 15.dp)
                    )
                }
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 8.dp, bottom = 8.dp),
            ) {
//                RichEditorAndroidView(
//                    viewModel = viewModel,
//                    videoArray = arrayListOf()
//                )
                EditorView(viewModel)
            }
            OrientationRadioGroup(
                orientations = ArrayList(viewModel.article.sexType),
                enabled = true,
                setOrientation = { viewModel.onEvent(EditArticleEvent.SetOrientation(it)) }
            )
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp),
                mainAxisSpacing = 10.dp,
                crossAxisSpacing = 5.dp
            ) {
                EditArticleTagItem(navController = navController, viewType = 99, tag = "")
                for (i in 0 until tags.size) {
                    EditArticleTagItem(
                        navController = navController,
                        onClick = { viewModel.onEvent(EditArticleEvent.RemoveTag(i)) },
                        viewType = i, tag = tags[i])
                }
            }
        }

        Divider(modifier = Modifier
            .then(Modifier.padding(start = 0.dp, end = 0.dp))
            .padding(top = 9.dp), color = Color(0xffd8d8d8), thickness = 1.dp)

        EditArticleAddBar(
            viewModel = viewModel,
            onClickPicture = {  },
            onClickVideo = { navController.navigate("add_article_video_screen") }
        )
    }

}

@Composable
fun EditArticleTagItem(
    navController: NavHostController,
    onClick: (Int) -> Unit = {},
    viewType: Int,
    tag: String
) {
    Row(
        modifier = Modifier
            .height(28.dp)
            .background(
                color = when (viewType) {
                    99 -> Color(0XFFF6F6F6)
                    0 -> Color(0xffff9d3e)
                    1 -> Color(0xff34b1fd)
                    2 -> Color(0xff55a870)
                    else -> Color(0xffff9d3e)
                },
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                if (viewType == 99) navController.navigate(BottomSheet.ADD_ARTICLE_TAG.route) { }
                else onClick(viewType)
            }
            .padding(start = 14.dp, end = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (viewType == 99) Icon(
            painter = painterResource(id = R.mipmap.add),
            contentDescription = "",
            modifier = Modifier
                .padding(end = 4.dp)
                .size(16.dp),
            tint = Color(0XFF696969)
        )
        Text(
            text = if (viewType == 99) "標籤" else tag,
            modifier = Modifier,
            color = if (viewType == 99) Color(0XFF696969) else Color.White,
            textAlign = TextAlign.Center
        )
        if (viewType != 99) Icon(
            painter = painterResource(id = R.drawable.create_article_close),
            contentDescription = "",
            modifier = Modifier
                .padding(start = 8.dp)
                .size(12.dp),
            tint = Color.White
        )
    }
}

@Composable
fun EditArticleAddBar(
    viewModel: EditArticleViewModel,
    onClickPicture: () -> Unit = {},
    onClickVideo: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.mipmap.insert_picture),
            contentDescription = "",
            modifier = Modifier
                .padding(start = 19.dp, top = 9.dp, end = 49.dp, bottom = 9.dp)
                .size(26.dp)
                .clickable { onClickPicture() }
        )
        Image(
            painter = painterResource(id = R.mipmap.insert_video),
            contentDescription = "",
            modifier = Modifier
                .padding(start = 19.dp, top = 9.dp, end = 9.dp, bottom = 9.dp)
                .size(26.dp)
                .clickable {
                    viewModel.onEvent(EditArticleEvent.ClickSelectVideo(onClick = onClickVideo))
                }
        )
    }
}

@Composable
fun EditorView(
    viewModel: EditArticleViewModel,
//    data: TopicArticleDetailItem
) {
    val data by remember { mutableStateOf(viewModel.article) }
//    Log.i("Arthur_debug", "EditorView: $data")

    Editor2(data = data) // use TextField and images
//    Editor3(data = data) // use webView
}


//@Composable
//fun RichEditorAndroidView(
//    viewModel: EditArticleViewModel,
////    imgUrls: ArrayList<EditArticleData.Image>,
////    videos: ArrayList<EditArticleData.Video>,
//    videoArray: ArrayList<Int>
//) {
//    val imgUrls = viewModel.article.images
////    val videos = remember { mutableStateOf(viewModel.article.videos) }
//    val videos = viewModel.article.videos
//
//    AndroidView(
//        modifier = Modifier,
//        factory = { context ->
//            RichEditor(context).apply {
//                var stream: ByteArrayInputStream? = null
//                try {
//                    stream = ByteArrayInputStream(viewModel.article.content.toByteArray())
//                } catch (e: Exception) {
//                    Log.e("rich_editor", e.toString())
//                }
//                val parser: XmlPullParser = Xml.newPullParser()
//                parser.setInput(stream, "UTF-8")
//                var type = parser.eventType
//                var imageIndex = 0
//                var videoIndex = 0
//                viewModel.article.videos?.forEach{
//                    videoArray.add(it.id)
//                }
//                Log.d("rich_editor", "videoArray: $videoArray")
//                Log.d("rich_editor", "setHtml content: ${viewModel.article.content}")
//                val html = StringBuilder()
//                this.focusEditor()
//                this.html = ""
//
//                while (type != XmlPullParser.END_DOCUMENT) {
//                    when (type) {
//                        XmlPullParser.START_TAG -> {
//                            Log.d("rich_editor", "parser.name: ${parser.name}")
//                            if (parser.name.equals("image", true)) {
//                                if (imageIndex < imgUrls.size) {
//                                    html.append("<img src=\"${imgUrls[imageIndex].url}\" alt=\"dachshund\" width=\"100%\" id=\"${imgUrls[imageIndex].id}\"/><br>")
//                                }
//                                imageIndex++
//                            }
//                            if (parser.name.equals("video", true)) {
//                                html.append("<br><video src=\" \" width=\"100%\" controls=\"\" poster=\"${videos?.get(0)?.coverUrl}\"></video><br><br>")
//                                videoIndex++
//                            }
//                        }
//                        XmlPullParser.TEXT -> {
//                            Log.d("rich_editor", "parser.text: ${parser.text ?: "NO TEXT"}")
//                            if (parser.text == "\n") html.append("<br>")
//                            else html.append(parser.text)
//                        }
//                    }
//                    type = parser.next()
//                }
//                this.html = html.toString()
//                Log.d("rich_editor", "editor.html: ${this.html}")
//
////                this.setOnTextChangeListener {
////                    viewModel.onEvent(
////                        EditArticleEvent
////                    )
////                }
//            }
//        },
//        update = {
//
////            it
////
////            val html = it.html
////            if (html == null || html.trim().isEmpty()) {
////                viewModel.onEvent(EditArticleEvent.SendToast("請輸入文章內容"))
////            }
////            val imageArray: ArrayList<File> = arrayListOf()
////            val htxService = HTXService()
////            val wrapHtml = htxService.toXML(html, imageArray, videoArray)
////                .replace("<article>\n ", "<article>")
////                .replace("<article>\n　", "<article>")
////                .replace("<article>\n  ", "<article>")
////            Log.d("rich_editor", "wrapHtml: $wrapHtml")
////
////            Log.d("rich_editor", "update html: $html")
////            if (!videos.isNullOrEmpty()) it.html += "<br><video src=\" \" width=\"100%\" controls=\"\" poster=\"${videos[0].coverUrl}\"></video><br>"
////            it.html += "<br><video src=\" \" width=\"100%\" controls=\"\" poster=\"${videos?.get(0)?.coverUrl}\"></video><br><br>"
//        }
//    )
//}

/**
 * HTML <video> 播放影片 / 多媒體影音串流
 *
 * <video> 標籤的屬性 (attributes)：
 * src: 影片的位址 (URL)
 * poster: 指定一個圖片位址，做為影片未開始播放之前的預覽圖
 * preload: 給瀏覽器是否該預載影片的提示。有這些值可以使用：
     * none: 不要預載，因為使用者很可能不會播放該音訊，或你想多節省 server 頻寬
     * metadata: 先下載影片的元數據資料 (像是片長)
     * auto: 使用者很可能會播放該影片，可以先進行下載
 * autoplay: 布林 (boolean) 屬性，控制是否自動播放影片，預設是 false
 * loop: 布林 (boolean) 屬性，控制是否重複播放影片，預設是 false
 * muted: 布林 (boolean) 屬性，控制是否靜音，預設是 false
 * controls: 布林 (boolean) 屬性，指定是否顯示影音控制面板，由瀏覽器提供上面會有播放進度、暫停鈕、播放鈕、靜音鈕等，預設是 false
 * width: 一個數字，設定影片顯示區域的寬度，單位是像素 (pixel)
 * height: 一個數字，設定影片顯示區域的高度，單位是像素 (pixel)
 *
 * e.q. <video src="clip.mp4" controls></video>
 */

@Composable
fun RichMediaTextEditor() {

//    TextField(value = , onValueChange = )



}
