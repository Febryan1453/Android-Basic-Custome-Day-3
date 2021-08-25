package com.febryan.perpesanan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

import com.febryan.perpesanan.databinding.ActivitySmsBinding;

public class SmsActivity extends AppCompatActivity {

    private ActivitySmsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySmsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnPilihKontak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent,300);

            }
        });

        binding.btnSmsIntent.setOnClickListener(v -> {
            String noTel = binding.edtNomorTelepeon.getText().toString();
            String pesan = binding.edtPesan.getText().toString();

            if (noTel.isEmpty() || pesan.isEmpty()){
                Toast.makeText(this, "Ga boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("sms:" + noTel));
            intent.putExtra("sms_body", pesan);
            startActivity(intent);

        });

        binding.btnKirimSms.setOnClickListener(v -> {
            String noTel = binding.edtNomorTelepeon.getText().toString();
            String pesan = binding.edtPesan.getText().toString();

            if (noTel.isEmpty() || pesan.isEmpty()){
                Toast.makeText(this, "Ga boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }

            if (ContextCompat.checkSelfPermission(SmsActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                SmsManager smsManager = SmsManager.getDefault();
                try {
                    smsManager.sendTextMessage(noTel,null, pesan, null, null);
                }catch (Exception e){
                    Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }else {
                ActivityCompat.requestPermissions(SmsActivity.this, new String[]{Manifest.permission.SEND_SMS}, 200);
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 300){
            if (resultCode == RESULT_OK){
                Uri uri = data.getData();
                Cursor cursor;
                cursor = getContentResolver().query(uri,
                        new String[] {ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},
                        null,
                        null,
                        null,
                        null);

                if (cursor != null && cursor.moveToNext()){
                    String notel = cursor.getString(0);
                    Toast.makeText(this, "Berhasil Ambil Kontak", Toast.LENGTH_SHORT).show();
                    binding.edtNomorTelepeon.setText(notel);
                }
            }
        } else if (requestCode == RESULT_CANCELED){
            binding.edtNomorTelepeon.setText("");
            Toast.makeText(this, "Gagal Ambil Kontak", Toast.LENGTH_SHORT).show();
        }

    }
}