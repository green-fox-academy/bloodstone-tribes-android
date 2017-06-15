package com.greenfox.tribesoflagopusandroid.api.model.response;

/**
 * Created by User on 2017. 06. 12..
 */

public class LoginResponse extends BaseResponse {

    private long id;
    private String username;
    private long kingdomId;

    public LoginResponse() {
    }

    public LoginResponse(long id, String username, long kingdomId) {
        this.id = id;
        this.username = username;
        this.kingdomId = kingdomId;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public long getKingdomId() {
        return kingdomId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setKingdomId(long kingdomId) {
        this.kingdomId = kingdomId;
    }
}

