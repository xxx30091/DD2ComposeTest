package com.example.dd2composetest.ui.compose.article

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

//sealed class EditorElement {
//    data class Text(val content: String) : EditorElement()
//    data class Image(val id: Int, val url: String) : EditorElement()
//    data class Video(val id: Int, val url: String) : EditorElement()
//}

sealed class EditorElement {
    data class Text(val value: String): EditorElement()
    data class Image(val id: String): EditorElement()
    data class Video(val id: String): EditorElement()
}


//data class Media(val id: Int, val url: String)

sealed class Media(val id: String, val url: String) {
    class Image(id: String, url: String): Media(id, url)
    class Video(id: String, url: String): Media(id, url)
}


data class ArticleElement(val content: String, val mediaIds: List<Int>)

val articleElements = mutableListOf<ArticleElement>()
//
//var editorElements by remember { mutableStateOf(emptyList<EditorElement>()) }
//editorElements = parseHtml(html, mediaList)

//@Composable
//fun ArticleContent(articleElements: List<ArticleElement>, mediaList: List<Media>) {
//    articleElements.forEach { element ->
//        Text(element.content)
//        element.mediaIds.forEach { mediaId ->
//            val media = mediaList.find { it.id == mediaId }
//            if (media != null) {
//                Image(painter = rememberImagePainter(media.url), contentDescription = null)
//            }
//        }
//    }
//}

//@Composable
fun InsertMedia(mediaId: Int) {
    val currentElement = articleElements.lastOrNull()
    if (currentElement != null) {
        val newMediaIds = currentElement.mediaIds + mediaId
        articleElements[articleElements.lastIndex] = currentElement.copy(mediaIds = newMediaIds)
    }
}

//@Composable
//fun MediaList(mediaList: List<Media>) {
//    LazyRow {
//        items(mediaList) { media ->
//            Box(
//                modifier = Modifier
//                    .padding(4.dp)
//                    .clickable { InsertMedia(media.id) }
//            ) {
//                Image(
//                    painter = rememberImagePainter(media.url),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(80.dp)
//                        .clip(RoundedCornerShape(8.dp))
//                )
//            }
//        }
//    }
//}

//@Composable
//fun MediaList(mediaList: List<Media>, onMediaSelected: (Media) -> Unit) {
//    LazyRow {
//        items(mediaList) { media ->
//            Box(modifier = Modifier.padding(8.dp)) {
//                when (media) {
//                    is EditorElement.Image -> Image(
//                        painter = rememberImagePainter(media.url),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .size(64.dp)
//                            .clickable { onMediaSelected(media) }
//                    )
//                    is EditorElement.Video -> VideoPlayer(
//                        videoUrl = media.url,
//                        modifier = Modifier
//                            .size(64.dp)
//                            .clickable { onMediaSelected(media) }
//                    )
//                }
//            }
//        }
//    }
//}

//@Composable
//fun EditorContent(elements: List<EditorElement>, onElementChanged: (Int, EditorElement) -> Unit) {
//    elements.forEachIndexed { index, element ->
//        when (element) {
//            is EditorElement.Text -> {
//                TextField(
//                    value = element.content,
//                    onValueChange = { onElementChanged(index, element.copy(content = it)) }
//                )
//            }
//            is EditorElement.Image -> {
//                Image(
//                    painter = rememberImagePainter(element.url),
//                    contentDescription = null,
//                    modifier = Modifier.clickable {
//                        // Open a file picker or camera to select a new image.
//                    }
//                )
//            }
//            is EditorElement.Video -> {
//                VideoPlayer(
//                    url = element.url,
//                    onVideoCompleted = { /* Handle video completion */ },
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }
//        }
//    }
//}

//@Composable
//fun EditorContent(
//    elements: List<EditorElement>,
//    onElementChanged: (Int, EditorElement) -> Unit
//) {
//    for ((index, element) in elements.withIndex()) {
//        when (element) {
//            is EditorElement.Text -> TextEditor(
//                text = element.text,
//                onTextChanged = { newText ->
//                    onElementChanged(index, element.copy(text = newText))
//                }
//            )
//            is EditorElement.Image -> ImageEditor(
//                media = element.media,
//                onMediaSelected = { media ->
//                    onElementChanged(index, element.copy(media = media))
//                }
//            )
//            is EditorElement.Video -> VideoEditor(
//                media = element.media,
//                onMediaSelected = { media ->
//                    onElementChanged(index, element.copy(media = media))
//                }
//            )
//        }
//    }
//}

