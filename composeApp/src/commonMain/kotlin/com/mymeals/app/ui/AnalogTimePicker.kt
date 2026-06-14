package com.mymeals.app.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt

private enum class PickerMode { Hour, Minute }

@Composable
fun AnalogTimePicker(
    hour: Int,
    minute: Int,
    onHourChange: (Int) -> Unit,
    onMinuteChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    is24Hour: Boolean = true,
) {
    var mode by remember { mutableStateOf<PickerMode>(PickerMode.Hour) }
    val textMeasurer = rememberTextMeasurer()

    val primary = MaterialTheme.colorScheme.primary
    val onPrimary = MaterialTheme.colorScheme.onPrimary
    val surfaceVariant = MaterialTheme.colorScheme.surfaceVariant
    val onSurface = MaterialTheme.colorScheme.onSurface
    val outline = MaterialTheme.colorScheme.outlineVariant

    Canvas(
        modifier = modifier
            .pointerInput(mode) {
                detectTapGestures { offset ->
                    val cx = size.width / 2f
                    val cy = size.height / 2f
                    val dx = offset.x - cx
                    val dy = offset.y - cy
                    val dist = sqrt(dx * dx + dy * dy)
                    val radius = size.width / 2f

                    val angle = (atan2(dy, dx) + PI.toFloat() / 2f + 2f * PI.toFloat()) % (2f * PI.toFloat())

                    when (mode) {
                        PickerMode.Hour -> {
                            val innerR = radius * 0.45f
                            val outerR = radius * 0.80f

                            val newHour = when {
                                is24Hour && dist > outerR -> {
                                    val idx = (angle / (2f * PI.toFloat()) * 12f).roundToInt() % 12
                                    if (idx == 0) 0 else idx + 12
                                }
                                dist > innerR -> {
                                    val idx = (angle / (2f * PI.toFloat()) * 12f).roundToInt() % 12
                                    if (idx == 0) 12 else idx
                                }
                                else -> null
                            }

                            if (newHour != null) {
                                onHourChange(newHour)
                                mode = PickerMode.Minute
                            }
                        }

                        PickerMode.Minute -> {
                            val centerR = radius * 0.35f
                            if (dist > centerR) {
                                val idx = (angle / (2f * PI.toFloat()) * 12f).roundToInt() % 12
                                onMinuteChange((idx * 5) % 60)
                            } else {
                                mode = PickerMode.Hour
                            }
                        }
                    }
                }
            }
    ) {
        val cx = size.width / 2f
        val cy = size.height / 2f
        val center = Offset(cx, cy)
        val radius = size.width / 2f

        drawCircle(color = surfaceVariant, radius = radius, center = center)
        drawCircle(
            color = outline,
            radius = radius,
            center = center,
            style = Stroke(width = 1.dp.toPx()),
        )

        val numberStyle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
        )
        val dotRadius = 14.dp.toPx()

        when (mode) {
            PickerMode.Hour -> {
                if (is24Hour) {
                    val innerR = radius * 0.60f
                    val outerR = radius * 0.84f

                    for (i in 1..12) {
                        val angle = (i * PI.toFloat() / 6f) - PI.toFloat() / 2f
                        val pos = Offset(cx + innerR * cos(angle), cy + innerR * sin(angle))
                        val text = i.toString()
                        val isSelected = hour == i
                        val result = textMeasurer.measure(text, numberStyle)

                        if (isSelected) {
                            drawCircle(color = primary, radius = dotRadius, center = pos)
                        }
                        drawText(
                            result,
                            color = if (isSelected) onPrimary else onSurface,
                            topLeft = Offset(
                                pos.x - result.size.width / 2f,
                                pos.y - result.size.height / 2f,
                            ),
                        )
                        if (isSelected) {
                            drawLine(
                                color = primary,
                                start = center,
                                end = pos,
                                strokeWidth = 2.dp.toPx(),
                                cap = StrokeCap.Round,
                            )
                        }
                    }

                    for (i in 0 until 12) {
                        val h = if (i == 0) 0 else i + 12
                        val angle = (i * PI.toFloat() / 6f) - PI.toFloat() / 2f
                        val pos = Offset(cx + outerR * cos(angle), cy + outerR * sin(angle))
                        val text = h.toString().padStart(2, '0')
                        val isSelected = hour == h
                        val result = textMeasurer.measure(text, numberStyle)

                        if (isSelected) {
                            drawCircle(color = primary, radius = dotRadius, center = pos)
                        }
                        drawText(
                            result,
                            color = if (isSelected) onPrimary else onSurface,
                            topLeft = Offset(
                                pos.x - result.size.width / 2f,
                                pos.y - result.size.height / 2f,
                            ),
                        )
                        if (isSelected) {
                            drawLine(
                                color = primary,
                                start = center,
                                end = pos,
                                strokeWidth = 2.dp.toPx(),
                                cap = StrokeCap.Round,
                            )
                        }
                    }
                } else {
                    val r = radius * 0.70f
                    for (i in 1..12) {
                        val angle = (i * PI.toFloat() / 6f) - PI.toFloat() / 2f
                        val pos = Offset(cx + r * cos(angle), cy + r * sin(angle))
                        val text = i.toString()
                        val h = if (i == 12) 0 else i
                        val isSelected = hour == h
                        val result = textMeasurer.measure(text, numberStyle)

                        if (isSelected) {
                            drawCircle(color = primary, radius = dotRadius, center = pos)
                        }
                        drawText(
                            result,
                            color = if (isSelected) onPrimary else onSurface,
                            topLeft = Offset(
                                pos.x - result.size.width / 2f,
                                pos.y - result.size.height / 2f,
                            ),
                        )
                        if (isSelected) {
                            drawLine(
                                color = primary,
                                start = center,
                                end = pos,
                                strokeWidth = 2.dp.toPx(),
                                cap = StrokeCap.Round,
                            )
                        }
                    }
                }
            }

            PickerMode.Minute -> {
                val r = radius * 0.70f
                val selectedIdx = ((minute + 2) / 5) % 12

                for (i in 0 until 12) {
                    val m = (i * 5) % 60
                    val angle = (i * PI.toFloat() / 6f) - PI.toFloat() / 2f
                    val pos = Offset(cx + r * cos(angle), cy + r * sin(angle))
                    val text = m.toString().padStart(2, '0')
                    val isSelected = i == selectedIdx
                    val result = textMeasurer.measure(text, numberStyle)

                    if (isSelected) {
                        drawCircle(color = primary, radius = dotRadius, center = pos)
                    }
                    drawText(
                        result,
                        color = if (isSelected) onPrimary else onSurface,
                        topLeft = Offset(
                            pos.x - result.size.width / 2f,
                            pos.y - result.size.height / 2f,
                        ),
                    )
                    if (isSelected) {
                        drawLine(
                            color = primary,
                            start = center,
                            end = pos,
                            strokeWidth = 2.dp.toPx(),
                            cap = StrokeCap.Round,
                        )
                    }
                }
            }
        }

        drawCircle(color = primary, radius = 4.dp.toPx(), center = center)
    }
}
