package com.greenfox.tribesoflagopusandroid.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.adapter.BuildingsAdapter;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;

import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

public class BuildingsFragment extends Fragment {

    private BuildingsAdapter buildingsAdapter;

    FloatingActionMenu buildingsFloatingMenu;
    FloatingActionButton addFarmFloatingButton, addMineFloatingButton;

    public BuildingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayList<Building> buildings = new ArrayList<>();
        Building townhall = new Building(1, "townhall", 1, 10);
        Building barrack = new Building(6, "barrack", 1, 5);

        buildingsAdapter = new BuildingsAdapter(this.getContext(), buildings);
        buildingsAdapter.add(townhall);
        buildingsAdapter.add(barrack);

        View rootView = inflater.inflate(R.layout.fragment_buildings, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.buildings_list);
        listView.setAdapter(buildingsAdapter);

        buildingsFloatingMenu = (FloatingActionMenu) rootView.findViewById(R.id.add_building_menu);
        addFarmFloatingButton = (FloatingActionButton) rootView.findViewById(R.id.add_farm_menu_item);
        addFarmFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Farm added", Toast.LENGTH_SHORT).show();
                buildingsAdapter.add(new Building(1, "farm", 1, 1));
            }
        });

        addMineFloatingButton = (FloatingActionButton) rootView.findViewById(R.id.add_mine_menu_item);
        addMineFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Mine added", Toast.LENGTH_SHORT).show();
                buildingsAdapter.add(new Building(1, "mine", 1, 1));
            }
        });
        return rootView;
    }
}
