package com.example.dd2composetest.ui.compose.components.datepicker.material3

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
//import androidx.compose.material3.tokens.DividerTokens
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.dd2composetest.ui.compose.components.datepicker.material3.token.DividerTokens

/**
 * <a href="https://m3.material.io/components/divider/overview" class="external" target="_blank">Material Design divider</a>.
 *
 * A divider is a thin line that groups content in lists and layouts.
 *
 * ![Divider image](https://developer.android.com/images/reference/androidx/compose/material3/divider.png)
 *
 * @param modifier the [Modifier] to be applied to this divider line.
 * @param thickness thickness of this divider line. Using [Dp.Hairline] will produce a single pixel
 * divider regardless of screen density.
 * @param color color of this divider line.
 */
@Composable
fun Divider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
    color: Color = DividerDefaults.color,
) {
    val targetThickness = if (thickness == Dp.Hairline) {
        (1f / LocalDensity.current.density).dp
    } else {
        thickness
    }
    Box(
        modifier
            .fillMaxWidth()
            .height(targetThickness)
            .background(color = color)
    )
}

/** Default values for [Divider] */
object DividerDefaults {
    /** Default thickness of a divider. */
    val Thickness: Dp = DividerTokens.Thickness

    /** Default color of a divider. */
    val color: Color @Composable get() = DividerTokens.Color.toColor()
}