package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.User;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

/**
 * Created by User on 2017. 06. 12..
 */

public class MockLoginService implements LoginService {

    @Override
    public MockCall<User> loginWithUser(@Field("username") String username, @Field("password") String password) {
        return new MockCall<User>() {

            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new User()));
            }
        };
    }
}

