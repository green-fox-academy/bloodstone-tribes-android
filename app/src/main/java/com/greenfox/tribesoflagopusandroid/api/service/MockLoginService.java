package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.LoginResponse;

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
    public Call<LoginResponse> loginWithUser(@Field("username") String username, @Field("password") String password) {
        return new Call<LoginResponse>() {
            @Override
            public Response<LoginResponse> execute() throws IOException {
                return null;
            }

            @Override
            public void enqueue(Callback<LoginResponse> callback) {
                callback.onResponse(null, Response.success(new LoginResponse()));
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
            public Call<LoginResponse> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
    }
}
