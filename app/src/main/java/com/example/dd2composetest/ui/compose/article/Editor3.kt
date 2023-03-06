package com.example.dd2composetest.ui.compose.article

import android.util.Log
import android.util.Xml
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.*
import androidx.compose.ui.viewinterop.AndroidView
import com.example.dd2composetest.data.bean.TopicArticleDetailItem
import org.xmlpull.v1.XmlPullParser
import java.io.ByteArrayInputStream

@Composable
fun Editor3(
    data: TopicArticleDetailItem,
//    url: String
) {
//    val itemData by remember { mutableStateOf(data) }
//    val url = parseArticleForHtml(itemData)
    val url = parseArticleForHtml(data)
//    val url by remember {
//        mutableStateOf(parseArticleForHtml(data))
//    }
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadData(url, "text/html", "UTF-8")
            }
        },
        update = {
            it.loadData(url, "text/html", "UTF-8")
        }
    )
    Log.i("Arthur_test", "url: $url")
//    Log.i("Arthur_debug", "item data: $itemData")
}

fun parseArticleForHtml(data: TopicArticleDetailItem): String {
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
    while (type != XmlPullParser.END_DOCUMENT) {
        when (type) {
            XmlPullParser.START_TAG -> {
                if (parser.name.equals("image", true)) {
                    if (imageIndex < data.images.size) {
                        val imgUrl = data.images[imageIndex].url
                        html.append("<img src=\"${imgUrl}\" alt=\"dachshund\" width=\"100%\" id=\"${data.images[imageIndex].id}\"/><br>")
                    }
                    imageIndex++
                }
                if (parser.name.equals("video", true)) {
                    val videoUrl = data.videos?.get(0)?.coverUrl!!
//                    html.append("<br><video src=\"\" width=\"100%\" controls=\"\"></video><br><br>")
                    html.append("<br><video src=\"\" poster=\"${videoUrl}\" width=\"100%\" controls=\"\"></video><br><br>")
                    videoIndex++
                }
            }
            XmlPullParser.TEXT -> {
                if (parser.text == "\n") html.append("<br>")
                else html.append(parser.text)
            }
        }
        type = parser.next()
    }
    return html.toString()
}
