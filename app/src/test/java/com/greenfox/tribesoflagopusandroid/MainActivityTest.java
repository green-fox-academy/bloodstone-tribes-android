package com.greenfox.tribesoflagopusandroid;

import android.content.Intent;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.greenfox.tribesoflagopusandroid.MainActivity.USERNAME;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    MainActivity main;

    LoginActivity login;

    @Before
    public void setup() {
        main = Robolectric.setupActivity(MainActivity.class);
        login = Robolectric.setupActivity(LoginActivity.class);
    }

    @Test
    public void userDoesNotExistAndGoToLoginActivityTest() throws Exception {
        main.checkUsername();

        Intent expectedIntent = new Intent(login, LoginActivity.class);
        assertEquals(expectedIntent.getClass(), shadowOf(main).getNextStartedActivity().getClass());
    }

    @Test
    public void logoutButtonIsClickedIsGoingToLoginActivityTest() throws Exception {
        Button button = (Button) main.findViewById(R.id.logout);
        button.performClick();
        Intent expectedIntent = new Intent(login, LoginActivity.class);
        assertEquals(expectedIntent.getClass(), shadowOf(main).getNextStartedActivity().getClass());
    }

    @Test
    public void logoutButtonClearSharedPreferencesTest() throws Exception {
        main.editor = main.preferences.edit();

        main.editor.putString(USERNAME, "testUsername");
        main.editor.apply();

        Button button = (Button) main.findViewById(R.id.logout);
        button.performClick();

        assertThat(null, is(main.preferences.getString(USERNAME, null)));
    }

}
