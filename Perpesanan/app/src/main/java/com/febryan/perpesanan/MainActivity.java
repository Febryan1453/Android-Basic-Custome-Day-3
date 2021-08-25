package com.febryan.perpesanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;

import com.febryan.perpesanan.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSms.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SmsActivity.class);
            startActivity(intent);
        });

        binding.btnEmail.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EmailActivity.class);
            startActivity(intent);
        });

        binding.btnNotifikasi.setOnClickListener(v -> {
            showNotifikasi();
        });

        binding.btnAlarm.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
            startActivity(intent);
        });

    }

    private void showNotifikasi() {

        int idNotif = 2;
        String idChannel = "android-basic";
        String channelName = "only-notif";

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(getBaseContext(), EmailActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, idChannel)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Email masuk")
                .setContentText("Pesan Notif")
                .setSubText("Belajar intent")
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