package com.saiful.broadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.saiful.broadcastreceiver.broadcast.AirplaneModeChangeReceiver

class MainActivity : AppCompatActivity() {
    lateinit var airplaneModeChangeReceiver: AirplaneModeChangeReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        airplaneModeChangeReceiver = AirplaneModeChangeReceiver()

        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            Log.d("TAG", "onCreate: called main activity //")
            registerReceiver(airplaneModeChangeReceiver, it)
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(airplaneModeChangeReceiver)
    }
}