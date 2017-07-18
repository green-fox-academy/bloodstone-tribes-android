package com.greenfox.tribesoflagopusandroid.fragments;

import android.view.Menu;

import com.greenfox.tribesoflagopusandroid.BuildConfig;
import com.greenfox.tribesoflagopusandroid.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by georgezsiga on 6/27/17.
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class FragmentTest {

    MainActivity mainActivity;
    BattleFragment battleFragment;
    Menu refreshMenu;
    @Before
    public void setup() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        battleFragment = new BattleFragment();
        SupportFragmentTestUtil.startFragment(battleFragment, MainActivity.class);
    }

    @Test
    public void fragmentOpens() throws Exception {
        assertNotNull(battleFragment);
    }
}
