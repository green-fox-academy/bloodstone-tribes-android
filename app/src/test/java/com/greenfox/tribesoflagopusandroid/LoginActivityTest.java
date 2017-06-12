package com.greenfox.tribesoflagopusandroid;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by georgezsiga on 6/11/17.
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class LoginActivityTest {

    LoginActivity login;
    MainActivity main;

    @Before
    public void setup() {
        login = Robolectric.setupActivity(LoginActivity.class);
        main = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void addUsername() throws Exception {
        EditText username = (EditText) login.findViewById(R.id.editText3);
        login.addUsername();
        assertEquals(username.getText().toString(), login.preferences.getString("Username", ""));
    }

    @Test
    public void addPassword() throws Exception {
        EditText password = (EditText) login.findViewById(R.id.editText2);
        login.addPassword();
        assertEquals(password.getText().toString(), login.preferences.getString("Password", ""));
    }

    @Test
    public void onClick() throws Exception {
        Button button = (Button) login.findViewById(R.id.button2);
        EditText username = (EditText) login.findViewById(R.id.editText3);
        EditText password = (EditText) login.findViewById(R.id.editText2);
        button.performClick();
        assertEquals(username.getText().toString(), login.preferences.getString("Username", ""));
        assertEquals(password.getText().toString(), login.preferences.getString("Password", ""));
    }

    @Test
    public void startMainActivity() {
        login.findViewById(R.id.button2).performClick();
        Intent expectedIntent = new Intent(login, MainActivity.class);
        assertEquals(expectedIntent.getClass(), shadowOf(login).getNextStartedActivity().getClass());
    }
}