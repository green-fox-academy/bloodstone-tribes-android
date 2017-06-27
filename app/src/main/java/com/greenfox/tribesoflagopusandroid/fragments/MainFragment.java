package com.greenfox.tribesoflagopusandroid.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.TribesApplication;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Resource;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Troop;
import com.greenfox.tribesoflagopusandroid.api.model.response.BuildingsResponse;
import com.greenfox.tribesoflagopusandroid.api.model.response.ResourcesResponse;
import com.greenfox.tribesoflagopusandroid.api.model.response.TroopsResponse;
import com.greenfox.tribesoflagopusandroid.api.service.ApiService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {

    @Inject
    ApiService apiService;

    List<Building> buildings;
    List<Resource> resources;
    List<Troop> troops;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        TribesApplication.app().basicComponent().inject(this);

        apiService.getResource(1).enqueue(new Callback<ResourcesResponse>() {
            @Override
            public void onResponse(Call<ResourcesResponse> call, Response<ResourcesResponse> response) {
                resources = response.body().getResources();
            }

            @Override
            public void onFailure(Call<ResourcesResponse> call, Throwable t) {

            }
        });

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ImageView goldImage = (ImageView) rootView.findViewById(R.id.gold_image);
        ImageView foodImage = (ImageView) rootView.findViewById(R.id.food_image);
        TextView gold = (TextView) rootView.findViewById(R.id.resources_gold);
        TextView food = (TextView) rootView.findViewById(R.id.resources_food);
        gold.setText(resources.get(0).getAmount() + " " + resources.get(0).getType());
        food.setText(resources.get(1).getAmount() + " " + resources.get(1).getType());
        goldImage.setImageResource(R.drawable.gold);
        foodImage.setImageResource(R.drawable.food);

        apiService.getBuildings(1).enqueue(new Callback<BuildingsResponse>() {
            @Override
            public void onResponse(Call<BuildingsResponse> call, Response<BuildingsResponse> response) {
                buildings = response.body().getBuildings();
            }

            @Override
            public void onFailure(Call<BuildingsResponse> call, Throwable t) {

            }
        });

        TextView totalBuildingNumber = (TextView) rootView.findViewById(R.id.buildings_finished);
        totalBuildingNumber.setText((buildings.size() + " finished"));

        apiService.getTroops(1).enqueue(new Callback<TroopsResponse>() {
            @Override
            public void onResponse(Call<TroopsResponse> call, Response<TroopsResponse> response) {
                troops = response.body().getTroops();
            }

            @Override
            public void onFailure(Call<TroopsResponse> call, Throwable t) {

            }
        });

        TextView totalTroopNumber = (TextView) rootView.findViewById(R.id.troops_finished);
        totalTroopNumber.setText((troops.size() + " finished"));

        return rootView;
    }
}