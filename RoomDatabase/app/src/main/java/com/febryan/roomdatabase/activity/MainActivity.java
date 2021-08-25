package com.febryan.roomdatabase.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.febryan.roomdatabase.R;
import com.febryan.roomdatabase.databinding.ActivityMainBinding;
import com.febryan.roomdatabase.room.Database;
import com.febryan.roomdatabase.room.Note;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSubmit.setOnClickListener(v -> {
            submitData();
        });

        binding.btnGet.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), GetDataActivity.class));
        });

    }

    private void submitData() {

        String title = binding.tvTitle.getText().toString().trim();
        String desc = binding.tvDesc.getText().toString().trim();

        Note note = new Note();
        note.setTitle(title);
        note.setDesc(desc);
        Database.getDatabase(getApplicationContext()).getDao().insertAllData(note);

        binding.tvTitle.setText("");
        binding.tvDesc.setText("");

        Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
    }
}