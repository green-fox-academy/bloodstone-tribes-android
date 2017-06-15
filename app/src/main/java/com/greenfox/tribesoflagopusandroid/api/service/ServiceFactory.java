package com.greenfox.tribesoflagopusandroid.api.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 2017. 06. 12..
 */

public class ServiceFactory {



    public static <T> T createRetrofitService(Class serviceClass, String url) {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        return (T) retrofit.create(serviceClass);
    }

    public static LoginService createMockService(Class serviceClass, String url) {

        return new MockLoginService();
    }
}
