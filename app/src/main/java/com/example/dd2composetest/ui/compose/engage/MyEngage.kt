package com.example.dd2composetest.ui.compose.engage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.dd2composetest.R
import com.example.dd2composetest.enum.Screen
import com.example.dd2composetest.ui.compose.components.ToolBarType
import com.example.dd2composetest.ui.compose.components.Toolbar
import com.example.dd2composetest.ui.compose.navigateToFansPromote


//@ExperimentalPagerApi
@Composable
fun EngageTab(navController: NavHostController) {
    var tabPosition by remember {
        mutableStateOf(0)
    }
    Column {
        Row(
            modifier = Modifier
                .height(45.dp)
                .fillMaxWidth()
                .background(Color.White)
                .padding(start = 7.dp, end = 7.dp)
        ) {
            Column(
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .padding(start = 10.dp, end = 10.dp)
                    .fillMaxHeight()
                    .clickable {
                        tabPosition = 0
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.engage_red_coin_incoming),
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
                    .width(IntrinsicSize.Max)
                    .padding(start = 10.dp, end = 10.dp)
                    .fillMaxHeight()
                    .clickable {
                        tabPosition = 1
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = LocalContext.current.getString(R.string.engage_unlocked_counts),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 11.dp, bottom = 10.dp))
                Divider(
                    modifier = Modifier
                        .height(2.dp)
                        .width(62.dp),
                    color = if (tabPosition == 1) Color(0xFF679FF8) else Color.White
                )
            }

            Column(
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .padding(start = 10.dp, end = 10.dp)
                    .fillMaxHeight()
                    .clickable {
                        tabPosition = 2
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = LocalContext.current.getString(R.string.engage_promote_tools),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 11.dp, bottom = 10.dp))
                Divider(
                    modifier = Modifier
                        .height(2.dp)
                        .width(62.dp),
                    color = if (tabPosition == 2) Color(0xFF679FF8) else Color.White
                )
            }
        }

        when (tabPosition) {
            0 -> {}
            1 -> {}
            2 -> PromoteTools(navController)
        }
    }
}

@Preview
@Composable
fun PreviewEngageTab() {
    EngageTab(NavHostController(LocalContext.current))
}

@Composable
fun PromoteTools(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffd8d8d8))
            .padding(top = 30.dp, start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
    ) {
        PromoteToolItem(imgId = R.drawable.ic_promote_video, title = "視頻推廣", action = {})
        PromoteToolItem(imgId = R.drawable.ic_promote_fans, title = "粉絲推送", action = { navController.navigateToFansPromote() })
    }
}

@Preview
@Composable
fun PreviewPromoteTool() {
    PromoteTools(NavHostController(LocalContext.current))
}

@Composable
fun PromoteToolItem(imgId: Int, title: String, action: () -> Unit) {
    val configuration = LocalConfiguration.current
    val screenWidth = (configuration.screenWidthDp.dp - 24.dp)/2
    Column(
        modifier = Modifier
            .clickable { action.invoke() }
            .background(Color.White)
            .width(screenWidth)
            .padding(top = 12.dp, bottom = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imgId),
            contentDescription = "",
            modifier = Modifier.size(width = 51.dp, height = 36.dp)
        )
        Text(text = title, modifier = Modifier.padding(top = 12.dp), fontSize = 12.sp, color = Color(0xff9d9d9d))
    }
}

@Preview
@Composable
fun PreviewPromoteToolItem() {
    PromoteToolItem(R.drawable.ic_promote_video, "視頻推廣", action = {})
}

@Composable
fun MyEngageScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Toolbar(
            navController = navController,
            title = stringResource(R.string.engage_status),
            toolbarType = ToolBarType.NORMAL_TOOLBAR
        )
        EngageTab(navController)
//        PromoteTools()
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
fun NavGraphBuilder.myEngage(navController: NavHostController) {
    composable(Screen.MY_ENGAGE_SCREEN.route) {
        MyEngageScreen(navController = navController)
    }
}

fun NavHostController.navigateToMyEngage() {
    navigate(Screen.MY_ENGAGE_SCREEN.route) {
        launchSingleTop = true
    }
}



