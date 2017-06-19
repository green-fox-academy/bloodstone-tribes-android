package com.greenfox.tribesoflagopusandroid.api.model.gameobject;

import com.greenfox.tribesoflagopusandroid.api.model.response.BaseResponse;

/**
 * Created by User on 2017. 06. 14..
 */

public class Troop extends BaseResponse {

    private long id;
    private int level;
    private int hp;
    private int attack;
    private int defense;

    public Troop() {
    }

    public Troop(long id, int level, int hp, int attack, int defense) {
        this.id = id;
        this.level = level;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}

