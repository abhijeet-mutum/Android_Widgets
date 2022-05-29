package com.paizuri.widgetapp

import android.app.Application
import com.paizuri.widgetapp.app_widget.preference.PreferenceManager

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        PreferenceManager.getPreferenceManager(applicationContext)
    }
}