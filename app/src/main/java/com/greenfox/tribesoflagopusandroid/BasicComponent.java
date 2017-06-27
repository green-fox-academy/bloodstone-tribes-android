package com.greenfox.tribesoflagopusandroid;

import com.greenfox.tribesoflagopusandroid.AppModule;
import com.greenfox.tribesoflagopusandroid.MainActivity;
import com.greenfox.tribesoflagopusandroid.fragments.BuildingsFragment;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;

/**
 * Created by georgezsiga on 6/18/17.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface BasicComponent {
    void inject(LoginActivity activity);
    void inject(MainActivity activity);
    void inject(SettingsFragment fragment);
    void inject(BuildingsFragment fragment);
}
