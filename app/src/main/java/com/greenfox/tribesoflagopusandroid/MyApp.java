package com.greenfox.tribesoflagopusandroid;

import android.app.Application;


/**
 * Created by georgezsiga on 6/18/17.
 */


public class MyApp extends Application {
    private static MyApp app;
    private BasicComponent basicComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        basicComponent = DaggerBasicComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();

    }

    public static MyApp app() {
        return app;
    }

    public BasicComponent basicComponent() {
        return basicComponent;
    }
}
