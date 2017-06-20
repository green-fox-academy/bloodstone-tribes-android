package com.greenfox.tribesoflagopusandroid.api.model.response;


import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Troop;

import java.util.List;

/**
 * Created by User on 2017. 06. 15..
 */

public class TroopsResponse extends BaseResponse {

    private List<Troop> troops;

    public TroopsResponse() {
    }

    public TroopsResponse(List<Troop> troops) {
        this.troops = troops;
    }

    public List<Troop> getTroops() {
        return troops;
    }

    public void setTroops(List<Troop> troops) {
        this.troops = troops;
    }
}
