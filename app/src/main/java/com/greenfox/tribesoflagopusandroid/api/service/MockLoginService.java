package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.User;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

/**
 * Created by User on 2017. 06. 12..
 */

public class MockLoginService implements LoginService {

    @Override
    public MockCall loginWithUser(@Field("username") String username, @Field("password") String password) {
        return new MockCall() {

            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new User()));
            }
        };
    }
}

