package com.greenfox.tribesoflagopusandroid;

import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static com.greenfox.tribesoflagopusandroid.MainActivity.USER_ACCESS_TOKEN;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by georgezsiga on 6/11/17.
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class LoginActivityTest {

    LoginActivity loginActivity;
    MainActivity mainActivity;

    SharedPreferences.Editor editor;

    @Before
    public void setup() {
        loginActivity = Robolectric.setupActivity(LoginActivity.class);
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void addUserToken() throws Exception {
        String token = "lasjdflksajdlkjfalkjd";
        loginActivity.addUserToken(token);
        assertEquals(token, loginActivity.preferences.getString(USER_ACCESS_TOKEN, ""));
    }

    @Test
    public void onClick() throws Exception {
        Button button = (Button) loginActivity.findViewById(R.id.login);
        EditText username = (EditText) loginActivity.findViewById(R.id.usernameText);
        button.performClick();
        assertEquals(username.getText().toString(), loginActivity.preferences.getString("Username", ""));
    }

    @Test
    public void checkFieldsEmpty() {
        Toast toast = Toast.makeText(RuntimeEnvironment.application, "Please fill in all the fields", Toast.LENGTH_SHORT);
        toast.show();
        loginActivity.checkFieldsNotEmpty(null, null);
        assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo("Please fill in all the fields");
        assertThat(ShadowToast.showedToast("Please fill in all the fields")).isTrue();
    }

}
