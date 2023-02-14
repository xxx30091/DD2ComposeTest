package com.example.dd2composetest.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dd2composetest.R
import com.example.dd2composetest.ui.compose.PromoteHistoryScreen
import com.example.dd2composetest.ui.compose.PromoteSettingScreen
import com.example.dd2composetest.ui.compose.data
import com.google.accompanist.pager.ExperimentalPagerApi


@ExperimentalPagerApi
@Preview
@Composable
fun TabBar(
    indicatorColor: Color = Color(0xff679ff8),
) {
    val scope = rememberCoroutineScope()
    var tabPosition by remember {
        mutableStateOf(0)
    }
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
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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

//        if (tabPosition == 0) PromoteSettingScreen(false)
//        else PromoteHistoryScreen(data)
    }
}

@ExperimentalPagerApi
@Preview
@Composable
fun PreviewTabBar() {
    TabBar(Color(0xff679ff8))
}

//@Composable
//fun TabItem() {
//    Column(
//        modifier = Modifier
//            .width(105.dp)
//            .fillMaxHeight()
//            .clickable {
//                tabPosition = 1
//            },
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        Text(
//            text = LocalContext.current.getString(R.string.string_promote_history),
//            fontSize = 16.sp,
//            modifier = Modifier.padding(top = 11.dp, bottom = 10.dp))
//        Divider(
//            modifier = Modifier
//                .height(2.dp)
//                .width(62.dp),
//            color = if (tabPosition == 1) Color(0xFF679FF8) else Color.White
//        )
//    }
//}

