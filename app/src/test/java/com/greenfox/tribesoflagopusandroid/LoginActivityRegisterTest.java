package com.greenfox.tribesoflagopusandroid;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by hegyi on 2017-07-05.
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class LoginActivityRegisterTest {

    LoginActivity loginActivity;

    @Before
    public void setup() {
        loginActivity = Robolectric.setupActivity(LoginActivity.class);
    }

    @Test
    public void registerButtonClickTest() throws Exception {
        Button registerButton = (Button) loginActivity.findViewById(R.id.register);
        registerButton.performClick();

        assertThat(loginActivity.registerLayout.getVisibility(), equalTo(View.VISIBLE));
        assertThat(loginActivity.loginLayout.getVisibility(), equalTo(View.INVISIBLE));
    }

    @Test
    public void backButtonGoBackToLoginTest() throws Exception {
        Button backButton = (Button) loginActivity.findViewById(R.id.back_to_login);
        backButton.performClick();

        assertThat(loginActivity.registerLayout.getVisibility(), equalTo(View.INVISIBLE));
        assertThat(loginActivity.loginLayout.getVisibility(), equalTo(View.VISIBLE));
    }

    @Test
    public void checkAllFieldsIsEmptyTest() throws Exception {
        Button registerButton = (Button) loginActivity.findViewById(R.id.send_register);
        registerButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast(), equalTo("Please fill in all the fields"));
    }

    @Test
    public void checkPasswordsDoNotMatchTest() throws Exception {
        String password = "test";
        String confirmPassword = "not matching";
        String username = "test";

        EditText registerName = (EditText) loginActivity.findViewById(R.id.register_name);
        registerName.setText(username);
        EditText passwordInput = (EditText) loginActivity.findViewById(R.id.register_password);
        passwordInput.setText(password);
        EditText confirmPasswordInput = (EditText) loginActivity.findViewById(R.id.register_password_confirm);
        confirmPasswordInput.setText(confirmPassword);
        Button registerButton = (Button) loginActivity.findViewById(R.id.send_register);
        registerButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast(), equalTo("Password not match"));
    }

    @Test
    public void registrationSuccessfulAndGoToLoginLayoutTest() throws Exception {
        String password = "test";
        String confirmPassword = "test";
        String username = "test";

        EditText registerName = (EditText) loginActivity.findViewById(R.id.register_name);
        registerName.setText(username);
        EditText passwordInput = (EditText) loginActivity.findViewById(R.id.register_password);
        passwordInput.setText(password);
        EditText confirmPasswordInput = (EditText) loginActivity.findViewById(R.id.register_password_confirm);
        confirmPasswordInput.setText(confirmPassword);
        Button registerButton = (Button) loginActivity.findViewById(R.id.send_register);
        registerButton.performClick();

        assertThat(loginActivity.registerLayout.getVisibility(), equalTo(View.INVISIBLE));
        assertThat(loginActivity.loginLayout.getVisibility(), equalTo(View.VISIBLE));
    }

    @Test
    public void usernameFilledOnLoginLayoutIfRegistrationSuccessTest() throws Exception {
        String password = "test";
        String confirmPassword = "test";
        String username = "test";

        EditText registerName = (EditText) loginActivity.findViewById(R.id.register_name);
        registerName.setText(username);
        EditText passwordInput = (EditText) loginActivity.findViewById(R.id.register_password);
        passwordInput.setText(password);
        EditText confirmPasswordInput = (EditText) loginActivity.findViewById(R.id.register_password_confirm);
        confirmPasswordInput.setText(confirmPassword);
        Button registerButton = (Button) loginActivity.findViewById(R.id.send_register);

        registerButton.performClick();

        String loginUserToShow;
        loginUserToShow = loginActivity.preferences.getString("RegisterName", null);

        assertEquals(username, loginUserToShow);
        assertThat(loginActivity.loginLayout.getVisibility(), equalTo(View.VISIBLE));
    }

}
