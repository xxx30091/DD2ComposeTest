package com.example.dd2composetest.ui.compose.mine

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import coil.compose.rememberAsyncImagePainter
import com.example.dd2composetest.MainActivity
import com.example.dd2composetest.R
import com.example.dd2composetest.data.bean.*
import com.example.dd2composetest.data.mock.MockData
import com.example.dd2composetest.enum.Screen
import com.example.dd2composetest.ui.compose.MineItemHeader
import com.example.dd2composetest.ui.compose.components.*
import com.example.dd2composetest.ui.compose.imgId
import com.example.dd2composetest.ui.compose.videoUrl
import com.example.dd2composetest.utils.DateUtils.getMinuteSecondTime
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalTextApi
@Composable
fun MyWorksScreen(
    navController: NavHostController,
    activity: MainActivity
) {
    Column {
        Toolbar(navController = navController, title = "我的作品", rightBtnType = ToolBarType.NORMAL_TOOLBAR)
        MyWorkContent(navController, activity)
    }
}

@ExperimentalFoundationApi
@ExperimentalTextApi
fun NavGraphBuilder.myWorks(navController: NavHostController, activity: MainActivity) {
    composable(Screen.MY_WORKS_SCREEN.route) {
        MyWorksScreen(navController, activity)
    }
}

fun NavHostController.navigateToMyWorks() {
    navigate(Screen.MY_WORKS_SCREEN.route) {
        launchSingleTop = true
    }
}

