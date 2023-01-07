package com.example.dd2composetest.ui.compose.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.dd2composetest.enum.Screen
import com.example.dd2composetest.ui.compose.components.Toolbar

@Preview
@Composable
fun SettingItem(title: String = "", subtitle: String = "", showPoint: Boolean = true, action: () -> Unit = {}) {
    Column(
        modifier = Modifier.clickable(onClick = action)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                modifier = Modifier.weight(1f)
            )
            if (showPoint) Divider(modifier = Modifier
                .size(5.dp)
                .background(
                    color = Color(0xffff0000),
                    shape = RoundedCornerShape(4.dp)
                )
            )
            Text(
                text = subtitle,
                modifier = Modifier.padding(start = 8.dp),
                color = Color(0xff999999)
            )
        }
        Divider(modifier = Modifier.height(0.5.dp), color = Color(0xff999999))
    }
}

@Preview
@Composable
fun SettingScreen(navController: NavHostController = NavHostController(LocalContext.current)) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Toolbar(navController = navController, title = "設置", rightBtnType = 1)
        SettingItem("修改密碼", "", false)
        SettingItem("註銷帳號", "", false)
        SettingItem("版本號", "2.0.11.0 st", false)
        SettingItem("軟件更新", "發現新版本", true)
        SettingItem("反饋問題", "", false)
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
fun NavGraphBuilder.setting(navController: NavHostController) {
    composable(Screen.SETTING_SCREEN.route) {
        SettingScreen(navController = navController)
    }
}

fun NavHostController.navigateToSetting() {
    navigate(Screen.SETTING_SCREEN.route) {
        launchSingleTop = true
    }
}
