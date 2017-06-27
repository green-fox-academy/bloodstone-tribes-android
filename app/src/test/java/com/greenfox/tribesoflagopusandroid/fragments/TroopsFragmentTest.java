package com.greenfox.tribesoflagopusandroid.fragments;

import com.greenfox.tribesoflagopusandroid.BuildConfig;
import com.greenfox.tribesoflagopusandroid.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.greenfox.tribesoflagopusandroid.MainActivity.TROOPS_FRAGMENT_SAVE;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

/**
 * Created by georgezsiga on 6/27/17.
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class TroopsFragmentTest {
    MainActivity mainActivity;
    @Before
    public void setup() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void saveStatusOnExit() {
        TroopsFragment troopsFragment = new TroopsFragment();
        startFragment(troopsFragment);
        assertNotNull(troopsFragment);
        troopsFragment.onStop();
        assertEquals(troopsFragment.timestamp, mainActivity.preferences.getString(TROOPS_FRAGMENT_SAVE, ""));
    }
}