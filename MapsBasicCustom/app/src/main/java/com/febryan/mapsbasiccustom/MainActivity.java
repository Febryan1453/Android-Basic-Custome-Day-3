package com.febryan.mapsbasiccustom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getLokasi(View view) {
        startActivity(new Intent(MainActivity.this, GetLocActivity.class));
    }

    public void showMaps(View view) {
        startActivity(new Intent(MainActivity.this, ShowMapsActivity.class));
    }

}