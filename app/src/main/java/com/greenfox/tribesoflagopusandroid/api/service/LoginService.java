package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by User on 2017. 06. 09..
 */

public interface LoginService {

    @GET("login")
    Call<List<User>> loginWithUser(String user);

}
