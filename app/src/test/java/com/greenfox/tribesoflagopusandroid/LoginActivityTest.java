package com.greenfox.tribesoflagopusandroid;

import android.content.Intent;
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

import static com.greenfox.tribesoflagopusandroid.MainActivity.USERNAME;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by georgezsiga on 6/11/17.
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class LoginActivityTest {

    LoginActivity loginActivity;
    MainActivity mainActivity;

    @Before
    public void setup() {
        loginActivity = Robolectric.setupActivity(LoginActivity.class);
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void addUsername() throws Exception {
        String username = "Username";
        loginActivity.addUsername(username);
        assertEquals(username, loginActivity.preferences.getString(USERNAME, ""));
    }

    @Test
    public void onClick() throws Exception {
        Button button = (Button) loginActivity.findViewById(R.id.button2);
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

    @Test
    public void checkLoginWithMockService() {
        String username = "Username";
        String password = "Password";
        loginActivity.loginWithAPIService(username, password);
        assertEquals(username, loginActivity.preferences.getString(USERNAME, ""));
        Intent expectedIntent = new Intent(loginActivity, MainActivity.class);
        assertEquals(expectedIntent.getClass(), shadowOf(loginActivity).getNextStartedActivity().getClass());
    }
}
