package com.example.dd2composetest.ui.compose

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.dd2composetest.R
import com.example.dd2composetest.ThisApp
import com.example.dd2composetest.enum.Screen
import com.example.dd2composetest.ui.compose.mine.navigateToMyGoldCoin
import com.example.dd2composetest.ui.compose.mine.navigateToMyRedCoin
import com.example.dd2composetest.ui.compose.payment.navigateToPayChoose
import com.example.dd2composetest.ui.compose.setting.navigateToSetting

val contrast = 2f // 0f..10f (1 should be default)
val brightness = -210f // -255f..255f (0 should be default)
val colorMatrix = floatArrayOf(
    contrast, 0f, 0f, 0f, brightness,
    0f, contrast, 0f, 0f, brightness,
    0f, 0f, contrast, 0f, brightness,
    0f, 0f, 0f, 1f, 0f,
)
// 用在圖片 Modifier colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)) 以改變色度、銳利度

val colorGoldList = arrayOf(
    0.0f to Color(0xfff9e87a),
    0.5f to Color(0xfff9e678),
    1f to Color(0xfff4c057)
)

val inlineId = "inlineContent"
val inlineContent = mapOf(
    Pair(
        // This tells the [CoreText] to replace the placeholder string "[icon]" by
        // the composable given in the [InlineTextContent] object.
        inlineId,
        InlineTextContent(
            // Placeholder tells text layout the expected size and vertical alignment of
            // children composable.
            Placeholder(
                width = 12.sp,
                height = 12.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            )
        ) {
            // This Icon will fill maximum size, which is specified by the [Placeholder]
            // above. Notice the width and height in [Placeholder] are specified in TextUnit,
            // and are converted into pixel by text layout.
            Icon(painterResource(id = R.drawable.ic_rocket), "", tint = Color(0xffff3b30))
        }
    )
)

//val hasUpdate = true

private val topicVideos = listOf(
    R.drawable.a,
    R.drawable.b,
    R.drawable.c,
    R.drawable.d,
    R.drawable.e,
)
val videoCounts = 0
val articleCounts = 100
val imgId =
    listOf<Int>(
        R.drawable.f,
//    R.drawable.g,
//    R.drawable.h,
    )
val videoUrl: Int? =
    null

//            R.drawable.c
val topicCounts = 87

@Composable
fun MineHeader(imgId: Int, isPlan: Boolean, navController: NavHostController) {
    var hasUpdate by remember { mutableStateOf(true) }
    var hasNewMessage by remember { mutableStateOf(true) }
    var hasNewSystemMessage by remember { mutableStateOf(true) }
    //  模糊效果僅適用於 Android 12 以上版本。嘗試在較舊的 Android 版本中使用這個修飾符會予以忽略
    Box(
        modifier = Modifier
            .fillMaxWidth()
//                .height(202.dp)
            .padding(bottom = 8.dp)
            .background(Color.White)
    ) {
        // 底層背景
        Image(
            painter = painterResource(id = imgId),
            contentDescription = "",
            modifier = Modifier
                .blur(radius = 9.dp, BlurredEdgeTreatment(RectangleShape))
                .fillMaxWidth()
                .height(141.dp),
            contentScale = ContentScale.Crop,
        )
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(141.dp)
            .background(Color(0x4d000000))) { }

        // 內容
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 8.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "设置",
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.clickable (onClick = { navController.navigateToSetting() }),
                )
            }
            Box(
                modifier = Modifier
                    .height(64.dp)
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),
//                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = imgId),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(
                            BorderStroke(0.5.dp, Color.White),
                            CircleShape
                        )
                        .clickable(onClick = { navController.navigateToMyData() }),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .padding(start = 58.dp)
                        .align(Alignment.CenterStart),
