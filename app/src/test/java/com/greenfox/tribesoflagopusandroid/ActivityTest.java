package com.greenfox.tribesoflagopusandroid;

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

@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class ActivityTest {

    MainActivity activity;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void wildButtonTest() throws Exception {

        Button button = (Button) activity.findViewById(R.id.button);
        TextView result = (TextView) activity.findViewById(R.id.textView2);
        EditText edit = (EditText) activity.findViewById(R.id.editText);
        button.performClick();
        assertEquals(result.getText().toString(), edit.getText().toString());
    }
}

