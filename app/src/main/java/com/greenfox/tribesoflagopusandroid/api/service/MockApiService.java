package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Kingdom;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Location;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Resource;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Troop;
import com.greenfox.tribesoflagopusandroid.api.model.response.BuildingsResponse;
import com.greenfox.tribesoflagopusandroid.api.model.response.ResourcesResponse;
import com.greenfox.tribesoflagopusandroid.api.model.response.TroopsResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.Path;
/**
 * Created by hegyi on 2017-06-22.
 */

public class MockApiService implements ApiService{

    private long id = 1;

    private String name = "kingdomname";
    private long idOfUser = 1;
    private Building farm1 = new Building(1, "farm", 1, 2);
    private Building mine1 = new Building(2, "mine", 1, 2);
    private Building townhall = new Building(3, "townhall", 1, 10);
    private List<Building> buildings = new ArrayList<>(Arrays.asList(farm1, mine1, townhall));
    private Resource gold = new Resource("gold", 100, 1);
    private Resource food = new Resource("food", 20, 1);
    private List<Resource> resources = new ArrayList<>(Arrays.asList(gold, food));
    private Troop troop1 = new Troop(1, 1, 10, 1, 2);
    private Troop troop2 = new Troop(2, 1, 10, 2, 2);
    private List<Troop> troops = new ArrayList<>(Arrays.asList(troop1, troop2));
    private Location location = new Location(1, 1);
    private String type = "farm";
    private int level = 1;
    private int hp = 10;

    @Override
    public Call<TroopsResponse> getTroops() {
        return new MockCall<TroopsResponse>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new TroopsResponse(troops)));
            }
        };
    }

    @Override
    public Call<BuildingsResponse> getBuildings() {
        return new MockCall<BuildingsResponse>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new BuildingsResponse(buildings)));
            }
        };
    }

    @Override
    public Call<Building> getCertainBuilding(@Path("buildingId") int buildingId) {
        return new MockCall<Building>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new Building(1, type, level, hp)));
            }
        };
    }

    @Override
    public Call<Kingdom> getKingdom() {
        return new MockCall<Kingdom>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new Kingdom(id, name, idOfUser, buildings, resources, troops, location)));
            }
        };
    }

    @Override
    public Call<Building> postBuilding(@Field("type") final String type) {
        return new MockCall<Building>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new Building(1L, type, 1, 1)));
            }
        };
    }

    @Override
    public Call<ResourcesResponse> getResource() {
        return new MockCall<ResourcesResponse>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new ResourcesResponse(resources)));
            }
        };
    }
}
