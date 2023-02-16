package com.example.dd2composetest.ui.compose.components.datepicker.rangePicker

import com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.CalendarConstants.Companion.MILLIS_JULIAN_EPOCH
import com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.CalendarConstants.Companion.MILLIS_OF_A_DAY
import com.example.dd2composetest.ui.compose.components.datepicker.rangePicker.PersianCalendarUtils.Companion.ceil
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor


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

    // seem no used
//    fun setCalendarDay(day: Int) {
//        calendarDay = day
//
//        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
//        val month = calendarMonth - 1
////        val date = dateFormat.parse("2023-02-03 00:00").time
//
////        timeInMillis =
//    }

    // TODO 不要用這個日期
    fun setCommonDay(day: Int) {
        calendarDay = day

        // 算出當天 毫秒
//        timeInMillis = convertToMillis(
//            PersianCalendarUtils.persianToJulian(
//                if (calendarYear > 0) calendarYear.toLong() else calendarYear + 1.toLong(),
//                calendarMonth - 1,
//                calendarDay
//            )
//        )
        timeInMillis = convertToMillis(calendarYear, calendarMonth, calendarDay)
    }

    override fun compareTo(other: java.util.Calendar): Int {
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
    fun getMonthLength(): Int = if (calendarMonth == 2 && calendarYear % 4 == 0 && calendarYear % 100 != 0 || calendarYear % 400 == 0) {
        29
    } else {
        gregorianDaysInMonth[calendarMonth - 1]
    }
    // MILLIS_JULIAN_EPOCH = -210866803200000L, MILLIS_OF_A_DAY = 86400000L
    private fun convertToMillis(julianDate: Long): Long =
        MILLIS_JULIAN_EPOCH + julianDate * MILLIS_OF_A_DAY + ceil((timeInMillis - MILLIS_JULIAN_EPOCH.toDouble()), MILLIS_OF_A_DAY.toDouble())

    private fun convertToMillis(year: Int, month: Int, day: Int): Long  {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = dateFormat.parse("$year-${month}-$day")
        return date?.time ?: 0L
    }

    /**
     * Calculate persian date from current Date and populates the corresponding
     * fields *persianYear, persianMonth, persianDay*
     */
    private fun calculatePersianDate() {
//        val persianYearMonthDay =
//            gregorianToJalali(YearMonthDay(get(YEAR), get(MONTH), get(DAY_OF_MONTH)))
//
//        calendarYear = persianYearMonthDay.year
//        calendarMonth = persianYearMonthDay.month
//        calendarDay = persianYearMonthDay.day
//        Log.i("AAA", "$calendarYear, $calendarMonth, $calendarDay")
    }

    internal class YearMonthDay(var year: Int, var month: Int, var day: Int) {
        override fun toString(): String {
            return "$year/$month/$day"
        }
    }

    companion object {

        private val gregorianDaysInMonth = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        private val persianDaysInMonth = intArrayOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29)

        private fun gregorianToJalali(gregorian: YearMonthDay): YearMonthDay {
            require(!(gregorian.month > 11 || gregorian.month < -11))

            var persianYear: Int
            val persianMonth: Int
            val persianDay: Int
            var persianDayNo: Int

            gregorian.year = gregorian.year - 1600
//            gregorian.day = gregorian.day - 1

            /**
             * 每一個可以被4整除的年份都是閏年，但可以被100整除的年份，必須也能被400整除才是閏年。
             * 因此，1700年、1800年、和1900年都不是閏年，而2000年是閏年[6]。
             */
            // 該從 0 到年份總共有幾天
            var gregorianDayNo: Int =
                (365 * gregorian.year + floor(((gregorian.year + 3) / 4).toDouble()).toInt() - floor(
                    ((gregorian.year + 99) / 100).toDouble()
                ).toInt() + floor(((gregorian.year + 399) / 400).toDouble()).toInt())

            // 加上到該月份為止的天數
            var i = 0
            while (i < gregorian.month) {
                gregorianDayNo += gregorianDaysInMonth[i]
                ++i
            }

            // 加一日
            if (gregorian.month > 1 && (gregorian.year % 4 == 0 && gregorian.year % 100 != 0 || gregorian.year % 400 == 0)) {
                ++gregorianDayNo
            }
            // 加上當前日數
            gregorianDayNo += gregorian.day

            persianDayNo = gregorianDayNo - 79
            val persianNP: Int = floor((persianDayNo / 12053).toDouble()).toInt()
            persianDayNo %= 12053
            persianYear = 979 + 33 * persianNP + 4 * (persianDayNo / 1461)
            persianDayNo %= 1461
            if (persianDayNo >= 366) {
                persianYear += floor(((persianDayNo - 1) / 365).toDouble()).toInt()
                persianDayNo = (persianDayNo - 1) % 365
            }
            i = 0
            while (i < 11 && persianDayNo >= persianDaysInMonth[i]) {
                persianDayNo -= persianDaysInMonth[i]
                ++i
            }
            persianMonth = i
            persianDay = persianDayNo + 1

//            return YearMonthDay(gregorian.year, gregorian.month, gregorian.day)
            return YearMonthDay(persianYear, persianMonth, persianDay)
        }

        private fun persianToGregorian(persian: YearMonthDay): YearMonthDay {
            require(!(persian.month > 11 || persian.month < -11))
            var gregorianYear: Int
            val gregorianMonth: Int
            val gregorianDay: Int
            var gregorianDayNo: Int
            var leap: Int
            persian.year = persian.year - 979
            persian.day = persian.day - 1
            var persianDayNo: Int =
                365 * persian.year + (persian.year / 33) * 8 + floor(((persian.year % 33 + 3) / 4).toDouble()).toInt()

            var i = 0
            while (i < persian.month) {
                persianDayNo += persianDaysInMonth[i]
                ++i
            }
            persianDayNo += persian.day
            gregorianDayNo = persianDayNo + 79
            gregorianYear = 1600 + 400 * floor((gregorianDayNo / 146097).toDouble()).toInt()
            gregorianDayNo %= 146097
            leap = 1
            if (gregorianDayNo >= 36525) {
                gregorianDayNo--
                gregorianYear += 100 * floor((gregorianDayNo / 36524).toDouble()).toInt()
                gregorianDayNo %= 36524
                if (gregorianDayNo >= 365) {
                    gregorianDayNo++
                } else {
                    leap = 0
                }
            }
            gregorianYear += 4 * floor((gregorianDayNo / 1461).toDouble()).toInt()
            gregorianDayNo %= 1461
            if (gregorianDayNo >= 366) {
                leap = 0
                gregorianDayNo--
                gregorianYear += floor((gregorianDayNo / 365).toDouble()).toInt()
                gregorianDayNo %= 365
            }
            i = 0
            while (gregorianDayNo >= gregorianDaysInMonth[i] + if (i == 1 && leap == 1) i else 0) {
                gregorianDayNo -= gregorianDaysInMonth[i] + if (i == 1 && leap == 1) i else 0
                i++
            }
            gregorianMonth = i
            gregorianDay = gregorianDayNo + 1
            return YearMonthDay(gregorianYear, gregorianMonth, gregorianDay)
        }
    }
}

fun formatToMilitary(i: Int): String = if (i <= 9) "0$i" else i.toString()