//                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "我是大哥", fontSize = 20.sp, color = Color.White, maxLines = 1)
                    Text(
                        text = if (isPlan) "包時段將於2023-09-30 00:00到期" else "目前无包时段优惠",
                        color = if (isPlan) Color(0xffce9f09) else Color(0xffb3ffffff),
                        fontSize = 12.sp,
                        maxLines = 1
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.BottomEnd),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    IconButton(
                        onClick = {},
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_mine_history), contentDescription = "",
                            modifier = Modifier.size(24.dp), tint = Color.White,
                        )
                    }
                    IconButton(
                        onClick = { hasUpdate = !hasUpdate },
                        modifier = Modifier.size(24.dp)
                    ) {
                        BadgedBox(
                            badge = {
                                if (hasUpdate) Divider(modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape), color = Color.Red)
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_download), contentDescription = "",
                                modifier = Modifier.size(24.dp), tint = Color.White,
                            )
                        }
                    }
                    IconButton(
                        onClick = { hasNewMessage = !hasNewMessage },
                        modifier = Modifier.size(24.dp)
                    ) {
                        BadgedBox(
                            badge = {
                                if (hasNewMessage) Divider(modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape), color = Color.Red)
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_customer_service), contentDescription = "",
                                modifier = Modifier.size(24.dp), tint = Color.White,
                            )
                        }
                    }
                    IconButton(
                        onClick = { hasNewSystemMessage = !hasNewSystemMessage },
                        modifier = Modifier.size(24.dp)
                    ) {
                        BadgedBox(
                            badge = {
                                if (hasNewSystemMessage) Divider(modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape), color = Color.Red)
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_notification), contentDescription = "",
                                modifier = Modifier.size(24.dp), tint = Color.White,
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        if (hasUpdate) 78.dp else 50.dp
                    )
                    .shadow(1.dp, shape = RoundedCornerShape(13.dp))
                    .background(
                        brush = Brush.horizontalGradient(colorStops = colorGoldList),
                        shape = RoundedCornerShape(13.dp)
                    )
            ) {
                Row( // 白色底
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(start = 14.dp, end = 7.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "金币", fontSize = 12.sp, color = Color(0xff777c81),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "6543. 万",
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                Toast
                                    .makeText(ThisApp.instance, "進入金幣頁面", Toast.LENGTH_SHORT)
                                    .show()
                                navController.navigateToMyGoldCoin()
                            },
                        fontSize = 18.sp,
                        color = Color(0xffce9f09),
                        textAlign = TextAlign.Center,
                        letterSpacing = (-0.48).sp,
                        maxLines = 1
                    )
                    Divider(
                        modifier = Modifier
                            .width(1.dp)
                            .height(17.dp),
                        color = Color(0xffe8e8e8)
                    )

                    Text(
                        text = "红币",
                        modifier = Modifier.padding(start = 8.dp),
                        fontSize = 12.sp, color = Color(0xff777c81),
                        textAlign = TextAlign.Start
                    )

                    Text(
                        text = "1234.65 万",
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                Toast
                                    .makeText(ThisApp.instance, "進入紅幣頁面", Toast.LENGTH_SHORT)
                                    .show()
                                navController.navigateToMyRedCoin()
                            },
                        fontSize = 18.sp,
                        color = Color(0xffcc2014),
                        textAlign = TextAlign.Center,
                        letterSpacing = (-0.48).sp,
                        maxLines = 1
                    )
                    TextButton(
                        onClick = { navController.navigateToPayChoose() },
                        modifier = Modifier
                            .height(32.dp)
                            .width(75.dp),
                        shape = RoundedCornerShape(9.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xff4e4e4f)),
                        contentPadding = PaddingValues(6.dp)
                    ) {
                        Text(
                            text = "充值", modifier = Modifier.fillMaxSize(),
                            color = Color.White, fontSize = 15.sp, textAlign = TextAlign.Center
                        )
                    }
                }

                if (hasUpdate) {
                    Row(
                        modifier = Modifier
                            .clickable { }
                            .padding(start = 15.dp, end = 10.dp, top = 6.dp, bottom = 6.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                appendInlineContent(inlineId)
                                append(" 最新升级包已发布")
                            },
                            modifier = Modifier.padding(start = 2.dp),
                            color = Color(0xffff3b30),
                            fontSize = 12.sp,
                            inlineContent = inlineContent
                        )
                        Text(
                            text = "马上点击升级，更新最新版本",
                            color = Color.Black,
                            fontSize = 12.sp,
                            textAlign = TextAlign.End
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun PreviewMineHeader(imgId: Int = R.drawable.a, isPlan: Boolean = true, navController: NavHostController = NavHostController(LocalContext.current)) {
    var hasUpdate by remember { mutableStateOf(true) }
    var hasNewMessage by remember { mutableStateOf(true) }
    var hasNewSystemMessage by remember { mutableStateOf(true) }
    //  模糊效果僅適用於 Android 12 以上版本。嘗試在較舊的 Android 版本中使用這個修飾符會予以忽略
    Box(
        modifier = Modifier
            .fillMaxWidth()
//                .height(202.dp)
            .padding(bottom = 8.dp)
            .background(Color.White)
    ) {
        // 底層背景
        Image(
            painter = painterResource(id = imgId),
            contentDescription = "",
            modifier = Modifier
                .blur(radius = 9.dp, BlurredEdgeTreatment(RectangleShape))
                .fillMaxWidth()
                .height(141.dp),
            contentScale = ContentScale.Crop,
        )
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(141.dp)
            .background(Color(0x4d000000))) { }

        // 內容
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 8.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "设置",
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.clickable { },
                )
            }
            Box(
                modifier = Modifier
                    .height(64.dp)
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),
//                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = imgId),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(
                            BorderStroke(0.5.dp, Color.White),
                            CircleShape
                        )
                        .clickable(onClick = { navController.navigateToMyData() }),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .padding(start = 58.dp)
                        .align(Alignment.CenterStart),
