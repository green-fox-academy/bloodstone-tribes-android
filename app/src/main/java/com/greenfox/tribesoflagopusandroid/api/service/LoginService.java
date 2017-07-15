package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Token;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.UserLoginDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by User on 2017. 06. 09..
 */

public interface LoginService {

    @POST("/login")
    Call<Token> loginWithUser(@Body UserLoginDTO user);

}

