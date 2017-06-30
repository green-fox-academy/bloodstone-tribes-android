package com.greenfox.tribesoflagopusandroid;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Kingdom;
import com.greenfox.tribesoflagopusandroid.fragments.BattleFragment;
import com.greenfox.tribesoflagopusandroid.fragments.BuildingsFragment;
import com.greenfox.tribesoflagopusandroid.fragments.MainFragment;
import com.greenfox.tribesoflagopusandroid.fragments.SettingsFragment;
import com.greenfox.tribesoflagopusandroid.fragments.TroopsFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String USER_ACCESS_TOKEN = "userToken";
    public static final String USERNAME = "username";
    public static final String NOTIFICATION = "notification";
    public static final String BACKGROUND_SYNC = "backgroundSync";
    public static final String APP_SAVE = "appSave";
    public static final String BUILDINGS_FRAGMENT_SAVE = "buildingsSave";
    public static final String TROOPS_FRAGMENT_SAVE = "troopsSave";
    public static final String SETTINGS_FRAGMENT_SAVE = "SettingsSave";
    public static final String BATTLE_FRAGMENT_SAVE = "battleSave";
    public static final String MAIN_FRAGMENT_SAVE = "mainSave";

    @Inject
    public
    SharedPreferences preferences;

    SharedPreferences.Editor editor;
    String timestamp;
    Fragment fragment = null;
    Kingdom thisKingdom = new Kingdom();
    public PendingIntent pendingIntent;
    public AlarmManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TribesApplication.app().basicComponent().inject(this);
        editor = preferences.edit();
        checkUserAccessToken();
        checkBackgroundSyncStatus();
        displaySelectedScreen(R.id.nav_kingdom);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void checkBackgroundSyncStatus() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getBoolean(BACKGROUND_SYNC, true)) {
            startBackgroundSync();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.refreshing:
                refreshActiveFragment();
        }
        return false;
    }

    public void refreshActiveFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.detach(fragment);
        transaction.attach(fragment);
        transaction.commit();
        Toast.makeText(this,"Refreshing", Toast.LENGTH_SHORT).show();
    }

    public void checkUserAccessToken() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (TextUtils.isEmpty(preferences.getString(USER_ACCESS_TOKEN, null))) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public void logout() {
        switchToBackgroundMode();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void startBackgroundSync() {
        manager = (AlarmManager)getApplicationContext().getSystemService(MainActivity.ALARM_SERVICE);
        long interval = 60000l;
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0,  alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),interval, pendingIntent);
    }

    public void switchToBackgroundMode() {
        long interval = 600000l;
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME, System.currentTimeMillis(),interval, pendingIntent);
    }

    public void stopBackgroundSync() {
        manager.cancel(pendingIntent);
    }

    @Override
    protected void onPause() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getBoolean(BACKGROUND_SYNC, true)) {
            switchToBackgroundMode();
        }
        saveOnExit(APP_SAVE);
        super.onPause();
    }

    @Override
    protected void onStop() {
        stopBackgroundSync();
        saveOnExit(APP_SAVE);
        super.onStop();
    }

    public void saveOnExit(String fragmentName) {
        TribesApplication.app().basicComponent().inject(this);
        editor = preferences.edit();
        timestamp = String.valueOf(System.currentTimeMillis());
        editor.putString(fragmentName, timestamp);
        editor.apply();
    }

    private void displaySelectedScreen(int id) {

        switch (id) {
            case R.id.nav_buildings:
                fragment = new BuildingsFragment();
                break;
            case R.id.nav_kingdom:
                fragment = new MainFragment();
                break;
            case R.id.nav_battle:
                fragment = new BattleFragment();
                break;
            case R.id.nav_settings:
                fragment = new SettingsFragment();
                break;
            case R.id.nav_troops:
                fragment = new TroopsFragment();
                break;
            case R.id.nav_logout:
                logout();
                break;
        }
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.layout_content, fragment);
            transaction.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        displaySelectedScreen(id);
        return true;
    }

}