@ExperimentalFoundationApi
@ExperimentalTextApi
@Composable
fun MyWorkContent(
    navController: NavHostController,
    activity: MainActivity,
    viewModel: MyWorkViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    var tabPosition by remember {
        mutableStateOf(0)
    }
    val pagerState = rememberPagerState(initialPage = 0)

    Scaffold(
        floatingActionButton = {
            IconButton(
                onClick = {
                    when (tabPosition) {
                        0 -> {
                            Log.i("Arthur_test", "添加視頻")
                        }
                        1 -> {
                            Log.i("Arthur_test", "添加主題")
                        }
                        2 -> {
                            Log.i("Arthur_test", "添加文章")
                        }
                    }
                },
                modifier = Modifier.clip(CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.addto),
                    contentDescription = "",
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(padding)
        ) {
            if (viewModel.transcodingCount != null && viewModel.transcodingCount > 0) {
                Text(
                    text = buildAnnotatedString {
                        append("正在處理視頻 ")
                        append(
                            AnnotatedString(
                                text = viewModel.transcodingCount.toString(),
                                spanStyle = SpanStyle(
                                    color = Color.Red
                                )
                            )
                        )
                        append(" 部")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0XFF656363)),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
            Row(
                modifier = Modifier
                    .height(45.dp)
                    .padding(start = 15.dp)
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .padding(end = 10.dp)
                        .fillMaxHeight()
                        .clickable {
                            tabPosition = 0
                            scope.launch {
                                pagerState.animateScrollToPage(0)
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.portfolio_tab_video),
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                        color = if (tabPosition == 0) Color(0xFFff932b) else Color.Black,
                        fontSize = 16.sp,
                    )
                    Divider(
                        modifier = Modifier
                            .height(3.dp)
                            .width(30.dp),
                        color = if (tabPosition == 0) Color(0xFFff932b) else Color.White
                    )
                }

                Column(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .padding(start = 10.dp, end = 10.dp)
                        .fillMaxHeight()
                        .clickable {
                            tabPosition = 1
                            scope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(id = R.string.portfolio_tab_video_topic),
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                        color = if (tabPosition == 1) Color(0xFFff932b) else Color.Black,
                        fontSize = 16.sp
                    )
                    Divider(
                        modifier = Modifier
                            .height(3.dp)
                            .width(30.dp),
                        color = if (tabPosition == 1) Color(0xFFff932b) else Color.White
                    )
                }

                Column(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .padding(start = 10.dp, end = 10.dp)
                        .fillMaxHeight()
                        .clickable {
                            tabPosition = 2
                            scope.launch {
                                pagerState.animateScrollToPage(2)
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(id = R.string.portfolio_tab_article_topic),
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                        color = if (tabPosition == 2) Color(0xFFff932b) else Color.Black,
                        fontSize = 16.sp
                    )
                    Divider(
                        modifier = Modifier
                            .height(3.dp)
                            .width(30.dp),
                        color = if (tabPosition == 2) Color(0xFFff932b) else Color.White
                    )
                }
            }
            HorizontalPager(
                count = 3,
                state = pagerState,
                userScrollEnabled = false
            ) { index ->
                when (index) {
                    0 -> MyVideoScreen(navController = navController, activity = activity, viewModel)
                    1 -> MyTopicScreen(viewModel)
                    2 -> MyArticleScreen(viewModel)
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalTextApi
@Composable
fun MyVideoScreen(
    navController: NavHostController,
    activity: MainActivity,
    viewModel: MyWorkViewModel
) {
    Column {
        HotVideoList(navController, viewModel)
        DateTimeFilter(
            showShadow = false,
            startDate = viewModel.startDate,
            endDate = viewModel.endDate,
            placeType = TimeFilterType.SHOW_COUNTS.type,
            videoCount = viewModel.videos.size,
            activity = activity,
            viewModel = viewModel
        )
        VideoList(navController, viewModel)
    }
}

@ExperimentalFoundationApi
@ExperimentalTextApi
@Composable
fun HotVideoList(
    navController: NavHostController,
    viewModel: MyWorkViewModel
) {
    val videos = viewModel.topVideos
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0XFFf5f5f5)),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "長按視頻可進行編輯",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            color = Color(0X8A000000),
            textAlign = TextAlign.Center
        )
        if (!videos.isNullOrEmpty()) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                contentPadding = PaddingValues(start = 10.dp, end = 10.dp)
            ) {
                items(videos) { video ->
                    Column(
                        modifier = Modifier.fillParentMaxWidth(0.5f)
                    ) {
                        if (video != null) {
                            VideoItem(video = video, true, navController)
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalTextApi
@Composable
fun VideoList(
    navController: NavHostController,
    viewModel: MyWorkViewModel
) {
    val videos = viewModel.videos
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0XFFf5f5f5))
            .padding(top = 4.dp),
    ) {
        if (videos.isNullOrEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_no_video),
                    contentDescription = "",
                    modifier = Modifier.size(80.dp)
                )
                Text(
                    text = "暫無視頻",
                    modifier = Modifier.padding(top = 12.dp),
                    color = Color(R.color.color_default_text_view)
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(top = 0.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp)
        ) {
            items(videos) { video ->
                if (video != null) {
//                    FansVideoItem(viewModel, video, selection)
                    VideoItem(video = video, false, navController)
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalTextApi
@Composable
fun VideoItem(
    video: MyVideoBean,
    isHot: Boolean = true,
    navController: NavHostController
) {
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        EditVideoDialog(
            navController = navController, setShowDialog = { showDialog.value = it }, isHot = isHot
        )
    }
    Column(
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .clickable {

            }
            .combinedClickable(
                onClick = {
                    // 解鎖/播放事件
                    Log.i("Arthur_test", "短按 解鎖/播放")
                },
                onLongClick = {
//                    if (isHot) Log.i("Arthur_test", "長按取消置頂")
//                    else Log.i("Arthur_test", "長按編輯置頂")
                    showDialog.value = true
                }
            )
    ) {
        Box(
            modifier = Modifier
                .height(94.dp)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = video.coverUrl),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            if (isHot) {
                Text(
                    text = "hot",
                    modifier = Modifier
                        .height(13.dp)
                        .background(
                            color = Color(0XFFff6969),
                            shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp)
                        )
                        .padding(start = 8.dp, end = 8.dp)
                        .align(Alignment.TopEnd),
                    color = Color.White,
                    fontSize = 10.sp,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
            }
            Image(
                painter = painterResource(
                    R.mipmap.play
//                    id = if (video.isUnlocked) R.mipmap.play
//                    else R.mipmap.lock
                ),
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .align(Alignment.Center)
//                    .clickable {  }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.mipmap.eye),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 6.dp, top = 4.dp, bottom = 4.dp)
                        .size(20.dp),
                    tint = Color.White
                )
                Text(
                    text = video.playCount.toString(),
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .weight(1f),
                    color = Color.White,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = getMinuteSecondTime(video.duration),
                    modifier = Modifier.padding(top = 4.dp, end = 6.dp, bottom = 4.dp),
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
        Text(
            text = video.title,
            modifier = Modifier.padding(start = 9.dp, top = 5.dp, end = 9.dp),
            color = Color(0XFF222226),
            fontSize = 13.sp,
            maxLines = 1
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.mipmap.like_outline),
                contentDescription = "",
                modifier = Modifier
                    .padding(start = 9.dp)
                    .size(14.dp),
            )
            Text(
                text = video.likeCount.toString(),
                modifier = Modifier
                    .padding(start = 4.dp)
                    .weight(1f),
                color = Color(0XFF696969),
                fontSize = 10.sp,
                textAlign = TextAlign.Start
            )
            Text(
                text = video.creationDate,
                modifier = Modifier.padding(end = 6.dp),
                color = Color(0XFF959595),
                fontSize = 10.sp
            )
        }
    }
}

@Composable
fun EditVideoDialog(
    navController: NavHostController,
    setShowDialog: (Boolean) -> Unit,
    isHot: Boolean
) {
    Dialog(
        onDismissRequest = { setShowDialog(false) },
    ) {
        Column(modifier = Modifier.background(Color.White)) {
            EditVideoDialogItem(title = "編輯") {
                // 編輯事件
                setShowDialog(false)
            }
            EditVideoDialogItem(title = if (isHot) "取消置頂" else "置頂") {
                // 置頂事件
                setShowDialog(false)
            }
            EditVideoDialogItem(title = "刪除") {
                // 刪除事件
                setShowDialog(false)
            }
        }
    }
}

@Preview
@Composable
fun PreviewEditVideoDialog() {
    EditVideoDialog(navController = NavHostController(LocalContext.current), setShowDialog = {}, isHot = true)
}

@Composable
fun EditVideoDialogItem(
    title: String,
    onClick: () -> Unit
) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick.invoke() }
            .padding(start = 20.dp, top = 12.dp, bottom = 12.dp, end = 20.dp),
        color = Color(R.color.color_default_text_view),
        fontWeight = FontWeight.Bold
    )
}

@Preview
@Composable
fun MyTopicScreen(
    viewModel: MyWorkViewModel = MyWorkViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        var topicType by remember {
            mutableStateOf(0)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
        ) {
            FilterCell(
                title = "必看主題",
                isChecked = topicType == 0,
                onClick = {
                    topicType = 0
                }
            )
            FilterCell(
                title = "用戶求片",
                isChecked = topicType == 1,
                onClick = {
                    topicType = 1
                }
            )
        }
        when (topicType) {
            0 -> MyTopicList(viewModel = viewModel)
            1 -> MyAskTopicList(viewModel = viewModel)
        }
    }
}

@Composable
fun MyTopicList(
    viewModel: MyWorkViewModel
) {
    val topics = viewModel.myTopics
    if (topics.isNullOrEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.mipmap.empty_topic),
                contentDescription = "",
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = "暫無主題",
                modifier = Modifier.padding(top = 12.dp),
                color = Color(R.color.color_default_text_view)
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(topics) { topic ->
                if (topic != null) {
                    MyTopicItem(topicItem = topic)
                }
            }
        }
    }
}

const val inlineArrow = "inlineArrow"
const val inlineRemove = "inlineRemove"
const val inlineEye = "inlineEye"
const val inlineLikeOutline = "inlineLikeOutline"
const val inlineVideo = "inlineVideo"
val myWorkInline = mapOf(
    Pair(
        inlineArrow,
        InlineTextContent(
            placeholder = Placeholder(
                width = 24.sp,
                height = 24.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            )
        ) {
            Icon(
                painter = painterResource(id = R.mipmap.arrow_right),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                tint = Color(R.color.color_default_text_view)
            )
        }
    ),
    Pair(
        inlineRemove,
        InlineTextContent(
            placeholder = Placeholder(
                width = 24.sp,
                height = 24.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.topic_question_del),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 4.dp, start = 0.dp, bottom = 4.dp)
                    .size(15.dp),
            )
        }
    ),
    Pair(
        inlineEye,
        InlineTextContent(
            placeholder = Placeholder(
                width = 24.sp,
                height = 24.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            )
        ) {
            Icon(
                painter = painterResource(id = R.mipmap.eye),
                contentDescription = "",
                modifier = Modifier.padding(top = 4.dp, start = 4.dp, bottom = 4.dp),
                tint = Color(R.color.color_default_text_view)
            )
        }
    ),
    Pair(
        inlineLikeOutline,
        InlineTextContent(
            placeholder = Placeholder(
                width = 24.sp,
                height = 24.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            )
        ) {
            Icon(
                painter = painterResource(id = R.mipmap.like_outline),
                contentDescription = "",
                modifier = Modifier.padding(top = 4.dp, start = 4.dp, bottom = 4.dp),
                tint = Color(R.color.color_default_text_view)
            )
        }
    ),
    Pair(
        inlineVideo,
        InlineTextContent(
            placeholder = Placeholder(
                width = 24.sp,
                height = 24.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            )
        ) {
            Icon(
                painter = painterResource(id = R.mipmap.video),
                contentDescription = "",
                modifier = Modifier.padding(top = 4.dp, start = 4.dp, bottom = 4.dp),
                tint = Color(R.color.color_default_text_view)
            )
        }
    ),
)

