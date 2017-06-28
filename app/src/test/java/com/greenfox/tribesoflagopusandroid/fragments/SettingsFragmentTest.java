package com.greenfox.tribesoflagopusandroid.fragments;

import com.greenfox.tribesoflagopusandroid.BuildConfig;
import com.greenfox.tribesoflagopusandroid.MainActivity;
import com.greenfox.tribesoflagopusandroid.fragments.SettingsFragment;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.greenfox.tribesoflagopusandroid.MainActivity.BACKGROUND_SYNC;
import static com.greenfox.tribesoflagopusandroid.MainActivity.NOTIFICATION;
import static com.greenfox.tribesoflagopusandroid.MainActivity.SETTINGS_FRAGMENT_SAVE;
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

    @Test
    public void turnBackGroundSyncSwitchOn() {
        SettingsFragment settingsFragment = new SettingsFragment();
        startFragment(settingsFragment);
        assertNotNull(settingsFragment);
        settingsFragment.background_sync.setChecked(false);
        settingsFragment.background_sync.performClick();
        String message = "Background sync is ON";
        assertEquals(message, settingsFragment.background_sync_status.getText());
        assertEquals(true, settingsFragment.preferences.getBoolean(BACKGROUND_SYNC, true));
    }

    @Test
    public void turnBackgroundSyncSwitchOff() {
        SettingsFragment settingsFragment = new SettingsFragment();
        startFragment(settingsFragment);
        assertNotNull(settingsFragment);
        settingsFragment.background_sync.setChecked(true);
        settingsFragment.background_sync.performClick();
        String message = "Background sync is OFF";
        assertEquals(message, settingsFragment.background_sync_status.getText());
        assertEquals(false, settingsFragment.preferences.getBoolean(BACKGROUND_SYNC, false));
    }

}

