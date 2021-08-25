package com.febryan.perpesanan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.febryan.perpesanan.databinding.ActivityEmailBinding;

public class EmailActivity extends AppCompatActivity {

    private ActivityEmailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnKirimEmail.setOnClickListener(v -> {

            String email = binding.edtEmailTujuan.getText().toString();
            String subject = binding.edtSubject.getText().toString();
            String body = binding.edtBodyEmail.getText().toString();

            if (email.isEmpty() || subject.isEmpty() || body.isEmpty()){
                Toast.makeText(this, "Ga boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT,body);
            startActivity(intent);

        });

    }
}