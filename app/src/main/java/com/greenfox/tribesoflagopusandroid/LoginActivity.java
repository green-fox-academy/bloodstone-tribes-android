package com.greenfox.tribesoflagopusandroid;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.User;
import com.greenfox.tribesoflagopusandroid.api.service.LoginService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.greenfox.tribesoflagopusandroid.MainActivity.USERNAME;


public class LoginActivity extends AppCompatActivity {

    @Inject
    SharedPreferences preferences;
    @Inject
    ObjectManager objectManager;
    @Inject
    LoginService loginService;

    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TribesApplication.app().basicComponent().inject(this);
        editor = preferences.edit();
        getSupportActionBar().hide();
    }

    public void login(View view) {

        String username = ((EditText) findViewById(R.id.usernameText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordText)).getText().toString();

        checkFieldsNotEmpty(username, password);
    }

    public void checkFieldsNotEmpty(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            showToastFillAllFields();
        } else {
            loginWithAPIService(username, password);
        }
    }

    public void showToastFillAllFields() {
        Toast toast = Toast.makeText(LoginActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    protected void loginWithAPIService(String username, String password) {
        loginService.loginWithUser(username, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                addUserInfoToPreferences(user.getUsername());
                sendNotification(user.getUsername());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "error getting login information from server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sendNotification(String username) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.tribes)
                        .setContentTitle("Hi " + username + "!")
                        .setContentText("Welcome to the game, and have fun!");
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(001, mBuilder.build());
    }

    protected void addUserInfoToPreferences(String username) {
        addUsername(username);
    }

    protected void addUsername(String username) {
        editor.putString(USERNAME, username);
        editor.apply();
    }
}
