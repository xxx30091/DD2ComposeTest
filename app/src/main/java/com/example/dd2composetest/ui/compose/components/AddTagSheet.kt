package com.example.dd2composetest.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AddTagSheet(
    navController: NavController,
    onClick: (String) -> Unit
) {
    val tag = remember {
        mutableStateOf("")
    }
    var isError by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
            )
            .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 25.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "添加標籤",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                textAlign = TextAlign.Center
            )
            Text(
                text = "完成",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable {
                        if (tag.value.replace(" ", "").isNotEmpty()) {
                            onClick(tag.value)
                            navController.popBackStack()
                        }
                    },
                color = Color(0XFFff9d3e)
            )
        }
        TextField(
            value = tag.value,
            onValueChange = { value ->
                if (tag.value.count() < 4) tag.value = value
                isError = false
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            textStyle = TextStyle(
                fontSize = 20.sp,
                textAlign = TextAlign.Start
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions {
                isError = tag.value.count() > 4
            },
            placeholder = { Text(text = "請輸入標籤") },
            trailingIcon = { Text(text = "${tag.value.count()}/4") }
        )
    }
}