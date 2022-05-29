package com.paizuri.widgetapp.app_widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.paizuri.widgetapp.MainActivity
import com.paizuri.widgetapp.R
import com.paizuri.widgetapp.app_widget.preference.PreferenceManager
import com.paizuri.widgetapp.helpers.BUTTON_WIDGET_TEXT
import com.paizuri.widgetapp.helpers.REQUEST_CODE_WIDGET_PENDING_INTENT

class MyAppWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {

        appWidgetIds?.forEach { appWidgetId ->
            val mainActivityIntent = Intent(context, MainActivity::class.java)
            val mainActivityPendingIntent = PendingIntent.getActivity(context, REQUEST_CODE_WIDGET_PENDING_INTENT, mainActivityIntent, 0)
            val remoteViews = RemoteViews(context?.packageName, R.layout.layout_button_widget).apply {
                setCharSequence(R.id.b_widgetButton, "setText", PreferenceManager.getString(appWidgetId.toString().plus(BUTTON_WIDGET_TEXT)))
                setOnClickPendingIntent(R.id.b_widgetButton, mainActivityPendingIntent)
            }
            appWidgetManager?.updateAppWidget(appWidgetId, remoteViews)
        }
    }
}