package com.example.dd2composetest.ui.compose.mine.mycoin

//import com.alirezamilani.persiandaterangepicker.DateRangePicker
//import com.alirezamilani.persiandaterangepicker.persianCalendar.PersianCalendar
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.dd2composetest.MainActivity
import com.example.dd2composetest.R
import com.example.dd2composetest.enum.DatePicker
import com.example.dd2composetest.enum.Screen
import com.example.dd2composetest.ui.compose.components.DateTimeFilter
import com.example.dd2composetest.ui.compose.components.ToolBarType
import com.example.dd2composetest.ui.compose.components.Toolbar
import com.example.dd2composetest.ui.compose.components.datepicker.material3.ExperimentalMaterial3Api
import com.example.dd2composetest.ui.compose.components.datepicker.material3.MaterialDateRangePicker
import com.example.dd2composetest.ui.compose.components.datepicker.material3.rememberDateRangePickerState2
import com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.DateRangePicker
import com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.RangePickerCalendar
import com.example.dd2composetest.ui.compose.payment.navigateToPayChoose
import com.example.dd2composetest.utils.NumberFormatUtils
import java.util.*

/**
 * ====================
 * Author: Arthur Tang
 * Date: 2023.01.07
 */

/*
 解鎖視頻：主題、首頁內的影片
 1 -> 解鎖視頻 解锁影片 -25 -450
 2 -> 上传视频 -24 -430
 3 -> 充值 10000
 4 -> ?
 5 -> ?
 6 -> ?
 7 -> 解锁主题 -50
 8 -> 解锁文章 -100
 9 -> 发表文章 0
 98-> 升级密友 -100
 以上信息跟文檔差異巨大
 */

val goldCoinData = listOf(
    CoinData(3, 9900, "充值", "2022-07-13"),
    CoinData(0, 0, "發表文章", "2022-07-13"),
    CoinData(2, -300, "上傳視頻", "2022-07-13"),
    CoinData(0, -400, "解鎖視頻", "2022-07-13"),
    CoinData(0, -450, "解锁主题", "2022-07-13"),
    CoinData(2, -50, "上傳視頻", "2022-07-13"),
    CoinData(0, -100, "解锁文章", "2022-07-13"),
    CoinData(3, 9900, "充值", "2022-07-13"),
    CoinData(0, 0, "發表文章", "2022-07-13"),
    CoinData(2, -300, "上傳視頻", "2022-07-13"),
    CoinData(0, -400, "解鎖視頻", "2022-07-13"),
    CoinData(0, -450, "解锁主题", "2022-07-13"),
    CoinData(2, -50, "上傳視頻", "2022-07-13"),
    CoinData(0, -100, "解锁文章", "2022-07-13"),
)

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
fun NavGraphBuilder.myCoin(navController: NavHostController, activity: MainActivity) {
    composable(Screen.MY_GOLD_COIN_SCREEN.route) {
        MyCoinScreen(navController = navController, 0, goldCoinData, activity)
    }
    composable(Screen.MY_RED_COIN_SCREEN.route) {
        MyCoinScreen(navController = navController, 1, goldCoinData, activity)
    }
    composable(DatePicker.MY_GOLD_COIN_RANGE_PIKER.route) { navBackStackEntry ->
        val parent = remember(navBackStackEntry)  {
            navController.getBackStackEntry(Screen.MY_GOLD_COIN_SCREEN.route)
        }
        MyCoinDatePicker(navController, viewModel = hiltViewModel(parent))
    }
    composable(DatePicker.MY_RED_COIN_RANGE_PIKER.route) { navBackStackEntry ->
        val parent = remember(navBackStackEntry)  {
            navController.getBackStackEntry(Screen.MY_RED_COIN_SCREEN.route)
        }
        MyCoinDatePicker(navController, viewModel = hiltViewModel(parent))
    }
}

fun NavHostController.navigateToMyGoldCoin() {
    navigate(Screen.MY_GOLD_COIN_SCREEN.route) {
        launchSingleTop = true
    }
}

fun NavHostController.navigateToMyRedCoin() {
    navigate(Screen.MY_RED_COIN_SCREEN.route) {
        launchSingleTop = true
    }
}

fun NavHostController.navigateToMyCoinDatePicker(coinType: Int) {
    if (coinType == 0) navigate(DatePicker.MY_GOLD_COIN_RANGE_PIKER.route) { launchSingleTop = true }
    else navigate(DatePicker.MY_RED_COIN_RANGE_PIKER.route) { launchSingleTop = true }
}

