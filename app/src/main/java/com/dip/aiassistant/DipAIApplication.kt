package com.dip.aiassistant

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DipAIApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
