package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Kingdom;
import com.greenfox.tribesoflagopusandroid.api.model.response.BuildingsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/{userId}/kingdom/buildings")
    Call<BuildingsResponse> getBuildings(@Path("userId") int userId);

    @GET("/{userId}/kingdom/buildings/{buildingId}")
    Call<Building> getCertainBuilding(@Path("userId") int userId, @Path("buildingId")int buildingId);

    @GET("/{userId}/kingdom")
    Call<Kingdom> getKingdom(@Path("userId") int userId);

}
