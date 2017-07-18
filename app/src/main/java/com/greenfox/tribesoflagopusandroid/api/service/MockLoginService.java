package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Token;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.UserLoginDTO;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

/**
 * Created by User on 2017. 06. 12..
 */

public class MockLoginService implements LoginService {

    String status = "ok";
    String token = "lksdjflksjdglkajldksgjslkdfhglksjdf";

    @Override
    public MockCall<Token> loginWithUser(@Body UserLoginDTO user) {
        return new MockCall<Token>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new Token(status, token)));
            }
        };
    }
}

