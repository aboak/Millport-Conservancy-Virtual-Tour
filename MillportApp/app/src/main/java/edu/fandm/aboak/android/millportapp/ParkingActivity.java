package edu.fandm.aboak.android.millportapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by boaki on 2/20/2018.
 */

public class ParkingActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_6);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Toast.makeText(ParkingActivity.this, "Home Clicked.", Toast.LENGTH_SHORT).show();
                        returnHome();
                        break;
                    case R.id.action_parking:
                        Toast.makeText(ParkingActivity.this, "Parking Clicked.", Toast.LENGTH_SHORT).show();
                        openParking();
                        break;
                    case R.id.action_nav:
                        Toast.makeText(ParkingActivity.this, "Nav Clicked.", Toast.LENGTH_SHORT).show();
                        openMaps();
                        break;
                    case R.id.action_tour:
                        Toast.makeText(ParkingActivity.this, "Tour Clicked.", Toast.LENGTH_SHORT).show();
                        openTour();
                        break;
                    case R.id.action_catalog:
                        Toast.makeText(ParkingActivity.this, "Catalog Clicked.", Toast.LENGTH_SHORT).show();
                        openCatalog();
                        break;
                    case R.id.action_sched:
                        Toast.makeText(ParkingActivity.this, "Schedule Clicked.", Toast.LENGTH_SHORT).show();
                        openCalendar();
                        break;
                    case R.id.action_donate:
                        Toast.makeText(ParkingActivity.this, "Donate Clicked.", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onResume() {
        //Log.d("PARKING ACTIVITY", "Resumed the Activity!");
        super.onResume();
    }

    @Override
    protected void onPause() {
        //Log.d("PARKING ACTIVITY", "Paused the Activity!");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        //Log.d("PARKING ACTIVITY", "Restarted the Activity!");
        super.onRestart();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        String stateToSave = ParkingActivity.class.getName();
        outState.putString("saved_state", stateToSave);
        //Log.d("PARKING ACTIVITY", "Saved parking instance to saved_state.");
    }
}
