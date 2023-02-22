package com.example.dd2composetest.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController


@Preview
@Composable
fun AddTopicVideoScreen(
    navController: NavHostController = NavHostController(LocalContext.current),
    onNextClick: () -> Unit = {}
) {
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
                onClick = { /*TODO*/ }
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
                        onNextClick()
                        navController.popBackStack()
                    },
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}


