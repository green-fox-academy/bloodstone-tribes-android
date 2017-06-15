package com.greenfox.tribesoflagopusandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;
import com.greenfox.tribesoflagopusandroid.api.model.User;
import com.greenfox.tribesoflagopusandroid.api.service.LoginService;
import com.greenfox.tribesoflagopusandroid.api.service.ServiceFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

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

        LoginService service = ServiceFactory.createMockService(LoginService.class, "https://tribes-of-lagopus.herokuapp.com/");
        service.loginWithUser("username", "password").enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
