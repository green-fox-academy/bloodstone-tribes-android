package com.greenfox.tribesoflagopusandroid.api.service;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by User on 2017. 06. 15..
 */

public abstract class MockCall<T> implements Call {
    @Override
    public Response execute() throws IOException {
        return null;
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
    public Call clone() {
        return null;
    }

    @Override
    public Request request() {
        return null;
    }
}
