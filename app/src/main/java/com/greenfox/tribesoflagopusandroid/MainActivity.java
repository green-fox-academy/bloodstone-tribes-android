package com.greenfox.tribesoflagopusandroid;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        checkUsername();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.refreshing:
                Toast.makeText(MainActivity.this, "Refreshing", Toast.LENGTH_SHORT).show();
        }
        return false;
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

