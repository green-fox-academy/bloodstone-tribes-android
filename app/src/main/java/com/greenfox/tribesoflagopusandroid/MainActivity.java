package com.greenfox.tribesoflagopusandroid;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "greenfox.com.tribesoflagopus.MESSAGE";
    public static final String USERNAME = "Username";
    public static final String PASSWORD = "Password";

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkUsernameAndPassword();
    }

    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText(message);
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

}