@Composable
fun TextEditor(text: String, onTextChanged: (String) -> Unit) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = onTextChanged,
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        )
    )

    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
    }
}

//@Composable
//fun Editor(html: String, mediaList: List<Media>) {
////    val elements = remember(mediaList, html) { parseHtml(html, mediaList) }
//    val editorElements = remember(mediaList, html) { parseHtml(html, mediaList) }.toMutableStateList()
//
//    Column {
//        EditorContent(
//            elements = editorElements,
//            onElementChanged = { index, element ->
//                editorElements[index] = element
//            }
//        )
//        MediaList(
//            mediaList = mediaList,
//            onMediaSelected = { media ->
//                val cursorIndex = editorElements.indexOfFirst { it is Cursor }
//                if (cursorIndex != -1) {
//                    val newElement = when (media) {
//                        is EditorElement.Image -> ImageElement(id = media.id)
//                        is EditorElement.Video -> VideoElement(id = media.id)
//                    }
//                    editorElements.add(cursorIndex, newElement)
//                }
//            }
//        )
//    }
//}

//@Composable
//fun Editor(html: String, mediaList: List<Media>) {
//    val editorElements = remember(html, mediaList) { parseHtml(html, mediaList) }.toMutableStateList()
//
//    Column {
//        EditorContent(
//            elements = editorElements,
//            onElementChanged = { index, element ->
//                editorElements[index] = element
//            }
//        )
//        Spacer(Modifier.height(16.dp))
//        MediaList(mediaList)
//    }
//}

fun parseHtml(html: String, mediaList: List<Media>): List<EditorElement> {
    val elements = mutableListOf<EditorElement>()

    val imageRegex = Regex("<image id=\"(\\w+)\" />")
    val videoRegex = Regex("<video id=\"(\\w+)\" />")

    var currentIndex = 0

    imageRegex.findAll(html).forEach { result ->
        val startIndex = result.range.first
        val endIndex = result.range.last + 1

        if (startIndex > currentIndex) {
            elements.add(EditorElement.Text(html.substring(currentIndex, startIndex)))
        }

        val id = result.groupValues[1]
        val media = mediaList.find { it.id == id }

        if (media is Media.Image) {
            elements.add(EditorElement.Image(media.id))
        }

        currentIndex = endIndex
    }

    videoRegex.findAll(html).forEach { result ->
        val startIndex = result.range.first
        val endIndex = result.range.last + 1

        if (startIndex > currentIndex) {
            elements.add(EditorElement.Text(html.substring(currentIndex, startIndex)))
        }

        val id = result.groupValues[1]
        val media = mediaList.find { it.id == id }

        if (media is Media.Video) {
            elements.add(EditorElement.Video(media.id))
        }

        currentIndex = endIndex
    }

    if (currentIndex < html.length) {
        elements.add(EditorElement.Text(html.substring(currentIndex)))
    }

    return elements
}

//fun parseHtml(html: String, mediaList: List<Media>): List<EditorElement> {
//    val doc = Jsoup.parse(html)
//    val elements = mutableListOf<EditorElement>()
//
//    doc.select("article").firstOrNull()?.let { article ->
//        val nodes = article.childNodes()
//        nodes.forEach { node ->
//            when {
//                node is TextNode -> {
//                    val text = node.text().trim()
//                    if (text.isNotEmpty()) {
//                        elements.add(EditorElement.Text(text))
//                    }
//                }
//                node is Element -> {
//                    val id = node.attr("id").toIntOrNull()
//                    val media = mediaList.find { it.id == id }
//
//                    when (node.tagName().toLowerCase()) {
//                        "image" -> {
//                            if (media != null) {
//                                elements.add(EditorElement.Image(media.id, media.url))
//                            }
//                        }
//                        "video" -> {
//                            if (media != null) {
//                                elements.add(EditorElement.Video(media.id, media.url))
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//    return elements
//}
