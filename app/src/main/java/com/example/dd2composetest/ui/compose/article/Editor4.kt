package com.example.dd2composetest.ui.compose.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberImagePainter
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

@Deprecated("not meet the problem")
@Composable
fun Editor4(html: String) {
    val a = remember {

    }
    val contentList = remember { parseHtml(html) }
    LazyColumn {
        items(contentList) { content ->
            when (content) {
                is HtmlContent.Text -> Text(content.text)
                is HtmlContent.Image -> Image(
                    painter = rememberImagePainter("https://example.com/image/${content.id}.png"),
                    contentDescription = "Image"
                )
                is HtmlContent.Video -> VideoPlayer(
                    url = "https://example.com/video/${content.id}.mp4",
                    autoPlay = false
                )
            }
        }
    }
}

fun parseHtml(html: String): List<HtmlContent> {
    val pattern = "<article>(.*?)</article>".toRegex()
    val article = pattern.find(html)?.groupValues?.getOrNull(1) ?: ""

    val textPattern = "(.*?)(<image id=\"(.*?)\" />|<video id=\"(.*?)\" />|\$)".toRegex()
    val matches = textPattern.findAll(article)

    return matches.map {
        val text = it.groupValues[1].trim()
        val imageId = it.groupValues[4].takeIf { it.isNotEmpty() }
        val videoId = it.groupValues[5].takeIf { it.isNotEmpty() }

        when {
            text.isNotEmpty() -> HtmlContent.Text(text)
            imageId != null -> HtmlContent.Image(imageId)
            videoId != null -> HtmlContent.Video(videoId)
            else -> throw IllegalStateException("Invalid HTML format")
        }
    }.toList()
}

@Composable
fun VideoPlayer(url: String, autoPlay: Boolean = false) {
    val context = LocalContext.current
    var exoPlayer = remember {
        SimpleExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(url))
            prepare()
            if (autoPlay) {
                play()
            }
        }
    }

    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                player = exoPlayer
            }
        },
        update = { view ->
            view.player = exoPlayer
        },
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
    )

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }
}


sealed class HtmlContent {
    data class Text(val text: String) : HtmlContent()
    data class Image(val id: String) : HtmlContent()
    data class Video(val id: String) : HtmlContent()
}