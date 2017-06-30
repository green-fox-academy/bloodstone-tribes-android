package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Token;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

/**
 * Created by User on 2017. 06. 12..
 */

public class MockLoginService implements LoginService {

    String status = "ok";
    String token = "lksdjflksjdglkajldksgjslkdfhglksjdf";

    @Override
    public MockCall<Token> loginWithUser(@Field("username") final String username, @Field("password") final String password) {
        return new MockCall<Token>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new Token(status, token)));
            }
        };
    }
}

