package com.greenfox.tribesoflagopusandroid.model.resource;

/**
 * Created by User on 2017. 06. 14..
 */

public class Food extends Resource {

    public Food() {
    }

    public Food(String type, int amount, int generation) {
        super(type, amount, generation);
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
    public int getAmount() {
        return super.getAmount();
    }

    @Override
    public void setAmount(int amount) {
        super.setAmount(amount);
    }

    @Override
    public int getGeneration() {
        return super.getGeneration();
    }

    @Override
    public void setGeneration(int generation) {
        super.setGeneration(generation);
    }
}

