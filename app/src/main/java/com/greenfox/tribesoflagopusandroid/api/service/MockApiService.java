package com.greenfox.tribesoflagopusandroid.api.service;

import android.os.AsyncTask;

import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Kingdom;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Location;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Resource;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Troop;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.User;
import com.greenfox.tribesoflagopusandroid.api.model.response.BuildingsResponse;
import com.greenfox.tribesoflagopusandroid.api.model.response.ResourcesResponse;
import com.greenfox.tribesoflagopusandroid.api.model.response.TroopsResponse;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by hegyi on 2017-06-22.
 */

public class MockApiService implements ApiService {

    private long id = 1;

    private String name = "kingdomname";
    private long idOfUser = 1;
    private Building townhall = new Building(3, "townhall", 1, 10);
    ;
    private List<Building> buildings = new ArrayList<>(Arrays.asList(townhall));
    private Resource gold = new Resource("gold", 100, 1);
    private Resource food = new Resource("food", 20, 1);
    private List<Resource> resources = new ArrayList<>(Arrays.asList(gold, food));
    private List<Troop> troops = new ArrayList<>();
    private Location location = new Location(1, 1);
    private String type = "farm";
    private int level = 1;
    private int hp = 10;
    private Troop troop = new Troop(1L,1,1,1,1);

    @Override
    public Call<User> register(@Field("username") final String username, @Field("password") String password, @Field("kingdomName") String kingdomName) {
        return new MockCall<User>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new User(1, username, 1)));
            }
        };
    }

    @Override
    public Call<TroopsResponse> getTroops(@Header("X-tribes-token") String token) {
        return new MockCall<TroopsResponse>() {
            @Override
            public void enqueue(final Callback callback) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        callback.onResponse(null, Response.success(new TroopsResponse(troops)));
                    }
                }.execute();
            }
        };
    }

    @Override
    public Call<BuildingsResponse> getBuildings(@Header("X-tribes-token") String token) {
        return new MockCall<BuildingsResponse>() {
            @Override
            public void enqueue(final Callback callback) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        callback.onResponse(null, Response.success(new BuildingsResponse(buildings)));

                    }
                }.execute();
            }
        };
    }


    @Override
    public Call<Building> getCertainBuilding(@Header("X-tribes-token") String token, @Path("buildingId") int buildingId) {
        return new MockCall<Building>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new Building(1, type, level, hp)));
            }
        };
    }

    @Override
    public Call<Kingdom> getKingdom(@Header("X-tribes-token") String token) {
        return new MockCall<Kingdom>() {
            @Override
            public void enqueue(final Callback callback) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        callback.onResponse(null, Response.success(new Kingdom(id, name, idOfUser, buildings, resources, troops, location)));

                    }
                }.execute();
            }
        };
    }

    @Override
    public Call<Building> postBuilding(@Header("X-tribes-token") String token, @Field("type") final String type) {
        EventBus.getDefault().post(new BuildingsResponse(buildings));
        return new MockCall<Building>() {
            @Override
            public void enqueue(Callback callback) {
                Building building = new Building(1L, type, 1, 1);
                buildings.add(building);
                callback.onResponse(null, Response.success(building));
            }
        };
    }

    @Override
    public Call<ResourcesResponse> getResource(@Header("X-tribes-token") String token) {
        return new MockCall<ResourcesResponse>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new ResourcesResponse(resources)));
            }
        };
    }

    public Call<Troop> postTroop(@Header("X-tribes-token") String token) {
        return new MockCall<Troop>() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(null, Response.success(new Troop(1L, 1, 1, 1, 1)));
            }
        };
    }
}

