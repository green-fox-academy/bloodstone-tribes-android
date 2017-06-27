package com.greenfox.tribesoflagopusandroid;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.greenfox.tribesoflagopusandroid.fragments.MainFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    public static final String USERNAME = "Username";
    public static final String NOTIFICATION = "Notification";
    public static final String BACKGROUND_SYNC = "BackgroundSync";
    public static final String APP_SAVE = "appSave";
    String timestamp;

    @Inject
    SharedPreferences preferences;

    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TribesApplication.app().basicComponent().inject(this);
        editor = preferences.edit();
        checkUsername();

        Button button = (Button) findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                logout();
            }
        });

        MainFragment mainFragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_activity_layout, mainFragment);
        fragmentTransaction.commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.refreshing:
                Toast.makeText(MainActivity.this, "Refreshing", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void checkUsername() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (TextUtils.isEmpty(preferences.getString(USERNAME, null))) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public void logout() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        editor.clear();
        editor.apply();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timestamp = String.valueOf(System.currentTimeMillis());
        editor.putString(APP_SAVE, timestamp);
        editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();
        timestamp = String.valueOf(System.currentTimeMillis());
        editor.putString(APP_SAVE, timestamp);
        editor.apply();
    }
}
