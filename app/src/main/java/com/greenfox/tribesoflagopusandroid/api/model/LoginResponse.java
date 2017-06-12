package com.greenfox.tribesoflagopusandroid.api.model;

/**
 * Created by User on 2017. 06. 12..
 */

public class LoginResponse {

    private long id;
    private String username;
    private long kingdomId;
    private String status;
    private String message;

    public LoginResponse() {
    }

    public LoginResponse(long id, String username, long kingdomId) {
        this.id = id;
        this.username = username;
        this.kingdomId = kingdomId;
    }

    public LoginResponse(String status, String message) {
        this.status = status;
        this.message = message;
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

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
