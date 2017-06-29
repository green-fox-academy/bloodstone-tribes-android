package com.greenfox.tribesoflagopusandroid.api.model.gameobject;

/**
 * Created by georgezsiga on 6/29/17.
 */

public class Token {

    private String status;
    private String token;

    public Token() {
    }

    public Token(String status, String token) {
        this.status = status;
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }
}
