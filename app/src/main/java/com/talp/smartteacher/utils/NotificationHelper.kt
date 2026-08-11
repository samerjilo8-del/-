package com.talp.smartteacher.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.talp.smartteacher.R

object NotificationHelper {
    const val HOMEWORK_CHANNEL_ID = "homework_channel"
    const val GRADES_CHANNEL_ID = "grades_channel"
    const val NOTES_CHANNEL_ID = "notes_channel"

    const val HOMEWORK_NOTIFICATION_ID = 1
    const val GRADES_NOTIFICATION_ID = 2
    const val NOTES_NOTIFICATION_ID = 3

    fun createNotificationChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Homework Channel
            val homeworkChannel = NotificationChannel(
                HOMEWORK_CHANNEL_ID,
                context.getString(R.string.homework_channel_name),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = context.getString(R.string.homework_channel_description)
            }
            notificationManager.createNotificationChannel(homeworkChannel)

            // Grades Channel
            val gradesChannel = NotificationChannel(
                GRADES_CHANNEL_ID,
                context.getString(R.string.grades_channel_name),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = context.getString(R.string.grades_channel_description)
            }
            notificationManager.createNotificationChannel(gradesChannel)

            // Notes Channel
            val notesChannel = NotificationChannel(
                NOTES_CHANNEL_ID,
                context.getString(R.string.notes_channel_name),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = context.getString(R.string.notes_channel_description)
            }
            notificationManager.createNotificationChannel(notesChannel)
        }
    }

    fun sendHomeworkNotification(context: Context, title: String, message: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(context, HOMEWORK_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(HOMEWORK_NOTIFICATION_ID, notification)
    }

    fun sendGradeNotification(context: Context, title: String, message: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(context, GRADES_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(GRADES_NOTIFICATION_ID, notification)
    }

    fun sendNoteNotification(context: Context, title: String, message: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(context, NOTES_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(NOTES_NOTIFICATION_ID, notification)
    }
}
