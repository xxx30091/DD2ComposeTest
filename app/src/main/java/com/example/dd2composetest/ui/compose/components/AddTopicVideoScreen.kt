package com.example.dd2composetest.ui.compose.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.dd2composetest.R
import com.example.dd2composetest.data.bean.ChooseVideoData
import com.example.dd2composetest.data.bean.EditArticleData
import com.example.dd2composetest.data.mock.MockData
import com.example.dd2composetest.data.mock.MockData.Companion.getMockChooseVideoData
import com.example.dd2composetest.utils.DateUtils

@Preview
@Composable
fun PreviewAddTopicVideoScreen() {
    AddTopicVideoScreen(NavHostController(LocalContext.current), {}, getMockChooseVideoData())
}

@Composable
fun AddTopicVideoScreen(
    navController: NavHostController,
    onNextClick: ( EditArticleData.Video ) -> Unit = {},
    videos: List<ChooseVideoData>
) {
    val selection = remember { mutableStateOf<ChooseVideoData?>(null) }
    val context = LocalContext.current
    Column(
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.Black),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "", tint = Color.White)
            }
            Text(
                text = "添加影片",
                modifier = Modifier
                    .weight(1f),
                color = Color.White,
                fontSize = 18.sp
            )
            Text(
                text = "下一步",
                modifier = Modifier
                    .padding(end = 15.dp)
                    .clickable {
                        selection.value?.let {
                            onNextClick(
                                EditArticleData.Video(
                                    id = it.id,
                                    coverUrl = it.coverUrl,
                                    previewUrl = it.previewUrl,
                                    isUnlocked = false
                                )
                            )
                            navController.popBackStack()
                        } ?: let {
                            Toast
                                .makeText(context, "请选择1个你的视频作品", Toast.LENGTH_SHORT)
                                .show()
                        }
                    },
                color = Color.White,
                fontSize = 18.sp
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(Color(0XFFf5f5f5))
                .padding(top = 4.dp, bottom = 4.dp),
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(videos) { video ->
                if (video != null) {
                    ChooseTopicVideoItem(video, selection)
                }
            }
        }
    }
}

@Composable
fun ChooseTopicVideoItem(
    video: ChooseVideoData = ChooseVideoData(coverUrl = MockData.url1, playCount = 3, duration = 90, title = "Test"),
    selection: MutableState<ChooseVideoData?>
) {
    Card(
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { selection.value = video }
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(12.dp),
        elevation = 1.dp
    ) {
        Box(modifier = Modifier) {
            Column() {
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
                            text = DateUtils.getMinuteSecondTime(video.duration.toLong()),
                            modifier = Modifier.padding(top = 4.dp, end = 6.dp, bottom = 4.dp),
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
                Text(
                    text = video.title,
                    modifier = Modifier.padding(start = 9.dp, top = 5.dp, end = 9.dp, bottom = 5.dp),
                    color = Color(0XFF222226),
                    fontSize = 13.sp,
                    maxLines = 1
                )
            }
            if (selection.value?.id == video.id)
                Spacer(
                    modifier = Modifier
                        .background(Color(0x99FFFFFF))
                        .matchParentSize()
                )
        }

    }
}


