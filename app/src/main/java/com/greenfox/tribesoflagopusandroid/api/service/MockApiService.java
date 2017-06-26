package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Troop;
import com.greenfox.tribesoflagopusandroid.api.model.response.TroopsResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;
import retrofit2.http.Field;
/**
 * Created by hegyi on 2017-06-22.
 */

public class MockApiService implements ApiService{

    private Troop troop1 = new Troop(1, 1, 10, 1, 2);
    private Troop troop2 = new Troop(2, 1, 10, 2, 2);
    private List<Troop> troops = new ArrayList<>(Arrays.asList(troop1, troop2));

    @Override
    public Call<TroopsResponse> getTroops(@Path("userId") int userId) {
        return new MockCall<TroopsResponse>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new TroopsResponse(troops)));
            }
        };
    }

    @Override
    public Call<Building> postBuilding(@Field("type") final String type) {
        return new MockCall<Building>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new Building(1L, type, 1, 1)));
            }
        };
    }

}
