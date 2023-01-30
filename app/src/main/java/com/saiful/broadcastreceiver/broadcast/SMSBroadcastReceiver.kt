package com.saiful.broadcastreceiver.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log
import android.widget.Toast


class SMSBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("TAG", "sms receive: ----------");
        if (!intent.action.equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) return
        val extractMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
        extractMessages.forEach {
            Toast.makeText(context, it.displayMessageBody, Toast.LENGTH_SHORT).show()
            intent.putExtra("sms", it.displayMessageBody)
            Log.d("TAG", "tag: ${it.displayMessageBody}");
        }
    }
}