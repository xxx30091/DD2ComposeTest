package com.example.dd2composetest.ui.compose.article

import android.util.Log
import android.util.Xml
import android.webkit.WebView
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.dd2composetest.R
import com.example.dd2composetest.data.bean.EditArticleData
import com.example.dd2composetest.data.bean.TopicArticleDetailItem
import org.xmlpull.v1.XmlPullParser
import java.io.ByteArrayInputStream

//    val a = "<article> This is an article with 3-pictures" +
//            " \n <image id=\"1\"/> \n <image id=\"2\" /> \n <image id=\"3\" />" +
//            " \n and 1 locked video \n <video id=\"100\" /> </article>"

@Composable
fun Editor2(
    data: TopicArticleDetailItem
) {
    val scroll = rememberScrollState()
    val parsedArticle by remember { mutableStateOf(parseArticle(data)) }
//    val parsedArticle = parseArticle(data)
//    var articleContent by remember { mutableStateOf("") }
    var articleContent =""
    Log.i("Arthur_test", "article_content_1: $articleContent")

    // TODO 直接用 event ?

    Column(
        modifier = Modifier
            .verticalScroll(scroll)
    ) {
//        articleContent.plus("<article>")
//        articleContent += "<article>"
        parsedArticle.forEach { block ->
            when {
                block.startsWith("<text ") -> {
                    var textBlock by remember { mutableStateOf(block.replace("<text ", "")) }
                    BasicTextField(
                        value = textBlock,
                        onValueChange = {
                            textBlock = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(),
                    )
                    articleContent += textBlock
                }
                block.startsWith("<img src=") -> {
                    val info = block.replace("<img src=", "").split(" id=")
                    Image(
                        painter = rememberAsyncImagePainter(model = info[0]),
//                        painter = rememberAsyncImagePainter(model = block.replace("<img src=", "")),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(210.dp),
                        contentScale = ContentScale.Crop
                    )
                    articleContent += "<image id=\"${info[1]}\"/>"
                }
                block.startsWith("<video src=") -> {
                    VideoPlayer(block.replace("<video src=", ""))
//                    articleContent.plus("<video index=\"0\"/>")
                    articleContent += "<video index=\"0\"/>"
                }
            }
//            articleContent.plus("</article>")
//            articleContent += "</article>"
        }

        Log.i("Arthur_test", "article_content_2: $articleContent")
    }
}

@Composable
fun VideoPlayer(imgUrl: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = imgUrl),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.mipmap.play),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .size(60.dp)
        )
    }
}

fun parseArticle(data: TopicArticleDetailItem): List<String> {
    val blocks = mutableListOf<String>()

    var stream: ByteArrayInputStream? = null
    try {
        stream = ByteArrayInputStream(data.content.toByteArray())
    } catch (e: Exception) {
        Log.i("Arthur_test", e.toString())
    }

    val parser = Xml.newPullParser()
    parser.setInput(stream, "UTF-8")
    var type = parser.eventType
    var imageIndex = 0
    var videoIndex = 0
    val html = StringBuilder()
    blocks.add("<article>")
    while (type != XmlPullParser.END_DOCUMENT) {
        when (type) {
            XmlPullParser.START_TAG -> {
                if (parser.name.equals("image", true)) {
                    if (imageIndex < data.images.size) {
                        val imgUrl = data.images[imageIndex].url
                        blocks.add("<img src=$imgUrl id=${data.images[imageIndex].id}")
//                        html.append("<img src=\"${data.images[imageIndex].url}\" alt=\"dachshund\" width=\"100%\" id=\"${data.images[imageIndex].id}\"/><br>")
                    }
                    imageIndex++
                }
                if (parser.name.equals("video", true)) {
                    val videoUrl = data.videos?.get(0)?.coverUrl!!
                    blocks.add("<video src=$videoUrl")
//                    html.append("<br><video src=\"\" poster=\"$videoUrl\" width=\"100%\" controls=\"\"></video><br><br>")
                    videoIndex++
                }
            }
            XmlPullParser.TEXT -> {
                blocks.add("<text ${parser.text}")
//                if (parser.text == "\n") html.append("<br>")
//                else html.append(parser.text)
            }
        }
        type = parser.next()
    }
    blocks.add("</article>")
    Log.i("Arthur_test", "blocks_content: $blocks")
    return blocks
}

//fun parseArticle(data: TopicArticleDetailItem): List<ArticleBlock> {
//    val blocks = mutableListOf<ArticleBlock>()
//
//    var stream: ByteArrayInputStream? = null
//    try {
//        stream = ByteArrayInputStream(data.content.toByteArray())
//    } catch (e: Exception) {
//        Log.i("Arthur_test", e.toString())
//    }
//
//    val parser = Xml.newPullParser()
//    parser.setInput(stream, "UTF-8")
//    var type = parser.eventType
//    var imageIndex = 0
//    var videoIndex = 0
////    val html = StringBuilder()
//    while (type != XmlPullParser.END_DOCUMENT) {
//        when (type) {
//            XmlPullParser.START_TAG -> {
//                if (parser.name.equals("image", true)) {
//                    if (imageIndex < data.images.size) {
//                        val imgUrl = data.images[imageIndex].url
//                        blocks.add(ImageBlock(imgUrl))
//                    }
//                    imageIndex++
//                }
//                if (parser.name.equals("video", true)) {
//                    val videoUrl = data.videos?.get(0)?.coverUrl!!
//                    blocks.add(VideoBlock(videoUrl))
//                    videoIndex++
//                }
//            }
//            XmlPullParser.TEXT -> {
////                if (parser.text == "\n")
//                blocks.add(TextBlock(parser.text))
//            }
//        }
//        type = parser.next()
//    }
//    return blocks
//}

sealed class ArticleBlock

data class TextBlock(var text: String) : ArticleBlock()

data class ImageBlock(val imageUrl: String) : ArticleBlock()

data class VideoBlock(val videoUrl: String) : ArticleBlock()



