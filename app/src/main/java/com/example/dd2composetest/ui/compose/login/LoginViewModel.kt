package com.example.dd2composetest.ui.compose.login

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.dd2composetest.ThisApp
import com.example.dd2composetest.preference.Preference
import com.example.dd2composetest.ui.compose.navigateToMine
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    var account by mutableStateOf<String?>(null)
    var password by mutableStateOf<String?>(null)

    fun onLoginEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.SetAccount -> {
                account = event.accountId
            }
            is LoginEvent.SetPassword -> {
                password = event.password
            }
            is LoginEvent.ClickLogin -> {
                when {
                    account.isNullOrEmpty() -> {
                        Toast.makeText(event.context, "請輸入正確的郵箱地址", Toast.LENGTH_SHORT).show()
                    }
                    password.isNullOrEmpty() -> {
                        Toast.makeText(event.context, "請輸入密碼", Toast.LENGTH_SHORT).show()
                    }
                    account == "1" && password == "111" -> {
                        Preference.getInstance(ThisApp.instance).saveLoginStatus(true)
                        event.navController.navigateToMine()
                    }
                    else -> {
                        Toast.makeText(event.context, "帳戶或密碼不正確", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}

sealed class LoginEvent {

    class SetAccount(val accountId: String) : LoginEvent()

    class SetPassword(val password: String) : LoginEvent()

    class ClickLogin(val context: Context, val navController: NavHostController) : LoginEvent()

}

fun validLoinIfo() {

}
