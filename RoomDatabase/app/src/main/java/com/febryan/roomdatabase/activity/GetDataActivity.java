package com.febryan.roomdatabase.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.febryan.roomdatabase.R;
import com.febryan.roomdatabase.adapter.NoteAdapter;
import com.febryan.roomdatabase.databinding.ActivityGetDataBinding;
import com.febryan.roomdatabase.room.Database;
import com.febryan.roomdatabase.room.Note;

import java.util.ArrayList;
import java.util.List;


public class GetDataActivity extends AppCompatActivity {

    private ActivityGetDataBinding binding;
    private List<Note> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGetDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {

        list = new ArrayList<>();
        list = Database.getDatabase(getApplicationContext()).getDao().getAllData();
        binding.rvTitle.setAdapter(new NoteAdapter(this, list, new NoteAdapter.DeleteItem() {
            @Override
            public void onItemDelete(int position, int id) {

                String title = list.get(position).title;
                AlertDialog.Builder alert = new AlertDialog.Builder(GetDataActivity.this);
                alert.setTitle("Konfirmasi");
                alert.setIcon(R.drawable.ic_launcher_background);
                alert.setMessage("Yakin ingin hapus "+title);

                alert.setPositiveButton("Ya", (dialog, which) -> {
                    Database.getDatabase(getApplicationContext()).getDao().deleteData(id);
                    getData();
                    Toast.makeText(GetDataActivity.this, "Terhapus", Toast.LENGTH_SHORT).show();
                });

                alert.setNegativeButton("Tidak", (dialog, which) -> {
                    dialog.dismiss();
                });

                alert.show();
            }
        }));

    }
}