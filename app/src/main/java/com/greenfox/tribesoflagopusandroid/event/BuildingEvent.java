package com.greenfox.tribesoflagopusandroid.event;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;

import java.util.ArrayList;

/**
 * Created by User on 2017. 06. 28..
 */

public class BuildingEvent {

    ArrayList<Building> buildings;

    public BuildingEvent(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }
}
