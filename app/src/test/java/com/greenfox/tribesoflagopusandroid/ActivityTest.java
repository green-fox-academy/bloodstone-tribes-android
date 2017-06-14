package com.greenfox.tribesoflagopusandroid;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

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
    public void wildButtonTest() throws Exception {
        Button button = (Button) activity.findViewById(R.id.button);
        TextView result = (TextView) activity.findViewById(R.id.textView2);
        EditText edit = (EditText) activity.findViewById(R.id.editText);
        button.performClick();
        assertEquals(result.getText().toString(), edit.getText().toString());
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
}
