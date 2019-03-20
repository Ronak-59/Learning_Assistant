package com.supermeetup.supermeetup.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.util.Log;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.supermeetup.supermeetup.MeetupApp;
import com.supermeetup.supermeetup.R;
import com.supermeetup.supermeetup.adapter.NearbyAdapter;
import com.supermeetup.supermeetup.common.LocationHelper;
import com.supermeetup.supermeetup.common.Util;
import com.supermeetup.supermeetup.databinding.ActivityHomeBinding;
import com.supermeetup.supermeetup.model.Category;
import com.supermeetup.supermeetup.model.Event;
import com.supermeetup.supermeetup.network.MeetupClient;

import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private MeetupClient meetupClient;
    private LocationHelper mLocationHelper;
    private Location mLocation;
    private int mCurrentTabId = R.id.navigation_nearby;
    private String mQuery = "";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            mCurrentTabId = item.getItemId();
            loadContent();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.homeNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Util.disableBottomNavigationViewShiftMode(binding.homeNavigation);
        binding.homeListview.setLayoutManager(new LinearLayoutManager(this));
        binding.homeListview.setAdapter(new NearbyAdapter(this));

        binding.homeSearchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                binding.homeNavigation.setSelectedItemId(R.id.navigation_find);
                mCurrentTabId = R.id.navigation_find;
                mQuery = binding.homeSearchview.getQuery().toString();
                Util.hideSoftKeyboard(HomeActivity.this);
                loadContent();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        meetupClient = MeetupApp.getRestClient(this);

        mLocationHelper = new LocationHelper();
        checkPermission();
        Fabric.with(this, new Crashlytics());


    }
    @Override
    public void onBackPressed() {
        System.exit(0);
    }

    private void setCategoryList(ArrayList<Category> categories){
        ((NearbyAdapter) binding.homeListview.getAdapter()).setCategories(categories);
    }

    private void setEventList(ArrayList<Event> events){
        ((NearbyAdapter) binding.homeListview.getAdapter()).setEvents(events);
    }

    private void checkPermission(){
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    Util.PERMISSIONREQUEST_ACCESS_LOCATION);

        }else{
            mLocationHelper.getLocation(this, locationResult);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case Util.PERMISSIONREQUEST_ACCESS_LOCATION:
                if (grantResults.length > 0) {
                    boolean coarseLocation = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean fineLocation = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if(coarseLocation && fineLocation)
                    {
                        mLocationHelper.getLocation(this, locationResult);
                    } else {
                        //TODO
                    }
                }
                break;
        }
    }

    private void loadContent(){
        if (mCurrentTabId == R.id.navigation_nearby) {
            loadCategories();
            loadRecommendEvents();

        } else if (mCurrentTabId == R.id.navigation_find) {
            loadEvents();

        } else if (mCurrentTabId == R.id.navigation_new) {
        } else if (mCurrentTabId == R.id.navigation_shake) {
        }
    }

    public LocationHelper.LocationResult locationResult = new LocationHelper.LocationResult() {

        @Override
        public void gotLocation(Location location) {
            mLocation = location;
            double Longitude = location.getLongitude();
            double Latitude = location.getLatitude();

            Toast.makeText(getApplicationContext(), "Got Location",
                    Toast.LENGTH_LONG).show();
            loadContent();
        }
    };

    private void loadCategories(){
        meetupClient.findTopicCategories(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                int statusCode = response.code();
                ArrayList<Category> categories = response.body();
                if(categories != null){
                    setCategoryList(categories);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                // Log error here since request failed
                Log.e("finderror", "Find topic categories request error: " + t.toString());
            }
        }, null, null, null, null);

    }

    private void loadRecommendEvents(){
        meetupClient.recommendedEvents(new Callback<ArrayList<Event>>() {
            @Override
            public void onResponse(Call<ArrayList<Event>> call, Response<ArrayList<Event>> response) {
                int statusCode = response.code();
                ArrayList<Event> events = response.body();
                statusCode = 0;
                if(events != null){
                    setEventList(events);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Event>> call, Throwable t) {
                // Log error here since request failed
                Log.e("finderror", "Recommended event request error: " + t.toString());
            }
        }, Util.FIELDS_DEFAULT, mLocation.getLatitude(), mLocation.getLongitude(), null, null, null);
    }

    private void loadEvents(){
        meetupClient.findEvent(new Callback<ArrayList<Event>>() {
            @Override
            public void onResponse(Call<ArrayList<Event>> call, Response<ArrayList<Event>> response) {
                int statusCode = response.code();
                ArrayList<Event> events = response.body();
                if(events != null){
                    setCategoryList(null);
                    setEventList(events);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Event>> call, Throwable t) {
                // Log error here since request failed
                Log.e("finderror", "Find event request error: " + t.toString());
            }
        }, Util.FIELDS_DEFAULT, null, null, null, mQuery);
    }

}
