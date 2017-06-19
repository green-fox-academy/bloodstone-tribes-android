package com.greenfox.tribesoflagopusandroid;

import com.greenfox.tribesoflagopusandroid.AppModule;
import com.greenfox.tribesoflagopusandroid.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by georgezsiga on 6/18/17.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface BasicComponent {
    void inject(LoginActivity activity);
}
