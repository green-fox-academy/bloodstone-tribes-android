package com.greenfox.tribesoflagopusandroid;

import com.greenfox.tribesoflagopusandroid.fragments.BaseFragment;
import com.greenfox.tribesoflagopusandroid.fragments.BuildingsFragment;
import com.greenfox.tribesoflagopusandroid.fragments.MainFragment;
import com.greenfox.tribesoflagopusandroid.fragments.SettingsFragment;
import com.greenfox.tribesoflagopusandroid.fragments.TroopsFragment;

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
    void inject(MainFragment fragment);
    void inject(BuildingsFragment fragment);
    void inject(TroopsFragment fragment);
    void inject(BaseFragment fragment);
}
