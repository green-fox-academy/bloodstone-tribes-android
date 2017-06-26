package com.greenfox.tribesoflagopusandroid.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.adapter.TroopAdapter;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Troop;

import java.util.ArrayList;

public class TroopsFragment extends Fragment {

    private TroopAdapter troopAdapter;

    public TroopsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayList<Troop> troopArrayList = new ArrayList<>();
        Troop troop = new Troop(1,1,5,5,5);
        Troop troop1 = new Troop(1,1,10,8,2);
        Troop troop2 = new Troop(1,2,20,3,7);

        troopAdapter = new TroopAdapter(this.getContext(), troopArrayList);
        troopAdapter.add(troop);
        troopAdapter.add(troop1);
        troopAdapter.add(troop2);
        troopAdapter.add(troop1);
        troopAdapter.add(troop1);
        troopAdapter.add(troop1);
        troopAdapter.add(troop1);
        troopAdapter.add(troop1);

        View rootView = inflater.inflate(R.layout.fragment_troops, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.troops_listView);
        listView.setAdapter(troopAdapter);

        return rootView;
    }

}
