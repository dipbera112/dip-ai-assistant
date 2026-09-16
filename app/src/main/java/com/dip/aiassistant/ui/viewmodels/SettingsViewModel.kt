package com.dip.aiassistant.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dip.aiassistant.utils.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    val language: Flow<String> = preferenceManager.language
    val theme: Flow<String> = preferenceManager.theme
    val isBiometricEnabled: Flow<Boolean> = preferenceManager.isBiometricEnabled
    val areNotificationsEnabled: Flow<Boolean> = preferenceManager.areNotificationsEnabled
    val isSoundEnabled: Flow<Boolean> = preferenceManager.isSoundEnabled
    val isVibrationEnabled: Flow<Boolean> = preferenceManager.isVibrationEnabled

    fun setLanguage(language: String) {
        viewModelScope.launch {
            preferenceManager.setLanguage(language)
        }
    }

    fun setTheme(theme: String) {
        viewModelScope.launch {
            preferenceManager.setTheme(theme)
        }
    }

    fun setBiometricEnabled(enabled: Boolean) {
        viewModelScope.launch {
            preferenceManager.setBiometricEnabled(enabled)
        }
    }

    fun setNotificationsEnabled(enabled: Boolean) {
        viewModelScope.launch {
            preferenceManager.setNotificationsEnabled(enabled)
        }
    }

    fun setSoundEnabled(enabled: Boolean) {
        viewModelScope.launch {
            preferenceManager.setSoundEnabled(enabled)
        }
    }

    fun setVibrationEnabled(enabled: Boolean) {
        viewModelScope.launch {
            preferenceManager.setVibrationEnabled(enabled)
        }
    }
}
