package com.bitcode.a17_02_25_notifications_demo

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.PendingIntentCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bitcode.a17_02_25_notifications_demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var notificationManager: NotificationManagerCompat
    private val bitcodeChannelId = "BitcodeNotificationChannel"
    private val SIMPLE_NOTIFICATION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        notificationManager = NotificationManagerCompat.from(this)
        createNotificationChannel()

        activityMainBinding.btnSimpleNotification.setOnClickListener {
            simpleNotification()
        }

        activityMainBinding.btnInboxStyleNotification.setOnClickListener {
            inboxStyleNotification()
        }

        activityMainBinding.btnBigPictureStyleNotification.setOnClickListener {
            bigPictureStyleNotification()
        }
    }

    @SuppressLint("MissingPermission")
    private fun simpleNotification(){
        var notificationCompat = NotificationCompat.Builder(this,bitcodeChannelId)
        notificationCompat.setContentTitle("Android Feb'25 Batch")
        notificationCompat.setContentText("Information about batch timings update")
        notificationCompat.setPriority(NotificationCompat.PRIORITY_LOW)
        notificationCompat.setLights(Color.GREEN,20,10)
        notificationCompat.setSmallIcon(R.drawable.ic_launcher_background)
        notificationCompat.setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        notificationCompat.setVibrate(LongArray(20) { i -> (i + 10).toLong() })

        var intent = Intent(this,DetailsActivity::class.java)

        var pendingIntent = PendingIntent.getActivity(this,
            1,
            intent,
            PendingIntent.FLAG_MUTABLE)

        notificationCompat.setContentIntent(pendingIntent)
        notificationManager.notify(SIMPLE_NOTIFICATION, notificationCompat.build())
    }

    private fun bigPictureStyleNotification(){

    }

    private fun inboxStyleNotification(){

    }

    private fun createNotificationChannel(){
        var notificationChannelCompat = NotificationChannelCompat.Builder(
                bitcodeChannelId,
                NotificationManagerCompat.IMPORTANCE_HIGH)
        notificationChannelCompat.setName("Bitcode Channel")
        notificationChannelCompat.setDescription("This is bitcode notification channel")
        var notificationChannelCompatBuild = notificationChannelCompat.build()

        notificationManager.createNotificationChannel(notificationChannelCompatBuild)
    }
}