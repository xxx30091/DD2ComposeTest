package com.example.dd2composetest.ui.compose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.dd2composetest.R
import com.example.dd2composetest.data.bean.HistoryData
import com.example.dd2composetest.enum.Screen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

val data = listOf(
    HistoryData(14000, 1200, "2019-09-09", "免費", 3),
    HistoryData(14000, 1200, "2020-10-10", "免費", 1),
    HistoryData(14000, 1200, "2021-11-11", "300 幣", 2),
    HistoryData(14000, 1200, "2022-11-12", "1,000 幣", 3),

    HistoryData(14000, 1200, "2019-09-09", "免費", 3),
    HistoryData(14000, 1200, "2020-10-10", "免費", 1),
    HistoryData(14000, 1200, "2021-11-11", "300 幣", 2),
    HistoryData(14000, 1200, "2022-11-12", "1,000 幣", 3),
    HistoryData(14000, 1200, "2019-09-09", "免費", 3),
    HistoryData(14000, 1200, "2020-10-10", "免費", 1),
    HistoryData(14000, 1200, "2021-11-11", "300 幣", 2),
    HistoryData(14000, 1200, "2022-11-12", "1,000 幣", 3),
)

@ExperimentalPagerApi
@Composable
fun FansPromoteScreen(navController: NavHostController) {
    Column {
        Toolbar(navController)
        FansPromoteTabs()
    }
}

@Composable
fun Toolbar(navController: NavHostController) {
    TopAppBar(
        modifier = Modifier.height(56.dp),
        title = {
            Row(Modifier
                .fillMaxWidth()
                .padding(end = 68.dp), horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "粉丝推送", fontSize = 18.sp)
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        },
        backgroundColor = Color.Black,
        contentColor = Color.White
    )
}

@Preview
@Composable
fun PreviewToolbar() {
    TopAppBar(
        modifier = Modifier.height(56.dp),
        title = {
            Row(Modifier
                .fillMaxWidth()
                .padding(end = 68.dp), horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "粉丝推送", fontSize = 18.sp)
            }
        },
        navigationIcon = {
            IconButton(
                onClick = {
                }
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        },
        backgroundColor = Color.Black,
        contentColor = Color.White
    )
}

@ExperimentalPagerApi
@Preview
@Composable
fun FansPromoteTabs() {
    // 拿到外面 用 string array
//        val list = listOf(
//            "推送设置", "推送历史"
////            "Home" to Icons.Default.Home, // 設置 tab 名稱 ＆ icon
//        )
    val scope = rememberCoroutineScope()
    var tabPosition by remember {
        mutableStateOf(0)
    }
    val pagerState = rememberPagerState(initialPage = 0)
    Column(
//            modifier =
    ) {
        Row(
            modifier = Modifier
                .height(45.dp)
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .width(105.dp)
                    .fillMaxHeight()
                    .clickable {
                        tabPosition = 0
                        scope.launch {
                            pagerState.animateScrollToPage(0)
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                LocalContext.current.getString(R.string.string_promote_setting)
                Text(
                    text = stringResource(id = R.string.string_promote_setting),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 11.dp, bottom = 10.dp))
                Divider(
                    modifier = Modifier
                        .height(2.dp)
                        .width(62.dp),
                    color = if (tabPosition == 0) Color(0xFF679FF8) else Color.White
                )
            }

            Column(
                modifier = Modifier
                    .width(105.dp)
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
                    text = LocalContext.current.getString(R.string.string_promote_history),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 11.dp, bottom = 10.dp))
                Divider(
                    modifier = Modifier
                        .height(2.dp)
                        .width(62.dp),
                    color = if (tabPosition == 1) Color(0xFF679FF8) else Color.White
                )
            }
        }
        HorizontalPager(
            count = 2,
            state = pagerState,
            userScrollEnabled = false
        ) { index ->
            when (index) {
                0 -> PromoteSettingScreen(false)
                1 -> PromoteHistoryScreen(data)
            }
        }
    }
}

