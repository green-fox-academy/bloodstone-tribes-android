package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.User;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

/**
 * Created by User on 2017. 06. 12..
 */

public class MockLoginService implements LoginService {

    @Override
    public Call<User> loginWithUser(@Field("username") String username, @Field("password") String password) {
        return new Call<User>() {
            @Override
            public Response<User> execute() throws IOException {
                return null;
            }

            @Override
            public void enqueue(Callback<User> callback) {
                callback.onResponse(null, Response.success(new User()));
            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<User> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
    }
}

