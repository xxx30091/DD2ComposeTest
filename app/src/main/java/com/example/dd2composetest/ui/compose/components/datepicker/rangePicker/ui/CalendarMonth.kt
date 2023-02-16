package com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
    modifier: Modifier = Modifier.background(Color.White),
    isRtl: Boolean,
    state: DateRangePickerState = rememberDateRangePickerState(yearRange = IntRange(1400, 1401)),
    colors: DateRangePickerColors = DateRangePickerDefaults.colors(),
    calendar: RangePickerCalendar
) {
    val (firstDay, numDays) = remember { state.getDates(calendar) }
    val datesList = remember { IntRange(1, numDays).toList() }
    val heightFactor = remember { state.getMaximumWeeks(firstDay, numDays) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = modifier.size(
            width = (48 * 7).dp,
            height = (heightFactor * 48).dp
        ),
        userScrollEnabled = false
    ) {

        // 計算每月第一日前空格數
        for (x in 0 until firstDay) {
            item { Box(Modifier.size(48.dp)) }
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