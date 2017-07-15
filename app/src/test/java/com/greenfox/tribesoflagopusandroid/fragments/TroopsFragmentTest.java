package com.greenfox.tribesoflagopusandroid.fragments;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.greenfox.tribesoflagopusandroid.BuildConfig;
import com.greenfox.tribesoflagopusandroid.MainActivity;
import com.greenfox.tribesoflagopusandroid.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

/**
 * Created by hegyi on 2017-06-29.
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class TroopsFragmentTest {

    MainActivity mainActivity;
    TroopsFragment troopsFragment;
    FloatingActionMenu troopsFloatingActionMenu;
    FloatingActionButton floatingActionButton;

    @Before
    public void setup() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        troopsFragment = new TroopsFragment();
        startFragment(troopsFragment);
    }

    @Test
    public void floatingActionButtonIsClickable() throws Exception {
        troopsFloatingActionMenu = (FloatingActionMenu) troopsFragment.getView().findViewById(R.id.add_troop_menu);
        floatingActionButton = (FloatingActionButton) troopsFragment.getView().findViewById(R.id.add_troop_menu_item);
        assertTrue(floatingActionButton.isClickable());
    }

}
