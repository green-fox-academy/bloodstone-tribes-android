package com.greenfox.tribesoflagopusandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.greenfox.tribesoflagopusandroid.api.model.User;
import com.greenfox.tribesoflagopusandroid.api.service.LoginService;
import com.greenfox.tribesoflagopusandroid.api.service.ServiceFactory;

import javax.inject.Inject;

import dagger.Provides;
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
    AppModule appModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TribesApplication.app().basicComponent().inject(this);
        appModule.provideSharedPreferences(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
    }

    public void login(View view) {
        addUserInfoToPreferences();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        LoginService service = ServiceFactory.createMockService(LoginService.class, "https://tribes-of-lagopus.herokuapp.com/");
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
        EditText editText = (EditText) findViewById(R.id.editText3);
        String username = editText.getText().toString();
        editor.putString(USERNAME, username);
        editor.apply();
    }

    protected void addPassword() {
        EditText editText = (EditText) findViewById(R.id.editText2);
        String password = editText.getText().toString();
        editor.putString(PASSWORD, password);
        editor.apply();
    }
}
