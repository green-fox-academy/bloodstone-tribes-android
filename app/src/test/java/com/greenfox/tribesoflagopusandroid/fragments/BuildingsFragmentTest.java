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
public class BuildingsFragmentTest {

    MainActivity mainActivity;
    BuildingsFragment buildingsFragment;
    FloatingActionMenu buildingFloatingActionMenu;
    FloatingActionButton floatingActionButtonFarm;

    @Before
    public void setup() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        buildingsFragment = new BuildingsFragment();
        startFragment(buildingsFragment);
    }

    @Test
    public void troopsFragmentOpens() throws Exception {
        assertNotNull(buildingsFragment);
    }

    @Test
    public void floatingActionButtonIsClickable() throws Exception {
        buildingFloatingActionMenu = (FloatingActionMenu) buildingsFragment.getView().findViewById(R.id.add_building_menu);
        floatingActionButtonFarm = (FloatingActionButton) buildingsFragment.getView().findViewById(R.id.add_farm_menu_item);
        assertTrue(floatingActionButtonFarm.isClickable());
    }

}
