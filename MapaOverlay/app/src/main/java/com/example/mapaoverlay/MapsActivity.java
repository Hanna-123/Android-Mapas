package com.example.mapaoverlay;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener
        , GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    Projection projection;
    int x=0;
    Marker marcador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        drawObjetos(sydney);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Toast toast = Toast.makeText(this, "Latitud: "+latLng.latitude+" Longitud: "+ latLng.longitude,
                Toast.LENGTH_SHORT);
        toast.show();
        if(marcador==null){

        }
        else{
            marcador.remove();
        }
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        marcador = mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.googleg_standard_color_18))
                .anchor(0.0f,1.0f)
                .position(latLng)
                .title("Marcador: "+x));
        x++;
    }
    private void drawObjetos( LatLng location ) {
        // Dibujo el primer rectangulo
        LatLng point1 = new LatLng( location.latitude -0.007,
                location.longitude -0.010);
        LatLng point2 = new LatLng( location.latitude + .007,
                location.longitude - 0.010);
        LatLng point3 = new LatLng( location.latitude -0.007,
                location.longitude + .010 );
        LatLng point4 = new LatLng( location.latitude + .007,
                location.longitude + .010 );
        PolygonOptions opciones = new PolygonOptions();
        opciones.add(point1,point2,point4,point3);
        opciones.strokeWidth(10);
        opciones.strokeColor(Color.BLUE);
        mMap.addPolygon(opciones);
        //Dinujo rectangulo de dentro
        point1 = new LatLng( location.latitude -0.005,
                location.longitude -0.007);
        point2 = new LatLng( location.latitude + .005,
                location.longitude - 0.007);
        point3 = new LatLng( location.latitude -0.005,
                location.longitude + .007 );
        point4 = new LatLng( location.latitude + .005,
                location.longitude + .007 );
        PolygonOptions opciones2 = new PolygonOptions();
        opciones2.add(point1,point2,point4,point3);
        opciones2.strokeWidth(10);
        opciones2.strokeColor(Color.BLUE);
        mMap.addPolygon(opciones2);
        //Dibujo el circulo
        CircleOptions options = new CircleOptions();
        LatLng centro = new LatLng(location.latitude,location.longitude-0.003);
        options.center(centro);
        //Radius in meters
        options.radius( 100 );
        options.fillColor(Color.RED);
        options.strokeColor(Color.RED);
        options.strokeWidth( 10 );
        mMap.addCircle(options);
    }
}