package com.example.selfieapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationManagerCompat;

public class ReminderBroadcast extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Notification.Builder builder = new Notification.Builder(context, "selfieApp")
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentText("Please take some selfie")
                .setContentTitle("Selfie App")
                .setAutoCancel(true);

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);

        manager.notify(200, builder.build());
    }
}
