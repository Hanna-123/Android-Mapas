package com.example.ejemplo1;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    Button jbnsatelite, jbncentrar, jbnanimar, jbnmover;
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_maps);
        jbnsatelite = (Button)findViewById(R.id.xbnsatelite);
        jbncentrar = (Button)findViewById(R.id.xbncentrar);
        jbnanimar = (Button)findViewById(R.id.xbnanimar);
        jbnmover = (Button)findViewById(R.id.xbnmover);
// Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        jbnsatelite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mMap.getMapType()==2){
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
                else {
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            }
        });
        jbncentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double latitud = 37.40*1E6;
                Double longitud = -5.99*1E6;
                LatLng centro = new LatLng (latitud, longitud);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(centro));
            }
        });
        jbnanimar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double latitud = 19.4284706;
                Double longitud = -99.1276627;
                LatLng cdmx = new LatLng (latitud, longitud);
                for(int x=0;x<=10;x++){
                    mMap.setMinZoomPreference(x);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(cdmx));
                }
            }
        });

        jbnmover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double latitud = 40.00;
                Double longitud = 40.00;
                LatLng eu = new LatLng (latitud, longitud);
                mMap.setMinZoomPreference(1);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(eu));
            }
        });
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
// Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(23.6345005, -102.5527878);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


    }
}