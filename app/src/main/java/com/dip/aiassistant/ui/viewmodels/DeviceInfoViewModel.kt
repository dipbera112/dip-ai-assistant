package com.dip.aiassistant.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dip.aiassistant.utils.BatteryUtil
import com.dip.aiassistant.utils.DeviceInfoUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext

@HiltViewModel
class DeviceInfoViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _batteryLevel = MutableStateFlow(0)
    val batteryLevel = _batteryLevel.asStateFlow()

    private val _batteryStatus = MutableStateFlow("Unknown")
    val batteryStatus = _batteryStatus.asStateFlow()

    private val _batteryHealth = MutableStateFlow("Unknown")
    val batteryHealth = _batteryHealth.asStateFlow()

    private val _batteryTemp = MutableStateFlow(0)
    val batteryTemp = _batteryTemp.asStateFlow()

    private val _deviceInfo = MutableStateFlow<Map<String, String>>(emptyMap())
    val deviceInfo = _deviceInfo.asStateFlow()

    init {
        updateBatteryInfo()
        updateDeviceInfo()
    }

    private fun updateBatteryInfo() {
        _batteryLevel.value = BatteryUtil.getBatteryPercentage(context)
        _batteryStatus.value = BatteryUtil.getBatteryStatus(context)
        _batteryHealth.value = BatteryUtil.getBatteryHealth(context)
        _batteryTemp.value = BatteryUtil.getBatteryTemperature(context)
    }

    private fun updateDeviceInfo() {
        val info = mapOf(
            "Device Name" to DeviceInfoUtil.getDeviceName(),
            "Manufacturer" to DeviceInfoUtil.getManufacturer(),
            "Model" to DeviceInfoUtil.getModel(),
            "Android Version" to DeviceInfoUtil.getAndroidVersion(),
            "SDK Version" to DeviceInfoUtil.getSDKVersion().toString(),
            "Processor Count" to DeviceInfoUtil.getProcessorCount().toString(),
            "Total Memory" to formatBytes(DeviceInfoUtil.getTotalMemory()),
            "Available Memory" to formatBytes(DeviceInfoUtil.getAvailableMemory()),
            "Screen Resolution" to DeviceInfoUtil.getScreenResolution(context),
            "Screen Density" to "${DeviceInfoUtil.getScreenDensity(context)}x",
            "Device ID" to DeviceInfoUtil.getDeviceId(context)
        )
        _deviceInfo.value = info
    }

    private fun formatBytes(bytes: Long): String {
        return when {
            bytes <= 0 -> "0 B"
            bytes < 1024 -> "$bytes B"
            bytes < 1024 * 1024 -> "${bytes / 1024} KB"
            else -> "${bytes / (1024 * 1024)} MB"
        }
    }

    fun refreshBatteryInfo() {
        updateBatteryInfo()
    }

    fun refreshDeviceInfo() {
        updateDeviceInfo()
    }
}