//                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "我是大哥", fontSize = 20.sp, color = Color.White, maxLines = 1)
                    Text(
                        text = if (isPlan) "包時段將於2023-09-30 00:00到期" else "目前无包时段优惠",
                        color = if (isPlan) Color(0xffce9f09) else Color(0xffb3ffffff),
                        fontSize = 12.sp,
                        maxLines = 1
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.BottomEnd),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    IconButton(
                        onClick = {},
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_mine_history), contentDescription = "",
                            modifier = Modifier.size(24.dp), tint = Color.White,
                        )
                    }
                    IconButton(
                        onClick = { hasUpdate = !hasUpdate },
                        modifier = Modifier.size(24.dp)
                    ) {
                        BadgedBox(
                            badge = {
                                if (hasUpdate) Divider(modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape), color = Color.Red)
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_download), contentDescription = "",
                                modifier = Modifier.size(24.dp), tint = Color.White,
                            )
                        }
                    }
                    IconButton(
                        onClick = { hasNewMessage = !hasNewMessage },
                        modifier = Modifier.size(24.dp)
                    ) {
                        BadgedBox(
                            badge = {
                                if (hasNewMessage) Divider(modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape), color = Color.Red)
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_customer_service), contentDescription = "",
                                modifier = Modifier.size(24.dp), tint = Color.White,
                            )
                        }
                    }
                    IconButton(
                        onClick = { hasNewSystemMessage = !hasNewSystemMessage },
                        modifier = Modifier.size(24.dp)
                    ) {
                        BadgedBox(
                            badge = {
                                if (hasNewSystemMessage) Divider(modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape), color = Color.Red)
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_notification), contentDescription = "",
                                modifier = Modifier.size(24.dp), tint = Color.White,
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        if (hasUpdate) 78.dp else 50.dp
                    )
                    .shadow(1.dp, shape = RoundedCornerShape(13.dp))
                    .background(
                        brush = Brush.horizontalGradient(colorStops = colorGoldList),
                        shape = RoundedCornerShape(13.dp)
                    )
            ) {
                Row( // 白色底
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(start = 14.dp, end = 7.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "金币", fontSize = 12.sp, color = Color(0xff777c81),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "6543. 万",
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                Toast
                                    .makeText(ThisApp.instance, "進入金幣頁面", Toast.LENGTH_SHORT)
                                    .show()
                            },
                        fontSize = 18.sp,
                        color = Color(0xffce9f09),
                        textAlign = TextAlign.Center,
                        letterSpacing = (-0.48).sp,
                        maxLines = 1
                    )
                    Divider(
                        modifier = Modifier
                            .width(1.dp)
                            .height(17.dp),
                        color = Color(0xffe8e8e8)
                    )

                    Text(
                        text = "红币",
                        modifier = Modifier.padding(start = 8.dp),
                        fontSize = 12.sp, color = Color(0xff777c81),
                        textAlign = TextAlign.Start
                    )

                    Text(
                        text = "1234.65 万",
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                Toast
                                    .makeText(ThisApp.instance, "進入紅幣頁面", Toast.LENGTH_SHORT)
                                    .show()
                            },
                        fontSize = 18.sp,
                        color = Color(0xffcc2014),
                        textAlign = TextAlign.Center,
                        letterSpacing = (-0.48).sp,
                        maxLines = 1
                    )
                    TextButton(
                        onClick = { navController.navigateToPayChoose() },
                        modifier = Modifier
                            .height(32.dp)
                            .width(75.dp),
                        shape = RoundedCornerShape(9.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xff4e4e4f)),
                        contentPadding = PaddingValues(6.dp)
                    ) {
                        Text(
                            text = "充值", modifier = Modifier.fillMaxSize(),
                            color = Color.White, fontSize = 15.sp, textAlign = TextAlign.Center
                        )
                    }
                }

                if (hasUpdate) {
                    Row(
                        modifier = Modifier
                            .clickable { }
                            .padding(start = 15.dp, end = 10.dp, top = 6.dp, bottom = 6.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                appendInlineContent(inlineId)
                                append(" 最新升级包已发布")
                            },
                            modifier = Modifier.padding(start = 2.dp),
                            color = Color(0xffff3b30),
                            fontSize = 12.sp,
                            inlineContent = inlineContent
                        )
                        Text(
                            text = "马上点击升级，更新最新版本",
                            color = Color.Black,
                            fontSize = 12.sp,
                            textAlign = TextAlign.End
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun PreviewInfoButton() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        InfoButtonItem(iconId = R.drawable.mine_engage, "我的运营", 0xff3b3a95,
            "243", "粉丝", "143", "获赞")
        Spacer(modifier = Modifier.height(8.dp))
        InfoButtonItem(iconId = R.drawable.mine_follow, "我的关注", 0xff36914d,
            "8811", "作者", "2143", "主题")
    }
}

