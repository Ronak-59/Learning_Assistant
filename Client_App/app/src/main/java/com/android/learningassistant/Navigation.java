package com.android.learningassistant;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.EditText;
import android.widget.Toast;

import com.ames.books.BookListActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.ionicframework.attendance914014.MyActivity;
import com.supermeetup.supermeetup.activities.LoginActivity;


public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText room;
    boolean doubleBackToExitPressedOnce = false;
    String id;
    Toolbar toolbar = null;
    NavigationView navigationView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        UpcomingWorkshops fragment = new UpcomingWorkshops();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        toolbar.setTitle("Upcoming Workshops");



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void signOut(){
        Intent signOutIntent = new Intent(Navigation.this, MainActivity.class);
        startActivity(signOutIntent);
        finish();
    }
    private void displayMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
//    @Override
//    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            finish();
//            System.exit(0);
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        startActivity(new Intent(getBaseContext(),Navigation.class));
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);
//    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_signout) {
            AuthUI.getInstance()
                    .signOut(Navigation.this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                signOut();
                            }else {
                                displayMessage("Signout Error");
                            }
                        }
                    });
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.attendance) {
            Intent intent = new Intent(this,MyActivity.class);
            startActivity(intent);
        } else if (id == R.id.courses) {
            startActivity(new Intent(Navigation.this, com.developers.algoexplorer.MainActivity.class));


        } else if (id == R.id.e_library) {
           startActivity(new Intent(Navigation.this, E_Library.class));
        } else if (id == R.id.jobs) {
            startActivity(new Intent(Navigation.this, com.trevorhalvorson.devjobs.activity.MainActivity.class));


        } else if (id == R.id.projects) {
           // startActivity(new Intent(Navigation.this, KanbanActivity.class));



        } else if (id == R.id.workshops) {
           startActivity(new Intent(Navigation.this, LoginActivity.class));

        } else if (id == R.id.about_us) {
            startActivity(new Intent(Navigation.this,AboutUs.class));


        }else if (id == R.id.forum) {
            Forum fragment = new Forum();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            toolbar.setTitle("Forum");


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
