package com.greenfox.tribesoflagopusandroid;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import com.greenfox.tribesoflagopusandroid.fragments.BattleFragment;
import com.greenfox.tribesoflagopusandroid.fragments.BuildingsFragment;
import com.greenfox.tribesoflagopusandroid.fragments.MainFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String USERNAME = "Username";
    public static final String PASSWORD = "Password";
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkUsernameAndPassword();

        MainFragment mainFragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.flContent, mainFragment);
        fragmentTransaction.commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nvView);
        navigationView.setNavigationItemSelectedListener(this); }

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
                Toast.makeText(MainActivity.this, "Refreshing", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void checkUsernameAndPassword() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String username = preferences.getString(USERNAME, null);
        String password = preferences.getString(PASSWORD, null);

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public void logout() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void displaySelectedScreen(int id) {
        Fragment fragment = null;

        switch (id) {
            case R.id.nav_buildings:
                fragment = new BuildingsFragment();
                break;
            case R.id.nav_kingdom:
                break;
            case R.id.nav_battle:
                fragment = new BattleFragment();
                break;
            case R.id.nav_settings:
                break;
            case R.id.nav_troops:
                break;
            case R.id.nav_logout:
                logout();
                break;
        }
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.flContent, fragment);
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
