package com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.*

/**
 * A layout to show days of month
 *
 * @author Alireza Milani
 * @since 1.0.0
 */
@Composable
fun CalendarMonth(
    modifier: Modifier = Modifier,
    isRtl: Boolean,
    state: DateRangePickerState = rememberDateRangePickerState(yearRange = IntRange(1400, 1401)),
    colors: DateRangePickerColors = DateRangePickerDefaults.colors(),
    calendar: RangePickerCalendar
) {
    val (firstDay, numDays) = remember { state.getDates(calendar) }
    val datesList = remember { IntRange(1, numDays).toList() }
    val heightFactor = remember { state.getMaximumWeeks(firstDay, numDays) }

    // 看能不能改成一開始就算好日期跟位置，而每一天的點擊事件去傳值就好

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = modifier
            .background(Color.White)
            .size(
                width = (48 * 7).dp,
                height = (heightFactor * 48).dp
            ),
        userScrollEnabled = false
    ) {

        // 設置每月第一日前空格數
//        for (x in 0 until firstDay) {
//            item { Spacer(Modifier.size(48.dp)) }
//        }
        items(firstDay) { Spacer(Modifier.size(48.dp)) }

        items(
            datesList
        ) { date ->
            val currentDate = (calendar.clone() as RangePickerCalendar).apply {
                setCommonDay(date)
            }
            val isSelected = remember(state.selectedStartDate, state.selectedEndDate) {
                state.isSelected(currentDate.timeInMillis)
            }

            val isRangeFill = remember(state.selectedStartDate, state.selectedEndDate) {
                state.isInRange(currentDate.timeInMillis)
            }

            val dayType = remember(state.selectedStartDate, state.selectedEndDate) {
                state.getDayType(currentDate.timeInMillis)
            }

            // 不要讓他知道這麼多資訊，他只負責顯示資料
            // 傳資料由點擊時再傳出相應位置的值？
            CalendarDay(
                date = date,
                selected = isSelected,
                dayType = dayType,
                isRtl = isRtl,
                isRangeFill = isRangeFill,
                colors = colors,
                onClick = { state.select(currentDate.timeInMillis) }
            )
        }
    }
}

@Preview
@Composable
fun CalendarMonthPreview() {
    CalendarMonth(calendar = RangePickerCalendar(), isRtl = false)
}

@Composable
fun CalendarMonth2(
    modifier: Modifier = Modifier,
    isRtl: Boolean,
    state: DateRangePickerState = rememberDateRangePickerState(yearRange = IntRange(1400, 1401)),
    colors: DateRangePickerColors = DateRangePickerDefaults.colors(),
    calendar: RangePickerCalendar
) {
    val (firstDay, numDays) = remember { state.getDates(calendar) }
    val datesList = remember { IntRange(1, numDays).toList() }
    val heightFactor = remember { state.getMaximumWeeks(firstDay, numDays) }

    // 看能不能改成一開始就算好日期跟位置，而每一天的點擊事件去傳值就好
    Column(
        modifier = Modifier
    ) {

    }


    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = modifier
            .background(Color.White)
            .size(
                width = (48 * 7).dp,
                height = (heightFactor * 48).dp
            ),
        userScrollEnabled = false
    ) {

        // 設置每月第一日前空格數
        for (x in 0 until firstDay) {
            item { Spacer(Modifier.size(48.dp)) }
        }

        items(datesList) { date ->
            val currentDate = (calendar.clone() as RangePickerCalendar).apply {
                setCommonDay(date)
            }
            val isSelected = remember(state.selectedStartDate, state.selectedEndDate) {
                state.isSelected(currentDate.timeInMillis)
            }

            val isRangeFill = remember(state.selectedStartDate, state.selectedEndDate) {
                state.isInRange(currentDate.timeInMillis)
            }

            val dayType = remember(state.selectedStartDate, state.selectedEndDate) {
                state.getDayType(currentDate.timeInMillis)
            }

            // 不要讓他知道這麼多資訊，他只負責顯示資料
            // 傳資料由點擊時再傳出相應位置的值？
            CalendarDay(
                date = date,
                selected = isSelected,
                dayType = dayType,
                isRtl = isRtl,
                isRangeFill = isRangeFill,
                colors = colors,
                onClick = { state.select(currentDate.timeInMillis) }
            )
        }
    }
}