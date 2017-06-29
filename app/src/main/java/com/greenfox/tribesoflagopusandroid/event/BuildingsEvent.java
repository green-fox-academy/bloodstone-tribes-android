package com.greenfox.tribesoflagopusandroid.event;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017. 06. 28..
 */

public class BuildingsEvent {

    ArrayList<Building> buildings;

    public BuildingsEvent(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public List<Building> getBuildings() {
        return buildings;
    }


}