@Composable
fun InfoButtonItem(
    iconId: Int, title: String, textColor: Long, content1: String, title1: String,
    content2: String, title2: String, action: () -> Unit = { }
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.White)
            .clickable(onClick = action),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = iconId),
            contentDescription = "",
            modifier = Modifier
                .size(28.dp)
                .padding(start = 8.dp)
        )
        Text(
            text = title,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
        Text(
            text = content1,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp),
            fontSize = 20.sp,
            color = Color(textColor),
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.48).sp,
            maxLines = 1
        )
        Text(
            text = title1, fontSize = 12.sp,
            color = Color(0xff9d9d9d)
        )
        Text(
            text = content2,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp),
            fontSize = 20.sp,
            color = Color(textColor),
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.48).sp,
            maxLines = 1
        )
        Text(
            text = title2,
            modifier = Modifier.padding(end = 8.dp),
            fontSize = 12.sp,
            color = Color(0xff9d9d9d)
        )
    }
}

@Preview
@Composable
fun PreviewMineContent() {
    val scroll = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
            .verticalScroll(scroll),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LatestVideoItem()
        LatestArticleItem()
        LatestTopicItem()
    }
}

@Composable
fun MineItemHeader(subtitle: String, title: String, itemCounts: Int) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.padding(start = 8.dp, top = 9.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.a),
                    contentDescription = "",
                    modifier = Modifier
                        .size(22.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "零之轨迹", modifier = Modifier.padding(start = 4.dp),
                    fontSize = 14.sp, color = Color(0xff3a3a3c)
                )
                Text(
                    text = subtitle, modifier = Modifier.padding(start = 8.dp),
                    fontSize = 14.sp, color = Color(0xffaeaeb2)
                )
            }
            Box(
                modifier = Modifier
                    .height(25.dp)
                    .background(
                        Color(0xfff2f2f7),
                        RoundedCornerShape(bottomStart = 8.dp, topEnd = 4.dp)
                    )
                    .clickable {
                        Log.i("Arthur_test", "click more")
                    }
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("更多")
                        append(
                            AnnotatedString(
                                text = if (itemCounts > 99) "(99+)"
                                else if (itemCounts == 0) ""
                                else "($itemCounts)",
                                spanStyle = SpanStyle(
                                    color = Color(0xffff3b30
                                    )
                                )
                            )
                        )
                    },
                    modifier = Modifier.padding(start = 8.dp, end = 4.dp, top = 4.dp, bottom = 4.dp),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun LatestVideoItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(4.dp))

            .clickable { Log.i("Arthur_test", "click video item") },
    ) {
        MineItemHeader("发布了新的视频", "这是视频的标题啊啊啊啊啊啊啊啊啊", 0)

        Text(
            text = "这是视频的标题啊啊啊啊啊啊啊啊啊", fontSize = 16.sp, color = Color(0xff3a3a3c),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp),
            fontWeight = FontWeight.Bold,
        )

        val isVideoUnlocked = true
        Box(
            modifier = Modifier
                .height(143.dp)
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.i), contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(
                    id = if (!isVideoUnlocked) R.mipmap.lock else R.mipmap.play
                ),
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
//                            if (isVideoUnlocked) {}
//                            else {}
                    }
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "00:00",
                    fontSize = 10.sp,
                    modifier = Modifier
                        .background(color = Color(0xffF7F7F7))
                        .padding(start = 3.dp, end = 3.dp)
                        .clip(RoundedCornerShape(2.dp)),
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "点赞 0  ∙ 收藏 12 ∙ 观看 12", fontSize = 12.sp, color = Color(0xffaeaeb2))
            Text(text = "2022-06-21", fontSize = 14.sp, color = Color(0xff757575))
        }
    }
}