@Composable
fun MyCoinHeader(type: Int, amount: Int, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(top = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = if (type == 0) R.mipmap.golden_coin else R.mipmap.red_coin),
            contentDescription = ""
        )
        Text(
            text = NumberFormatUtils.formatNumberString(number = amount),
            fontSize = 36.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = if (type == 0) "充值" else "提現",
            modifier = Modifier
                .clickable {
                    if (type == 0) navController.navigateToPayChoose()
                }
                .padding(top = 16.dp)
                .border(
                    BorderStroke(
                        1.dp,
                        if (type == 0) Color(0xffce9f09)
                        else Color(0xfff99797)
                    ),
                    RoundedCornerShape(12.dp)
                )
                .padding(start = 32.dp, end = 32.dp, top = 4.dp, bottom = 4.dp),
            color = if (type == 0) Color(0xffce9f09) else Color(0xfff99797),
        )
    }
}

//@Preview
@Composable
fun PreviewMyCoinHeader() {
    MyCoinHeader(type = 0, amount = 40000, NavHostController(LocalContext.current))
}

@Composable
fun MyCoinContent(coinType: Int = 0, data: List<CoinData> = listOf()) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.White)
    ) {
        items(data) { data ->
            CoinDataItem(data, coinType)
        }
    }
}

@Composable
fun CoinDataItem(data: CoinData, coinType: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp, start = 18.dp, end = 16.dp)
    ) {
        Image(
            painter = painterResource(id = if (coinType == 0) R.mipmap.coin_gold else R.mipmap.coin_red),
            contentDescription = ""
        )
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
        ) {
            Text(
                text = data.description,
                color = Color.Black,
                fontSize = 14.sp
            )
            Text(
                text = data.creationDateTime,
                color = Color(0xff999999),
                fontSize = 12.sp
            )
        }
        Text(
            text = "${data.amount}",
            color = if (data.type == 2) Color(0xffe02020) else Color(0xff178649),
            fontSize = 14.sp
        )
    }
}

//@Preview
@Composable
fun PreviewCoinDataItem() {
    CoinDataItem(data = CoinData(0, 9900, "充值", "2022-07-13"), coinType = 0)
}

// type: 0 -> gold, 1 -> red
@Composable
fun MyCoinScreen(
    navController: NavHostController,
    type: Int,
    goldCoinData: List<CoinData>,
    activity: MainActivity,
    viewModel: MyCoinViewModel = hiltViewModel()
) {
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        CoinFilterDialog(
            coinType = type,
            sortType = if (type == 0) viewModel.goldFilterType else viewModel.redFilterType,
            setShowDialog = { showDialog.value = it },
            navController,
            viewModel
        )
    }
    val goldSort = when (viewModel.goldFilterType) {
        0 -> "全部"
        1 -> "金幣充值"
        2 -> "金幣消費"
        else -> "全部"
    }
    val redSort = when (viewModel.redFilterType) {
        0 -> "全部"
        1 -> "紅幣收入"
        2 -> "紅幣支出"
        3 -> "紅幣提現"
        else -> "全部"
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.White)
    ) {
        Toolbar(
            navController = navController,
            title = if (type == 0) "我的金幣" else "我的紅幣",
            toolbarType = ToolBarType.NORMAL_TOOLBAR,
            onClickLeft = { viewModel.initDate() }
        )
        MyCoinHeader(type = type, 40000, navController)
        DateTimeFilter(
            navController = navController,
            startDate = viewModel.startDate,
            endDate = viewModel.endDate,
            placeType = 0,
            activity = activity,
            viewModel = viewModel,
            setDate = { navController.navigateToMyCoinDatePicker(type) },
            setFilter = { showDialog.value = true },
            sortType = if (type == 0) goldSort else redSort
        )
        MyCoinContent(type, goldCoinData)
    }
}

@Preview
@Composable
fun PreviewMyCoinScreen() {
    MyCoinScreen(navController = NavHostController(LocalContext.current), type = 0, goldCoinData, MainActivity())
}

data class CoinData(
    val type: Int = 0,
    val amount: Int = 0,
    val description: String = "",
    val creationDateTime: String = "",
)

val calendar = Calendar.getInstance()
val thisYear = calendar.get(Calendar.YEAR)
val thisMonth = calendar.get(Calendar.MONTH)
val today = calendar.get(Calendar.DAY_OF_MONTH)

