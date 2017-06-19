package com.greenfox.tribesoflagopusandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.greenfox.tribesoflagopusandroid.api.model.User;
import com.greenfox.tribesoflagopusandroid.api.service.LoginService;
import com.greenfox.tribesoflagopusandroid.api.service.MockLoginService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Call;
import retrofit2.http.Field;

/**
 * Created by georgezsiga on 6/18/17.
 */

@Module
public class AppModule {

    private Context context;
    private Boolean switchLoginOrMockService = true;

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

    @Singleton @Provides
    public LoginService provideLoginService() {
        if (switchLoginOrMockService == true) {
            return new LoginService() {
                @Override
                public Call<User> loginWithUser(@Field("username") String username, @Field("password") String password) {
                    return null;
                }
            };
        }
        return new MockLoginService();
    }

    public Boolean getSwitchLoginOrMockService() {
        return switchLoginOrMockService;
    }

    public void setSwitchLoginOrMockService(Boolean switchLoginOrMockService) {
        this.switchLoginOrMockService = switchLoginOrMockService;
    }
}
