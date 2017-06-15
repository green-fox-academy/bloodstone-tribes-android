package com.greenfox.tribesoflagopusandroid.api.model.response;

/**
 * Created by User on 2017. 06. 15..
 */

public class ResourceResponse extends BaseResponse {

    private String type;
    private int amount;
    private int generation;

    public ResourceResponse() {
    }

    public ResourceResponse(String type, int amount, int generation) {
        this.type = type;
        this.amount = amount;
        this.generation = generation;
    }
}
