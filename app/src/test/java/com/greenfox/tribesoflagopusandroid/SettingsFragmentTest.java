package com.greenfox.tribesoflagopusandroid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.greenfox.tribesoflagopusandroid.MainActivity.BACKGROUND_SYNC;
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

    @Test
    public void turnNotificationSwitchOn() {
        SettingsFragment settingsFragment = new SettingsFragment();
        startFragment(settingsFragment);
        assertNotNull(settingsFragment);
        settingsFragment.notification.setChecked(false);
        settingsFragment.notification.performClick();
        String message = "You will receive game notifications";
        assertEquals(message, settingsFragment.notification_status.getText());
        assertEquals("true", settingsFragment.preferences.getString(NOTIFICATION, ""));
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
        assertEquals("false", settingsFragment.preferences.getString(NOTIFICATION, ""));
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
        assertEquals("true", settingsFragment.preferences.getString(BACKGROUND_SYNC, ""));
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
        assertEquals("false", settingsFragment.preferences.getString(BACKGROUND_SYNC, ""));
    }
}