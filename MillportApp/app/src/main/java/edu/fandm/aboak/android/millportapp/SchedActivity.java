package edu.fandm.aboak.android.millportapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class SchedActivity extends AppCompatActivity {

    CalendarView simpleCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sched);

        simpleCalendarView = findViewById(R.id.simpleCalendarView);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_3);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        Toast.makeText(SchedActivity.this, "Home Clicked.", Toast.LENGTH_SHORT).show();
                        returnHome();
                        break;
                    case R.id.action_parking:
                        Toast.makeText(SchedActivity.this, "Parking Clicked.", Toast.LENGTH_SHORT).show();
                        openParking();
                        break;
                    case R.id.action_nav:
                        Toast.makeText(SchedActivity.this, "Nav Clicked.", Toast.LENGTH_SHORT).show();
                        openMaps();
                        break;
                    case R.id.action_tour:
                        Toast.makeText(SchedActivity.this, "Tour Clicked.", Toast.LENGTH_SHORT).show();
                        openTour();
                        break;
                    case R.id.action_catalog:
                        Toast.makeText(SchedActivity.this, "Catalog Clicked.", Toast.LENGTH_SHORT).show();
                        openCatalog();
                        break;
                    case R.id.action_sched:
                        Toast.makeText(SchedActivity.this, "Schedule Clicked.", Toast.LENGTH_SHORT).show();
                        openCalendar();
                        break;
                    case R.id.action_donate:
                        Toast.makeText(SchedActivity.this, "Donate Clicked.", Toast.LENGTH_SHORT).show();
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

    public void openTour() {
        Intent tourIntent = new Intent(this,TourActivity.class);
        startActivity(tourIntent);
    }

    public void openCatalog(){
        Intent catalogIntent = new Intent(this, CatalogActivity.class);
        startActivity(catalogIntent);
    }

    public void openCalendar(){
        Intent calendarIntent = new Intent(this, SchedActivity.class);
        startActivity(calendarIntent);
    }

    public void openDonations(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://securelb.imodules.com/s/1840/interior.aspx?sid=1840&gid=2&pgid=492&cid=1207"));
        startActivity(browserIntent);
    }
}
