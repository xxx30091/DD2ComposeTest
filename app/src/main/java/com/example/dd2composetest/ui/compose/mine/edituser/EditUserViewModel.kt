package com.example.dd2composetest.ui.compose.mine.edituser

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dd2composetest.data.bean.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditUserViewModel @Inject constructor() : ViewModel() {

    var userData by mutableStateOf(UserData())

    val value = MutableLiveData<Int>()

    init {
        getUserData()
        Log.i("Arthur_test", "EditUserViewModel init ${this.hashCode()}")
    }

    fun getUserData() {
        userData = UserData(
            imageUrl = "https://i1.jueshifan.com/2f5221d51dedbc4f9f/205a28/7c077a81/204328d017a8fa4d8c26.png",
            nickName = "叫我大哥",
            gender = 1,
            sexOrientation = 3,
            email = "17@ax2tw.net",
            tags = arrayListOf("可愛", "帥氣", "運動", "陽光", "結實")
        )
    }

    fun onEditUserEvent(event: EditUserEvent) {
        when (event) {
            is EditUserEvent.ChangeUserImg -> {

            }
            is EditUserEvent.ChangeEmail -> {
                userData = userData.copy(email = event.email)
            }
            is EditUserEvent.ChangeNickname -> {
                userData = userData.copy(nickName = event.nickname)
            }
            is EditUserEvent.ChangeSexOrientation -> {
                userData = userData.copy(sexOrientation = event.orientation)
                Log.i("Arthur_test", "${userData.sexOrientation}")
            }
            is EditUserEvent.AddUserTag -> {
//                userData = userData.copy(tags = userData.tags.plus(event.tag) as ArrayList<String>)
                val arr = ArrayList(userData.tags)
                arr.add(event.tag)
                userData = userData.copy(tags = arr)

                Log.i("Arthur_test", "${userData.tags}")
            }
            is EditUserEvent.RemoveUserTag -> {
                if (event.position in userData.tags.indices) {
                    val arr = ArrayList(userData.tags)
                    arr.removeAt(event.position)
                    userData = userData.copy(tags = arr)
                }
                Log.i("Arthur_test", "${userData.tags}")
            }
            is EditUserEvent.SaveAllChange -> {
                // Save data
            }
        }
    }
}

sealed class EditUserEvent {

    class ChangeUserImg() : EditUserEvent()

    class ChangeEmail(val email: String) : EditUserEvent()

    class ChangeNickname(val nickname: String) : EditUserEvent()

    class ChangeSexOrientation(val orientation: Int) : EditUserEvent()

    class AddUserTag(val tag: String) : EditUserEvent()

    class RemoveUserTag(val position: Int) : EditUserEvent()

    class SaveAllChange(val userData: UserData) : EditUserEvent()

}

