package com.greenfox.tribesoflagopusandroid.api.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.api.adapter.BuildingsAdapter;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;

import java.util.ArrayList;

public class BuildingsFragment extends Fragment {

    private BuildingsAdapter buildingsAdapter;

    public BuildingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayList<Building> buildings = new ArrayList<>();
        Building townhall = new Building(1, "townhall", 1, 10);
        Building farm1 = new Building(2, "farm", 1, 2);
        Building farm2 = new Building(3, "farm", 1, 2);
        Building mine1 = new Building(4, "mine", 1, 2);
        Building mine2 = new Building(5, "mine", 1, 2);
        Building barrack1 = new Building(6, "barrack", 1, 5);
        Building barrack2 = new Building(7, "barrack", 1, 5);

        buildingsAdapter = new BuildingsAdapter(this.getContext(), buildings);
        buildingsAdapter.add(farm1);
        buildingsAdapter.add(farm2);
        buildingsAdapter.add(townhall);
        buildingsAdapter.add(mine1);
        buildingsAdapter.add(mine2);
        buildingsAdapter.add(barrack1);
        buildingsAdapter.add(barrack2);

        View rootView = inflater.inflate(R.layout.fragment_buildings, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.buildings_listView);
        listView.setAdapter(buildingsAdapter);

        return rootView;
    }
}