package com.greenfox.tribesoflagopusandroid;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.greenfox.tribesoflagopusandroid.fragments.MainFragment;
import com.greenfox.tribesoflagopusandroid.fragments.SettingsFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.support.v4.Shadows;

import static com.google.common.base.Predicates.equalTo;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;


/**
 * Created by User on 2017. 06. 27..
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class NavigationDrawerTest {

    MainActivity mainActivity;
    LoginActivity loginActivity;
    DrawerLayout drawerLayout;

    @Before
    public void setup() throws Exception {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        loginActivity = Robolectric.setupActivity(LoginActivity.class);
    }


    @Test
    public void switchToBuildingsTest() {
        Shadows.shadowOf(drawerLayout).getDrawerListener().onDrawerOpened(drawerLayout);
    }
}