@Preview
@Composable
fun LatestArticleItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(4.dp))
            .clickable { Log.i("Arthur_test", "click article item") },
    ) {
        MineItemHeader("发布了新的文章", "这是文章的标题啊啊啊啊啊啊啊啊啊", 111)

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
                    text = "这是文章的标题啊啊啊啊啊啊啊啊啊",
                    fontSize = 16.sp,
                    color = Color(0xff3a3a3c),
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "性高潮可以透过许多性活动来实现：XXX，sieruew，这是文章的内容简要啊…",
                    fontSize = 14.sp,
                    color = Color(0xff3a3a3c),
                    modifier = Modifier
                        .padding(top = 8.dp),
                    maxLines = 2
                )
            }

            if (imgId.size == 1 && videoUrl == null) {
                Image(
                    painter = painterResource(id = imgId[0]),
                    contentDescription = "",
                    modifier = Modifier
                        .size(76.dp)
                        .aspectRatio(1f / 1f),
                    contentScale = ContentScale.Crop
                )
            }
        }

        // 有影片時只顯示影片
        val isVideoUnlocked = true
        if (videoUrl != null) {
            Box(
                modifier = Modifier
                    .height(143.dp)
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.i), contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Icon(
                    painter = painterResource(
                        id = if (!isVideoUnlocked) R.mipmap.lock else R.mipmap.play
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {

                        }
                )
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 8.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "00:00",
                        fontSize = 10.sp,
                        modifier = Modifier
                            .background(color = Color(0xffF7F7F7))
                            .padding(start = 3.dp, end = 3.dp)
                            .clip(RoundedCornerShape(2.dp)),
                    )
                }
            }
        }

        // 多圖
        if (videoUrl == null && imgId.size > 1) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                Image(
                    painter = painterResource(id = imgId[0]),
                    modifier = Modifier.aspectRatio(1f / 1f),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = painterResource(id = imgId[1]),
                    modifier = Modifier.aspectRatio(1f / 1f),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )

                when (imgId.size) {
                    2 -> Spacer(modifier = Modifier.aspectRatio(1f / 1f))
                    else -> {
                        Image(
                            painter = painterResource(id = imgId[2]),
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
            Text(text = "点赞 0  ∙  收藏 12  ∙  评论  112  ∙  观看 12", fontSize = 12.sp, color = Color(0xffaeaeb2))
            Text(text = "2022-06-20", fontSize = 14.sp, color = Color(0xff757575))
        }
    }
}

