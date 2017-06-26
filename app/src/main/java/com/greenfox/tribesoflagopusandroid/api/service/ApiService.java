package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * Created by hegyi on 2017-06-22.
 */

public interface ApiService {

    @POST("/{userId}/kingdom/buildings")
    Call<Building> postBuilding (@Field("type") String type);

}
