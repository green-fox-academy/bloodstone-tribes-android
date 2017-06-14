package com.greenfox.tribesoflagopusandroid.model;

/**
 * Created by User on 2017. 06. 14..
 */

public class Resource {

    private String type;
    private int amount;
    private int generation;

    public Resource() {
    }

    public Resource(String type, int amount, int generation) {
        this.type = type;
        this.amount = amount;
        this.generation = generation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }
}

