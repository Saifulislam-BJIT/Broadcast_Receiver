package com.saiful.broadcastreceiver

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.saiful.broadcastreceiver.broadcast.AirplaneModeChangeReceiver
import com.saiful.broadcastreceiver.broadcast.BatteryStatus
import com.saiful.broadcastreceiver.broadcast.Internet

private const val MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 1

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

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECEIVE_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECEIVE_SMS),
                MY_PERMISSIONS_REQUEST_RECEIVE_SMS
            )
        }


    }
    override fun onStop() {
        super.onStop()
        unregisterReceiver(airplaneModeChangeReceiver)
        unregisterReceiver(batteryStatus)
        unregisterReceiver(internet)
    }
}