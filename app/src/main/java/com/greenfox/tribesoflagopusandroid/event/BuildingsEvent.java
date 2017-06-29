package com.greenfox.tribesoflagopusandroid.event;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;

import java.util.List;

public class BuildingsEvent {

  List<Building> buildings;

    public BuildingsEvent() {
    }

    public BuildingsEvent(List<Building> buildings) {
        this.buildings = buildings;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }
}
