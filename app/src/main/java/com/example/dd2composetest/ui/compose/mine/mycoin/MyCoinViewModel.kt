package com.example.dd2composetest.ui.compose.mine.mycoin

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.dd2composetest.utils.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MyCoinViewModel @Inject constructor() : ViewModel() {

    var startDate by mutableStateOf("")
    var endDate by mutableStateOf("")

    var goldFilterType by mutableStateOf(0) // 0-全部 1-金幣充值 2-金幣消費
    var redFilterType by mutableStateOf(0) // 0-全部 1-金幣充值 2-金幣消費

    init {
        initDate()
        Log.i("Arthur_test", "init MyCoinViewModel ${this.hashCode()}")
    }

    fun onEvent(event: MyCoinEvent) {
        when (event) {
            is MyCoinEvent.SelectDate -> {
                startDate = event.selectedDate.first
                endDate = event.selectedDate.second
            }
            is MyCoinEvent.SetGoldFilter -> {
                goldFilterType = event.type
            }
            is MyCoinEvent.SetRedFilter -> {
                redFilterType = event.type
            }
        }
    }

    fun initDate() {
        val c = Calendar.getInstance()
        endDate = DateUtils.getDateString(c.timeInMillis)
        c.add(Calendar.MONTH, -3)
        startDate = DateUtils.getDateString(c.timeInMillis)
    }
}

sealed class MyCoinEvent() {

    class SelectDate(val selectedDate: Pair<String, String>) : MyCoinEvent()

    class SetGoldFilter(val type: Int) : MyCoinEvent()

    class SetRedFilter(val type: Int) : MyCoinEvent()
}