val start = RangePickerCalendar().apply {
    setCalendarDate(thisYear, thisMonth, today)
//    setCalendarDate(1970, thisMonth, today)
}
val end = RangePickerCalendar().apply {
    setCalendarDate(thisYear, thisMonth, today + 1)
//    setCalendarDate(1970, thisMonth, today + 1)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCoinDatePicker(navController: NavHostController, viewModel: MyCoinViewModel) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
//        DateRangePicker(
//            modifier = Modifier,
//            initialDates = start to end,
//            yearRange = IntRange(2023, 2050),
//            title = "Select Date",
//            saveLabel = "儲存",
//            isRtl = false,
//            onCloseClick = { navController.popBackStack() },
//            onConfirmClick = { start, end ->
//                Log.i("Arthur_test", "選擇 start: ${start.longDateString}, end: ${end.shortDateString}")
//                viewModel.onEvent(MyCoinEvent.SelectDate(
//                    Pair(
//                        start.selectedDateString,
//                        end.selectedDateString
//                    )
//                ))
//                navController.popBackStack()
//            }
//        )
        //        val
        val state = rememberDateRangePickerState2(
//            initialSelectedStartDateMillis =
        )
        MaterialDateRangePicker(state = state)
    }
}

@Composable
fun CoinFilterDialog(
    coinType: Int,
    sortType: Int,
    setShowDialog: (Boolean) -> Unit,
    navController: NavHostController,
    viewModel: MyCoinViewModel
) {
    Dialog(
        onDismissRequest = { setShowDialog(false) },
        properties = DialogProperties(
            dismissOnClickOutside = false
        )
    ) {
        Box(modifier = Modifier
//            .background(color = Color(0x52000000))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // 改用 recycler view?
                Text(
                    text = "全部",
                    modifier = Modifier
                        .padding(top = 40.dp, bottom = 10.dp)
                        .clickable {
                            viewModel.onEvent(
                                if (coinType == 0) MyCoinEvent.SetGoldFilter(0)
                                else MyCoinEvent.SetRedFilter(0)
                            )
                            setShowDialog(false)
                        },
                    color = if (sortType == 0) Color.White else Color(0XFF9a9898),
                    fontSize = if (sortType == 0) 22.sp else 18.sp,
                    fontWeight = if (sortType == 0) FontWeight.Bold else FontWeight.Normal
                )
                Text(
                    text = if (coinType == 0) "金幣充值" else "紅幣收入",
                    modifier = Modifier
                        .padding(top = 40.dp, bottom = 10.dp)
                        .clickable {
                            viewModel.onEvent(
                                if (coinType == 0) MyCoinEvent.SetGoldFilter(1)
                                else MyCoinEvent.SetRedFilter(1)
                            )
                            setShowDialog(false)
                        },
                    color = if (sortType == 1) Color.White else Color(0XFF9a9898),
                    fontSize = if (sortType == 1) 22.sp else 18.sp,
                    fontWeight = if (sortType == 1) FontWeight.Bold else FontWeight.Normal
                )
                Text(
                    text = if (coinType == 0) "金幣消費" else "紅幣支出",
                    modifier = Modifier
                        .padding(top = 40.dp, bottom = 10.dp)
                        .clickable {
                            viewModel.onEvent(
                                if (coinType == 0) MyCoinEvent.SetGoldFilter(2)
                                else MyCoinEvent.SetRedFilter(2)
                            )
                            setShowDialog(false)
                        },
                    color = if (sortType == 2) Color.White else Color(0XFF9a9898),
                    fontSize = if (sortType == 2) 22.sp else 18.sp,
                    fontWeight = if (sortType == 2) FontWeight.Bold else FontWeight.Normal
                )
                if (coinType != 0) {
                    Text(
                        text = "紅幣提現",
                        modifier = Modifier
                            .padding(top = 40.dp, bottom = 10.dp)
                            .clickable {
                                viewModel.onEvent(
                                    MyCoinEvent.SetRedFilter(3)
                                )
                                setShowDialog(false)
                            },
                        color = if (sortType == 3) Color.White else Color(0XFF9a9898),
                        fontSize = if (sortType == 3) 22.sp else 18.sp,
                        fontWeight = if (sortType == 3) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
            IconButton(
                onClick = { setShowDialog(false) },
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(20.dp)),
            ) {
                Image(painter = painterResource(id = R.mipmap.cancel), contentDescription = "")
            }
        }
    }
}

//@Composable
//fun CoinSortItem(title: String, ) {
//    Text(
//        text = "紅幣提現",
//        modifier = Modifier
//            .padding(top = 40.dp, bottom = 10.dp)
//            .clickable {
//                viewModel.onEvent(
//                    MyCoinEvent.SetRedFilter(3)
//                )
//                setShowDialog(false)
//            },
//        color = if (sortType == 3) Color.White else Color(0XFF9a9898),
//        fontSize = if (sortType == 3) 22.sp else 18.sp,
//        fontWeight = if (sortType == 3) FontWeight.Bold else FontWeight.Normal
//    )
//}



