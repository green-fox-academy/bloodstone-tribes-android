package com.greenfox.tribesoflagopusandroid.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.TribesApplication;
import com.greenfox.tribesoflagopusandroid.adapter.TroopAdapter;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Troop;
import com.greenfox.tribesoflagopusandroid.api.model.response.TroopsResponse;
import com.greenfox.tribesoflagopusandroid.api.service.ApiService;
import com.greenfox.tribesoflagopusandroid.api.service.MockApiService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TroopsFragment extends Fragment {

    private TroopAdapter troopAdapter;
    @Inject ApiService apiService;
    List<Troop> troopArrayList;


    public TroopsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TribesApplication.app().basicComponent().inject(this);

        apiService.getTroops(1).enqueue(new Callback<TroopsResponse>() {
            @Override
            public void onResponse(Call<TroopsResponse> call, Response<TroopsResponse> response) {
                troopArrayList = response.body().getTroops();
            }

            @Override
            public void onFailure(Call<TroopsResponse> call, Throwable t) {

            }
        });

        troopAdapter = new TroopAdapter(this.getContext(), troopArrayList);

        View rootView = inflater.inflate(R.layout.fragment_troops, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.troops_listView);
        listView.setAdapter(troopAdapter);

        return rootView;
    }

}
