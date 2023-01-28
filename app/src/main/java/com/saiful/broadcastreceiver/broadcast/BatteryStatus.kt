package com.saiful.broadcastreceiver.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.util.Log

class BatteryStatus : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val status: Int = intent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
        Log.d("TAG", "onReceive: battery Status: $status")
        val scale = intent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
        val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        Log.d("TAG", "onReceive: battery scale: $scale")
        Log.d("TAG", "onReceive: battery level: $level")
        Log.d("TAG", "onReceive: battery percentage: ${level * 100 / scale.toFloat()}")
        Log.d("TAG", "onReceive: ----------------")
        val isCharging =
            status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL
        Log.d("TAG", "onReceive: battery is changing: $isCharging")
        Log.d("TAG", "onReceive: battery is BatteryManager.BATTERY_STATUS_FULL: ${BatteryManager.BATTERY_STATUS_FULL}")
        Log.d("TAG", "onReceive: battery BatteryManager.BATTERY_STATUS_CHARGING: ${BatteryManager.BATTERY_STATUS_CHARGING}")
        Log.d("TAG", "onReceive: ----------------")

        val chargePlug: Int = intent?.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) ?: -1
        Log.d("TAG", "onReceive: battery charge plug: $chargePlug")
        Log.d("TAG", "onReceive: ----------------")

        val usbCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
        Log.d("TAG", "onReceive: battery usb change: $usbCharge")
        Log.d("TAG", "onReceive: ----------------")
        val acCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_AC
        Log.d("TAG", "onReceive: battery ac charge: $acCharge")
        Log.d("TAG", "onReceive: ----------------")
        val batteryPorperty = BatteryManager.BATTERY_PROPERTY_STATUS
        Log.d("TAG", "onReceive: battery property: $batteryPorperty")

    }
}