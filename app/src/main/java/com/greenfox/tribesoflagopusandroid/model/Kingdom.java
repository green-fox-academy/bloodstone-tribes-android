package com.greenfox.tribesoflagopusandroid.model;

import com.greenfox.tribesoflagopusandroid.model.building.Building;
import com.greenfox.tribesoflagopusandroid.model.resource.Resource;

import java.util.List;

/**
 * Created by User on 2017. 06. 14..
 */

public class Kingdom {


    private long id;
    private String name;
    private long userId;
    private List<Building> buildings;
    private List<Resource> resources;
    private List<Troop> troops;
    private Location location;

    public Kingdom() {
    }

    public Kingdom(long id, String name, long userId, List<Building> buildings, List<Resource> resources, List<Troop> troops, Location location) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.buildings = buildings;
        this.resources = resources;
        this.troops = troops;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public List<Troop> getTroops() {
        return troops;
    }

    public void setTroops(List<Troop> troops) {
        this.troops = troops;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

