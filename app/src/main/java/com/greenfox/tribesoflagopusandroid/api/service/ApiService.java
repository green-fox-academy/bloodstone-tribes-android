package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.BuildingDTO;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Kingdom;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Troop;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.User;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.UserRegisterDTO;
import com.greenfox.tribesoflagopusandroid.api.model.response.BuildingsResponse;
import com.greenfox.tribesoflagopusandroid.api.model.response.ResourcesResponse;
import com.greenfox.tribesoflagopusandroid.api.model.response.TroopsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by hegyi on 2017-06-22.
 */

public interface ApiService {

    @POST("/register")
    Call<User> register(@Body UserRegisterDTO userRegisterDTO);

    @GET("/kingdom/troops")
    Call<TroopsResponse> getTroops(@Header("X-tribes-token") String token);

    @GET("/kingdom/buildings")
    Call<BuildingsResponse> getBuildings(@Header("X-tribes-token") String token);

    @GET("/kingdom/buildings/{buildingId}")
    Call<Building> getCertainBuilding(@Header("X-tribes-token") String token, @Path("buildingId")int buildingId);

    @GET("/kingdom")
    Call<Kingdom> getKingdom(@Header("X-tribes-token") String token);

    @POST("/kingdom/buildings")
    Call<Building> postBuilding(@Header("X-tribes-token") String token, @Body BuildingDTO building);

    @GET("/kingdom/resources")
    Call<ResourcesResponse> getResource(@Header("X-tribes-token") String token);

    @POST("/kingdom/troops")
    Call<Troop> postTroop(@Header("X-tribes-token") String token);
}
