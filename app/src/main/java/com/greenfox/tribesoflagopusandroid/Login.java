package com.greenfox.tribesoflagopusandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        addUsernameToPreferences();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addUsernameToPreferences() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        EditText editText = (EditText) findViewById(R.id.editText3);
        String username = editText.getText().toString();
        editor.putString("Username", username);
        editor.apply();
    }
}
