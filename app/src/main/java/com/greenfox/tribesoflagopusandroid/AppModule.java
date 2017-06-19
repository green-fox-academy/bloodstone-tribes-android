package com.greenfox.tribesoflagopusandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by georgezsiga on 6/18/17.
 */

@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public Context provideContext(){
        return context;
    }

    @Singleton @Provides
    public SharedPreferences provideSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Singleton @Provides
    public Gson provideGson(){
        return new Gson();
    }

    @Singleton @Provides
    public ObjectManager provideObjectManager(SharedPreferences sharedPreferences, Gson gson){
        return new ObjectManager(sharedPreferences, gson);
    }
}
