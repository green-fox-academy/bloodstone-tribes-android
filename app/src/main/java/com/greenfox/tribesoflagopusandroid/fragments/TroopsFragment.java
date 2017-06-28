package com.greenfox.tribesoflagopusandroid.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TroopsFragment extends Fragment {

    private TroopAdapter troopAdapter;
    @Inject ApiService apiService;

    public TroopsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TribesApplication.app().basicComponent().inject(this);

        troopAdapter = new TroopAdapter(getContext(), new ArrayList<Troop>());
        apiService.getTroops(1).enqueue(new Callback<TroopsResponse>() {
            @Override
            public void onResponse(Call<TroopsResponse> call, Response<TroopsResponse> response) {
                troopAdapter.addAll(response.body().getTroops());
            }

            @Override
            public void onFailure(Call<TroopsResponse> call, Throwable t) {

            }
        });

        View rootView = inflater.inflate(R.layout.fragment_troops, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.troops_listView);
        listView.setAdapter(troopAdapter);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Troops");
    }
}
