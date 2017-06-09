package greenfox.com.tribesoflagopus;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static android.R.id.message;
import static greenfox.com.tribesoflagopus.MainActivity.EXTRA_MESSAGE;
import static junit.framework.Assert.assertEquals;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by hegyi on 2017-06-08.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class RobolectricTest {

    @Test
    public void wildButtonTest() throws Exception {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);

        Button button = (Button)activity.findViewById(R.id.button);
        TextView result = (TextView)activity.findViewById(R.id.textView2);
        EditText edit = (EditText)activity.findViewById(R.id.editText);

        button.performClick();
        assertEquals(result.getText().toString(), edit.getText().toString());
    }

    @Test
    public void clickButtonTest() throws Exception {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);

        EditText edit = (EditText)activity.findViewById(R.id.editText);

        activity.findViewById(R.id.button).performClick();

        Intent expectedIntent = new Intent(activity, MainActivity.class);
        String message = edit.getText().toString();

        expectedIntent.putExtra(EXTRA_MESSAGE, message);

        assertThat(shadowOf(activity).getNextStartedActivity()).isEqualTo(expectedIntent);
    }
}