package com.dip.aiassistant.utils

object Constants {
    const val DATABASE_NAME = "dip_ai_assistant_db"
    
    // API Endpoints
    const val JOKE_API_BASE_URL = "https://official-joke-api.appspot.com/"
    const val WEATHER_API_BASE_URL = "https://api.openweathermap.org/"
    const val NEWS_API_BASE_URL = "https://newsapi.org/"
    const val GEMINI_API_BASE_URL = "https://generativelanguage.googleapis.com/"
    
    // Language Codes
    const val LANGUAGE_ENGLISH = "en"
    const val LANGUAGE_BENGALI = "bn"
    const val LANGUAGE_HINDI = "hi"
    
    // Theme Modes
    const val THEME_DARK = "dark"
    const val THEME_LIGHT = "light"
    const val THEME_AUTO = "auto"
    
    // Priority Levels
    const val PRIORITY_LOW = 0
    const val PRIORITY_MEDIUM = 1
    const val PRIORITY_HIGH = 2
    
    // Chat Limits
    const val MAX_MESSAGE_LENGTH = 5000
    const val CHAT_HISTORY_LIMIT = 100
    
    // Animation Durations
    const val ANIMATION_DURATION_SHORT = 300L
    const val ANIMATION_DURATION_MEDIUM = 500L
    const val ANIMATION_DURATION_LONG = 800L
    
    // Permissions
    const val PERMISSION_CAMERA = "android.permission.CAMERA"
    const val PERMISSION_RECORD_AUDIO = "android.permission.RECORD_AUDIO"
    const val PERMISSION_READ_STORAGE = "android.permission.READ_EXTERNAL_STORAGE"
    const val PERMISSION_WRITE_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE"
    
    // Wake Words
    const val WAKE_WORD_BENGALI = "রাধে রাধে"
    const val WAKE_WORD_ENGLISH = "Hey Dip"
    const val WAKE_WORD_HINDI = "हे दिप"
}
