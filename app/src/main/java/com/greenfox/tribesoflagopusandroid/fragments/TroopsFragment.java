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
        troopAdapter = new TroopAdapter(this.getContext());
        ListView listView = (ListView) getView().findViewById(R.id.troops_listView);
        listView.setAdapter(troopAdapter);
        return inflater.inflate(R.layout.fragment_troops, container, false);
    }

}
