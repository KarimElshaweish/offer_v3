package com.example.karim.offer_v3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import com.example.karim.offer_v3.Fragmets.CategoryFragment;
import com.example.karim.offer_v3.Fragmets.ExploreFragment;
import com.example.karim.offer_v3.Fragmets.FavourteFragment;
import com.example.karim.offer_v3.Fragmets.MoreFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import com.onesignal.OneSignal;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        final List<String>Ids=new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(Map.Entry entry :((Map<String,Object>)dataSnapshot.getValue()).entrySet()){
                    Ids.add(entry.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        FirebaseMessaging.getInstance().subscribeToTopic("com.example.karim.offer_v3.Firebase");

        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft=fragmentManager.beginTransaction();
        ft.replace(R.id.content,new ExploreFragment()).commit();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Explore");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem myItems=menu.findItem(R.id.meanuSearch);
        final SearchView searchView=(SearchView)myItems.getActionView();
        searchView.setQueryHint("search");
        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.Fragment fragment;
        android.support.v4.app.FragmentManager fragmentManager;
        android.support.v4.app.FragmentTransaction ft;
        if (id == R.id.nav_camera) {
            fragment=new ExploreFragment();
            fragmentManager=getSupportFragmentManager();
            ft=fragmentManager.beginTransaction();
            ft.replace(R.id.content,fragment).commit();
            toolbar.setTitle(R.string.explore);

        } else if (id == R.id.nav_gallery) {
             fragment=new CategoryFragment();
             fragmentManager=getSupportFragmentManager();
            ft=fragmentManager.beginTransaction();
            ft.replace(R.id.content,fragment).commit();
            toolbar.setTitle(R.string.category);

        } else if (id == R.id.nav_slideshow) {
            fragment=new FavourteFragment();
            fragmentManager=getSupportFragmentManager();
            ft=fragmentManager.beginTransaction();
            ft.replace(R.id.content,fragment).commit();
            toolbar.setTitle(R.string.Favourite);
        } else if(id == R.id.nav_more){
             fragment=new MoreFragment();
             fragmentManager=getSupportFragmentManager();
             ft=fragmentManager.beginTransaction();
            ft.replace(R.id.content,fragment).commit();
            toolbar.setTitle(R.string.more);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
