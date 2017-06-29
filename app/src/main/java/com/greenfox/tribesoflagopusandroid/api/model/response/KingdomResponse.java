package com.greenfox.tribesoflagopusandroid.api.model.response;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Kingdom;

/**
 * Created by georgezsiga on 6/28/17.
 */

public class KingdomResponse extends BaseResponse{

    private Kingdom kingdom;

    public KingdomResponse() {
    }

    public KingdomResponse(Kingdom kingdom) {
        this.kingdom = kingdom;
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
    }
}
