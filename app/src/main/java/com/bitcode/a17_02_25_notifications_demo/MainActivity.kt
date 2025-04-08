package com.bitcode.a17_02_25_notifications_demo

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.BigPictureStyle
import androidx.core.app.NotificationCompat.InboxStyle
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
    private val BIG_PICTURE_NOTIFICATION = 2
    private val ACTION_TEXT_STYLE_NOTIFICATION = 3
    private val INBOX_STYLE_NOTIFICATION = 4
    private val SNACK_BAR_NOTIFICATION = 5

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

    @SuppressLint("MissingPermission", "NewApi")
    private fun bigPictureStyleNotification(){
        var notificationCompat = NotificationCompat.Builder(this,bitcodeChannelId)

        var bigPictureStyle = BigPictureStyle(notificationCompat)

        var bitmapImage = BitmapFactory.decodeResource(resources,R.drawable.test_image_3)

        notificationCompat.setSmallIcon(R.drawable.ic_launcher_background)

        bigPictureStyle.bigLargeIcon(bitmapImage)
        bigPictureStyle.bigPicture(bitmapImage)
        bigPictureStyle.setBigContentTitle("Upcoming Batch Schedule")
        bigPictureStyle.setSummaryText("Android, iOS, Web batches will be starting in April 2025 2nd Week")
        bigPictureStyle.setContentDescription("Android, iOS, Web batches will be starting in April 2025 2nd Week")
        bigPictureStyle.build()

        notificationManager.notify(BIG_PICTURE_NOTIFICATION,notificationCompat.build())
    }

    @SuppressLint("MissingPermission")
    private fun inboxStyleNotification(){

        var notificationCompat = NotificationCompat.Builder(this,bitcodeChannelId)
        notificationCompat.setSmallIcon(R.drawable.test_image_3)
        var inboxStyle = InboxStyle(notificationCompat)

        inboxStyle.setSummaryText("Upcoming Bitcode Batches For April")
        inboxStyle.setBigContentTitle("Batch Schedule 2025")
        inboxStyle.addLine("C/Cpp batch by Snehal Mam")
        inboxStyle.addLine("iOS Batch by Aishwarya Mam")
        inboxStyle.addLine("Android Batch by Aishwarya Mam")
        inboxStyle.addLine("Web Batch By Akanksha Mam")
        inboxStyle.addLine("Core Java Batch By Vishal Sir")

        var intent = Intent(this,DetailsActivity::class.java)

        var pendingIntent = PendingIntent.getActivity(this,
            1,
            intent,
            PendingIntent.FLAG_MUTABLE)
        notificationCompat.setContentIntent(pendingIntent)

        inboxStyle.build()
        notificationManager.notify(INBOX_STYLE_NOTIFICATION,notificationCompat.build())
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