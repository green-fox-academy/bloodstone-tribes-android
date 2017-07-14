package com.greenfox.tribesoflagopusandroid;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greenfox.tribesoflagopusandroid.fragments.BattleFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private static final int MP = ViewGroup.LayoutParams.MATCH_PARENT;

    MainActivity main;
    LoginActivity login;
    DrawerLayout drawerLayout;

    @Before
    public void setup() {
        main = Robolectric.setupActivity(MainActivity.class);
        login = Robolectric.setupActivity(LoginActivity.class);
        drawerLayout = new DrawerLayout(RuntimeEnvironment.application);
        drawerLayout.addView(
                new TextView(RuntimeEnvironment.application),
                0,
                new DrawerLayout.LayoutParams(MP, MP, GravityCompat.START));
    }

    @Test
    public void userDoesNotExistAndGoToLoginActivityTest() throws Exception {
        main.checkUserAccessToken();

        Intent expectedIntent = new Intent(login, LoginActivity.class);
        assertEquals(expectedIntent.getClass(), shadowOf(main).getNextStartedActivity().getClass());
    }

    @Test
    public void testDrawerIsClosedByDefault() throws Exception {
        assertThat(drawerLayout.isDrawerOpen(GravityCompat.START), is(false));
    }

    @Test
    public void testDrawerOpens() throws Exception {
        drawerLayout.openDrawer(GravityCompat.START);
        assertThat(drawerLayout.isDrawerOpen(GravityCompat.START), is(true));
    }

    @Test
    public void testKingdomMenuClickedRedirectsToMain() throws Exception {
        drawerLayout.openDrawer(GravityCompat.START);
        main.findViewById(R.id.nav_kingdom);
        assertEquals("Kingdom", main.getTitle().toString());
    }

    @Test
    public void refreshActiveFragment() throws Exception {
        Fragment fragmentTest = new BattleFragment();
        main.fragment = fragmentTest;
        main.refreshActiveFragment();
        assertEquals(fragmentTest, main.fragment);
    }
}
