package com.greenfox.tribesoflagopusandroid;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Kingdom;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Token;
import com.greenfox.tribesoflagopusandroid.api.service.ApiService;
import com.greenfox.tribesoflagopusandroid.api.service.LoginService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.simbio.encryption.Encryption;

import static com.greenfox.tribesoflagopusandroid.MainActivity.USERNAME;
import static com.greenfox.tribesoflagopusandroid.MainActivity.USER_ACCESS_TOKEN;


public class LoginActivity extends AppCompatActivity {

    @Inject
    SharedPreferences preferences;
    @Inject
    ObjectManager objectManager;
    @Inject
    LoginService loginService;
    @Inject
    ApiService apiService;

    SharedPreferences.Editor editor;
    MainActivity mainActivity = new MainActivity();

    String key = "YourKey";
    String salt = "YourSalt";
    byte[] iv = new byte[16];
    Encryption encryption = Encryption.getDefault(key, salt, iv);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TribesApplication.app().basicComponent().inject(this);
        editor = preferences.edit();
    }

    public void login(View view) {

        String username = ((EditText) findViewById(R.id.usernameText)).getText().toString();
        String password = encryption.encryptOrNull(((EditText) findViewById(R.id.passwordText)).getText().toString());

        checkFieldsNotEmpty(username, password);
    }

    public void checkFieldsNotEmpty(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            showToastFillAllFields();
        } else {
            loginWithAPIService(username, encryption.encryptOrNull(password));
        }
    }

    public void showToastFillAllFields() {
        Toast toast = Toast.makeText(LoginActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    protected void loginWithAPIService(final String username, String password) {
        loginService.loginWithUser(username, password).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Token token = response.body();
                addUserToken(token.getToken());
                addUserName(username);
                getKingdomFromAPI();
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "error getting login information from server", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getKingdomFromAPI() {
        apiService.getKingdom(preferences.getString(USER_ACCESS_TOKEN, "")).enqueue(new Callback<Kingdom>() {
            @Override
            public void onResponse(Call<Kingdom> call, Response<Kingdom> response) {
                mainActivity.thisKingdom = response.body();
                sendNotification(preferences.getString(USERNAME, ""));
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Kingdom> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "error getting information from server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sendNotification(String username) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.tribes)
                        .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.tribesbig))
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
        mBuilder.setContentIntent(resultPendingIntent).setAutoCancel(true);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(001, mBuilder.build());
    }

    protected void addUserToken(String token) {
        editor.putString(USER_ACCESS_TOKEN, token);
        editor.apply();
    }

    private void addUserName(String username) {
        editor.putString(USERNAME, username);
        editor.apply();
    }

}
