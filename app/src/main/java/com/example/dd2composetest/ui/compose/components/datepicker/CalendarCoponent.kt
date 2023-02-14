package com.example.dd2composetest.ui.compose.components.datepicker

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.example.dd2composetest.ui.compose.mine.TestDatePickerScreen
import com.example.dd2composetest.utils.DatePosition
import com.example.dd2composetest.utils.DateUtils
import java.time.LocalDateTime
import java.time.Month
import java.time.Year

/**
 * 參考自
 * https://blog.csdn.net/u010055819/article/details/127867177?spm=1001.2101.3001.6650.3&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EYuanLiJiHua%7EPosition-3-127867177-blog-124315741.pc_relevant_3mothn_strategy_recovery&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EYuanLiJiHua%7EPosition-3-127867177-blog-124315741.pc_relevant_3mothn_strategy_recovery&utm_relevant_index=3
 * Date: 2023.02.01
 */

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarComponent(
    startDate: String = DateUtils.getTodayDate(),
    endDate: String = DateUtils.getTodayDate(),
    dateSelectedBack: (String, String) -> Unit
) {
    // 當前年份
    var cYear by remember {
        mutableStateOf(LocalDateTime.now().year)
    }
    // 當前月
    var cMonth by remember {
        mutableStateOf(LocalDateTime.now().month)
    }

    /**
     * 選中的日期
     */
    var selectedDate = remember {
        mutableStateListOf(startDate, endDate)
    }

    MonthComponent(
        year = cYear,
        month = cMonth,
        selectDay = selectedDate,
        preMonth = {
            if (cMonth.value == 1) {
                cYear = cYear.minus(1)
                cMonth = Month.DECEMBER
            } else {
                cMonth = cMonth.minus(1)
            }
        },
        nextMonth = {
            if (cMonth.value == 12) {
                cYear = cYear.plus(1)
                cMonth = Month.JANUARY
            } else {
                cMonth = cMonth.plus(1)
            }
        },
        dayClick = {
            if (selectedDate.size == 2) {
                selectedDate.clear()
            }
            selectedDate.add(it)
            if (selectedDate.size == 2) {
                if (DateUtils.compareTime(selectedDate[0], selectedDate[1])) {
                    dateSelectedBack.invoke(selectedDate[0], selectedDate[1])
                } else {
                    dateSelectedBack.invoke(selectedDate[1], selectedDate[0])
                }
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewCalendarComponent() {
    CalendarComponent(dateSelectedBack = {string1, string2 -> })
}

/**
 *
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MonthComponent(
    year: Int,
    month: Month,
    selectDay: List<String>,
    preMonth: () -> Unit,
    nextMonth: () -> Unit,
    dayClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(vertical = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Icon(
                bitmap = ImageBitmap.imageResource(id = R.mipmap.arrow_right),
                contentDescription = "",
                modifier = Modifier
                    .rotate(180f)
                    .clickable { preMonth.invoke() },
                tint = Color.White
            )
            Text(
                text = "$year - ${DateUtils.getMonthValue(month.value)}",
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Icon(
                bitmap = ImageBitmap.imageResource(id = R.mipmap.arrow_right),
                contentDescription = "",
                modifier = Modifier.clickable { nextMonth.invoke() },
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        // 星期展示
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = "一", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(text = "二", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(text = "三", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(text = "四", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(text = "五", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(text = "六", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(text = "日", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(7)) {
            items(DateUtils.getWeekOfDate("$year-${DateUtils.getMonthValue(month.value)}-01") - 1) {
                Text(text = "")
            }
            items(DateUtils.getDayOfMonth("$year-${DateUtils.getMonthValue(month.value)}-01")) { index ->
                Day(
                    day = index + 1,
                    position = DateUtils.checkDataIsInTime(
                        "$year-${DateUtils.getMonthValue(month.value)}-${DateUtils.getMonthValue(index + 1)}",
                        selectDay
                    ),
                    dayClick = {
                        dayClick.invoke(
                            "$year-${DateUtils.getMonthValue(month.value)}-${DateUtils.getMonthValue(it)}"
                        )
                    }
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewMonthComponent() {
    MonthComponent(
        year = 2023,
        month = Month.FEBRUARY,
        selectDay = listOf(),
        preMonth = { /*TODO*/ },
        nextMonth = { /*TODO*/ }, dayClick = {})
}

// 建一個函數，day 就是第幾天，selectDay 用來判斷是否是被點擊的那天，然後拋出去一個 lambda，處理點擊事件，lambda傳入的day就是用來說明是第幾天
@Composable
fun Day(day: Int, position: DatePosition, dayClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
            .clip(
                shape = RoundedCornerShape(
                    topStart = if (position == DatePosition.START || position == DatePosition.SINGLE_1 || position == DatePosition.SINGLE_2) 10.dp else 0.dp,
                    bottomStart = if (position == DatePosition.START || position == DatePosition.SINGLE_1 || position == DatePosition.SINGLE_2) 10.dp else 0.dp,
                    topEnd = if (position == DatePosition.END || position == DatePosition.SINGLE_1 || position == DatePosition.SINGLE_2) 10.dp else 0.dp,
                    bottomEnd = if (position == DatePosition.END || position == DatePosition.SINGLE_1 || position == DatePosition.SINGLE_2) 10.dp else 0.dp
                )
            )
            .background(
                when (position) {
                    DatePosition.OTHER -> Color.Transparent
                    DatePosition.SINGLE_1 -> colorResource(id = R.color.purple_200)
                    else -> colorResource(id = R.color.purple_500)
                }
            )
            .clickable {
                dayClick(day)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.toString(),
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun PreviewDay() {
    Day(day = 3, position = DatePosition.SINGLE_1, dayClick = {})
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Toolbar(navController = navController, title = "", rightBtnType = ToolBarType.NORMAL_TOOLBAR)
        CalendarComponent(
            dateSelectedBack = { start, end ->
                Log.i("Arthur_test", "開始$start 結束$end")
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.datePicker(navController: NavHostController) {
    composable(Screen.CALENDAR_SCREEN.route) {
        CalendarScreen(navController)
    }
}

fun NavHostController.navigateToCalendar() {
    navigate(Screen.CALENDAR_SCREEN.route) {
        launchSingleTop = true
    }
}



