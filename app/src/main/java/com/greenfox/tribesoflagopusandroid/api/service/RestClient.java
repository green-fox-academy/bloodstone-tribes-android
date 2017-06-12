package com.greenfox.tribesoflagopusandroid.api.service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 2017. 06. 12..
 */

public final class RestClient {
    private static IRestService mRestService = null;

    public static IRestService getClient() {
        if (mRestService == null) {
            final OkHttpClient client = new OkHttpClient();
            client.interceptors().add(new FakeInterceptor());

            final Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    // Endpoint
                    .baseUrl(IRestService.ENDPOINT)
                    .client(client)
                    .build();

            mRestService = retrofit.create(IRestService.class);
        }
        return mRestService;
    }
}