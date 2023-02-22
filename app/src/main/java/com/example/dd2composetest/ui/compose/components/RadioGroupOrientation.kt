package com.example.dd2composetest.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dd2composetest.R

/**
 * date: 2023.02.20
 */

@Composable
fun OrientationRadioGroup(
    orientations: ArrayList<Int>,
    enabled: Boolean,
    setOrientation: (ArrayList<Int>) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "取向", color = Color(R.color.color_default_text_view), fontSize = 16.sp)
        HorizontalRadioBtnItem(
            title = "男女",
            checked = orientations.contains(0),
            onClick = {
                if (orientations.contains(0)) orientations.remove(0)
                else orientations.add(0)
                setOrientation(orientations)
            },
            enabled = enabled
        )
        HorizontalRadioBtnItem(
            title = "男男",
            checked = orientations.contains(1),
            onClick = {
                if (orientations.contains(1)) orientations.remove(1)
                else orientations.add(1)
                setOrientation(orientations)
            },
            enabled = enabled
        )
        HorizontalRadioBtnItem(
            title = "女女",
            checked = orientations.contains(2),
            onClick = {
                if (orientations.contains(2)) orientations.remove(2)
                else orientations.add(2)
                setOrientation(orientations)
            },
            enabled = enabled
        )
    }
}

@Composable
fun HorizontalRadioBtnItem(title: String, checked: Boolean, onClick: () -> Unit, enabled: Boolean) {
    Row (
        modifier = Modifier
            .padding(start = 16.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = checked,
            onClick = { onClick() },
            enabled = enabled,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color(0XFF898989),
                unselectedColor = Color(0XFF898989),
                disabledColor = Color.LightGray
            )
        )
        Text(
            text = title,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun PreviewOrientationRadioGroup() {
    OrientationRadioGroup(enabled = true, orientations = arrayListOf(0,2), setOrientation = {})
}