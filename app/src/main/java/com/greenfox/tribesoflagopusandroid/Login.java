package com.greenfox.tribesoflagopusandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

        SharedPreferences preferences;
        SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences =PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
    }

    public void login(View view) {
        addUserInfoToPreferences();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    protected void addUserInfoToPreferences() {
        addUsername();
        addPassword();
    }

    protected void addUsername() {
        EditText editText = (EditText) findViewById(R.id.editText3);
        String username = editText.getText().toString();
        editor.putString("Username", username);
        editor.apply();
    }

    protected void addPassword() {
        EditText editText = (EditText) findViewById(R.id.editText2);
        String password = editText.getText().toString();
        editor.putString("Password", password);
        editor.apply();
    }
}
