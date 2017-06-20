package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.response.BuildingsResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;

public class ApiService {

//    @FormUrlEncoded
    @GET
    BuildingsResponse getBuilding(@Body int userId) {
        return new BuildingsResponse();
    }

}
