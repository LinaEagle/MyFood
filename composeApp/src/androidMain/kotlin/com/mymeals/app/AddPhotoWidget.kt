package com.mymeals.app

import android.content.Context
import android.content.Intent
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding

class AddPhotoWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Box(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .clickable(onClick = actionStartActivity(
                        Intent(context, MainActivity::class.java)
                    )),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    provider = ImageProvider(com.mymeals.app.R.drawable.ic_add_plus),
                    contentDescription = "Add photo",
                    modifier = GlanceModifier
                        .padding(12.dp)
                        .fillMaxSize()
                )
            }
        }
    }
}

class AddPhotoWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = AddPhotoWidget()
}
