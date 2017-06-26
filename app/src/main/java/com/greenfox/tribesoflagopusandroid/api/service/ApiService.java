package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Kingdom;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Resource;
import com.greenfox.tribesoflagopusandroid.api.model.response.BuildingsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * Created by hegyi on 2017-06-22.
 */

public interface ApiService {

    @GET("/{userId}/kingdom/buildings")
    Call<BuildingsResponse> getBuildings(@Path("userId") int userId);

    @GET("/{userId}/kingdom/buildings/{buildingId}")
    Call<Building> getCertainBuilding(@Path("userId") int userId, @Path("buildingId")int buildingId);

    @GET("/{userId}/kingdom")
    Call<Kingdom> getKingdom(@Path("userId") int userId);

    @POST("/{userId}/kingdom/buildings")
    Call<Building> postBuilding (@Field("type") String type);

    @GET("/{userId}/kingdom/resources")
    Call<Resource> getResource (@Path("userId") int userId);

}
