package com.greenfox.tribesoflagopusandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.greenfox.tribesoflagopusandroid.api.model.User;
import com.greenfox.tribesoflagopusandroid.api.service.LoginService;
import com.greenfox.tribesoflagopusandroid.api.service.ServiceFactory;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.greenfox.tribesoflagopusandroid.MainActivity.PASSWORD;
import static com.greenfox.tribesoflagopusandroid.MainActivity.USERNAME;


public class LoginActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Inject ObjectManager objectManager;
    @Inject LoginService loginService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MyApp.app().basicComponent().inject(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
    }

    public void login(View view) {
        checkFieldsNotEmpty();
    }

    private void checkFieldsNotEmpty() {
        EditText editText = (EditText) findViewById(R.id.editText3);
        String username = editText.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        String password = editText2.getText().toString();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast toast = Toast.makeText(LoginActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            LoginService service = ServiceFactory.createRetrofitService(LoginService.class, "https://tribes-of-lagopus.herokuapp.com/");
            service.loginWithUser("username", "password").enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();
                    addUserInfoToPreferences(user.getUsername(), user.getPassword());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "error getting login information from server", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    protected void addUserInfoToPreferences(String username, String password) {
        addUsername(username);
        addPassword(password);
    }

    protected void addUsername(String username) {
//        EditText editText = (EditText) findViewById(R.id.editText3);
//        String username = editText.getText().toString();
        editor.putString(USERNAME, username);
        editor.apply();
    }

    protected void addPassword(String password) {
//        EditText editText = (EditText) findViewById(R.id.editText2);
//        String password = editText.getText().toString();
        editor.putString(PASSWORD, password);
        editor.apply();
    }
}
