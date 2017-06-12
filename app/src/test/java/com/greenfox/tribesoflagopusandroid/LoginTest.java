package com.greenfox.tribesoflagopusandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by georgezsiga on 6/11/17.
 */
public class LoginTest {

    Login login;


    @Test
    public void addUsername() throws Exception {
        Button button = (Button) login.findViewById(R.id.button2);
        EditText username = (EditText) login.findViewById(R.id.editText3);
        button.performClick();
        assertEquals(username, login.preferences.getString("Username", ""));
    }

    @Test
    public void addPassword() throws Exception {

    }

}