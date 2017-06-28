package com.greenfox.tribesoflagopusandroid.fragments;

import com.greenfox.tribesoflagopusandroid.BuildConfig;
import com.greenfox.tribesoflagopusandroid.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.greenfox.tribesoflagopusandroid.MainActivity.BATTLE_FRAGMENT_SAVE;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

/**
 * Created by georgezsiga on 6/27/17.
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class FragmentTest {

    MainActivity mainActivity;
    @Before
    public void setup() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void saveFragmentStatusOnExit() {
        BattleFragment battleFragment  = new BattleFragment();
        startFragment(battleFragment);
        assertNotNull(battleFragment);
        battleFragment.onStop();
        assertEquals(battleFragment.timestamp, mainActivity.preferences.getString(BATTLE_FRAGMENT_SAVE, ""));
    }
}
