package com.greenfox.tribesoflagopusandroid;

import com.greenfox.tribesoflagopusandroid.fragments.SettingsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by georgezsiga on 6/18/17.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface BasicComponent {
    void inject(LoginActivity activity);
    void inject(MainActivity activity);
    void inject(SettingsFragment fragment);
}
