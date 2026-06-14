package com.mymeals.app.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Suppress("CheckReturnValue")
public val photo_camera: ImageVector
  get() {
    if (_photo_camera != null) {
      return _photo_camera!!
    }
    _photo_camera =
      ImageVector.Builder(
          name = "photo_camera",
          defaultWidth = 48.dp,
          defaultHeight = 48.dp,
          viewportWidth = 48f,
          viewportHeight = 48f,
        )
        .apply {
          path(
            fill = SolidColor(Color.Black),
            fillAlpha = 1f,
            stroke = null,
            strokeAlpha = 1f,
            strokeLineWidth = 1f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Bevel,
            strokeLineMiter = 1f,
            pathFillType = PathFillType.Companion.NonZero,
          ) {
            moveTo(23.98f, 34.65f)
            quadToRelative(3.63f, 0f, 6.07f, -2.45f)
            reflectiveQuadTo(32.5f, 26.13f)
            reflectiveQuadTo(30.05f, 20.08f)
            reflectiveQuadTo(23.98f, 17.65f)
            reflectiveQuadToRelative(-6.05f, 2.43f)
            reflectiveQuadTo(15.5f, 26.13f)
            reflectiveQuadToRelative(2.43f, 6.08f)
            reflectiveQuadToRelative(6.05f, 2.45f)
            close()
            moveToRelative(0f, -3f)
            quadToRelative(-2.38f, 0f, -3.93f, -1.57f)
            reflectiveQuadTo(18.5f, 26.13f)
            reflectiveQuadTo(20.05f, 22.2f)
            reflectiveQuadToRelative(3.93f, -1.55f)
            reflectiveQuadToRelative(3.95f, 1.55f)
            reflectiveQuadToRelative(1.57f, 3.93f)
            reflectiveQuadToRelative(-1.57f, 3.95f)
            reflectiveQuadToRelative(-3.95f, 1.57f)
            close()
            moveTo(7f, 42f)
            quadTo(5.8f, 42f, 4.9f, 41.1f)
            reflectiveQuadTo(4f, 39f)
            verticalLineTo(13.35f)
            quadTo(4f, 12.2f, 4.9f, 11.27f)
            reflectiveQuadTo(7f, 10.35f)
            horizontalLineToRelative(7.35f)
            lineTo(18f, 6f)
            horizontalLineTo(30f)
            lineToRelative(3.65f, 4.35f)
            horizontalLineTo(41f)
            quadToRelative(1.15f, 0f, 2.08f, 0.92f)
            reflectiveQuadTo(44f, 13.35f)
            verticalLineTo(39f)
            quadToRelative(0f, 1.2f, -0.92f, 2.1f)
            reflectiveQuadTo(41f, 42f)
            horizontalLineTo(7f)
            close()
            moveTo(7f, 39f)
            horizontalLineTo(41f)
            verticalLineTo(13.35f)
            horizontalLineTo(32.25f)
            lineTo(28.6f, 9f)
            horizontalLineTo(19.4f)
            lineToRelative(-3.65f, 4.35f)
            horizontalLineTo(7f)
            verticalLineTo(39f)
            close()
            moveTo(24f, 26.15f)
            close()
          }
        }
        .build()
    return _photo_camera!!
  }

private var _photo_camera: ImageVector? = null
