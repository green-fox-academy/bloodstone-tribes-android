package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.response.TroopsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hegyi on 2017-06-22.
 */

public interface ApiService {

    @GET("/{userId}/kingdom/troops")
    Call<TroopsResponse> getTroops(@Path("userId") int userId);
}
