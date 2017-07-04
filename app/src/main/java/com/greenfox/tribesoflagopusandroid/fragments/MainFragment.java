package com.greenfox.tribesoflagopusandroid.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenfox.tribesoflagopusandroid.MainActivity;
import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.TribesApplication;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Kingdom;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Resource;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Troop;
import com.greenfox.tribesoflagopusandroid.api.service.ApiService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.greenfox.tribesoflagopusandroid.MainActivity.MAIN_FRAGMENT_SAVE;
import static com.greenfox.tribesoflagopusandroid.MainActivity.USER_ACCESS_TOKEN;

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

    View rootView;

    public MainFragment() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Kingdom");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TribesApplication.app().basicComponent().inject(this);
        editor = preferences.edit();
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        refreshActiveFragment();

        Button buildingButton = (Button) rootView.findViewById(R.id.go_to_buildings_btn);
        buildingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).activeFragment = new BuildingsFragment();
                (getActivity()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.layout_content, ((MainActivity)getActivity()).activeFragment)
                        .commit();
            }
        });

        Button troopButton = (Button) rootView.findViewById(R.id.go_to_troops_btn);
        troopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).activeFragment = new TroopsFragment();
                (getActivity()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.layout_content, ((MainActivity)getActivity()).activeFragment)
                        .commit();
            }
        });
        return rootView;
    }

    public void getKingdomFromAPI() {
        apiService.getKingdom(preferences.getString(USER_ACCESS_TOKEN, "")).enqueue(new Callback<Kingdom>() {
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
    }

    public void fillResources() {
        ImageView goldImage = (ImageView) rootView.findViewById(R.id.gold_image);
        ImageView foodImage = (ImageView) rootView.findViewById(R.id.food_image);
        TextView gold = (TextView) rootView.findViewById(R.id.resources_gold);
        TextView food = (TextView) rootView.findViewById(R.id.resources_food);
        gold.setText(resources.get(0).getAmount() + " " + resources.get(0).getType());
        food.setText(resources.get(1).getAmount() + " " + resources.get(1).getType());
        goldImage.setImageResource(R.drawable.gold);
        foodImage.setImageResource(R.drawable.food);
    }

    public void fillBuildings() {
        TextView totalBuildingNumber = (TextView) rootView.findViewById(R.id.buildings_finished);
        totalBuildingNumber.setText((buildings.size() + " finished"));
    }

    public void fillTroops() {
        TextView totalTroopNumber = (TextView) rootView.findViewById(R.id.troops_finished);
        totalTroopNumber.setText((troops.size() + " finished"));
    }

    @Override
    public void refreshActiveFragment() {
        getKingdomFromAPI();
        fillResources();
        fillBuildings();
        fillTroops();
        super.refreshActiveFragment();
    }

    @Override
    public void onStop() {
        super.saveOnExit(MAIN_FRAGMENT_SAVE);
        timestamp = BaseFragment.timestamp;
        super.onStop();
    }
}
