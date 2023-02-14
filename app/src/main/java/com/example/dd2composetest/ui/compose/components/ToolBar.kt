package com.example.dd2composetest.ui.compose.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dd2composetest.ThisApp


enum class ToolBarType {
    NORMAL_TOOLBAR,
    LOGOUT_TOOLBAR
}

// rightBtnType: 0 -> not show, 1 -> 登出
@Composable
fun Toolbar(navController: NavHostController, title: String, rightBtnType: ToolBarType, otherAction1: () -> Unit = {}) {
    TopAppBar(
        modifier = Modifier.height(56.dp),
        backgroundColor = Color(0xff24262c),
        contentColor = Color.White,
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                    otherAction1.invoke()
                          },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            if (rightBtnType != ToolBarType.NORMAL_TOOLBAR) Text(
                text = if (rightBtnType == ToolBarType.LOGOUT_TOOLBAR) "登出" else "",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .align(Alignment.CenterEnd)
                    .clickable {
                        Toast
                            .makeText(ThisApp.instance, "登出", Toast.LENGTH_SHORT)
                            .show()
                        otherAction1.invoke()
                    },
                color = Color.White,
                fontSize = 18.sp
            )

        }
    }
//    TopAppBar(
//        modifier = Modifier.height(56.dp),
//        title = {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(end = 68.dp),
//                horizontalArrangement = Arrangement.Center
//            ) {
//                Text(text = title, fontSize = 18.sp)
//            }
//        },
//        navigationIcon = {
//            IconButton(onClick = {
//                navController.popBackStack()
//            }) {
//                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
//            }
//        },
//        backgroundColor = Color(0xff24262c),
//        contentColor = Color.White,
//    )
}

@Preview
@Composable
fun PreviewToolbar(navController: NavHostController = NavHostController(LocalContext.current),
                   title: String = "頁面名稱", rightBtnType: Int = 1) {
    Toolbar(navController = navController, title = title, rightBtnType = ToolBarType.LOGOUT_TOOLBAR)
}
//
//@Preview
//@Composable
//fun PreviewToolbar1(navController: NavHostController = NavHostController(LocalContext.current),
//                    title: String = "頁面名稱", rightBtnType: Int = 0) {
//    TopAppBar(
//        modifier = Modifier.height(56.dp),
//        title = {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
////                horizontalArrangement = Arrangement.Center
//            ) {
//                Text(
//                    text = title,
//                    modifier = Modifier.padding(start = 90.dp),
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold
//                )
//                Text(
//                    text = if (rightBtnType == 0) "" else "登出",
//                    modifier = Modifier.padding(end = 16.dp),
//                    fontSize = 18.sp
////                    modifier = Modifier.padding(end = 12.dp)
//                )
//            }
//        },
//        navigationIcon = {
//            IconButton(onClick = {
//                navController.popBackStack()
//            }) {
//                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
//            }
//        },
//        backgroundColor = Color(0xff24262c),
//        contentColor = Color.White,
//    )
//}
