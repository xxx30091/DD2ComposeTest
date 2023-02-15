package com.example.dd2composetest.ui.compose.mine

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.dd2composetest.utils.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class MyCoinViewModel @Inject constructor() : ViewModel() {

    var startDate by mutableStateOf("")
    var endDate by mutableStateOf("")



    init {
        initDate()
    }

    fun onEvent(event: MyCoinEvent) {
        when (event) {
            is MyCoinEvent.SelectDate -> {
                startDate = event.selectedDate.first
                endDate = event.selectedDate.second
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

}