package com.dk.eventtracker;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dk.eventtracker.adapters.BirthdaysAdapter;
import com.dk.eventtracker.fragments.AboutAppFragment;
import com.dk.eventtracker.fragments.BirthdaysFragment;
import com.dk.eventtracker.fragments.HolidaysFragment;
import com.dk.eventtracker.fragments.MainScreenFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener {
    private FragmentManager mFragmentManager;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFragmentManager = getFragmentManager();
        mFragmentManager.addOnBackStackChangedListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, timeNow(), Snackbar.LENGTH_LONG)
                        .setActionTextColor(Color.WHITE)
                        .setAction("Action", null).show();

            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ButterKnife.bind(this);

        MainScreenFragment msf = new MainScreenFragment();
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_container, msf);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        int a = mFragmentManager.getBackStackEntryCount();
        if (a>=2){
            if(drawer.isDrawerOpen(GravityCompat.START)){
                drawer.closeDrawer(GravityCompat.START);
            }
            else {
                mFragmentManager.popBackStack();
            }
        } else {
            if (drawer.isDrawerOpen(GravityCompat.START)){
                drawer.closeDrawer(GravityCompat.START);
            }else {
                this.finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_start) {
            MainScreenFragment msf = new MainScreenFragment();
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.replace(R.id.fragment_container, msf);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
        } else if (id == R.id.nav_holiday) {
            HolidaysFragment hf = new HolidaysFragment();
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.replace(R.id.fragment_container, hf);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();

        } else if (id == R.id.nav_birthday) {
            BirthdaysFragment bf = new BirthdaysFragment();
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.replace(R.id.fragment_container, bf);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();

        } else if (id == R.id.nav_other) {

        } else if (id == R.id.nav_about) {
            AboutAppFragment aaf = new AboutAppFragment();
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.replace(R.id.fragment_container, aaf);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String timeNow() {
        String outString;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm - dd.MM.yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        String currentDateAndTime = sdf.format(new Date());
        outString = "Sada je: " + currentDateAndTime;
        return outString;
    }

    @Override
    public void onBackStackChanged() {
        //toggle.setDrawerIndicatorEnabled(mFragmentManager.getBackStackEntryCount()==1 || mFragmentManager.getBackStackEntryCount()==2);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(mFragmentManager.getBackStackEntryCount()>2);
        toggle.syncState();
    }
}
