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
import android.widget.Toast;

import com.greenfox.tribesoflagopusandroid.api.model.User;
import com.greenfox.tribesoflagopusandroid.api.service.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "greenfox.com.tribesoflagopus.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://tribes-of-lagopus.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        LoginService service = retrofit.create(LoginService.class);
        Call<User> call = service.loginWithUser("username", "password");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });

        checkUsername();

    }

    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText(message);
    }

    public void checkUsername() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String username = preferences.getString("Username", null);

        if (TextUtils.isEmpty(username)) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

}