@Composable
fun MyTopicItem(
    topicItem: TopicItem
) {
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        CommonAlertDialog(
            setShowDialog = {showDialog.value = it},
            title = "提醒",
            content = "是否下架該主題？",
            action = {}
        )
    }
    Column(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = topicItem.name,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 8.dp, bottom = 8.dp),
                fontSize = 14.sp
            )
            Text(
                text = buildAnnotatedString {
                    append("共有${topicItem.videoUserCount}個作者")
                    appendInlineContent(inlineArrow)
                },
                color = Color(R.color.color_default_text_view),
                fontSize = 12.sp,
                inlineContent = myWorkInline
            )
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(end = 10.dp)
        ) {
            items(topicItem.videos) { video ->
                MyTopicVideoItem(imgUrl = video.coverUrl)
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                    appendInlineContent(inlineEye)
                    append(" ${topicItem.playCount}")
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 4.dp, bottom = 4.dp),
                fontSize = 12.sp,
                inlineContent = myWorkInline
            )
            Text(
                text = buildAnnotatedString {
                    appendInlineContent(inlineLikeOutline)
                    append(" ${topicItem.likeCount}")
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 4.dp, bottom = 4.dp),
                inlineContent = myWorkInline
            )
            Text(
                text = buildAnnotatedString {
                    appendInlineContent(inlineVideo)
                    append(" ${topicItem.videoCount}")
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 4.dp, bottom = 4.dp),
                inlineContent = myWorkInline
            )
            Text(
                text = topicItem.creationDateTime,
                modifier = Modifier,
                color = Color(0XFF6D7278),
                fontSize = 12.sp,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = buildAnnotatedString {
                    appendInlineContent(inlineRemove)
                    append("下架")
                },
                modifier = Modifier
                    .padding(top = 2.dp, bottom = 6.dp)
                    .background(
                        color = Color(0XFFF6F6F6),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clip(RoundedCornerShape(20.dp))
                    .clickable { showDialog.value = true }
                    .padding(start = 6.dp, top = 3.dp, end = 10.dp, bottom = 3.dp),
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                inlineContent = myWorkInline
            )
        }
    }
}

