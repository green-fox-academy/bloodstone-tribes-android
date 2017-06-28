package com.greenfox.tribesoflagopusandroid.api.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 2017. 06. 12..
 */

public class ServiceFactory {

private static final String SERVER_URL = "https://tribes-of-lagopus.herokuapp.com";

    public static <T> T createRetrofitService() {

        Class serviceClass = LoginService.class;

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        return (T) retrofit.create(serviceClass);
    }

    public static LoginService createMockService() {
        return new MockLoginService();
    }

    public static <T> T createRetrofitApiService() {

        Class serviceClass = ApiService.class;
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        return (T) retrofit.create(serviceClass);
    }

    public static ApiService createMockApiService() {
        return new MockApiService();
    }
}

