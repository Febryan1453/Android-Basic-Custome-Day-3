package com.febryan.roomdatabase.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.febryan.roomdatabase.R;
import com.febryan.roomdatabase.databinding.ActivityUpdateBinding;
import com.febryan.roomdatabase.room.Database;

public class UpdateActivity extends AppCompatActivity {

    private ActivityUpdateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.judul.setText(getIntent().getExtras().getString("title"));
        binding.deskripsi.setText(getIntent().getExtras().getString("desc"));
        final String id = getIntent().getExtras().getString("id");

        binding.btnUpdateNote.setOnClickListener(v -> {
            String title = binding.judul.getText().toString().trim();
            String desc = binding.deskripsi.getText().toString().trim();
            if (title.equals("") || desc.equals("")){
                Toast.makeText(this, "Title & Desc Dibutuhkan", Toast.LENGTH_SHORT).show();
            }else {
                Database.getDatabase(getApplicationContext()).getDao().updateData(title, desc, Integer.parseInt(id));
                finish();
            }
        });


    }
}