package com.example.dd2composetest.ui.fagment.promote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PromoteViewModel : ViewModel() {

    val tabPosition = MutableLiveData<Int>()

    init {
        tabPosition.postValue(0)
    }

    fun setTabPosition(position: Int) {
        tabPosition.postValue(position)
    }

}