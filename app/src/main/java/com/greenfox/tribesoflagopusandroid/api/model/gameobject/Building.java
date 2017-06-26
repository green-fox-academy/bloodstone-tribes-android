package com.greenfox.tribesoflagopusandroid.api.model.gameobject;

import com.greenfox.tribesoflagopusandroid.api.model.response.BaseResponse;

/**
 * Created by User on 2017. 06. 14..
 */

public class Building extends BaseResponse{

    private long id;
    private String type;
    private int level;
    private int hp;

    public Building() {
    }

    public Building(long id, String type, int level, int hp) {
        this.id = id;
        this.type = type;
        this.level = level;
        this.hp = hp;
    }

    public Building(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

