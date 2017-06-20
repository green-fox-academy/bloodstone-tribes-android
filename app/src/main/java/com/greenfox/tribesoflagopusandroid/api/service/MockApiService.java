package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Kingdom;
import com.greenfox.tribesoflagopusandroid.api.model.response.BuildingsResponse;

import retrofit2.Call;
import retrofit2.http.Path;

/**
 * Created by User on 2017. 06. 20..
 */

public class MockApiService implements ApiService {

    @Override
    public Call<BuildingsResponse> getBuildings(@Path("userId") int userId) {
        return null;
    }

    @Override
    public Call<Building> getCertainBuilding(@Path("userId") int userId, @Path("buildingId") int buildingId) {
        return null;
    }

    @Override
    public Call<Kingdom> getKingdom(@Path("userId") int userId) {
        return null;
    }
}
