package com.example.dd2composetest.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dd2composetest.R
import com.example.dd2composetest.ui.compose.colorGoldList

const val clock = "clock"
const val downArrow = "downArrow"
const val filter = "filter"

val colorShadowList = arrayOf(
    0.0f to Color(0xffC0C0C0),
    0.3f to Color(0xffD3D3D3),
    0.5f to Color(0xffDCDCDC),
    0.7f to Color(0xffF5F5F5),
    1f to Color(0xffffffff),
//    1f to Color(0xffffffff)
)

val inlineContent = mapOf(
    Pair(
        clock,
        InlineTextContent(
            Placeholder(
                width = 24.sp,
                height = 24.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            )
        ) {
            Icon(painterResource(id = R.mipmap.time_black), "")
        }
    ),
    Pair(
        downArrow,
        InlineTextContent(
            Placeholder(
                width = 24.sp,
                height = 24.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            )
        ) {
            Icon(painterResource(id = R.mipmap.arrow_down_black), "")
        }
    ),
    Pair(
        filter,
        InlineTextContent(
            Placeholder(
                width = 24.sp,
                height = 24.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            )
        ) {
            Icon(painterResource(id = R.mipmap.filter_white), "")
        }
    )
)

// placeType: 0 -> coin, 1 -> video
@Preview
@Composable
fun DateTimeFilter(startDate: String = "2022-10-06", endDate: String = "2022-01-06", placeType: Int = 1, videoCount: Int? = 30) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
    ) {
        Row(
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                    appendInlineContent(clock)
                    append("$startDate 至 $endDate")
                    appendInlineContent(downArrow)
                },
                modifier = Modifier.clickable {  },
                fontSize = 14.sp,
                inlineContent = inlineContent,
                textAlign = TextAlign.Center
            )

            if (placeType == 0) {
                Text(
                    text = buildAnnotatedString {
                        appendInlineContent(filter)
                        append("全部")
                        appendInlineContent(downArrow)
                    },
                    modifier = Modifier.clickable {  },
                    fontSize = 14.sp,
                    inlineContent = inlineContent,
                    textAlign = TextAlign.Center
                )
            } else {
                Text(
                    text = "視頻: $videoCount",
                    modifier = Modifier.clickable {  },
                )
            }
        }
        Divider(
            modifier = Modifier
                .height(3.dp)
                .background(brush = Brush.verticalGradient(colorStops = colorShadowList))
        )
    }
}