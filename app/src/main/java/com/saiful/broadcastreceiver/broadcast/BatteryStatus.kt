package com.saiful.broadcastreceiver.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

class BatteryStatus : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("battery", "-------- START --------")
        val status: Int = intent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
        Log.d("battery", "battery Status: $status")
        val scale = intent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
        val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        Log.d("battery", "battery scale: $scale")
        Log.d("battery", "battery level: $level")
        Log.d("battery", "battery percentage: ${level * 100 / scale.toFloat()}")
        Log.d("battery", "----------------")
        val isCharging =
            status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL
        Log.d("battery", "battery is changing: $isCharging")
        Log.d("battery", "battery is BatteryManager.BATTERY_STATUS_FULL: ${BatteryManager.BATTERY_STATUS_FULL}")
        Log.d("battery", "battery BatteryManager.BATTERY_STATUS_CHARGING: ${BatteryManager.BATTERY_STATUS_CHARGING}")
        Log.d("battery", "----------------")

        val chargePlug: Int = intent?.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) ?: -1
        Log.d("battery", "battery charge plug: $chargePlug")
        Log.d("battery", "----------------")

        val usbCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
        Log.d("battery", "battery usb change: $usbCharge")
        Log.d("battery", "----------------")
        val acCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_AC
        Log.d("battery", "battery ac charge: $acCharge")
        Log.d("battery", "----------------")
        val batteryPorperty = BatteryManager.BATTERY_PROPERTY_STATUS
        Log.d("battery", "battery property: $batteryPorperty")
        Log.d("battery", "-------- END --------")
    }
}