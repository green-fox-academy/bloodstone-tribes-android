package com.greenfox.tribesoflagopusandroid.fragments;

import com.greenfox.tribesoflagopusandroid.BuildConfig;
import com.greenfox.tribesoflagopusandroid.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.greenfox.tribesoflagopusandroid.MainActivity.NOTIFICATION;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

/**
 * Created by georgezsiga on 6/23/17.
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class SettingsFragmentTest extends android.app.Activity {

    MainActivity mainActivity;
    @Before
    public void setup() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void turnNotificationSwitchOn() {
        SettingsFragment settingsFragment = new SettingsFragment();
        startFragment(settingsFragment);
        assertNotNull(settingsFragment);
        settingsFragment.notification.setChecked(false);
        settingsFragment.notification.performClick();
        String message = "You will receive game notifications";
        assertEquals(message, settingsFragment.notification_status.getText());
        assertEquals(true, settingsFragment.preferences.getBoolean(NOTIFICATION, true));
    }

    @Test
    public void turnNotificationSwitchOff() {
        SettingsFragment settingsFragment = new SettingsFragment();
        startFragment(settingsFragment);
        assertNotNull(settingsFragment);
        settingsFragment.notification.setChecked(true);
        settingsFragment.notification.performClick();
        String message = "You don`t receive game notifications";
        assertEquals(message, settingsFragment.notification_status.getText());
        assertEquals(false, settingsFragment.preferences.getBoolean(NOTIFICATION, false));
    }

}

