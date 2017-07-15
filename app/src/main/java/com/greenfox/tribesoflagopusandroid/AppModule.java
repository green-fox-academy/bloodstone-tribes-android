package com.greenfox.tribesoflagopusandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.greenfox.tribesoflagopusandroid.api.service.ApiService;
import com.greenfox.tribesoflagopusandroid.api.service.LoginService;
import com.greenfox.tribesoflagopusandroid.api.service.ServiceFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by georgezsiga on 6/18/17.
 */

@Module
public class AppModule {

    private Context context;
    private static final Boolean APISERVICE_IS_ACTIVE = false;
    ServiceFactory serviceFactory;

    public AppModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public Context provideContext(){
        return context;
    }

    @Singleton
    @Provides
    public SharedPreferences provideSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Singleton
    @Provides
    public Gson provideGson(){
        return new Gson();
    }

    @Singleton
    @Provides
    public ObjectManager provideObjectManager(SharedPreferences sharedPreferences, Gson gson){
        return new ObjectManager(sharedPreferences, gson);
    }

    @Singleton
    @Provides
    public LoginService provideLoginService() {
        if (APISERVICE_IS_ACTIVE) {
            return serviceFactory.createRetrofitService();
        }
        return serviceFactory.createMockLoginService();
    }

    @Singleton
    @Provides
    public ApiService provideApiService() {
        if (APISERVICE_IS_ACTIVE) {
            return serviceFactory.createRetrofitApiService();
        }
        return serviceFactory.createMockApiService();
    }

}
