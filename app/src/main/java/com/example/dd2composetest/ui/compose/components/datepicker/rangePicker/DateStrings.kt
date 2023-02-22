package com.example.dd2composetest.ui.compose.components.datepicker.rangePicker

import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

/**
 * Util methods for formatting date strings for use in [DateRangePicker].
 *
 * @author Alireza Milani
 * @since 1.0.0
 */
object DateStrings {

    fun getDateString(timeInMillis: Long, userDefinedDateFormat: SimpleDateFormat? = null): String {
        val currentCalendar = RangePickerDates.getTodayCalendar()
        val calendarDate = RangePickerDates.getCalendar()
        calendarDate.timeInMillis = timeInMillis

        if (userDefinedDateFormat != null) {
            val date = Date(timeInMillis)
            return userDefinedDateFormat.format(date)
        } else if (currentCalendar.calendarYear == calendarDate.calendarYear) {
            return getMonthDay(calendarDate)
        }

        return getYearMonthDay(calendarDate)
    }

    fun getFieldDateString(timeInMillis: Long, userDefinedDateFormat: SimpleDateFormat? = null): String {
        val currentCalendar = RangePickerDates.getTodayCalendar()
        val calendarDate = RangePickerDates.getCalendar()
        calendarDate.timeInMillis = timeInMillis

        if (userDefinedDateFormat != null) {
            val date = Date(timeInMillis)
            return userDefinedDateFormat.format(date)
        } else if (currentCalendar.calendarYear == calendarDate.calendarYear) {
            return getMonthDayYear(calendarDate)
        }

        return getMonthDayYear(calendarDate)
    }



    private fun getMonthDay(calendar: RangePickerCalendar): String =
        calendar.run { "$calendarDay $calendarMonthName" }

    private fun getYearMonthDay(calendar: RangePickerCalendar): String =
        calendar.run { "$calendarDay $calendarMonthName ,$calendarYear" }

    private fun getMonthDayYear(calendar: RangePickerCalendar): String =
        calendar.run { "$calendarMonth/$calendarDay/${getTwoStringYear(calendarYear)}" }

    fun getTwoStringYear(year: Int): String {
        val yearString = "$year"
        return yearString.substring(2)
    }

    fun getDateRangeString(start: Long?, end: Long?): Pair<String?, String?> {
        if (start == null && end == null) {
            return Pair(null, null)
        } else if (start == null) {
            return Pair(null, getDateString(end!!))
        } else if (end == null) {
            return Pair(getDateString(start), null)
        }

        val currentCalendar = RangePickerDates.getTodayCalendar()
        val startCalendar = RangePickerDates.getCalendar().apply {
            timeInMillis = start
        }
        val endCalendar = RangePickerDates.getCalendar().apply {
            timeInMillis = end
        }

        if (startCalendar.calendarYear == endCalendar.calendarYear) {
            return if (startCalendar.calendarYear == currentCalendar.calendarYear) {
                Pair(getMonthDay(startCalendar), getMonthDay(endCalendar))
            } else {
                Pair(getMonthDay(startCalendar), getYearMonthDay(endCalendar))
            }
        }

        return Pair(getYearMonthDay(startCalendar), getYearMonthDay(endCalendar))
    }

    fun convertMDYToMills(string: String): Long {
        val list = string.split("/")
        val year = if (list[2].length == 2) "20${list[2]}" else list[2]
        val month = list[0]
        val day = list[1]
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.parse("$year-$month-$day")?.time ?: 0L
    }
}