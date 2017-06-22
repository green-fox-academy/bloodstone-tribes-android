package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

/**
 * Created by hegyi on 2017-06-22.
 */

public class MockApiService implements ApiService {

    @Override
    public Call<Building> postBuilding(@Field("type") final String type) {
        return new MockCall<Building>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new Building(type)));
            }
        };
    }

}
