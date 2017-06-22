package com.greenfox.tribesoflagopusandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.User;
import com.greenfox.tribesoflagopusandroid.api.service.LoginService;
import com.greenfox.tribesoflagopusandroid.api.service.ServiceFactory;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.greenfox.tribesoflagopusandroid.MainActivity.PASSWORD;
import static com.greenfox.tribesoflagopusandroid.MainActivity.USERNAME;


public class LoginActivity extends AppCompatActivity {

    @Inject SharedPreferences preferences;
    @Inject ObjectManager objectManager;
    @Inject LoginService loginService;

    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TribesApplication.app().basicComponent().inject(this);
        editor = preferences.edit();
//        getSupportActionBar().hide();
    }

    public void login(View view) {
        addUserInfoToPreferences();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        LoginService service = ServiceFactory.createMockService();
        service.loginWithUser("username", "password").enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void addUserInfoToPreferences() {
        addUsername();
        addPassword();
    }

    protected void addUsername() {
        EditText editText = (EditText) findViewById(R.id.usernameText);
        String username = editText.getText().toString();
        editor.putString(USERNAME, username);
        editor.apply();
    }

    protected void addPassword() {
        EditText editText = (EditText) findViewById(R.id.passwordText);
        String password = editText.getText().toString();
        editor.putString(PASSWORD, password);
        editor.apply();
    }
}
