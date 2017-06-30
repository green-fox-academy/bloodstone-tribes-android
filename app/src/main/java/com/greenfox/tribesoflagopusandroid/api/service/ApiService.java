package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Kingdom;
import com.greenfox.tribesoflagopusandroid.api.model.response.BuildingsResponse;
import com.greenfox.tribesoflagopusandroid.api.model.response.ResourcesResponse;
import com.greenfox.tribesoflagopusandroid.api.model.response.TroopsResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by hegyi on 2017-06-22.
 */

public interface ApiService {

    @GET("/kingdom/troops")
    Call<TroopsResponse> getTroops();

    @GET("/kingdom/buildings")
    Call<BuildingsResponse> getBuildings();

    @GET("/kingdom/buildings/{buildingId}")
    Call<Building> getCertainBuilding(@Path("buildingId")int buildingId);

    @GET("/kingdom")
    Call<Kingdom> getKingdom(@Header("X-tribes-token") String token);

    @POST("/kingdom/buildings")
    Call<Building> postBuilding(@Field("type") String type);

    @GET("/kingdom/resources")
    Call<ResourcesResponse> getResource();

}
