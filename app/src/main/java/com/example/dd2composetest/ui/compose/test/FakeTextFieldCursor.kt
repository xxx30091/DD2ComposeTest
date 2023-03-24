package com.example.dd2composetest.ui.compose.test

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Suppress("ModifierInspectorInfo")
internal fun Modifier.cursor(
    state: TextFieldState,
    value: TextFieldValue,
    offsetMapping: OffsetMapping,
    cursorBrush: Brush,
    enabled: Boolean
) = if (enabled) composed {
    val cursorAlpha = remember { Animatable(1f) }
    val isBrushSpecified = !(cursorBrush is SolidColor && cursorBrush.value.isUnspecified)
    if (state.hasFocus && value.selection.collapsed && isBrushSpecified) {
        LaunchedEffect(cursorBrush, value.annotatedString, value.selection) {
            cursorAlpha.animateTo(0f, cursorAnimationSpec)
        }
        drawWithContent {
            this.drawContent()
            val cursorAlphaValue = cursorAlpha.value.coerceIn(0f, 1f)
            if (cursorAlphaValue != 0f) {
                val transformedOffset = offsetMapping
                    .originalToTransformed(value.selection.start)
                val cursorRect = state.layoutResult?.value?.getCursorRect(transformedOffset)
                    ?: Rect(0f, 0f, 0f, 0f)
                val cursorWidth = DefaultCursorThickness.toPx()
                val cursorX = (cursorRect.left + cursorWidth / 2)
                    .coerceAtMost(size.width - cursorWidth / 2)

                drawLine(
                    cursorBrush,
                    Offset(cursorX, cursorRect.top),
                    Offset(cursorX, cursorRect.bottom),
                    alpha = cursorAlphaValue,
                    strokeWidth = cursorWidth
                )
            }
        }
    } else {
        Modifier
    }
} else this

private val cursorAnimationSpec: AnimationSpec<Float>
    get() = infiniteRepeatable(
        animation = keyframes {
            durationMillis = 1000
            1f at 0
            1f at 499
            0f at 500
            0f at 999
        }
    )

internal val DefaultCursorThickness = 2.dp