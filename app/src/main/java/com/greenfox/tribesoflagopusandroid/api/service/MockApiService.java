package com.greenfox.tribesoflagopusandroid.api.service;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Troop;
import com.greenfox.tribesoflagopusandroid.api.model.response.BuildingsResponse;
import com.greenfox.tribesoflagopusandroid.api.model.response.ResourcesResponse;
import com.greenfox.tribesoflagopusandroid.api.model.response.TroopsResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Kingdom;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Location;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Resource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;
import retrofit2.http.Field;
/**
 * Created by hegyi on 2017-06-22.
 */

public class MockApiService implements ApiService{

    private long id = 1;

    private String name = "kingdomname";
    private long idOfUser = 1;
    private Building townhall = new Building(3, "townhall", 1, 10);
    private List<Building> buildings = new ArrayList<>(Arrays.asList(townhall));
    private Resource gold = new Resource("gold", 100, 1);
    private Resource food = new Resource("food", 20, 1);
    private List<Resource> resources = new ArrayList<>(Arrays.asList(gold, food));
    private List<Troop> troops = new ArrayList<>();
    private Location location = new Location(1, 1);
    private String type = "farm";
    private int level = 1;
    private int hp = 10;

    @Override
    public Call<TroopsResponse> getTroops(@Path("userId") int userId) {
        return new MockCall<TroopsResponse>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new TroopsResponse(troops)));
            }
        };
    }

    @Override
    public Call<BuildingsResponse> getBuildings(@Path("userId") int userId) {
        return new MockCall<BuildingsResponse>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new BuildingsResponse(buildings)));
            }
        };
    }

    @Override
    public Call<Building> getCertainBuilding(@Path("userId") int userId, @Path("buildingId") int buildingId) {
        return new MockCall<Building>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new Building(1, type, level, hp)));
            }
        };
    }

    @Override
    public Call<Kingdom> getKingdom(@Path("userId") final int userId) {
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
    public Call<ResourcesResponse> getResource(@Path("userId") int userId) {
        return new MockCall<ResourcesResponse>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new ResourcesResponse(resources)));
            }
        };
    }

    @Override
    public void addBuildingToList(Building building) {
        buildings.add(building);
    }

    public Call<Troop> postTroop(@Path("userId") int userId) {
        return new MockCall<Troop>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new Troop(1L,1,1,1,1)));
            }
        };
    }

    public void addTroopToMockTroops(Troop troop) {
        troops.add(troop);
    }

}
