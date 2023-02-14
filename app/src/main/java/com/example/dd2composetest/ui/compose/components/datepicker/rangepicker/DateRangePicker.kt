package com.example.dd2composetest.ui.compose.components.datepicker.rangepicker

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.StateObject
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dd2composetest.utils.DateUtils
import java.time.LocalDateTime
import java.time.Month
import kotlin.reflect.KProperty

@Composable
fun DateRangePicker(

) {

}

@Preview
@Composable
fun PreviewDateRangePicker() {

}

//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun CalendarComponent(
//    startDate: String = DateUtils.getTodayDate(),
//    endDate: String = DateUtils.getTodayDate(),
//    dateSelectedBack: (String, String) -> Unit
//) {
//    // 當前年月
//    var cYear by remember {
//        mutableStateOf(LocalDateTime.now().year)
//    }
//    var cMonth by remember {
//        mutableStateOf(LocalDateTime.now().month)
//    }
//
//    /**
//     * 選中日期
//     */
////    var selectedDate by remember {
////        mutableStateListOf(startDate, endDate)
////    }
//
//
//}
//
///**
// * 月份組件
// * @param year Int 當前年份
// * @param month Month 當前月份
// * @param selectDay List<String> 已選中的日期
// * @param preMonth Function0<Unit> 上個月點擊事件
// * @param nextMonth Function0<Unit> 下個月點擊事件
// * @param dayClick Function1<String, Unit>
// */
//@Composable
//fun MonthComponent(
//    year: Int,
//    month: Month,
//    selectDay: List<String>,
//    preMonth: () -> Unit,
//    nextMonth: () -> Unit,
//    dayClick: (String) -> Unit
//) {
//    Column(
//        modifier = Modifier
//            .background(Color.Black)
//            .padding(vertical = 20.dp)
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceAround
//        ) {
//        }
//    }
//}