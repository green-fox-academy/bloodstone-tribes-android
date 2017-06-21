package com.greenfox.tribesoflagopusandroid;

import android.app.Application;


/**
 * Created by georgezsiga on 6/18/17.
 */


public class TribesApplication extends Application {
    private static TribesApplication tribesApplication;
    private BasicComponent basicComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        tribesApplication = this;

        basicComponent = DaggerBasicComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();

    }

    public static TribesApplication app() {
        return tribesApplication;
    }

    public BasicComponent basicComponent() {
        return basicComponent;
    }
}
