package com.example.dd2composetest

import com.example.dd2composetest.ui.compose.components.datepicker.material3.CalendarModel
import com.example.dd2composetest.ui.compose.components.datepicker.material3.ExperimentalMaterial3Api
import java.util.Calendar

var testNum = 0
    private set
    get() = field + 2

@OptIn(ExperimentalMaterial3Api::class)
fun main() {

//    init()
//    println("獎項共有：$giftList")
//
//    var thisTime: String
//    for (i in 0 until giftList.size) {
//        thisTime = giftList[Random.nextInt(giftList.size)]
//        playerList.add(thisTime)
//        giftList.remove(thisTime)
//        println("這次抽到:$thisTime")
//        println("已被抽走:$playerList")
//        println("獎項還剩：" + if (giftList.size == 0) "已抽完" else "$giftList")
//    }

//    val date = CalendarModel().getCanonicalDate(1675414126957)
//    val today = Calendar.getInstance().timeInMillis
//    val todayFormat = CalendarModel().getCanonicalDate(today)
//    println("時間：$date")
//    println("今天：$todayFormat")

    println("setNum：$testNum")
    setNum(0)
    println("setNum：$testNum")

}

fun setNum(num: Int) {
    testNum = num - 1
    println("setNum：$testNum")
}

// 抽抽樂總共有五個獎項, 1,2,3 獎各只有一個, 4 獎有 3 個，5 獎有 4 個，請寫出一個程式可以「隨機」的取得「不重複」的禮物
val giftList: ArrayList<String> = arrayListOf()
val playerList: ArrayList<String> = arrayListOf()

fun init() {
    addGift(1)
    addGift(2)
    addGift(3)
    addGift(4)
    addGift(5)
}

fun addGift(gift: Int) {
    if (gift <= 3) giftList.add(gift.toString())
    else for (i in 0 until gift - 1) { giftList.add(gift.toString()) }
}