@Preview
@Composable
fun LatestTopicItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(4.dp))
            .clickable { Log.i("Arthur_test", "click topic item") },
    ) {
        MineItemHeader("发布了新的主題", "这是主題的标题啊啊啊啊啊啊啊啊啊", 87)
        Text(
            text = "这是主題的标题啊啊啊啊啊啊啊啊啊",
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp),
            fontSize = 16.sp,
            color = Color(0xff3a3a3c),
            fontWeight = FontWeight.Bold,
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(topicVideos) { items ->
                Column(
                    modifier = Modifier.fillParentMaxWidth()
                ) {
                    TopicVideoItems(items)
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
            Text(text = "点赞 0  ∙ 收藏 12 ∙ 观看 12", fontSize = 12.sp, color = Color(0xffaeaeb2))
            Text(text = "2022-06-21", fontSize = 14.sp, color = Color(0xff757575))
        }
    }
}

@Preview
@Composable
fun TopicVideoItems(imgId: Int = R.drawable.i) {
    val isVideoUnlocked = false
    Box(
        modifier = Modifier
            .height(143.dp)
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imgId), contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(
                id = if (!isVideoUnlocked) R.mipmap.lock else R.mipmap.play
            ),
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)
                .clickable {

                }
        )
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "00:00",
                fontSize = 10.sp,
                modifier = Modifier
                    .background(color = Color(0xffF7F7F7))
                    .padding(start = 3.dp, end = 3.dp)
                    .clip(RoundedCornerShape(2.dp)),
            )
        }
    }
}

@Composable
fun MineScreen(navController: NavHostController) {
    Scaffold(
        floatingActionButton = {
            IconButton(
                onClick = { navController.navigateToRecommendationVideo() },
                modifier = Modifier
                    .height(40.dp)
                    .width(47.dp)
                    .padding(end = 7.dp)
                    .background(color = Color(0xBF0091FF), shape = CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_letter), contentDescription = "",
                    tint = Color.White,
                )
                Divider(
                    modifier = Modifier
                        .width(27.dp)
                        .height(19.dp)
                        .padding(start = 21.dp, bottom = 13.dp)
                        .clip(CircleShape), color = Color.Red
                )
            }
        }
    ) { paddings ->
        Column(
            modifier = Modifier
                .background(Color(0xFFF5F5F5))
                .padding(paddings)
        ) {
            MineHeader(imgId = R.drawable.a, isPlan = true, navController = navController)
            PreviewInfoButton()
            PreviewMineContent()
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
fun NavGraphBuilder.mine(navController: NavHostController) {
    composable(Screen.MINE_SCREEN.route) {
        MineScreen(navController = navController)
    }
}

fun NavHostController.navigateToMine() {
    navigate(Screen.MINE_SCREEN.route) {
        launchSingleTop = true
    }
}

