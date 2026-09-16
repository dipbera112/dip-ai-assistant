package com.dip.aiassistant.utils

import android.content.Context
import android.os.Build
import android.os.BatteryManager
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import android.view.WindowManager

object DeviceInfoUtil {

    fun getDeviceName(): String = Build.DEVICE ?: "Unknown"

    fun getManufacturer(): String = Build.MANUFACTURER ?: "Unknown"

    fun getModel(): String = Build.MODEL ?: "Unknown"

    fun getAndroidVersion(): String = Build.VERSION.RELEASE ?: "Unknown"

    fun getSDKVersion(): Int = Build.VERSION.SDK_INT

    fun getProcessorCount(): Int = Runtime.getRuntime().availableProcessors()

    fun getTotalMemory(): Long {
        val runtime = Runtime.getRuntime()
        return runtime.totalMemory()
    }

    fun getAvailableMemory(): Long {
        val runtime = Runtime.getRuntime()
        return runtime.maxMemory() - (runtime.totalMemory() - runtime.freeMemory())
    }

    fun getScreenResolution(context: Context): String {
        val displayMetrics = DisplayMetrics()
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        @Suppress("DEPRECATION")
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return "${displayMetrics.widthPixels} x ${displayMetrics.heightPixels}"
    }

    fun getScreenDensity(context: Context): Float {
        return context.resources.displayMetrics.density
    }

    fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun getPhoneNumber(context: Context): String {
        return try {
            val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            telephonyManager.line1Number ?: "Not Available"
        } catch (e: Exception) {
            "Not Available"
        }
    }

    fun getSimOperatorName(context: Context): String {
        return try {
            val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            telephonyManager.simOperatorName ?: "Not Available"
        } catch (e: Exception) {
            "Not Available"
        }
    }
}
