package com.example.dd2composetest.ui.compose.components.datepicker.rangepicker

import android.content.res.Resources
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

//class DateRangePickerState(
//    private val initialDates: Pair<Long?, Long?>? = null,
//    yearRange: IntRange,
//    private val resources: Resources
//) {
//    // 紀錄所選擇的起始/結束日期
//    var selectedStartDate: Long? by mutableStateOf(null)
//    var selectedEndDate: Long? by mutableStateOf(null)
//
//    var position: Int by mutableStateOf(0)
//
//    val months = mutableListOf<MyCalendar>()
//
//    init {
//        if (initialDates != null) {
//            setSelection(initialDates)
//        }
//        getMonths(yearRange)
//    }
//
//    /**
//     * Creates list of months according to year range
//     */
//    private fun getMonths(yearRange: IntRange) {
//        val calendar = if (initialDates?.first != null) {
//            IRSTDates.getCalendarOf(MyCalendar(initialDates.first!!))
//        } else {
//            IRSTDates.getTodayCalendar()
//        }
//
//        for (i in 0 until (yearRange.last - yearRange.first + 1) * 12) {
//            val currentCalendar = IRSTDates.getCalendar(
//                year = yearRange.first + (i / 12), month = i % 12 + 1, day = 1
//            )
//            if (currentCalendar.calendarYear == calendar.calendarYear && currentCalendar.calendarMonth == calendar.calendarMonth) {
//                position = i
//            }
//
//            months.add(currentCalendar)
//        }
//    }
//
//    private fun setSelection(selection: Pair<Long?, Long?>) {
//        if (selection.first != null && selection.second != null) {
//            require(isValidRange(selection.first!!, selection.second!!))
//        }
//
//        selectedStartDate = selection.first?.let {
//            IRSTDates.canonicalYearMonthDay(it)
//        }
//
//        selectedEndDate = selection.second?.let {
//            IRSTDates.canonicalYearMonthDay(it)
//        }
//    }
//
//    private fun isValidRange(start: Long, end: Long): Boolean = start <= end
//
//}
//
//
//
///**
//* Remembers and creates an instance of [DateRangePickerState]
//*/
//@Composable
//fun rememberDateRangePickerState(
//    initialDates: Pair<MyCalendar, MyCalendar>? = null,
//    yearRange: IntRange,
//    resources: Resources = LocalContext.current.resources
//): DateRangePickerState {
//    val startEndDates = initialDates?.run {
//        Pair(first.timeInMillis, second.timeInMillis)
//    }
//
//    return remember {
//        DateRangePickerState(startEndDates, yearRange, resources)
//    }
//}