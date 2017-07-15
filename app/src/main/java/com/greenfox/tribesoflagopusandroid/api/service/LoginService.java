package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Token;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by User on 2017. 06. 09..
 */

public interface LoginService {

    @POST("/login")
    Call<Token> loginWithUser(@Field("username") String username, @Field("password") String password);

}

