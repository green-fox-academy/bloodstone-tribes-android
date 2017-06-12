package com.greenfox.tribesoflagopusandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * Created by georgezsiga on 6/11/17.
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class LoginTest {

    Login login;

    @Before
    public void setup() {
        login = Robolectric.setupActivity(Login.class);
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

}