package com.example.sarsintitespiti1;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class NotificationReceiver {

    public void onReceive(Context context, Intent intent) {
        String mesaj = intent.getStringExtra("mesaj");

        // Bildirimi göster
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(context, "deprem")
                .setContentTitle("Deprem uyarısı")
                .setContentText("Deprem sarsıntısı tespit edildi")
                .setSmallIcon(android.R.drawable.ic_popup_reminder)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .build();

        notificationManager.notify(0, notification);
    }
}
