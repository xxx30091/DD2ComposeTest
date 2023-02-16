package com.example.dd2composetest.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.dd2composetest.R
import com.example.dd2composetest.enum.Screen
import com.example.dd2composetest.ui.compose.mine.edituser.navigateToEditUser
import com.example.dd2composetest.ui.compose.mine.myworks.navigateToMyWorks
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
fun MyDataToolbar(navController: NavHostController, title: String) {
    TopAppBar(
        modifier = Modifier.height(56.dp),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 68.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = title, fontSize = 18.sp)
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        },
        backgroundColor = Color.Black,
        contentColor = Color.White,
    )
}

@Preview
@Composable
fun PreviewMyDataToolbar() {
    TopAppBar(
        modifier = Modifier.height(56.dp),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 68.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "我的资料", fontSize = 18.sp)
            }
        },
        navigationIcon = {
            IconButton(onClick = {

            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        },
        backgroundColor = Color.Black,
        contentColor = Color.White,
    )
}

@ExperimentalPagerApi
@Composable
fun Item(imgId: Int, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imgId),
            contentDescription = "",
            modifier = Modifier
                .size(49.dp, 20.dp)
                .padding(start = 23.dp)
        )
        Text(
            text = title,
            modifier = Modifier
                .padding(start = 21.dp)
                .weight(0.7f),
            fontSize = 14.sp,
            color = Color(0xff222226)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = "",
            modifier = Modifier.padding(end = 13.dp)
        )
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp),
        color = Color(0xffe2e2e2)
    )
}

@ExperimentalPagerApi
@Preview
@Composable
fun PreviewItem(imgId: Int = R.drawable.ic_edit_user, title: String = "编辑个人资料", onClick: () -> Unit = { }) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imgId),
            contentDescription = "",
            modifier = Modifier
                .size(49.dp, 20.dp)
                .padding(start = 23.dp)
        )
        Text(
            text = title,
            modifier = Modifier
                .padding(start = 21.dp)
                .weight(0.7f),
            fontSize = 14.sp,
            color = Color(0xff222226)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = "",
            modifier = Modifier.padding(end = 13.dp)
        )
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp),
        color = Color(0xffe2e2e2)
    )
}

@ExperimentalPagerApi
@Composable
fun MyDataScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top
    ) {
        MyDataToolbar(navController, "我的资料")
        Item(R.drawable.ic_edit_user, "编辑个人资料") { navController.navigateToEditUser() }
        Item(R.drawable.ic_my_work, "我的作品") { navController.navigateToMyWorks() }
        Item(R.drawable.ic_notification, "消息通知") { navController.navigateToFansPromote() }
        Item(R.drawable.ic_my_favorite, "我的收藏") { navController.navigateToRecommendationVideo() }
    }
}
//fun MyDataScreen(onClickRecommendVideo: () -> Unit, onClickFansPromote: () -> Unit) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White),
//        verticalArrangement = Arrangement.Top
//    ) {
//        Toolbar()
//        Item(R.drawable.ic_edit_user, "编辑个人资料", onClickRecommendVideo)
//        Item(R.drawable.ic_my_work, "我的作品", onClickFansPromote)
//        Item(R.drawable.ic_notification, "消息通知", onClickRecommendVideo)
//        Item(R.drawable.ic_my_favorite, "我的收藏", onClickFansPromote)
//    }
//}
// function 直接寫在這邊就好


@ExperimentalPagerApi
@Preview
@Composable
fun PreviewMyDataScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top
    ) {
        PreviewMyDataToolbar()
        Item(R.drawable.ic_edit_user, "编辑个人资料") {  }
        Item(R.drawable.ic_my_work, "我的作品") {  }
        Item(R.drawable.ic_notification, "消息通知") {  }
        Item(R.drawable.ic_my_favorite, "我的收藏") {  }
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
fun NavGraphBuilder.myData(navController: NavHostController) {
    composable(Screen.MY_DATA_SCREEN.route) {
        MyDataScreen(navController = navController)
    }
}

fun NavHostController.navigateToMyData() {
    navigate(Screen.MY_DATA_SCREEN.route) {
        launchSingleTop = true
    }
}