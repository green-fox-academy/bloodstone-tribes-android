package com.greenfox.tribesoflagopusandroid.api.model.response;

import com.greenfox.tribesoflagopusandroid.model.Building;

import java.util.List;

/**
 * Created by User on 2017. 06. 15..
 */

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
