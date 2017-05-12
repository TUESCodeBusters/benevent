package benevent.elsys.org.benevent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class SelectLocation extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private double latG;
    private double lonG;
    private Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);
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
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        /* Add a marker in Sydney and move the camera */
        /*if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            public void onResume() {
                Toast.makeText(SelectLocation.this,
                        latG + " " + lonG,
                        Toast.LENGTH_LONG).show();
                if (location != null) {
                    latG = location.getAltitude();
                    lonG = location.getLongitude();
                    Toast.makeText(SelectLocation.this,
                            latG + " " + lonG,
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onLocationChanged(Location location) {

            }
        };*/
        latG = 42.50d;
        lonG = 22.3d;
        LatLng yourLocation = new LatLng(latG, lonG);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(yourLocation));
        mMap.setOnMapLongClickListener(this);

    }

    @Override
    public void onMapLongClick(LatLng point) {
        mMap.clear();
        marker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(point.latitude, point.longitude)));
        Intent returnIntent = new Intent();
        String longLatStr = point.latitude + " " + point.longitude;
        returnIntent.putExtra("point", longLatStr);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}