@Composable
fun MyTopicVideoItem(imgUrl: String?) {
    Box(
        modifier = Modifier
            .padding(start = 1.dp, end = 1.dp)
            .size(100.dp)
    ) {
        if (imgUrl != null) {
            Image(
                painter = rememberAsyncImagePainter(model = imgUrl),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = painterResource(id = R.mipmap.logo_gray),
                contentDescription = "",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun MyAskTopicList(
    viewModel: MyWorkViewModel
) {
    val request = viewModel.myAskTopic
    if (request.isNullOrEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.mipmap.empty_topic),
                contentDescription = "",
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = "暫無主題",
                modifier = Modifier.padding(top = 12.dp),
                color = Color(R.color.color_default_text_view)
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(request) { request ->
                if (request != null) {
                    MyAskTopicItem(item = request)
                }
            }
        }
    }
}

@Composable
fun MyAskTopicItem(item: TopicAskItem) {
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        CommonAlertDialog(
            setShowDialog = { showDialog.value = it },
            title = "提醒",
            content = "是否下架該主題？",
            action = {}
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp)
            .background(Color.White)
            .padding(start = 15.dp, top = 8.dp, end = 15.dp, bottom = 8.dp)

    ) {
        Row(
            modifier = Modifier
        ) {
            Box(
                modifier = Modifier
                    .size(width = 100.dp, height = 60.dp)
                    .clip(RoundedCornerShape(4.dp))
            ) {
                Image(
                    painter = rememberAsyncImagePainter(item.coverUrl),
//                    painter = painterResource(id = item.coverUrl),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                if (item.coverUrl == null) {
                    Image(
                        painter = painterResource(id = R.mipmap.logo_gray),
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.Center)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 10.dp),
            ) {
                Text(
                    text = item.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp)
                        .align(Alignment.TopStart),
                    fontSize = 13.sp
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.creationDateTime, color = Color(R.color.color_default_text_view),
                        fontSize = 12.sp
                    )
                    Text(
                        text = "●", modifier = Modifier.padding(start = 6.dp, end = 6.dp),
                        color = Color(R.color.color_default_text_view), fontSize = 3.sp
                    )
                    Text(
                        text = "想看 ${item.favoriteCount}", color = Color(R.color.color_default_text_view),
                        fontSize = 12.sp
                    )
                    Text(
                        text = "●", modifier = Modifier.padding(start = 6.dp, end = 6.dp),
                        color = Color(R.color.color_default_text_view), fontSize = 3.sp
                    )
                    Text(
                        text = "視頻 ${item.videoCount}", color = Color(R.color.color_default_text_view),
                        fontSize = 12.sp
                    )

                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = buildAnnotatedString {
                    appendInlineContent(inlineRemove)
                    append("下架")
                },
                modifier = Modifier
                    .background(
                        color = Color(0XFFF6F6F6),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {
                        showDialog.value = true
                    }
                    .padding(start = 5.dp, top = 3.dp, end = 10.dp, bottom = 3.dp),
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                inlineContent = myWorkInline
            )
        }
    }
}

@Composable
fun MyArticleScreen(
    viewModel: MyWorkViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        var articleType by remember {
            mutableStateOf(0)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
        ) {
            FilterCell(
                title = "必看文章",
                isChecked = articleType == 0,
                onClick = {
                    articleType = 0
                }
            )
            FilterCell(
                title = "用戶提問",
                isChecked = articleType == 1,
                onClick = {
                    articleType = 1
                }
            )
        }
        when (articleType) {
//            0 -> MyTopicList(viewModel = viewModel)
//            1 -> MyAskTopicList(viewModel = viewModel)
        }
    }
}

