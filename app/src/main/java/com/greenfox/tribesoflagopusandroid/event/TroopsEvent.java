package com.greenfox.tribesoflagopusandroid.event;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Troop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017. 06. 28..
 */

public class TroopsEvent {

    List<Troop> troops;

    public TroopsEvent() {
    }

    public TroopsEvent(List<Troop> troops) {
        this.troops = troops;
    }

    public List<Troop> getTroops() {
        return troops;
    }

    public void setTroops(List<Troop> troops) {
        this.troops = troops;
    }
}


