package com.example.dd2composetest.ui.fagment.mine

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dd2composetest.ComposeNavigation
import com.example.dd2composetest.MainActivity
import com.example.dd2composetest.R
import com.example.dd2composetest.databinding.FragmentMineBinding
import com.example.dd2composetest.ui.compose.myData
import com.example.dd2composetest.ui.compose.navigateToMyData
import com.google.accompanist.pager.ExperimentalPagerApi

class MineFragment : Fragment() {
    private lateinit var binding: FragmentMineBinding
    private val topicVideos = listOf(
        R.drawable.a,
        R.drawable.b,
        R.drawable.c,
        R.drawable.d,
        R.drawable.e,
    )

    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMineBinding.inflate(inflater, container, false)

        binding.mineComposeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
//                    ComposeNavigation(activity = (activity as MainActivity))
//                    MineScreen(navController)
                }
            }
        }

        return binding.root
    }

    @Composable
    fun MineHeader(imgId: Int = R.drawable.a, isPlan: Boolean = true, navController: NavHostController) {
        val contrast = 2f // 0f..10f (1 should be default)
        val brightness = -210f // -255f..255f (0 should be default)
        val colorMatrix = floatArrayOf(
            contrast, 0f, 0f, 0f, brightness,
            0f, contrast, 0f, 0f, brightness,
            0f, 0f, contrast, 0f, brightness,
            0f, 0f, 0f, 1f, 0f,
        )
        val colorGoldList = arrayOf(
            0.0f to Color(0xfff9e87a),
            0.5f to Color(0xfff9e678),
            1f to Color(0xfff4c057)
        )

        //  模糊效果僅適用於 Android 12 以上版本。嘗試在較舊的 Android 版本中使用這個修飾符會予以忽略
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .background(Color.White)
        ) {
            // 底層背景
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = imgId),
                    contentDescription = "",
                    modifier = Modifier
                        .blur(radius = 9.dp, BlurredEdgeTreatment(RectangleShape))
                        .fillMaxWidth()
                        .height(141.dp),
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
                )
            }

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
                Row(
                    modifier = Modifier
                        .height(64.dp)
                        .padding(bottom = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = imgId),
                        contentDescription = "",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .border(
                                BorderStroke(1.dp, Color.White),
                                CircleShape
                            )
                            .clickable(
                                onClick = { navController.navigateToMyData() }
                            ),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .fillMaxWidth()
                            .weight(0.7f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "我是大哥", fontSize = 20.sp, color = Color.White)
                        Text(
                            text = if (isPlan) "包時段將於2023-09-30 00:00到期" else "目前无包时段优惠",
                            color = if (isPlan) Color(0xffce9f09) else Color(0xffb3ffffff)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_mine_history), contentDescription = "", tint = Color.White,
                            modifier = Modifier.clickable { }
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_download), contentDescription = "", tint = Color.White,
                            modifier = Modifier.clickable { }
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_customer_service), contentDescription = "", tint = Color.White,
                            modifier = Modifier.clickable { }
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_notification), contentDescription = "", tint = Color.White,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { }
                        )
                    }
                }

                val hasUpdate = false
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

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(start = 14.dp, end = 7.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "金币", fontSize = 12.sp, color = Color(0xff777c81))
                        Text(
                            text = "3447",
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.3f)
                                .clickable { },
                            fontSize = 18.sp,
                            color = Color(0xffce9f09),
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                        Divider(
                            modifier = Modifier
                                .width(2.dp)
                                .height(17.dp),
                            color = Color(0xffe8e8e8)
                        )
                        Text(
                            text = "红币", modifier = Modifier.padding(start = 13.dp),
                            fontSize = 12.sp, color = Color(0xff777c81)
                        )
                        Text(
                            text = "1234.65 万",
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.3f)
                                .clickable { },
                            fontSize = 18.sp,
                            color = Color(0xffcc2014),
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                        TextButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .height(32.dp)
                                .width(75.dp)
                                .padding(0.dp),
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
                                .padding(start = 15.dp, end = 10.dp, top = 6.dp, bottom = 6.dp)
                                .clickable { }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_rocket),
                                contentDescription = "",
                                modifier = Modifier.size(15.dp),
                                tint = Color(0xffff3b30)
                            )
                            Text(
                                text = "最新升级包已发布",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(0.4f)
                                    .padding(start = 2.dp),
                                color = Color(0xffff3b30),
                                fontSize = 12.sp
                            )
                            Text(
                                text = "马上点击升级，更新最新版本",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(0.5f),
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
    fun PreviewMineHeader(imgId: Int = R.drawable.a, isPlan: Boolean = true) {
        val contrast = 2f // 0f..10f (1 should be default)
        val brightness = -210f // -255f..255f (0 should be default)
        val colorMatrix = floatArrayOf(
            contrast, 0f, 0f, 0f, brightness,
            0f, contrast, 0f, 0f, brightness,
            0f, 0f, contrast, 0f, brightness,
            0f, 0f, 0f, 1f, 0f,
        )
        val colorGoldList = arrayOf(
            0.0f to Color(0xfff9e87a),
            0.5f to Color(0xfff9e678),
            1f to Color(0xfff4c057)
        )

        //  模糊效果僅適用於 Android 12 以上版本。嘗試在較舊的 Android 版本中使用這個修飾符會予以忽略
        Box(
            modifier = Modifier
                .fillMaxWidth()
//                .height(202.dp)
                .padding(bottom = 8.dp)
                .background(Color.White)
        ) {
            // 底層背景
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = imgId),
                    contentDescription = "",
                    modifier = Modifier
                        .blur(radius = 9.dp, BlurredEdgeTreatment(RectangleShape))
                        .fillMaxWidth()
                        .height(141.dp),
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
                )
            }

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
                Row(
                    modifier = Modifier
                        .height(64.dp)
                        .padding(bottom = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = imgId),
                        contentDescription = "",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .border(
                                BorderStroke(1.dp, Color.White),
                                CircleShape
                            )
                            .clickable(
//                                onClick = {}
                                onClick = { NavHostController(requireContext()).navigateToMyData() }
                            ),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .fillMaxWidth()
                            .weight(0.7f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "我是大哥", fontSize = 20.sp, color = Color.White)
                        Text(
                            text = if (isPlan) "包時段將於2023-09-30 00:00到期" else "目前无包时段优惠",
                            color = if (isPlan) Color(0xffce9f09) else Color(0xffb3ffffff)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_mine_history), contentDescription = "", tint = Color.White,
                            modifier = Modifier.clickable { }
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_download), contentDescription = "", tint = Color.White,
                            modifier = Modifier.clickable { }
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_customer_service), contentDescription = "", tint = Color.White,
                            modifier = Modifier.clickable { }
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_notification), contentDescription = "", tint = Color.White,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { }
                        )
                    }
                }

                val hasUpdate = false
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

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(start = 14.dp, end = 7.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "金币", fontSize = 12.sp, color = Color(0xff777c81))
                        Text(
                            text = "3447",
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.3f)
                                .clickable { },
                            fontSize = 18.sp,
                            color = Color(0xffce9f09),
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                        Divider(
                            modifier = Modifier
                                .width(2.dp)
                                .height(17.dp),
                            color = Color(0xffe8e8e8)
                        )
                        Text(
                            text = "红币", modifier = Modifier.padding(start = 13.dp),
                            fontSize = 12.sp, color = Color(0xff777c81)
                        )
                        Text(
                            text = "1234.65 万",
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.3f)
                                .clickable { },
                            fontSize = 18.sp,
                            color = Color(0xffcc2014),
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                        TextButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .height(32.dp)
                                .width(75.dp)
                                .padding(0.dp),
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
                                .padding(start = 15.dp, end = 10.dp, top = 6.dp, bottom = 6.dp)
                                .clickable { }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_rocket),
                                contentDescription = "",
                                modifier = Modifier.size(15.dp),
                                tint = Color(0xffff3b30)
                            )
                            Text(
                                text = "最新升级包已发布",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(0.4f)
                                    .padding(start = 2.dp),
                                color = Color(0xffff3b30),
                                fontSize = 12.sp
                            )
                            Text(
                                text = "马上点击升级，更新最新版本",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(0.5f),
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(Color.White)
//                    .padding(start = 8.dp, end = 8.dp)
                    .clickable(
                        onClick = { },
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mine_engage),
                    contentDescription = "",
                    modifier = Modifier
                        .size(28.dp)
                        .padding(start = 8.dp)
                )
                Text(text = "我的运营", fontSize = 16.sp, modifier = Modifier.padding(start = 8.dp))
                Text(
                    text = "243",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(0.25f),
                    color = Color(0xff3b3a95),
                    textAlign = TextAlign.End,
                    maxLines = 1
                )
                Text(
                    text = "粉丝", fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color(0xff9d9d9d)
                )
                Text(
                    text = "143",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(0.25f),
                    color = Color(0xff3b3a95),
                    textAlign = TextAlign.End,
                    maxLines = 1
                )
                Text(
                    text = "获赞", fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                    color = Color(0xff9d9d9d)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(Color.White)
                    .clickable { },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mine_follow),
                    contentDescription = "",
                    modifier = Modifier
                        .size(28.dp)
                        .padding(start = 8.dp)
                )
                Text(text = "我的关注", fontSize = 16.sp, modifier = Modifier.padding(start = 8.dp))
                Text(
                    text = "8811",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(0.25f),
                    color = Color(0xff36914d),
                    textAlign = TextAlign.End,
                    maxLines = 1
                )
                Text(
                    text = "作者", fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color(0xff9d9d9d)
                )
                Text(
                    text = "2143",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(0.25f),
                    color = Color(0xff36914d),
                    textAlign = TextAlign.End,
                    maxLines = 1
                )
                Text(
                    text = "主题", fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                    color = Color(0xff9d9d9d)
                )
            }
        }
    }

    @Preview
    @Composable
    fun PreviewMineContent() {
        val scroll = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .verticalScroll(scroll),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LatestVideoItem()
            LatestArticleItem()
            LatestTopicItem()
        }
    }

    @Preview
    @Composable
    fun LatestVideoItem() {
        val videoCounts = 0
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(4.dp))
                .clickable { Log.i("Arthur_test", "click video item") },
        ) {

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
                        text = "发布了新的视频", modifier = Modifier.padding(start = 8.dp),
                        fontSize = 14.sp, color = Color(0xffaeaeb2)
                    )
                }
                Box(
                    modifier = Modifier
                        .height(25.dp)
                        .background(
                            Color(0xfff2f2f7),
                            RoundedCornerShape(bottomStart = 8.dp)
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
                                    text = if (videoCounts > 99) "(99+)"
                                    else if (videoCounts == 0) ""
                                    else "($videoCounts)",
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
                Icon(
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
        val articleCounts = 100
        val imgId = listOf(
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
        )
        val videoUrl: Int? =
            null
//            R.drawable.c

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(4.dp))
                .clickable { Log.i("Arthur_test", "click article item") },
        ) {

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
                        text = "发布了新的文章", modifier = Modifier.padding(start = 8.dp),
                        fontSize = 14.sp, color = Color(0xffaeaeb2)
                    )
                }
                Box(
                    modifier = Modifier
                        .height(25.dp)
                        .background(
                            Color(0xfff2f2f7),
                            RoundedCornerShape(bottomStart = 8.dp)
                        )
                        .clickable {
                            Log.i("Arthur_test", "click article more")
                        }
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("更多")
                            append(
                                AnnotatedString(
                                    text = if (articleCounts > 99) "(99+)"
                                    else if (articleCounts == 0) ""
                                    else "($articleCounts)",
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(0.8f)
                ) {
                    Text(
                        text = "这是文章的标题啊啊啊啊啊啊啊啊啊",
                        fontSize = 16.sp,
                        color = Color(0xff3a3a3c),
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp),
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                    Text(
                        text = "性高潮可以透过许多性活动来实现：XXX，sieruew，这是文章的内容简要啊…",
                        fontSize = 14.sp,
                        color = Color(0xff3a3a3c),
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp),
                        maxLines = 2
                    )
                }
                if (imgId.size == 1 && videoUrl == null) {
                    Image(
                        painter = painterResource(id = imgId[0]),
                        contentDescription = "",
                        modifier = Modifier
                            .size(76.dp)
                            .padding(end = 8.dp, top = 8.dp)
                            .weight(0.2f)
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
                    horizontalArrangement = Arrangement.Start,

                ) {
                    Image(
                        painter = painterResource(id = imgId[0]),
                        modifier = Modifier
                            .weight(0.33f)
                            .aspectRatio(1f / 1f),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(4.5.dp)
                    )
                    Image(
                        painter = painterResource(id = imgId[1]),
                        modifier = Modifier
                            .weight(0.33f)
                            .aspectRatio(1f / 1f),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(4.5.dp)
                    )

                    when (imgId.size) {
                        2 -> {
                            Image(
                                painter = painterResource(id = R.drawable.empty_img),
                                modifier = Modifier
                                    .weight(0.33f)
                                    .aspectRatio(1f / 1f),
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )
                        }
                        else -> {
                            Image(
                                painter = painterResource(id = imgId[2]),
                                modifier = Modifier
                                    .weight(0.33f)
                                    .aspectRatio(1f / 1f),
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
        val topicCounts = 87
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(4.dp))
                .clickable { Log.i("Arthur_test", "click topic item") },
        ) {

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
                        text = "发布了新的主題", modifier = Modifier.padding(start = 8.dp),
                        fontSize = 14.sp, color = Color(0xffaeaeb2)
                    )
                }
                Box(
                    modifier = Modifier
                        .height(25.dp)
                        .background(
                            Color(0xfff2f2f7),
                            RoundedCornerShape(bottomStart = 8.dp)
                        )
                        .clickable {
                            Log.i("Arthur_test", "click article more")
                        }
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("更多")
                            append(
                                AnnotatedString(
                                    text = if (topicCounts > 99) "(99+)"
                                    else if (topicCounts == 0) ""
                                    else "($topicCounts)",
                                    spanStyle = SpanStyle(
                                        color = Color(0xffff3b30
                                        )
                                    )
                                )
                            )
                        },
                        modifier = Modifier
                            .padding(start = 8.dp, end = 4.dp, top = 4.dp, bottom = 4.dp),
                        fontSize = 12.sp
                    )
                }
            }

            Text(
                text = "这是主題的标题啊啊啊啊啊啊啊啊啊", fontSize = 16.sp, color = Color(0xff3a3a3c),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp),
                fontWeight = FontWeight.Bold,
            )
            LazyRow (
                modifier = Modifier.fillMaxWidth()
            ) {
                items(topicVideos) {items ->
                    TopicVideoItems(items)
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

    @Composable
    fun MineScreen(navController: NavHostController) {
        Column(
            modifier = Modifier.background(Color(0xFFF5F5F5))
        ) {
            MineHeader(navController = navController)
            PreviewInfoButton()
            PreviewMineContent()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MineFragment()
    }
}

//@ExperimentalMaterialApi
//@ExperimentalComposeUiApi
//fun NavGraphBuilder.mine(navController: NavHostController) {
//    composable(Screen.MINE_SCREEN.route) {
//        MineScreen(navController = navController)
//    }
//}
//
//fun NavHostController.navigateToMine() {
//    navigate(Screen.MINE_SCREEN.route) {
//        launchSingleTop = true
//    }
//}