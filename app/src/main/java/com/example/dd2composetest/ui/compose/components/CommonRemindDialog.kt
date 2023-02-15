package com.example.dd2composetest.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.dd2composetest.R

@Composable
fun CommonRemindDialog(
    setShowDialog: (Boolean) -> Unit,
    title: String,
    content: String,
    action: () -> Unit
) {
    Dialog(
        onDismissRequest = { setShowDialog(false) },
    ) {
        Column(
             modifier = Modifier
                 .background(
                     color = Color.White,
                     shape = RoundedCornerShape(4.dp)
                 )
                 .padding(start = 24.dp, top = 20.dp, end = 8.dp, bottom = 8.dp)
        ) {
            Text(
                text = title,
                color = Color.Black,
                fontSize = 20.sp
            )
            Text(
                text = content,
                modifier = Modifier
                    .padding(top = 12.dp, end = 24.dp),
                color = Color(R.color.color_default_text_view),
                fontSize = 14.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "取消",
                    modifier = Modifier
                        .padding(start = 20.dp, top = 12.dp, end = 20.dp, bottom = 12.dp)
                        .clickable { setShowDialog(false) }
                        .padding(end = 8.dp),
                    fontSize = 14.sp
                )
                Text(
                    text = "確認",
                    modifier = Modifier
                        .padding(start = 20.dp, top = 12.dp, end = 20.dp, bottom = 12.dp)
                        .clickable {
                            action.invoke()
                            setShowDialog(false)
                        },
                    fontSize = 14.sp
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewCommonAlertDialog() {
//    CommonRemindDialog(setShowDialog = {}, title = "提醒", content = "內容", action = {})
//}