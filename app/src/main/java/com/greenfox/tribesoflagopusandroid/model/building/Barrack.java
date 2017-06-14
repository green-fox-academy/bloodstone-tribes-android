package com.greenfox.tribesoflagopusandroid.model.building;

/**
 * Created by User on 2017. 06. 14..
 */

public class Barrack extends Building {

    public Barrack() {
    }

    public Barrack(long id, String type, int level, int hp) {
        super(id, type, level, hp);
    }

    @Override
    public String getType() {
        return super.getType();
    }

    @Override
    public void setType(String type) {
        super.setType(type);
    }

    @Override
    public int getLevel() {
        return super.getLevel();
    }

    @Override
    public void setLevel(int level) {
        super.setLevel(level);
    }

    @Override
    public int getHp() {
        return super.getHp();
    }

    @Override
    public void setHp(int hp) {
        super.setHp(hp);
    }

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public void setId(long id) {
        super.setId(id);
    }
}

