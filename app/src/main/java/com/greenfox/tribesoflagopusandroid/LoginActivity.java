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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Kingdom;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Token;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.User;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.UserLoginDTO;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.UserRegisterDTO;
import com.greenfox.tribesoflagopusandroid.api.service.ApiService;
import com.greenfox.tribesoflagopusandroid.api.service.LoginService;

import org.json.JSONObject;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.simbio.encryption.Encryption;

import static com.greenfox.tribesoflagopusandroid.MainActivity.NOTIFICATION;
import static com.greenfox.tribesoflagopusandroid.MainActivity.USERNAME;
import static com.greenfox.tribesoflagopusandroid.MainActivity.USER_ACCESS_TOKEN;


public class LoginActivity extends AppCompatActivity {

  @Inject
  SharedPreferences preferences;
  @Inject
  LoginService loginService;
  @Inject
  ApiService apiService;

  SharedPreferences.Editor editor;
  MainActivity mainActivity = new MainActivity();

  EditText username;
  LinearLayout loginLayout, registerLayout;
  public static final String KEY = "YourKey";
  public static final String SALT = "YourSalt";
  Encryption encryption;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    TribesApplication.app().basicComponent().inject(this);
    editor = preferences.edit();
//        if (settingsFragment.notification.isChecked()) {
//            editor.putBoolean(NOTIFICATION, true);
//            editor.apply();
//        } else {
//            editor.putBoolean(NOTIFICATION, false);
//            editor.apply();
//        }

    registerLayout = (LinearLayout) findViewById(R.id.registerLayout);
    loginLayout = (LinearLayout) findViewById(R.id.loginLayout);
    username = (EditText) findViewById(R.id.usernameText);
    registerLayout.setVisibility(View.INVISIBLE);

    String registeredName = preferences.getString("RegisterName", null);

    if (!TextUtils.isEmpty(registeredName)) {
      username.setText(registeredName);
    }

    byte[] iv = new byte[16];
    encryption = Encryption.getDefault(KEY, SALT, iv);
  }

  public void login(View view) {
    String username = ((EditText) findViewById(R.id.usernameText)).getText().toString();
    String password = ((EditText) findViewById(R.id.passwordText)).getText().toString();

    checkFieldsNotEmpty(username, password);
  }

  public void registerUsername(View view) {
    registerLayout.setVisibility(View.VISIBLE);
    loginLayout.setVisibility(View.INVISIBLE);
  }

  public void back(View view) {
    registerLayout.setVisibility(View.INVISIBLE);
    loginLayout.setVisibility(View.VISIBLE);
  }

  public void sendRegister(View view) {
    String username = ((EditText) findViewById(R.id.register_name)).getText().toString();
    String password = ((EditText) findViewById(R.id.register_password)).getText().toString();
    String confirmPassword = ((EditText) findViewById(R.id.register_password_confirm)).getText().toString();
    String kingdomName = ((EditText) findViewById(R.id.kingdom_name)).getText().toString();

    editor.putString("RegisterName", username);
    editor.apply();

    checkFieldsInRegisterIsNotEmpty(username, password, confirmPassword, kingdomName);
  }

  protected void registerWithAPIService(String username, String password, String kingdomName) {
    try {
      password = encryption.encrypt(password);
    } catch (Exception e) {
      e.printStackTrace();
    }
    apiService.register(new UserRegisterDTO(username, password, kingdomName)).enqueue(new Callback<User>() {
      @Override
      public void onResponse(Call<User> call, Response<User> response) {
        if (response.code() != 200) {
          try {
            JSONObject errorJSONAnswer = new JSONObject(response.errorBody().string());
            Toast.makeText(getApplication(), errorJSONAnswer.getString("message"), Toast.LENGTH_LONG).show();
          } catch (Exception e) {
            Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_LONG).show();
          }
          return;
        }
        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
        startActivity(intent);
      }

      @Override
      public void onFailure(Call<User> call, Throwable t) {

      }
    });
  }

  public void checkPasswordMatch(String username, String password, String passwordConfirm, String kingdomName) {
    if (!password.equals(passwordConfirm)) {
      Toast toast = Toast.makeText(LoginActivity.this, "Password not match", Toast.LENGTH_SHORT);
      toast.setGravity(Gravity.CENTER, 0, 0);
      toast.show();
    } else {
      registerWithAPIService(username, password, kingdomName);
    }
  }

  public void checkFieldsInRegisterIsNotEmpty(String username, String password, String passwordConfirm, String kingdomName) {
    if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(passwordConfirm)) {
      showToastFillAllFields();
    } else {
      checkPasswordMatch(username, password, passwordConfirm, kingdomName);
    }
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
    loginService.loginWithUser(new UserLoginDTO(username, password)).enqueue(new Callback<Token>() {
      @Override
      public void onResponse(Call<Token> call, Response<Token> response) {
        if (response.code() != 200) {
          try {
            JSONObject errorJSONAnswer = new JSONObject(response.errorBody().string());
            Toast.makeText(getApplication(), errorJSONAnswer.getString("message"), Toast.LENGTH_LONG).show();
          } catch (Exception e) {
            Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_LONG).show();
          }
          return;
        }
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
        checkNotificationStatus(preferences.getString(USERNAME, ""));
//                sendNotification(preferences.getString(USERNAME, ""));
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
      }

      @Override
      public void onFailure(Call<Kingdom> call, Throwable t) {
        Toast.makeText(LoginActivity.this, "error getting information from server", Toast.LENGTH_SHORT).show();
      }
    });
  }

  public void checkNotificationStatus(String username) {
//        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    if (preferences.getBoolean(NOTIFICATION, true)) {
      sendNotification(username);
    }
  }

  public void sendNotification(String username) {
    if (preferences.getBoolean(NOTIFICATION, true)) {
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
