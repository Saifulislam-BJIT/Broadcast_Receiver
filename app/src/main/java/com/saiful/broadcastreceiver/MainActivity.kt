package com.saiful.broadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.saiful.broadcastreceiver.broadcast.AirplaneModeChangeReceiver
import com.saiful.broadcastreceiver.broadcast.BatteryStatus
import com.saiful.broadcastreceiver.broadcast.Internet

class MainActivity : AppCompatActivity() {
    lateinit var airplaneModeChangeReceiver: AirplaneModeChangeReceiver
    lateinit var batteryStatus: BatteryStatus
    lateinit var internet: Internet
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        airplaneModeChangeReceiver = AirplaneModeChangeReceiver()

        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(airplaneModeChangeReceiver, it)
        }

        batteryStatus = BatteryStatus()
        IntentFilter(Intent.ACTION_BATTERY_CHANGED).also {
            registerReceiver(batteryStatus, it)
        }

        internet = Internet()
        val filter = IntentFilter()
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(internet, filter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(airplaneModeChangeReceiver)
        unregisterReceiver(batteryStatus)
        unregisterReceiver(internet)
    }
}