package com.lydone.restaurantwaiterapp

import android.speech.tts.TextToSpeech
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.*


class MessagingService : FirebaseMessagingService() {

    private var tts: TextToSpeech? = null


    override fun onMessageReceived(message: RemoteMessage) {
        Log.d("Service", "On message received")
        val table = message.data["table"]!!.toInt()
        val dish = message.data["dish"]
        val text = if (dish != null) {
            getString(R.string.event_dish, dish)
        } else {
            getString(R.string.event_call)
        } + getString(R.string.event_table, table)
        tts = TextToSpeech(this) {
            tts!!.speak(text, TextToSpeech.QUEUE_ADD, null, null)
        }.apply {
            language = Locale("ru")
        }
    }

    override fun onDestroy() {
        Log.d("Service", "onDestroy")
        super.onDestroy()
    }
}