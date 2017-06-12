package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.BuildConfig;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by User on 2017. 06. 12..
 */

public class FakeInterceptor implements Interceptor {

    private final static String LOGIN_ID_1 = "{\"id\":1,\"username\":Bond,\"kingdomId\":\"1\"}";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = null;
        if(BuildConfig.DEBUG) {
            String responseString;
            final HttpUrl url = chain.request().url();
            final String query = url.query();
            final String[] parsedQuery = query.split("=");
            if(parsedQuery[0].equalsIgnoreCase("id") && parsedQuery[1].equalsIgnoreCase("1")) {
                responseString = LOGIN_ID_1;
            }
            else {
                responseString = "";
            }

            response = new Response.Builder()
                    .code(200)
                    .message(responseString)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                    .addHeader("content-type", "application/json")
                    .build();
        }
        else {
            response = chain.proceed(chain.request());
        }

        return response;
    }
}