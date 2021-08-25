package com.febryan.perpesanan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;

import com.febryan.perpesanan.databinding.ActivityAlarmBinding;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    private ActivityAlarmBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAlarmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnTimePicker.setOnClickListener(v -> {

            setAlarm();

        });

    }

    private void setAlarm() {

        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(AlarmActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent intent = new Intent(AlarmActivity.this, AlarmReceiver.class);

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                cal.set(Calendar.SECOND, 0);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 1, intent,0);

                if (alarmManager != null){
                    alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
                }

                Toast.makeText(AlarmActivity.this, "Alarm di set untuk "+ cal.getTime(), Toast.LENGTH_SHORT).show();
                binding.tvSetAlarm.setText("Alarm di set untuk "+ cal.getTime());

            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();

    }
}