package com.example.qq;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class AlarmReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent1, 0);
        Notification notification = new Notification.Builder(context)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle("今天該來記帳囉")
                .setContentText("很高興認識你，我是你的小助手 adapter")
                .setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.icon_notification)
                .build();
        notification.defaults = Notification.DEFAULT_SOUND;
        NotificationManager manager = (NotificationManager)
                context.getSystemService(context.NOTIFICATION_SERVICE);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        manager.notify(1, notification);
    }
}