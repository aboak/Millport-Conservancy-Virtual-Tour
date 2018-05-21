package edu.fandm.aboak.android.millportapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TourActivity extends AppCompatActivity implements edu.fandm.aboak.android.millportapp.TourAdapter.TourAdapterOnClickHandler, LocationListener{

    private LocationManager manager;
    private String provider;
    private Location location;
    private edu.fandm.aboak.android.millportapp.TourAdapter mTourAdapter;
    private RecyclerView mRecyclerView;
    private Context superContext;

    public double[] blueLine = {40.0461, -76.3199};
    public double[] hartman = {40.0472, -76.3204};
    public double[] ccrossing = {40.13404, -76.258};
    public double[] riverbend = {40.13574, -76.25834};
    public double[] horsepen = {40.1412, -76.26408};
    public double[] lake = {40.14179, -76.26527};
    public double[] barn = {40.14753, -76.27096};
    public double[] campground = {40.14388, -76.27045};
    public double[] singeperch = {40.14266, -76.26982};
    public double[] doubleperch = {40.14375, -76.26912};
    public double[] trailhead = {40.14395, -76.2676};
    public double[] boathouse = {40.1431, -76.26626};
    public double[] swimminghole = {40.13764, -76.2583};
    public double[] overlook = {40.1376, -76.25849};
    public double[] meadow = {40.1363, -76.25839};
    public double[] entrance = {40.13413, -76.25773};
    public ArrayList<edu.fandm.aboak.android.millportapp.Place> seenLocations;
    public ArrayList<double[]> seenCords;

    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);
        superContext = getApplicationContext();

        mRecyclerView = findViewById(R.id.recylcerview_tour);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        seenLocations = new ArrayList<>(0);
        seenCords = new ArrayList<>(0);
        mTourAdapter = new edu.fandm.aboak.android.millportapp.TourAdapter(seenLocations);
        mRecyclerView.setAdapter(mTourAdapter);

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 2, this);

        Criteria criteria = new Criteria();
        provider = manager.getBestProvider(criteria, false);
        location = manager.getLastKnownLocation(provider);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_2);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        Toast.makeText(TourActivity.this, "Home Clicked.", Toast.LENGTH_SHORT).show();
                        returnHome();
                        break;
                    case R.id.action_parking:
                        Toast.makeText(TourActivity.this, "Parking Clicked.", Toast.LENGTH_SHORT).show();
                        openParking();
                        break;
                    case R.id.action_nav:
                        Toast.makeText(TourActivity.this, "Nav Clicked.", Toast.LENGTH_SHORT).show();
                        openMaps();
                        break;
                    case R.id.action_tour:
                        Toast.makeText(TourActivity.this, "Tour Clicked.", Toast.LENGTH_SHORT).show();
                        openTour();
                        break;
                    case R.id.action_catalog:
                        Toast.makeText(TourActivity.this, "Catalog Clicked.", Toast.LENGTH_SHORT).show();
                        openCatalog();
                        break;
                    case R.id.action_sched:
                        Toast.makeText(TourActivity.this, "Schedule Clicked.", Toast.LENGTH_SHORT).show();
                        openCalendar();
                        break;
                    case R.id.action_donate:
                        Toast.makeText(TourActivity.this, "Donate Clicked.", Toast.LENGTH_SHORT).show();
                        openDonations();
                        break;
                }
                return true;
            }
        });
    }

    public void returnHome(){
        Intent homeIntent = new Intent(this, edu.fandm.aboak.android.millportapp.Home.class);
        startActivity(homeIntent);
    }

    public void openParking(){
        Intent parkingIntent = new Intent(this, edu.fandm.aboak.android.millportapp.ParkingActivity.class);
        startActivity(parkingIntent);
    }

    public void openMaps() {
        Intent mapsIntent = new Intent(this, edu.fandm.aboak.android.millportapp.MapsActivity.class);
        startActivity(mapsIntent);
    }

    public void openTour() {
        Intent tourIntent = new Intent(this,TourActivity.class);
        startActivity(tourIntent);
    }

    public void openCatalog(){
        Intent catalogIntent = new Intent(this, edu.fandm.aboak.android.millportapp.CatalogActivity.class);
        startActivity(catalogIntent);
    }

    public void openCalendar(){
        Intent calendarIntent = new Intent(this, edu.fandm.aboak.android.millportapp.SchedActivity.class);
        startActivity(calendarIntent);
    }

    public void openDonations(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://securelb.imodules.com/s/1840/interior.aspx?sid=1840&gid=2&pgid=492&cid=1207"));
        startActivity(browserIntent);
    }

    @Override
    public void onClick(double lat, double lon) {

    }

    @Override
    public void onLocationChanged(Location location) {

        ImageView locImage = new ImageView(this);
        String[] tempInfo = {"", "", ""};
        edu.fandm.aboak.android.millportapp.Place currentPlace = new edu.fandm.aboak.android.millportapp.Place();

        Location triggerLoc = new Location("Trigger Location (blue line)");
        triggerLoc.setLatitude(blueLine[0]);
        triggerLoc.setLongitude(blueLine[1]);

        double distanceBL = triggerLoc.distanceTo(location);
        if(distanceBL < 30 && !seenCords.contains(blueLine))
        {
            currentPlace.setLocImage(R.drawable.blue_line);
            tempInfo[0] = "Blue Line";
            tempInfo[1] = blueLine[0] + ", " + blueLine[1];
            tempInfo[2] = "The best place to eat on campus!";
            currentPlace.setInfo(tempInfo);
            currentPlace.setCords(blueLine);
            populateLandmark(currentPlace);
            seenCords.add(blueLine);
        }

        Location hartmanTrigger = new Location("Trigger Location (hartman)");
        hartmanTrigger.setLatitude(hartman[0]);
        hartmanTrigger.setLongitude(hartman[1]);

        double distanceHart = hartmanTrigger.distanceTo(location);
        if(distanceHart < 30 && !seenCords.contains(hartman))
        {
            currentPlace.setLocImage(R.drawable.hartman);
            tempInfo[0] = "Hartman Green";
            tempInfo[1] = hartman[0] + ", " + hartman[1];
            tempInfo[2] = "The center of campus!";
            currentPlace.setInfo(tempInfo);
            currentPlace.setCords(hartman);
            populateLandmark(currentPlace);
            seenCords.add(hartman);
        }

        Location ccrossingTrigger = new Location("Trigger Location (Carolyn's Crossing)");
        ccrossingTrigger.setLatitude(ccrossing[0]);
        ccrossingTrigger.setLongitude(ccrossing[1]);

        double distanceCcrossing = ccrossingTrigger.distanceTo(location);
        if(distanceCcrossing < 30 && !seenCords.contains(ccrossing))
        {
            currentPlace.setLocImage(R.drawable.carolynscrossing);
            tempInfo[0] = "Carolyn's Crossing";
            tempInfo[1] = ccrossing[0] + ", " + ccrossing[1];
            tempInfo[2] = "A handcrafted footbridge spanning the creek.";
            currentPlace.setInfo(tempInfo);
            currentPlace.setCords(ccrossing);
            populateLandmark(currentPlace);
            seenCords.add(ccrossing);
        }

        Location riverbendTrigger = new Location("Trigger Location (Riverbend)");
        riverbendTrigger.setLatitude(riverbend[0]);
        riverbendTrigger.setLongitude(riverbend[1]);

        double distanceRiverbend = riverbendTrigger.distanceTo(location);
        if(distanceRiverbend < 30 && !seenCords.contains(riverbend))
        {
            currentPlace.setLocImage(R.drawable.riverbend);
            tempInfo[0] = "Riverbend";
            tempInfo[1] = riverbend[0] + ", " + riverbend[1];
            tempInfo[2] = "Restoration efforts over the years have revitalized the coastline.";
            currentPlace.setInfo(tempInfo);
            currentPlace.setCords(riverbend);
            populateLandmark(currentPlace);
            seenCords.add(riverbend);
        }

        Location horsepenTrigger = new Location("Trigger Location (Horse Pen)");
        horsepenTrigger.setLatitude(horsepen[0]);
        horsepenTrigger.setLongitude(horsepen[1]);

        double distanceHorsepen = horsepenTrigger.distanceTo(location);
        if(distanceHorsepen < 30 && !seenCords.contains(horsepen))
        {
            currentPlace.setLocImage(R.drawable.horsepen);
            tempInfo[0] = "Horse Pen";
            tempInfo[1] = horsepen[0] + ", " + horsepen[1];
            tempInfo[2] = "The horses names are & !";
            currentPlace.setInfo(tempInfo);
            currentPlace.setCords(horsepen);
            populateLandmark(currentPlace);
            seenCords.add(horsepen);
        }

        Location lakeTrigger = new Location("Trigger Location (Lake)");
        lakeTrigger.setLatitude(lake[0]);
        lakeTrigger.setLongitude(lake[1]);

        double distanceLake = lakeTrigger.distanceTo(location);
        if(distanceLake < 30 && !seenCords.contains(lake))
        {
            currentPlace.setLocImage(R.drawable.lake);
            tempInfo[0] = "Lake";
            tempInfo[1] = lake[0] + ", " + lake[1];
            tempInfo[2] = "Generally open to public fishing.";
            currentPlace.setInfo(tempInfo);
            currentPlace.setCords(lake);
            populateLandmark(currentPlace);
            seenCords.add(lake);
        }

        Location barnTrigger = new Location("Trigger Location (Barn)");
        barnTrigger.setLatitude(barn[0]);
        barnTrigger.setLongitude(barn[1]);

        double distanceBarn = barnTrigger.distanceTo(location);
        if(distanceBarn < 30 && !seenCords.contains(barn))
        {
            currentPlace.setLocImage(R.drawable.barn);
            tempInfo[0] = "Barn";
            tempInfo[1] = barn[0] + ", " + barn[1];
            tempInfo[2] = "An old storage barn for fishing/maintenance equipment.";
            currentPlace.setInfo(tempInfo);
            currentPlace.setCords(barn);
            populateLandmark(currentPlace);
            seenCords.add(barn);
        }

        Location campTrigger = new Location("Trigger Location (Campground)");
        campTrigger.setLatitude(campground[0]);
        campTrigger.setLongitude(campground[1]);

        double distanceCamp = campTrigger.distanceTo(location);
        if(distanceCamp < 30 && !seenCords.contains(campground))
        {
            currentPlace.setLocImage(R.drawable.campground);
            tempInfo[0] = "Campground";
            tempInfo[1] = campground[0] + ", " + campground[1];
            tempInfo[2] = "Open for reservations upon contacting the directors.";
            currentPlace.setInfo(tempInfo);
            currentPlace.setCords(campground);
            populateLandmark(currentPlace);
            seenCords.add(campground);
        }

        Location singleTrigger = new Location("Trigger Location (Single Perch)");
        singleTrigger.setLatitude(singeperch[0]);
        singleTrigger.setLongitude(singeperch[1]);

        double distanceSingle = singleTrigger.distanceTo(location);
        if(distanceSingle < 30 && !seenCords.contains(singeperch))
        {
            currentPlace.setLocImage(R.drawable.singleperch);
            tempInfo[0] = "Single Birdview Perch";
            tempInfo[1] = singeperch[0] + ", " + singeperch[1];
            tempInfo[2] = "Gives a wide view of the valley around the campground.";
            currentPlace.setInfo(tempInfo);
            currentPlace.setCords(singeperch);
            populateLandmark(currentPlace);
            seenCords.add(singeperch);
        }

        Location doubleTrigger = new Location("Trigger Location (Double Perch)");
        doubleTrigger.setLatitude(doubleperch[0]);
        doubleTrigger.setLongitude(doubleperch[1]);

        double distanceDouble = doubleTrigger.distanceTo(location);
        if(distanceDouble < 30 && !seenCords.contains(doubleperch))
        {
            currentPlace.setLocImage(R.drawable.doubleperch);
            tempInfo[0] = "Double Birdview Perch";
            tempInfo[1] = doubleperch[0] + ", " + doubleperch[1];
            tempInfo[2] = "This perch has enough room for a partner.";
            currentPlace.setInfo(tempInfo);
            currentPlace.setCords(doubleperch);
            populateLandmark(currentPlace);
            seenCords.add(doubleperch);
        }

        Location trailheadTrigger = new Location("Trigger Location (Trailhead)");
        trailheadTrigger.setLatitude(trailhead[0]);
        trailheadTrigger.setLongitude(trailhead[1]);

        double distanceTrailhead = trailheadTrigger.distanceTo(location);
        if(distanceTrailhead < 30 && !seenCords.contains(trailhead))
        {
            currentPlace.setLocImage(R.drawable.trailhead);
            tempInfo[0] = "Trailhead";
            tempInfo[1] = trailhead[0] + ", " + trailhead[1];
            tempInfo[2] = "The entrance to the deeper conservancy loop.";
            currentPlace.setInfo(tempInfo);
            currentPlace.setCords(trailhead);
            populateLandmark(currentPlace);
            seenCords.add(trailhead);
        }

        Location boathouseTrigger = new Location("Trigger Location (Boathouse)");
        boathouseTrigger.setLatitude(boathouse[0]);
        boathouseTrigger.setLongitude(boathouse[1]);

        double distanceBoathouse = boathouseTrigger.distanceTo(location);
        if(distanceBoathouse < 30 && !seenCords.contains(boathouse))
        {
            currentPlace.setLocImage(R.drawable.boathouse);
            tempInfo[0] = "Boathouse";
            tempInfo[1] = boathouse[0] + ", " + boathouse[1];
            tempInfo[2] = "A storage unit for small fishing boats.";
            currentPlace.setInfo(tempInfo);
            currentPlace.setCords(boathouse);
            populateLandmark(currentPlace);
            seenCords.add(boathouse);
        }

        Location swimholeTrigger = new Location("Trigger Location (Swimhole)");
        swimholeTrigger.setLatitude(swimminghole[0]);
        swimholeTrigger.setLongitude(swimminghole[1]);

        double distanceSwimhole = swimholeTrigger.distanceTo(location);
        if(distanceSwimhole < 30 && !seenCords.contains(swimminghole))
        {
            currentPlace.setLocImage(R.drawable.swimminghole);
            tempInfo[0] = "Swimming Hole";
            tempInfo[1] = swimminghole[0] + ", " + swimminghole[1];
            tempInfo[2] = "A pleasant spot to beat the heat.";
            currentPlace.setInfo(tempInfo);
            currentPlace.setCords(swimminghole);
            populateLandmark(currentPlace);
            seenCords.add(swimminghole);
        }

        Location overlookTrigger = new Location("Trigger Location (Overlook)");
        overlookTrigger.setLatitude(overlook[0]);
        overlookTrigger.setLongitude(overlook[1]);

        double distanceOverlook = overlookTrigger.distanceTo(location);
        if(distanceOverlook < 30 && !seenCords.contains(overlook))
        {
            currentPlace.setLocImage(R.drawable.overlook);
            tempInfo[0] = "Overlook";
            tempInfo[1] = overlook[0] + ", " + overlook[1];
            tempInfo[2] = "The perfect place to catch a sunset.";
            currentPlace.setInfo(tempInfo);
            currentPlace.setCords(overlook);
            populateLandmark(currentPlace);
            seenCords.add(overlook);
        }

        Location meadowTrigger = new Location("Trigger Location (Meadow)");
        meadowTrigger.setLatitude(meadow[0]);
        meadowTrigger.setLongitude(meadow[1]);

        double distanceMeadow = meadowTrigger.distanceTo(location);
        if(distanceMeadow < 30 && !seenCords.contains(meadow))
        {
            currentPlace.setLocImage(R.drawable.meadow);
            tempInfo[0] = "Meadow";
            tempInfo[1] = meadow[0] + ", " + meadow[1];
            tempInfo[2] = "A romantic pathway lined in flowers.";
            currentPlace.setInfo(tempInfo);
            currentPlace.setCords(meadow);
            populateLandmark(currentPlace);
            seenCords.add(meadow);
        }

        Location entranceTrigger = new Location("Trigger Location (Entrance)");
        entranceTrigger.setLatitude(entrance[0]);
        entranceTrigger.setLongitude(entrance[1]);

        double distanceEntrance = entranceTrigger.distanceTo(location);
        if(distanceEntrance < 30 && !seenCords.contains(entrance))
        {
            currentPlace.setLocImage(R.drawable.entry);
            tempInfo[0] = "Path Entry";
            tempInfo[1] = entrance[0] + ", " + entrance[1];
            tempInfo[2] = "The entrance to the trails.";
            currentPlace.setInfo(tempInfo);
            currentPlace.setCords(entrance);
            populateLandmark(currentPlace);
            seenCords.add(entrance);
        }
    }

    public void populateLandmark(edu.fandm.aboak.android.millportapp.Place tempLoc)
    {
        if(!seenLocations.contains(tempLoc))
        {
            //Log.d("LocationListener", "seenLocations doesn't have tempLoc: " + tempLoc);
            seenLocations.add(tempLoc);
            mTourAdapter.notifyDataSetChanged();
        }
        //Log.d("LocationListener", "Populated landmark with: " + tempLoc);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