@Composable
fun PromoteHistoryScreen(data: List<HistoryData> = listOf()) {
    LazyColumn(
        modifier = Modifier
            .background(Color(0xFFF5F5F5))
            .fillMaxSize()
    ) {
        items(data) { data ->
            HistoryItem(data)
        }
    }
}

@Preview
@Composable
fun PreviewPromoteHistoryScreen(data: List<HistoryData> = listOf(HistoryData( 14000, 1200, "2020-10-12", "1,000", 3))) {
    LazyColumn(
        modifier = Modifier
            .background(Color(0xFFF5F5F5))
            .fillMaxSize()
    ) {
        items(data) { data ->
            HistoryItem(data)
        }
    }
}

@Composable
fun HistoryItem(data: HistoryData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(79.dp)
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .background(Color(0xFFF5F5F5)),

        ) {
        Box(
            modifier = Modifier
                .width(108.dp)
                .height(63.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(R.drawable.h),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            if (data.videoCount > 1) {
                Text(
                    text = data.videoCount.toString(),
                    fontSize = 14.sp,
                    color = Color.White,
                    modifier = Modifier.padding(end = 4.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .width(196.dp)
                .height(63.dp)
                .padding(start = 12.dp)
        ) {
            Text(text = "粉丝定向推送", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(
                text = buildAnnotatedString {
                    append("解鎖  ")
                    append(data.unlockCount.toString())
                    append("        增粉  ")
                    append(data.increaseFans.toString())
                },
                fontSize = 12.sp,
                color = Color(0xFF50585D)
            )
            Text(
                text = buildAnnotatedString {
                    append(data.date)
                    append("    花費:  ")
                    append(data.cost)
                },
                fontSize = 10.sp,
                color = Color(0xFF777C81),
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewHistoryItem(data: HistoryData = HistoryData( 14000, 1200, "2020-10-12", "1,000", 0)) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(79.dp)
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .background(Color(0xFFF5F5F5)),

        ) {
        Box(
            modifier = Modifier
                .width(108.dp)
                .height(63.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(R.drawable.h),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            if (data.videoCount > 1) {
                Text(
                    text = data.videoCount.toString(),
                    fontSize = 14.sp,
                    color = Color.White,
                    modifier = Modifier.padding(end = 4.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .width(196.dp)
                .height(63.dp)
                .padding(start = 12.dp)
        ) {
            Text(text = "粉丝定向推送", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(
                text = buildAnnotatedString {
                    append("解鎖  ")
                    append(data.unlockCount.toString())
                    append("        增粉  ")
                    append(data.increaseFans.toString())
                },
                fontSize = 12.sp,
                color = Color(0xFF50585D)
            )
            Text(
                text = buildAnnotatedString {
                    append(data.date)
                    append("    花費:  ")
                    append(data.cost)
                },
                fontSize = 10.sp,
                color = Color(0xFF777C81),
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}

@Preview
@Composable
fun FansPromote(
    hasPromoted: Boolean = false,
    navController: NavHostController = NavHostController(LocalContext.current)
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(6.dp))
            .clip(RoundedCornerShape(6.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
//                    .background(Color.White, RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "向自己的粉丝定向推送", fontSize = 14.sp, color = Color.Black)
            Text(
                text = buildAnnotatedString {
                    append("(每月首次免费，第2次起 ")
                    append(
                        AnnotatedString(
                            text = "300",
                            spanStyle = SpanStyle(
                                color = Color(0xFFFF3B30)
                            )
                        )
                    )
                    append(" 币/次)")
                },
                fontSize = 10.sp,
                color = Color(0xFF34C759))
        }
        Divider()

        VideoSelector(true)

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(top = 12.dp),
            colors = ButtonDefaults.buttonColors(Color.Black),
            shape = RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp),
            onClick = {

            }
        ) {
            Text(
                text = if (hasPromoted) "马上推送" else "马上推送 (本次免费)",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun NotFansPromote(hasPromoted: Boolean = false) {
    var amount by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(6.dp))
            .clip(RoundedCornerShape(6.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
//                    .background(Color.White, RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "向非粉丝定向推送", fontSize = 14.sp, color = Color.Black)
            Text(
                text = buildAnnotatedString {
                    append("( ")
                    append(
                        AnnotatedString(
                            text = "300",
                            spanStyle = SpanStyle(
                                color = Color(0xFFFF3B30)
                            )
                        )
                    )
                    append(" 币/100用户，每月限1次)")
                },
                fontSize = 10.sp, color = Color(0xFF34C759)
            )
        }
        Divider()
        //
//            selectedVideo = listOf("", "")
        VideoSelector(false, "")
        VideoSelector(false, "")
        VideoSelector(false)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "推送人数：", fontSize = 14.sp, modifier = Modifier.padding(end = 8.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_promote_neg),
                contentDescription = "",
                modifier = Modifier
                    .size(12.dp)
                    .clickable {
                        if (amount >= 100) amount -= 100
                    }
            )
            Text(
                text = amount.toString(),
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                color = Color(0xFFFF9500)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_promote_add),
                contentDescription = "",
                modifier = Modifier
                    .size(12.dp)
//                        .padding(start = 8.dp)
                    .clickable {
                        amount += 100
                    }
            )
        }
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = buildAnnotatedString {
                append("当前推送的人数，收费  ")
                append( (amount * 3).toString() )
                append(" 币")
            },
            fontSize = 12.sp,
            color = Color(0xFF679FF8)
        )
        val localContext = LocalContext.current
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .padding(top = 18.dp),
            colors = ButtonDefaults.buttonColors(
                if (hasPromoted) Color(0xFFC7C7CC)
                else Color.Black
            ),
            shape = RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp),
            onClick = {
//                if (!hasPromoted) Toast.makeText(requireContext(), "推送成功", Toast.LENGTH_SHORT).show()
//                else Toast.makeText(LocalContext.current.applicationContext, "本月已推送过，无法再推送", Toast.LENGTH_SHORT).show()
                showToast(context = localContext, hasPromoted = hasPromoted)
            }
        ) {
            Text(
                text = if (hasPromoted) "本月已推送过，无法再推送" else "马上推送 (本次免费)",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

fun showToast(context: Context, hasPromoted: Boolean) {
    val message = if (hasPromoted) "本月已推送过，无法再推送" else "推送成功"
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview
@Composable
fun VideoSelector(isFans: Boolean = false, coverUrl: String? = null) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(155.dp)
            .padding(start = 8.dp, end = 8.dp, top = 12.dp)
            .background(Color(0xFFf5f5f5)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (coverUrl == null) {
            Image(
                painter = painterResource(id = R.drawable.ic_add_video),
                contentDescription = "",
                modifier = Modifier.size(51.dp)
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = if (isFans) "请选择1个你的视频作品" else "请选择你的视频作品（最多可3个)",
                fontSize = 14.sp,
                color = Color(0xFF9D9D9D)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.a),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun PromoteSettingScreen(hasPromoted: Boolean) {
    val scroll = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Column(
            modifier = Modifier.verticalScroll(scroll)
        ) {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(Color(0xFFF5F5F5))
            )
            FansPromote()
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(38.dp)
                .background(Color(0xFFF5F5F5))
            )
            NotFansPromote(hasPromoted)
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(38.dp)
                .background(Color(0xFFF5F5F5))
            )
        }
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
fun NavGraphBuilder.fansPromote(navController: NavHostController) {
    composable(Screen.FANS_PROMOTE_SCREEN.route) {
        FansPromoteScreen(navController = navController)
    }
}

fun NavHostController.navigateToFansPromote() {
    navigate(Screen.FANS_PROMOTE_SCREEN.route) {
        launchSingleTop = true
    }
}