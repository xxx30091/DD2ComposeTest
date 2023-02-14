package com.example.dd2composetest.ui.compose.components.datepicker.rangepicker

import com.example.dd2composetest.YearMonthDay
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
     * String of persian date formatted by
     *  **2023[delimiter]01[delimiter]01** default delimiter is '/'
     */
    val shortDateString: String
        get() = formatToMilitary(calendarYear) + delimiter + formatToMilitary(calendarMonth) + delimiter + formatToMilitary(calendarDay)

    constructor(millis: Long) {
        timeInMillis = millis
    }

    constructor() : super(TimeZone.getDefault(), Locale.getDefault())

    override fun toString(): String {
        return super.toString()
    }

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

    /**
     * 從當前日期填充相應的字段 *calendarYear、calendarMonth、calendarDay*
     */
    private fun calculateDate() {
//        val persianYearMonthDay = gregorianToJalali(YearMonthDay(get(YEAR), get(MONTH), get(DAY_OF_MONTH)))
        val yearMonthDay = YearMonthDay(get(YEAR), get(MONTH), get(DAY_OF_MONTH))

        calendarYear = yearMonthDay.year
        calendarMonth = yearMonthDay.month
        calendarDay = yearMonthDay.day
    }

    /**
     * Set the persian date it converts PersianDate to the Julian and
     * assigned equivalent milliseconds to the instance
     * 設置將 Date 轉換為等效的毫秒數
     */

    fun setCalendarDate(year: Int, month: Int, day: Int) {
        calendarYear = year
        calendarMonth = month - 1
        calendarDay = day

//        val gregorianYearMonthDay =
//            persianToGregorian(YearMonthDay(calendarYear, this.calendarMonth - 1, calendarDay))
//        set(gregorianYearMonthDay.year, gregorianYearMonthDay.month, gregorianYearMonthDay.day)
        set(calendarYear, calendarMonth, calendarDay)
    }

    fun setCalendarDay(day: Int) {
        calendarDay = day

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val month = calendarMonth - 1
//        val date = dateFormat.parse("2023-02-03 00:00").time

//        timeInMillis =
    }


}

fun formatToMilitary(i: Int): String = if (i <= 9) "0$i" else i.toString()