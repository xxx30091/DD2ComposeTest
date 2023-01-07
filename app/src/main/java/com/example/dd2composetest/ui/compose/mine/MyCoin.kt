package com.example.dd2composetest.ui.compose.mine

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.example.dd2composetest.enum.Screen
import com.example.dd2composetest.ui.compose.components.DateTimeFilter
import com.example.dd2composetest.ui.compose.components.Toolbar
import com.example.dd2composetest.utils.NumberFormatUtils

/**
 * ====================
 * Author: Arthur Tang
 * Data: 2023.01.07
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

@Preview
@Composable
fun MyCoinHeader(type: Int = 0, amount: Int = 40000) {
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
                .clickable {  }
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

@Preview
@Composable
fun CoinDataItem(data: CoinData = CoinData(0, 9900, "充值", "2022-07-13"), coinType: Int = 0) {
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

// type: 0 -> gold, 1 -> red
@Preview
@Composable
fun MyCoinScreen(navController: NavHostController = NavHostController(LocalContext.current), type: Int = 0) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.White)
    ) {
        Toolbar(navController = navController, title = if (type == 0) "我的金幣" else "我的紅幣", 0)
        MyCoinHeader(type = type, 40000)
        DateTimeFilter(placeType = 0)
        MyCoinContent(0,goldCoinData)
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
fun NavGraphBuilder.myCoin(navController: NavHostController) {
    composable(Screen.MY_GOLD_COIN_SCREEN.route) {
        MyCoinScreen(navController = navController, 0)
    }
    composable(Screen.MY_RED_COIN_SCREEN.route) {
        MyCoinScreen(navController = navController, 1)
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

data class CoinData(
    val type: Int = 0,
    val amount: Int = 0,
    val description: String = "",
    val creationDateTime: String = "",
)


