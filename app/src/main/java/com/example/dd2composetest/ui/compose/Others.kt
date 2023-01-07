package com.example.dd2composetest.ui.compose

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dd2composetest.R
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Preview
@Composable
fun Tab() {
    var tabPosition by remember {
        mutableStateOf(0)
    }
    Column(
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
                    text = "作者",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 11.dp, bottom = 10.dp),
                    color = if (tabPosition == 0) Color(0xFFff932b) else Color.Black
                )
                Divider(
                    modifier = Modifier
                        .height(2.dp)
                        .width(62.dp),
                    color = if (tabPosition == 0) Color(0xFFff932b) else Color.White
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
                    text = "視頻",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 11.dp, bottom = 10.dp),
                    color = if (tabPosition == 1) Color(0xFFff932b) else Color.Black
                )
                Divider(
                    modifier = Modifier
                        .height(2.dp)
                        .width(62.dp),
                    color = if (tabPosition == 1) Color(0xFFff932b) else Color.White
                )
            }

            Column(
                modifier = Modifier
                    .width(105.dp)
                    .fillMaxHeight()
                    .clickable {
                        tabPosition = 2
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "視頻主題",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 11.dp, bottom = 10.dp),
                    color = if (tabPosition == 2) Color(0xFFff932b) else Color.Black
                )
                Divider(
                    modifier = Modifier
                        .height(2.dp)
                        .width(62.dp),
                    color = if (tabPosition == 2) Color(0xFFff932b) else Color.White
                )
            }

            Column(
                modifier = Modifier
                    .width(105.dp)
                    .fillMaxHeight()
                    .clickable {
                        tabPosition = 3
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "文章主題",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 11.dp, bottom = 10.dp),
                    color = if (tabPosition == 3) Color(0xFFff932b) else Color.Black
                )
                Divider(
                    modifier = Modifier
                        .height(2.dp)
                        .width(62.dp),
                    color = if (tabPosition == 3) Color(0xFFff932b) else Color.White
                )
            }
        }
    }
}