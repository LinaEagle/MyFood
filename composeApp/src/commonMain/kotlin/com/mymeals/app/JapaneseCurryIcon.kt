package com.mymeals.app

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Suppress("CheckReturnValue")
val JapaneseCurryIcon: ImageVector
    get() {
        if (_japaneseCurryIcon != null) {
            return _japaneseCurryIcon!!
        }
        _japaneseCurryIcon =
            ImageVector.Builder(
                name = "japanese_curry",
                defaultWidth = 48.dp,
                defaultHeight = 48.dp,
                viewportWidth = 48f,
                viewportHeight = 48f,
            ).apply {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    stroke = null,
                    strokeAlpha = 1f,
                    strokeLineWidth = 1f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Bevel,
                    strokeLineMiter = 1f,
                    pathFillType = PathFillType.NonZero,
                ) {
                    moveTo(15.45f, 44.28f)
                    quadToRelative(-4f, -1.73f, -7f, -4.73f)
                    reflectiveQuadToRelative(-4.73f, -7f)
                    reflectiveQuadTo(2f, 24f)
                    reflectiveQuadTo(3.73f, 15.45f)
                    reflectiveQuadToRelative(4.73f, -7f)
                    reflectiveQuadToRelative(7f, -4.73f)
                    reflectiveQuadTo(24f, 2f)
                    reflectiveQuadToRelative(8.55f, 1.72f)
                    reflectiveQuadToRelative(7f, 4.73f)
                    reflectiveQuadToRelative(4.73f, 7f)
                    reflectiveQuadTo(46f, 24f)
                    reflectiveQuadToRelative(-1.72f, 8.55f)
                    reflectiveQuadToRelative(-4.73f, 7f)
                    reflectiveQuadToRelative(-7f, 4.73f)
                    reflectiveQuadTo(24f, 46f)
                    reflectiveQuadTo(15.45f, 44.28f)
                    close()
                    moveTo(24f, 37.8f)
                    quadToRelative(5.75f, 0f, 9.77f, -4.02f)
                    reflectiveQuadTo(37.8f, 24f)
                    reflectiveQuadTo(33.78f, 14.23f)
                    reflectiveQuadTo(24f, 10.2f)
                    quadToRelative(-1.4f, 0f, -2.6f, 0.7f)
                    reflectiveQuadToRelative(-2.05f, 1.8f)
                    quadToRelative(-1.4f, 0f, -2.75f, 0.28f)
                    reflectiveQuadToRelative(-2.35f, 1.27f)
                    reflectiveQuadToRelative(-1.38f, 2.32f)
                    reflectiveQuadTo(12.75f, 19.3f)
                    quadTo(11.6f, 20.15f, 10.9f, 21.35f)
                    reflectiveQuadTo(10.2f, 24f)
                    quadToRelative(0f, 1.4f, 0.7f, 2.6f)
                    reflectiveQuadToRelative(1.85f, 2.05f)
                    quadToRelative(-0.25f, 1.4f, 0.13f, 2.75f)
                    reflectiveQuadToRelative(1.38f, 2.35f)
                    reflectiveQuadToRelative(2.35f, 1.38f)
                    reflectiveQuadToRelative(2.75f, 0.13f)
                    quadTo(20.2f, 36.4f, 21.4f, 37.1f)
                    reflectiveQuadTo(24f, 37.8f)
                    close()
                    moveToRelative(0f, -3f)
                    quadToRelative(-1.15f, 0f, -1.9f, -0.92f)
                    reflectiveQuadTo(20.65f, 32f)
                    quadToRelative(-1.15f, 0.15f, -2.3f, 0.3f)
                    reflectiveQuadToRelative(-2f, -0.65f)
                    quadToRelative(-0.8f, -0.85f, -0.7f, -2f)
                    reflectiveQuadToRelative(0.3f, -2.3f)
                    quadTo(15f, 26.65f, 14.1f, 25.9f)
                    reflectiveQuadTo(13.2f, 24f)
                    reflectiveQuadToRelative(0.92f, -1.9f)
                    reflectiveQuadToRelative(1.83f, -1.5f)
                    quadTo(15.8f, 19.45f, 15.68f, 18.3f)
                    reflectiveQuadToRelative(0.68f, -1.95f)
                    quadTo(17.2f, 15.5f, 18.38f, 15.63f)
                    reflectiveQuadTo(20.7f, 15.9f)
                    quadToRelative(0.7f, -0.95f, 1.42f, -1.83f)
                    reflectiveQuadTo(24f, 13.2f)
                    verticalLineTo(34.8f)
                    close()
                    moveToRelative(6.15f, -1.85f)
                    lineTo(26.5f, 29.3f)
                    lineToRelative(3.65f, -3.65f)
                    lineTo(33.8f, 29.3f)
                    lineToRelative(-3.65f, 3.65f)
                    close()
                    moveTo(31.7f, 22.8f)
                    lineTo(26.55f, 17.7f)
                    quadToRelative(1.1f, -1.1f, 2.6f, -1.1f)
                    reflectiveQuadToRelative(2.55f, 1.1f)
                    quadToRelative(1.05f, 1.05f, 1.05f, 2.55f)
                    reflectiveQuadTo(31.7f, 22.8f)
                    close()
                }
            }.build()
        return _japaneseCurryIcon!!
    }

private var _japaneseCurryIcon: ImageVector? = null
