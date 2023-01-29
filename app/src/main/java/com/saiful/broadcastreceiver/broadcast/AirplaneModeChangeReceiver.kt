package com.saiful.broadcastreceiver.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AirplaneModeChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
//        val airplaneModeEnable = intent?.getBooleanExtra("state", false) ?: return
        val airplaneModeEnable = intent?.getBooleanExtra("state", false) ?: return
        if (airplaneModeEnable) {
            Log.d("airplane_mode", "airplaneMode enable")

        } else {
            Log.d("airplane_mode", "airplaneMode disable")
        }
    }
}