package com.dk.eventtracker;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.widget.Toast;

import com.dk.eventtracker.adapters.BirthdaysAdapter;
import com.dk.eventtracker.fragments.AboutAppFragment;
import com.dk.eventtracker.fragments.BirthdaysFragment;
import com.dk.eventtracker.fragments.HolidaysFragment;
import com.dk.eventtracker.fragments.MainScreenFragment;
import com.dk.eventtracker.fragments.OtherEventsFragment;
import com.dk.eventtracker.fragments.RegisterFragment;
import com.dk.eventtracker.fragments.UpcomingEventsFragment;
import com.dk.eventtracker.helpers.FragmentStarter;
import com.dk.eventtracker.helpers.Util;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener, SharedPreferences.OnSharedPreferenceChangeListener {
    private FragmentManager mFragmentManager;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private Util util = new Util();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        util.setLanguage(this);

        mFragmentManager = getFragmentManager();
        mFragmentManager.addOnBackStackChangedListener(this);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount()==1){
                    drawer.openDrawer(GravityCompat.START);
                }else if(getFragmentManager().getBackStackEntryCount()==2){
                    drawer.openDrawer(GravityCompat.START);
                }
                else {
                    onBackPressed();
                }
            }
        });

        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this).build());

        MainScreenFragment msf = new MainScreenFragment();
        mFragmentManager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentStarter.StartNewFragment(msf, this, 0);

        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
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
            Intent intent = new Intent(this, AppPreferenceActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.action_about){
            AboutAppFragment aaf = new AboutAppFragment();
            FragmentStarter.StartNewFragment(aaf, this, 1);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_start) {
            MainScreenFragment msf = new MainScreenFragment();
            mFragmentManager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
            FragmentStarter.StartNewFragment(msf, this, 0);

        } else if (id == R.id.nav_holiday) {
            HolidaysFragment hf = new HolidaysFragment();
            FragmentStarter.StartNewFragment(hf, this, 1);

        } else if (id == R.id.nav_birthday) {
            BirthdaysFragment bf = new BirthdaysFragment();
            FragmentStarter.StartNewFragment(bf, this, 1);

        } else if (id == R.id.nav_upcoming) {
            UpcomingEventsFragment aef = new UpcomingEventsFragment();
            FragmentStarter.StartNewFragment(aef, this, 1);

        } else if (id == R.id.nav_other) {
            OtherEventsFragment oef = new OtherEventsFragment();
            FragmentStarter.StartNewFragment(oef, this, 1);

        } else if (id == R.id.nav_reg) {
            RegisterFragment ref = new RegisterFragment();
            FragmentStarter.StartNewFragment(ref, this, 1);

        } else if (id == R.id.nav_login) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackStackChanged() {
        toggle.setDrawerIndicatorEnabled(mFragmentManager.getBackStackEntryCount()==1 || mFragmentManager.getBackStackEntryCount()==2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(mFragmentManager.getBackStackEntryCount()>2);
        toggle.syncState();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        util.setLanguage(this);
        this.recreate();
    }
}
