package com.greenfox.tribesoflagopusandroid;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by georgezsiga on 6/11/17.
 */
public class LoginTest {


    @Test
    public void addUsername() throws Exception {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        String username = "Test";
        String message = preferences.getString("Username", "");
        assertEquals(username, message);
    }

    @Test
    public void addPassword() throws Exception {

    }

}