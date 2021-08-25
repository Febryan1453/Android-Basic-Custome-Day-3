package com.febryan.mapsbasiccustom;

import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.febryan.mapsbasiccustom.helper.GPSTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GetLocActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private GPSTracker gpsTracker;
    private double latAwal;
    private double lonAwal;
    private LatLng latLngAwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_loc);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        cekPermission();

    }

    private void cekPermission() {

        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_PHONE_STATE};

        Permissions.check(this, permissions, null, null, new PermissionHandler() {
            @Override
            public void onGranted() {
                Toast.makeText(GetLocActivity.this, "Permissions telah aktif", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                super.onDenied(context, deniedPermissions);
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        // 1
        gpsTracker = new GPSTracker(this);

        if (gpsTracker.canGetLocation()) {

            latAwal = gpsTracker.getLatitude();
            lonAwal = gpsTracker.getLongitude();

            String lokasi = convertLocation(latAwal, lonAwal);

            latLngAwal = new LatLng(latAwal, lonAwal);
            mMap.addMarker(new MarkerOptions().position(latLngAwal).title(lokasi));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngAwal, 17));
        }
    }

    private String convertLocation(double myLat, double myLng) {

        String nameLocation = null;

        Geocoder geocoder = new Geocoder(GetLocActivity.this, Locale.getDefault());
        try {
            List<Address> list = geocoder.getFromLocation(myLat, myLng, 1);
            if (list != null && list.size() > 0) {
                nameLocation = list.get(0).getAddressLine(0) + " " + list.get(0).getCountryName();
                //fetch data from addresses
            } else {
                Toast.makeText(GetLocActivity.this, "kosong", Toast.LENGTH_SHORT).show();
                //display Toast message
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameLocation;
    }
}