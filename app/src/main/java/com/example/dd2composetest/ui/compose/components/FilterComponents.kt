package com.example.dd2composetest.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilterCell(
    title: String,
    isChecked: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = title,
        modifier = Modifier
            .padding(start = 16.dp)
            .clip(RoundedCornerShape(18.dp))
            .clickable {
                onClick.invoke()
            }
            .background(color = if (isChecked) Color(0XFFd8d8d8) else Color.White)
            .padding(start = 12.dp, top = 4.dp, end = 12.dp, bottom = 4.dp),
        color = if (isChecked) Color(0XFFff932b) else Color.Black,
        fontSize = 14.sp
    )
}

@Preview
@Composable
fun PreviewFilterCell() {
    FilterCell(title = "必看主題", isChecked = true) {}
}