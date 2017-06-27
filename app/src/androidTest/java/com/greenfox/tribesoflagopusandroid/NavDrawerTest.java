package com.greenfox.tribesoflagopusandroid;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.DrawerLayout;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.Is.is;


@RunWith(AndroidJUnit4.class)
public class NavDrawerTest {

    MainActivity mainActivity;
    DrawerLayout drawerLayout;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testNavDrawerOpens() throws Exception {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open()).check(matches(isDisplayed()));
    }

    @Test
    public void testSwitchViewFromSettingsToBuildings() throws Exception {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.navigation_view)).perform(NavigationViewActions.navigateTo(R.id.nav_settings));
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.navigation_view)).perform(NavigationViewActions.navigateTo(R.id.nav_buildings));
        Thread.sleep(50);
        assertThat(mainActivityActivityTestRule.getActivity().getTitle().toString(), is("Buildings"));
    }

    @Test
    public void testOpenTroopsFragment() throws Exception {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.navigation_view)).perform(NavigationViewActions.navigateTo(R.id.nav_buildings));
        Thread.sleep(50);
        assertThat(mainActivityActivityTestRule.getActivity().getTitle().toString(), is("Troops"));
    }
}

