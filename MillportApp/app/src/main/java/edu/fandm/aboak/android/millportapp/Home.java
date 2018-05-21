package edu.fandm.aboak.android.millportapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity implements LocationListener {
    private TextView latituteField;
    private TextView longitudeField;
    private LocationManager locationManager;
    private String provider;
    public Location location;
    public int permissionCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            // Define the criteria how to select the locatioin provider -> use
            // default
            Criteria criteria = new Criteria();
            provider = locationManager.getBestProvider(criteria, false);
            location = locationManager.getLastKnownLocation(provider);
        }

        latituteField = (TextView) findViewById(R.id.latitude);
        longitudeField = (TextView) findViewById(R.id.longitude);
        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            latituteField.setText("Location not available");
            longitudeField.setText("Location not available");
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        Toast.makeText(Home.this, "Home Clicked.", Toast.LENGTH_SHORT).show();
                        returnHome();
                        break;
                    case R.id.action_parking:
                        Toast.makeText(Home.this, "Parking Clicked.", Toast.LENGTH_SHORT).show();
                        openParking();
                        break;
                    case R.id.action_nav:
                        Toast.makeText(Home.this, "Nav Clicked.", Toast.LENGTH_SHORT).show();
                        openMaps();
                        break;
                    case R.id.action_tour:
                        Toast.makeText(Home.this, "Tour Clicked.", Toast.LENGTH_SHORT).show();
                        openTour();
                        break;
                    case R.id.action_catalog:
                        Toast.makeText(Home.this, "Catalog Clicked.", Toast.LENGTH_SHORT).show();
                        openCatalog();
                        break;
                    case R.id.action_sched:
                        Toast.makeText(Home.this, "Schedule Clicked.", Toast.LENGTH_SHORT).show();
                        openCalendar();
                        break;
                    case R.id.action_donate:
                        Toast.makeText(Home.this, "Donate Clicked.", Toast.LENGTH_SHORT).show();
                        openDonations();
                        break;
                }
                return true;
            }
        });
    }

    public void returnHome(){
        Intent homeIntent = new Intent(this, Home.class);
        startActivity(homeIntent);
    }

    public void openParking(){
        Intent parkingIntent = new Intent(this, ParkingActivity.class);
        startActivity(parkingIntent);
    }

    public void openMaps() {
        Intent mapsIntent = new Intent(this, MapsActivity.class);
        startActivity(mapsIntent);
    }

    public void openCatalog(){
        Intent catalogIntent = new Intent(this, CatalogActivity.class);
        startActivity(catalogIntent);
    }

    public void openTour() {
        Intent tourIntent = new Intent(this,TourActivity.class);
        startActivity(tourIntent);
    }

    public void openCalendar(){
        Intent calendarIntent = new Intent(this, SchedActivity.class);
        startActivity(calendarIntent);
    }

    public void openDonations(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://securelb.imodules.com/s/1840/interior.aspx?sid=1840&gid=2&pgid=492&cid=1207"));
        startActivity(browserIntent);
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        //Log.d("HOME ACTIVITY", "Resumed the Activity!");
        super.onResume();
        if(permissionCheck == PackageManager.PERMISSION_GRANTED)
            locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        //Log.d("HOME ACTIVITY", "Paused the Activity!");
        super.onPause();
        if(permissionCheck == PackageManager.PERMISSION_GRANTED)
            locationManager.removeUpdates(this);
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onRestart() {
        //Log.d("HOME ACTIVITY", "Restarted the Activity!");
        super.onRestart();
        if(permissionCheck == PackageManager.PERMISSION_GRANTED)
            locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        String stateToSave = Home.class.getName();
        outState.putString("saved_state", stateToSave);
        //Log.d("HOME ACTIVITY", "Saved home instance to saved_state.");
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        latituteField.setText(String.valueOf(lat));
        longitudeField.setText(String.valueOf(lng));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }
}
