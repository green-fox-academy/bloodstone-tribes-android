package com.greenfox.tribesoflagopusandroid.api.model.response;


import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;

import java.util.List;


public class BuildingsResponse extends BaseResponse {

    private List<Building> buildings;

    public BuildingsResponse() {
    }

    public BuildingsResponse(List<Building> buildings) {
        this.buildings = buildings;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }
}
