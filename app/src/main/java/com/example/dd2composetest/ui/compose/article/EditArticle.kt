package com.example.dd2composetest.ui.compose.article

import android.util.Log
import android.util.Xml
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.dd2composetest.R
import com.example.dd2composetest.data.bean.EditArticleData
import com.example.dd2composetest.enum.BottomSheet
import com.example.dd2composetest.enum.Screen
import com.example.dd2composetest.ui.compose.components.AddTagSheet
import com.example.dd2composetest.ui.compose.components.OrientationRadioGroup
import com.example.dd2composetest.ui.compose.components.ToolBarType
import com.example.dd2composetest.ui.compose.components.Toolbar
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.navigation.material.bottomSheet
import com.pointlessapps.rt_editor.model.RichTextValue
import jp.wasabeef.richeditor.RichEditor
import org.xmlpull.v1.XmlPullParser
import java.io.ByteArrayInputStream

fun NavGraphBuilder.editArticle(navController: NavHostController) {
    composable(Screen.EDIT_ARTICLE_SCREEN.route) {
        EditArticleScreen(navController = navController)
    }
    bottomSheet(BottomSheet.ADD_ARTICLE_TAG.route) {
        val parent = remember(it) {
            navController.getBackStackEntry(Screen.EDIT_ARTICLE_SCREEN.route)
        }
        val viewModel: EditArticleViewModel = hiltViewModel(parent)
        AddTagSheet(
            navController = navController,
            onClick = { tag -> viewModel.onEvent(EditArticleEvent.AddTag(tag)) }
        )
    }
}

fun NavHostController.navigateToEditArticle() {
    navigate(Screen.EDIT_ARTICLE_SCREEN.route) {
        launchSingleTop = true
    }
}

@Composable
fun EditArticleScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.background(Color.White)
    ) {
        Toolbar(
            navController = navController,
            title = "編輯文章",
            toolbarType = ToolBarType.HAS_RIGHT_BTN_TOOLBAR,
            rightName = "發布",
            onClickRight = {}
        )
        EditArticleContent(navController = navController)
    }
}

@Preview
@Composable
fun PreviewEditArticleScreen() {
    EditArticleScreen(NavHostController(LocalContext.current))
}

@Composable
fun EditArticleContent(
    navController: NavHostController,
    viewModel: EditArticleViewModel = hiltViewModel()
) {
    val interactionSource = remember { MutableInteractionSource() }
    var content by remember { mutableStateOf(RichTextValue.get()) }
    val tags = viewModel.article.tags
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
                viewModel.article.videos?.let { videos ->
                    RichEditor(
                        content = viewModel.article.content,
                        imgUrls = viewModel.article.images,
                        videos = videos,
                        videoArray = arrayListOf()
                    )
                }
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

        EditArticleAddBar()
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
                if (viewType == 99) navController.navigate(BottomSheet.ADD_ARTICLE_TAG.route)
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
                .clickable { onClickVideo() }
        )
    }
}

class EditArticleFragment: Fragment()

@Composable
fun RichEditor(
    content: String,
    imgUrls: ArrayList<EditArticleData.Image>,
    videos: ArrayList<EditArticleData.Video>,
    videoArray: ArrayList<Int>
) {
    AndroidView(
        modifier = Modifier,
        factory = { context ->
            RichEditor(context).apply {
                var stream: ByteArrayInputStream? = null
                try {
                    stream = ByteArrayInputStream(content.toByteArray())
                } catch (e: Exception) {
                    Log.e("rich_editor", e.toString())
                }
                val parser: XmlPullParser = Xml.newPullParser()
                parser.setInput(stream, "UTF-8")
                var type = parser.eventType
                var imageIndex = 0
                var videoIndex = 0
                videos.forEach{
                    videoArray.add(it.id)
                }
                Log.d("Arthur_test", "setHtml content: $content")
                val html = StringBuilder()
                this.focusEditor()
                this.html = ""

                while (type != XmlPullParser.END_DOCUMENT) {
                    when (type) {
                        XmlPullParser.START_TAG -> {
                            Log.d("Arthur_test", "parser.name: ${parser.name}")
                            if (parser.name.equals("image", true)) {
                                if (imageIndex < imgUrls.size) {
                                    html.append("<img src=\"${imgUrls[imageIndex].url}\" alt=\"dachshund\" width=\"100%\" id=\"${imgUrls[imageIndex].id}\"/><br>")
                                }
                                imageIndex++
                            }
                            if (parser.name.equals("video", true)) {
                                html.append("<br><video src=\"\" width=\"100%\" controls=\"\"></video><br><br>")
                                videoIndex++
                            }
                        }
                        XmlPullParser.TEXT -> {
                            Log.d("Arthur_test", "parser.text: ${parser.text ?: "NO TEXT"}")
                            if (parser.text == "\n") html.append("<br>")
                            else html.append(parser.text)
                        }
                    }
                    type = parser.next()
                }
                this.html = html.toString()
                Log.d("Arthur_test", "editor.html: ${this.html}")
            }
        },
        update = {

        }
    )
}


//@Preview
//@Composable
//fun RichTextEditor(editArticle: TopicArticleDetailItem = TopicArticleDetailItem()) {
//    val images = editArticle.images
//    var inlineContent: Map<String, InlineTextContent> = mapOf()
//    if (images.isNotEmpty()) {
//        for (i in images.indices) {
//            inlineContent.plus(
//                Pair(
//                    "img${images[i].id}",
//                    InlineTextContent(
//                        Placeholder(width = 0.5.em, height = 0.5.em, PlaceholderVerticalAlign.Center)
//                    ) {
//                        Image(
//                            painter = rememberAsyncImagePainter(model = images[i].url),
//                            contentDescription = ""
//                        )
//                    }
//                )
//            )
//        }
//    }
//
//
//
//    TextField(
//        value = "",
//        onValueChange = {
//
//        }
//    )
//}

