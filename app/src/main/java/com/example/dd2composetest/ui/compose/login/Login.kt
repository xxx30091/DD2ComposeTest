package com.example.dd2composetest.ui.compose.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.dd2composetest.R
import com.example.dd2composetest.enum.Screen


@ExperimentalMaterialApi
@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                text = "反饋問題",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 16.dp)
                    .clickable { },
                color = Color.Black
            )
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.logo_dark),
                    contentDescription = "",
                    modifier = Modifier.size(128.dp)
                )
                Text(
                    text = "DD2-Compose",
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SlashTextField(
                value = viewModel.account.orEmpty(),
                onValueChange = { value ->
                    viewModel.onLoginEvent(
                        LoginEvent.SetAccount(value)
                    )
                },
                placeholder = { Text(text = "郵箱") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.mipmap.email),
                        contentDescription = "",
                        tint = Color.Black
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next // 點確定後自動跳到下一個輸入框
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            SlashTextField(
                value = viewModel.password.orEmpty(),
                onValueChange = { value ->
                    viewModel.onLoginEvent(
                        LoginEvent.SetPassword(value)
                    )
                },
                placeholder = { Text(text = "密碼") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.mipmap.key),
                        contentDescription = "",
                        tint = Color.Black
                    )
                },
                visualTransformation = PasswordVisualTransformation(), // 設定輸入的字會被隱藏
                keyboardOptions = KeyboardOptions.Default.copy(
//                    capitalization = KeyboardCapitalization.Characters, // 自動大寫
//                    autoCorrect = true, // 是否啟用自動更正
//                    keyboardType = KeyboardType.Number  // 設定成鍵盤型式
                    imeAction = ImeAction.Done // 在鍵盤上顯示什麼類型的操作按鈕
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                )
            )
            Button(
                onClick = {
                    viewModel.onLoginEvent(
                        LoginEvent.ClickLogin(context, navController)
                    )
                },
                modifier = Modifier
                    .padding(top = 80.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(Color.Black)
            ) {
                Text(
                    text = "登入", modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                    color = Color.White, fontSize = 18.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 48.dp)
            ) {
                Text(
                    text = "尚未擁有帳戶？",
                    modifier = Modifier.padding(6.dp),
                    color = Color(0XFF6D7278),
                    fontSize = 14.sp
                )
                Text(
                    text = "註冊",
                    modifier = Modifier
                        .padding(6.dp)
                        .clickable { },
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "忘記密碼",
                    modifier = Modifier
                        .padding(6.dp)
                        .clickable { },
                    color = Color(0XFF6D7278),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SlashTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val textColor = textStyle.color.takeOrElse {
        colors.textColor(enabled).value
    }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))
//    val focusRequester = remember {
//        FocusRequester()
//    }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .indicatorLine(
                enabled = true,
                isError = isError,
                interactionSource = interactionSource,
                colors = colors
            )
            .fillMaxWidth(),
        enabled = enabled,
        readOnly = readOnly,
        textStyle = mergedTextStyle,
        cursorBrush = SolidColor(colors.cursorColor(isError = isError).value),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        singleLine = true,
        maxLines = 1,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = enabled,
                singleLine = true,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                isError = isError,
                label = label,
                placeholder = placeholder,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                colors = colors,
                contentPadding = PaddingValues(0.dp)
            )
        }
    )
}

//@ExperimentalMaterialApi
//@Preview
//@Composable
//fun PreviewSlashTextField() {
//    SlashTextField(
//        value = "123",
//        onValueChange = {
//
//        }
//    )
//}

@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(NavHostController(LocalContext.current))
}

@ExperimentalMaterialApi
fun NavGraphBuilder.login(navController: NavHostController) {
    composable(Screen.LOGIN_SCREEN.route) {
        LoginScreen(navController)
    }
}
