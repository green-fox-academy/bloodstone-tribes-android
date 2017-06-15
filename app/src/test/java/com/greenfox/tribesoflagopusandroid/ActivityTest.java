package com.greenfox.tribesoflagopusandroid;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowHandler;
import org.robolectric.shadows.ShadowToast;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class ActivityTest {

    MainActivity activity;

    LoginActivity login;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(MainActivity.class);
        login = Robolectric.setupActivity(LoginActivity.class);
    }

    @Test

    public void userIsNotExistAndGoToLoginActivityActivityTest() throws Exception {
        activity.checkUsername();

        Intent expectedIntent = new Intent(login, LoginActivity.class);
        assertEquals(expectedIntent.getClass(), shadowOf(activity).getNextStartedActivity().getClass());
    }

    @Test
    public void userIsExistAndStayInMainActivityTest() throws Exception {
        login.editor.putString("Username", "test").apply();
        activity.checkUsername();

        Intent expectedIntent = new Intent(activity, MainActivity.class);
        assertEquals(expectedIntent.getClass(), shadowOf(activity).getNextStartedActivity().getClass());
    }

    @Test
    public void shouldHaveShortDuration() throws Exception {
        Toast toast = Toast.makeText(RuntimeEnvironment.application, "Refreshing", Toast.LENGTH_SHORT);
        assertThat(toast).isNotNull();
        assertThat(toast.getDuration()).isEqualTo(Toast.LENGTH_SHORT);
    }

    @Test
    public void shouldMakeTextCorrectly() throws Exception {
        Toast toast = Toast.makeText(RuntimeEnvironment.application, "Refreshing", Toast.LENGTH_SHORT);
        assertThat(toast).isNotNull();
        assertThat(toast.getDuration()).isEqualTo(Toast.LENGTH_SHORT);
        toast.show();
        assertThat(ShadowToast.getLatestToast()).isSameAs(toast);
        assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo("Refreshing");
        assertThat(ShadowToast.showedToast("Refreshing")).isTrue();
    }

    @Test
    public void shouldSetTextCorrectly() throws Exception {
        Toast toast = Toast.makeText(RuntimeEnvironment.application, "Refreshing", Toast.LENGTH_SHORT);
        toast.setText("Refreshing");
        toast.show();
        assertThat(ShadowToast.getLatestToast()).isSameAs(toast);
        assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo("Refreshing");
        assertThat(ShadowToast.showedToast("Refreshing")).isTrue();
    }

    @Test
    public void shouldSetTextWithIdCorrectly() throws Exception {
        Toast toast = Toast.makeText(RuntimeEnvironment.application, "Refreshing", Toast.LENGTH_SHORT);
        toast.setText(R.string.refreshing);
        toast.show();
        assertThat(ShadowToast.getLatestToast()).isSameAs(toast);
        assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo("Refreshing");
        assertThat(ShadowToast.showedToast("Refreshing")).isTrue();
    }

    @Test
    public void shouldSetViewCorrectly() throws Exception {
        Toast toast = new Toast(RuntimeEnvironment.application);
        toast.setDuration(Toast.LENGTH_SHORT);
        final View view = new TextView(RuntimeEnvironment.application);
        toast.setView(view);
        assertThat(toast.getView()).isSameAs(view);
    }
}

