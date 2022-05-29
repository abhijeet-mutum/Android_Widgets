package com.paizuri.widgetapp.app_widget.configuration_activity

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import com.paizuri.widgetapp.MainActivity
import com.paizuri.widgetapp.R
import com.paizuri.widgetapp.app_widget.preference.PreferenceManager
import com.paizuri.widgetapp.databinding.ActivityButtonWidgetConfigurationBinding
import com.paizuri.widgetapp.helpers.*

class ActivityButtonWidgetConfiguration : AppCompatActivity() {

    private lateinit var binding: ActivityButtonWidgetConfigurationBinding
    private var appWidgetId: Int = DEFAULT_APP_WIDGET_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButtonWidgetConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        catchIntent()
        setClickListeners()
    }

    private fun catchIntent() {
        intent?.let {
            appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, DEFAULT_APP_WIDGET_ID).apply {
                if (this == DEFAULT_APP_WIDGET_ID) {
                    Intent().apply {
                        putExtra(APP_WIDGET_ID, appWidgetId)
                        setResult(RESULT_CANCELED, this)
                        finish()
                    }
                }
            }
        }
    }

    private fun setClickListeners() {
        binding.bConfirm.setOnClick {
            if (isEditTextEmpty().not()) {
                val buttonPendingIntent =
                    PendingIntent.getActivity(this, REQUEST_CODE_WIDGET_PENDING_INTENT, Intent(this, MainActivity::class.java), 0)
                val remoteViews = RemoteViews(this.packageName, R.layout.layout_button_widget).apply {
                    setOnClickPendingIntent(R.id.b_widgetButton, buttonPendingIntent)
                    setCharSequence(R.id.b_widgetButton, "setText", binding.etButtonWidgetName.text.toString())
                }
                AppWidgetManager.getInstance(this).updateAppWidget(appWidgetId, remoteViews)
                PreferenceManager.putString(appWidgetId.toString().plus(BUTTON_WIDGET_TEXT), binding.etButtonWidgetName.text.toString())
                Intent().apply {
                    putExtra(APP_WIDGET_ID, appWidgetId)
                    setResult(RESULT_OK, this)
                    finish()
                }
            }
        }
    }

    private fun isEditTextEmpty() = binding.etButtonWidgetName.isEmpty()
}