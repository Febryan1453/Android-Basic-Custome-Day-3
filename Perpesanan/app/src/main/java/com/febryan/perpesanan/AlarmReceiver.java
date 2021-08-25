package com.febryan.perpesanan;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.alarm);
        mediaPlayer.start();
        showNotifikasi(context);
    }

    private void showNotifikasi(Context context) {

        int idNotif = 3;
        String idChannel = "channel-alarm";
        String channelName = "notif-alarm";

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(context, AlarmActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, idChannel)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Alarm Bangun")
                .setContentText("Pesan Notif")
                .setSubText("Sholat shubuh")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(idChannel, channelName, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Notification notification = builder.build();
        notificationManager.notify(idNotif,notification);

    }


}
