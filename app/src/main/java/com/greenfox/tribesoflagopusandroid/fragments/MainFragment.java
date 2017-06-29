package com.greenfox.tribesoflagopusandroid.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.TribesApplication;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Kingdom;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Resource;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Troop;
import com.greenfox.tribesoflagopusandroid.api.service.ApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.greenfox.tribesoflagopusandroid.MainActivity.MAIN_FRAGMENT_SAVE;

public class MainFragment extends BaseFragment {

    @Inject
    SharedPreferences preferences;

    SharedPreferences.Editor editor;
    String timestamp;

    @Inject
    ApiService apiService;

    List<Building> buildings;
    List<Resource> resources;
    List<Troop> troops;

    public MainFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TribesApplication.app().basicComponent().inject(this);
        editor = preferences.edit();

        apiService.getKingdom(1).enqueue(new Callback<Kingdom>() {
            @Override
            public void onResponse(Call<Kingdom> call, Response<Kingdom> response) {
                buildings = response.body().getBuildings();
                troops = response.body().getTroops();
                resources = response.body().getResources();
            }

            @Override
            public void onFailure(Call<Kingdom> call, Throwable t) {
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
        TextView totalBuildingNumber = (TextView) rootView.findViewById(R.id.buildings_finished);
        totalBuildingNumber.setText((buildings.size() + " finished"));
        TextView totalTroopNumber = (TextView) rootView.findViewById(R.id.troops_finished);
        totalTroopNumber.setText((troops.size() + " finished"));

        return rootView;
    }

    @Override
    public void onStop() {
        super.saveOnExit(MAIN_FRAGMENT_SAVE);
        timestamp = BaseFragment.timestamp;
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
