package com.example.a16022653.p06taskmanager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class TaskNotification extends BroadcastReceiver {
    int reqCode = 12345;
    Tasks data;

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel("default", "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("This is for default notification");
            notificationManager.createNotificationChannel(channel);
        }


        String name = intent.getStringExtra("name");
        String desc = intent.getStringExtra("desc");
        Intent i = new Intent(context, MainActivity.class);


        PendingIntent pIntent = PendingIntent.getActivity(context, reqCode, i, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.BigTextStyle bigText = new
                NotificationCompat.BigTextStyle();
        bigText.setBigContentTitle("Task Manager Reminder");
        bigText.bigText(name);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default");
        builder.setContentTitle(name);
        builder.setContentText(desc);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info);
        builder.setContentIntent(pIntent);
        builder.setStyle(bigText);
        builder.setAutoCancel(true);
        Notification n = builder.build();

        notificationManager.notify(123, n);
    }
}