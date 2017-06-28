package com.greenfox.tribesoflagopusandroid;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.greenfox.tribesoflagopusandroid.MainActivity.USERNAME;
import static org.hamcrest.core.Is.is;

@RunWith(AndroidJUnit4.class)
public class NavDrawerTest {

    MainActivity mainActivity;
    DrawerLayout drawerLayout;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void setup() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mainActivityActivityTestRule.getActivity());
        preferences.edit().putString(USERNAME, "tesUser").commit();
    }

    @Test
    public void testNavDrawerOpens() throws Exception {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open()).check(matches(isDisplayed()));
    }

    @Test
    public void testSwitchViewFromSettingsToBuildings() throws Exception {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.navigation_view)).perform(NavigationViewActions.navigateTo(R.id.nav_settings));
        Thread.sleep(500);
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.navigation_view)).perform(NavigationViewActions.navigateTo(R.id.nav_buildings));
        Thread.sleep(500);
        assertThat(mainActivityActivityTestRule.getActivity().getTitle().toString(), is("Buildings"));
    }

    @Test
    public void testOpenTroopsFragment() throws Exception {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.navigation_view)).perform(NavigationViewActions.navigateTo(R.id.nav_troops));
        Thread.sleep(500);
        assertThat(mainActivityActivityTestRule.getActivity().getTitle().toString(), is("Troops"));
    }

    @Test
    public void testLogout() throws Exception {
        mainActivityActivityTestRule.getActivity().editor.putString(USERNAME, "testUsername");
        mainActivityActivityTestRule.getActivity().editor.commit();
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.navigation_view)).perform(NavigationViewActions.navigateTo(R.id.nav_logout));
        assertThat(null, is(mainActivityActivityTestRule.getActivity().preferences.getString(USERNAME, null)));
        Thread.sleep(2000);
    }

    @Test
    public void testLogoutRedirectsToLoginActivity() throws Exception {
        mainActivityActivityTestRule.getActivity().editor.putString(USERNAME, "testUsername");
        mainActivityActivityTestRule.getActivity().editor.commit();
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.navigation_view)).perform(NavigationViewActions.navigateTo(R.id.nav_logout));
        onView(withId(R.id.usernameText)).check(matches(isDisplayed()));
        onView(withId(R.id.passwordText)).check(matches(isDisplayed()));
    }
}

