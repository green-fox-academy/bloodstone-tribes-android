package com.greenfox.tribesoflagopusandroid.api.model.gameobject;

import com.greenfox.tribesoflagopusandroid.api.model.response.BaseResponse;

/**
 * Created by User on 2017. 06. 09..
 */

public class User extends BaseResponse{

    private long id;
    private String username;
    private long kingdomId;

    public User() {
    }

    public User(long id, String username, long kingdomId) {
        this.id = id;
        this.username = username;
        this.kingdomId = kingdomId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
