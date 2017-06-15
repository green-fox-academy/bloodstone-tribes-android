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

import static com.greenfox.tribesoflagopusandroid.MainActivity.PASSWORD;
import static com.greenfox.tribesoflagopusandroid.MainActivity.USERNAME;
import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    MainActivity main;

    LoginActivity login;

    @Before
    public void setup() {
        main = Robolectric.setupActivity(MainActivity.class);
        login = Robolectric.setupActivity(LoginActivity.class);
    }

    @Test
    public void wildButtonTest() throws Exception {
        Button button = (Button) main.findViewById(R.id.button);
        TextView result = (TextView) main.findViewById(R.id.textView2);
        EditText edit = (EditText) main.findViewById(R.id.editText);
        button.performClick();
        assertEquals(result.getText().toString(), edit.getText().toString());
    }

    @Test
    public void userDoesNotExistAndGoToLoginActivityTest() throws Exception {
        main.checkUsernameAndPassword();

        Intent expectedIntent = new Intent(login, LoginActivity.class);
        assertEquals(expectedIntent.getClass(), shadowOf(main).getNextStartedActivity().getClass());

    }
}
