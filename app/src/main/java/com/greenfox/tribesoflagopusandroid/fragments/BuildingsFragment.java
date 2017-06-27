package com.greenfox.tribesoflagopusandroid.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.TribesApplication;
import com.greenfox.tribesoflagopusandroid.adapter.BuildingsAdapter;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;
import com.greenfox.tribesoflagopusandroid.api.model.response.BuildingsResponse;
import com.greenfox.tribesoflagopusandroid.api.service.ApiService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuildingsFragment extends Fragment {

    @Inject
    ApiService apiService;

    private BuildingsAdapter buildingsAdapter;
    List<Building> buildings;

    public BuildingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TribesApplication.app().basicComponent().inject(this);



        apiService.getBuildings(1).enqueue(new Callback<BuildingsResponse>() {
            @Override
            public void onResponse(Call<BuildingsResponse> call, Response<BuildingsResponse> response) {
                buildings = response.body().getBuildings();
            }

            @Override
            public void onFailure(Call<BuildingsResponse> call, Throwable t) {

            }
        });

        buildingsAdapter = new BuildingsAdapter(this.getContext(), buildings);

        View rootView = inflater.inflate(R.layout.fragment_troops, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.troops_listView);
        listView.setAdapter(buildingsAdapter);

        return rootView;
    }
}