@Composable
fun MyArticleList() {

}

@Preview
@Composable
fun MyArticleItem(
    item: TopicArticleItem = MockData().getMockMyArticles()[0]
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(4.dp))
            .clickable { Log.i("Arthur_test", "click article item") },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = item.title,
                    fontSize = 16.sp,
                    color = Color(0xff3a3a3c),
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = item.content,
                    fontSize = 14.sp,
                    color = Color(0xff3a3a3c),
                    modifier = Modifier
                        .padding(top = 8.dp),
                    maxLines = 2
                )
            }

            if (item.imageUrls?.size == 1 && item.videos.isNullOrEmpty()) {
                Image(
                    painter = rememberAsyncImagePainter(item.imageUrls?.get(0)),
                    contentDescription = "",
                    modifier = Modifier
                        .size(76.dp)
                        .aspectRatio(1f / 1f),
                    contentScale = ContentScale.Crop
                )
            }
        }

        // 有影片時只顯示影片
        if (!item.videos.isNullOrEmpty()) {
            Box(
                modifier = Modifier
                    .height(143.dp)
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = item.videos!![0].coverUrl),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Icon(
                    painter = painterResource(id = R.mipmap.play),
                    contentDescription = "",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .clickable { }
                )
            }
        }

        // 多圖
        if (item.videos.isNullOrEmpty() && item.imageUrls?.size!! > 1) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                Image(
                    painter = rememberAsyncImagePainter(model = item.imageUrls!![0]),
                    modifier = Modifier.aspectRatio(1f / 1f),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = rememberAsyncImagePainter(model = item.imageUrls!![1]),
                    modifier = Modifier.aspectRatio(1f / 1f),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )

                when (imgId.size) {
                    2 -> Spacer(modifier = Modifier.aspectRatio(1f / 1f))
                    else -> {
                        Image(
                            painter = rememberAsyncImagePainter(model = item.imageUrls!![2]),
                            modifier = Modifier.aspectRatio(1f / 1f),
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "点赞 0  ∙  收藏 12  ∙  评论  112  ∙  观看 12",
                fontSize = 12.sp,
                color = Color(0xffaeaeb2)
            )
        }
    }
}

@Composable
fun ArticleTagItem(title: String, position: Int) {
    Text(
        text = "#$title",
        modifier = Modifier
            .padding(start = 2.dp, top = 1.dp, end = 2.dp, bottom = 1.dp)
            .background(
                color = when (position) {
                    0 -> Color(0XFFFF9D3E)
                    1 -> Color(0XFF34B1FD)
                    2 -> Color(0XFF55A870)
                    else -> Color(0XFFFF9D3E)
                },
                shape = RoundedCornerShape(2.dp)
            )
            .padding(4.dp),
        color = Color.White,
        fontSize = 10.sp
    )
}

@Composable
fun MyQuestionList() {

}

@ExperimentalFoundationApi
@ExperimentalTextApi
@Preview
@Composable
fun PreviewMyWorkScreen() {
    MyWorkContent(
        NavHostController(LocalContext.current), MainActivity())
}
