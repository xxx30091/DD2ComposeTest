package com.example.dd2composetest.ui.compose.components.datepicker.rangePicker

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class RangePickerCalendar : GregorianCalendar {

    var calendarYear = 0
        private set // 將 setter 變為私有，此時限制在外部使用時，僅能讀取無法修改。

    var calendarMonth = 0
        private set
        get() = field + 1

    var calendarDay = 0
        private set

    /**
     * Use to separate Date's field and also Parse the DateString based on this delimiter
     */
    var delimiter = "/"

    val calendarMonthName: String
        get() = CalendarConstants.MONTH_NAMES[calendarMonth - 1]

    val longDateString: String
        get() = "$calendarYear-$calendarMonth-$calendarDay"

    val selectedDateString: String
        get() {
            val month = formatToMilitary(calendarMonth)
            val day = formatToMilitary(calendarDay)
            return "$calendarYear-$month-$day"
        }

    /**
     * String of date formatted by
     *  **2023[delimiter]01[delimiter]01** default delimiter is '/'
     */
    val shortDateString: String
        get() = formatToMilitary(calendarYear) + delimiter + formatToMilitary(calendarMonth) + delimiter + formatToMilitary(calendarDay)

    constructor(millis: Long) {
        timeInMillis = millis
    }

    constructor() : super(TimeZone.getDefault(), Locale.getDefault())

    override fun set(field: Int, value: Int) {
        super.set(field, value)
        calculateDate()
    }

    override fun setTimeInMillis(millis: Long) {
        super.setTimeInMillis(millis)
        calculateDate()
    }

    override fun setTimeZone(zone: TimeZone) {
        super.setTimeZone(zone)
        calculateDate()
    }

    fun setDisplayDate(y: Int, m: Int, d: Int) {
        calendarYear = y
        calendarMonth = m
        calendarDay = d
    }

    /**
     * 從當前日期填充相應的字段 *calendarYear、calendarMonth、calendarDay*
     */
    private fun calculateDate() {
        val yearMonthDay = YearMonthDay(get(YEAR), get(MONTH), get(DAY_OF_MONTH))
        calendarYear = yearMonthDay.year
        calendarMonth = yearMonthDay.month
        calendarDay = yearMonthDay.day
    }

    fun setCalendarDate(year: Int, month: Int, day: Int) {
        calendarYear = year
        calendarMonth = month - 1
        calendarDay = day
        Log.i("Arthur_debug", "2. setCalendarDate: month = $month, calendarMonth = $calendarMonth")
        Log.i("Arthur_debug", "3. get date: year = ${get(YEAR)}, month = ${get(MONTH)}, day = ${get(DAY_OF_MONTH)}")
        set(calendarYear, calendarMonth, calendarDay)
        Log.i("Arthur_debug", "4. get date2: year = ${get(YEAR)}, month = ${get(MONTH)}, day = ${get(DAY_OF_MONTH)}")
    }

    /**
     * Set the date it converts to milliseconds to the instance.
     * 將 Date 轉換為等效的毫秒數
     */
    fun setCommonDay(day: Int) {
        calendarDay = day

        // 算出當天 毫秒
        timeInMillis = convertToMillis(calendarYear, calendarMonth, calendarDay)
//        timeInMillis = convertToMillis(get(YEAR), get(MONTH), get(DAY_OF_MONTH))
    }

    override fun compareTo(other: Calendar): Int {
        require(other is RangePickerCalendar) { "other is not PersianCalendar" }

        return when {
            calendarYear < other.calendarYear -> -1
            calendarYear > other.calendarYear -> 1
            else -> {
                when {
                    calendarMonth < other.calendarMonth -> -1
                    calendarMonth > other.calendarMonth -> 1
                    else -> {
                        when {
                            calendarDay < other.calendarDay -> -1
                            calendarDay > other.calendarDay -> 1
                            else -> 0
                        }
                    }
                }
            }
        }
    }

    /**
     * 每一個可以被4整除的年份都是閏年，但可以被100整除的年份，必須也能被400整除才是閏年。
     * 因此，1700年、1800年、和1900年都不是閏年，而2000年是閏年[6]。
     */
    fun getMonthLength(): Int =
        if (calendarMonth == 2 && calendarYear % 4 == 0 && calendarYear % 100 != 0 || calendarYear % 400 == 0) 29
        else gregorianDaysInMonth[calendarMonth - 1]

    private fun convertToMillis(year: Int, month: Int, day: Int): Long  {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = dateFormat.parse("$year-${month}-$day")
        return date?.time ?: 0L
    }

    internal class YearMonthDay(var year: Int, var month: Int, var day: Int) {
        override fun toString(): String {
            return "$year/$month/$day"
        }
    }

    companion object {
        private val gregorianDaysInMonth = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    }
}

fun formatToMilitary(i: Int): String = if (i <= 9) "0$i" else i.toString()