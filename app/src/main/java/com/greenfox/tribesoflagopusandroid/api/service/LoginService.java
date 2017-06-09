package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by User on 2017. 06. 09..
 */

public interface LoginService {

    @FormUrlEncoded
    @POST("/login")
    Call<User> loginWithUser(@Field("username") String username,@Field("password") String password);

}
