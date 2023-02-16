package com.example.dd2composetest

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.math.floor

@RequiresApi(Build.VERSION_CODES.N)
fun main() {
//    val a = ceil((2023 - 474.0), 2820.0)
//    val b = 2820 * floor(1549.0/2820.0)
//    val c = a + 474L - 1L
//
//    val d = persianToJulian(1401, 11, 13)
//    val e = convertToMillis(d)
//
//    val isLeapYear = isLeapYear(1700)
//
//    val shit = gregorianToJalali(YearMonthDay(2016, 6, 3))

    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
    val date = dateFormat.parse("2023-02-16 00:00").time

    val c = Calendar.getInstance()
    val t = c.timeInMillis
//    val weekDay = calWeekDay(2021, 1, 1)
    println("date = $date")
    println("tttt = $t") // 14:27
//    println(a)
//    println(b)
//    println(c)
//    println("date = $date")
//    println("weekDay = $weekDay")
//    println(e)
}
//
//fun ceil(x: Double, y: Double): Long = (x - y * floor(x / y)).toLong()
//
//fun persianToJulian(year: Long, month: Int, day: Int): Long {
//    return 365L * (ceil((year - 474.0), 2820.0) + 474L - 1L) +
//            floor((682L * (ceil((year - 474.0), 2820.0) + 474L) - 110L) / 2816.0).toLong() +
//            (1948321L - 1L) +
//            (1029983L * floor((year - 474L) / 2820.0).toLong()) +
//            (if (month < 7) 31 * month else 30 * month + 6) + day
//}

//const val MILLIS_JULIAN_EPOCH = -210866803200000L
//
///**
// * Milliseconds of a day calculated by
// *
// * ``24L(hours) * 60L(minutes) * 60L(seconds) * 1000L(millis)``
// */
//const val MILLIS_OF_A_DAY = 86400000L
//
///**
// * The JDN of 1 Farvardin 1; Equivalent to March 19, 622 A.D.
// */
//const val PERSIAN_EPOCH = 1948321L
//const val timeInMillis: Long = 0L
//
//private fun convertToMillis(julianDate: Long): Long =
//    MILLIS_JULIAN_EPOCH + julianDate * MILLIS_OF_A_DAY + ceil((timeInMillis - MILLIS_JULIAN_EPOCH.toDouble()), MILLIS_OF_A_DAY.toDouble())
//
//fun isLeapYear(year: Int): Boolean {
//    return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
//}
/////
//val persianDaysInMonth = intArrayOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29)
//val gregorianDaysInMonth = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
//class YearMonthDay(var year: Int, var month: Int, var day: Int)
//
//fun gregorianToJalali(gregorian: YearMonthDay): YearMonthDay {
//    require(!(gregorian.month > 11 || gregorian.month < -11))
//
//    var persianYear: Int
//    val persianMonth: Int
//    val persianDay: Int
//    var persianDayNo: Int
//
//    gregorian.year = gregorian.year - 1600
////            gregorian.day = gregorian.day - 1
//
//    /**
//     * 每一個可以被4整除的年份都是閏年，但可以被100整除的年份，必須也能被400整除才是閏年。
//     * 因此，1700年、1800年、和1900年都不是閏年，而2000年是閏年[6]。
//     */
//    // 該從 0 到年份總共有幾天
//    var gregorianDayNo: Int =
//        (365 * gregorian.year + floor(((gregorian.year + 3) / 4).toDouble()).toInt() - floor(
//            ((gregorian.year + 99) / 100).toDouble()
//        ).toInt() + floor(((gregorian.year + 399) / 400).toDouble()).toInt())
//
//    // 加上到該月份為止的天數
//    var i = 0
//    while (i < gregorian.month) {
//        gregorianDayNo += gregorianDaysInMonth[i]
//        ++i
//    }
//
//    // 加一日
//    if (gregorian.month > 1 && (gregorian.year % 4 == 0 && gregorian.year % 100 != 0 || gregorian.year % 400 == 0)) {
//        ++gregorianDayNo
//    }
//    // 加上當前日數
//    gregorianDayNo += gregorian.day
//
//    persianDayNo = gregorianDayNo - 79
//    val persianNP: Int = floor((persianDayNo / 12053).toDouble()).toInt()
//    persianDayNo %= 12053
//    persianYear = 979 + 33 * persianNP + 4 * (persianDayNo / 1461)
//    persianDayNo %= 1461
//    if (persianDayNo >= 366) {
//        persianYear += floor(((persianDayNo - 1) / 365).toDouble()).toInt()
//        persianDayNo = (persianDayNo - 1) % 365
//    }
//    i = 0
//    while (i < 11 && persianDayNo >= persianDaysInMonth[i]) {
//        persianDayNo -= persianDaysInMonth[i]
//        ++i
//    }
//    persianMonth = i
//    persianDay = persianDayNo + 1
//
////            return YearMonthDay(gregorian.year, gregorian.month, gregorian.day)
//    return YearMonthDay(persianYear, persianMonth, persianDay)
//}

fun calWeekDay(year: Int, month: Int, day: Int): Int {
    var y = year
    var m = month
    if (m == 1 || m == 2) {
        m += 12
        y--
    }
    val iWeek = ( 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7
    return when (iWeek) {
        0 -> 2
        1 -> 3
        2 -> 4
        3 -> 5
        4 -> 6
        5 -> 7
        6 -> 1
        else -> 0
    }
}
