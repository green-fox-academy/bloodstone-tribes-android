package com.greenfox.tribesoflagopusandroid.fragments;

import com.greenfox.tribesoflagopusandroid.BuildConfig;
import com.greenfox.tribesoflagopusandroid.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.greenfox.tribesoflagopusandroid.MainActivity.BUILDINGS_FRAGMENT_SAVE;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

/**
 * Created by georgezsiga on 6/27/17.
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class BuildingsFragmentTest {

    MainActivity mainActivity;
    @Before
    public void setup() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void saveStatusOnExit() {
        BuildingsFragment buildingsFragment = new BuildingsFragment();
        startFragment(buildingsFragment);
        assertNotNull(buildingsFragment);
        buildingsFragment.onStop();
        assertEquals(buildingsFragment.timestamp, mainActivity.preferences.getString(BUILDINGS_FRAGMENT_SAVE, ""));
    }
}