package com.greenfox.tribesoflagopusandroid.event;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Troop;

import java.util.ArrayList;

/**
 * Created by User on 2017. 06. 28..
 */

public class TroopsEvent {

    ArrayList<Troop> troops;

    public TroopsEvent(ArrayList<Troop> troops) {
        this.troops = troops;
    }
}


