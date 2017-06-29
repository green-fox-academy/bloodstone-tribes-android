package com.greenfox.tribesoflagopusandroid.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.greenfox.tribesoflagopusandroid.event.BuildingsEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.greenfox.tribesoflagopusandroid.MainActivity.BUILDINGS_FRAGMENT_SAVE;

public class BuildingsFragment extends BaseFragment {

    @Inject
    SharedPreferences preferences;

    SharedPreferences.Editor editor;
    @Inject
    ApiService apiService;

    private BuildingsAdapter buildingsAdapter;
    String timestamp;

    public BuildingsFragment() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Buildings");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TribesApplication.app().basicComponent().inject(this);
        editor = preferences.edit();

        buildingsAdapter = new BuildingsAdapter(getContext(), new ArrayList<Building>());
        apiService.getBuildings(1).enqueue(new Callback<BuildingsEvent>() {
            @Override
            public void onResponse(Call<BuildingsEvent> call, Response<BuildingsEvent> response) {
                buildingsAdapter.addAll(response.body().getBuildings());
            }

            @Override
            public void onFailure(Call<BuildingsEvent> call, Throwable t) {
            }
        });

        View rootView = inflater.inflate(R.layout.fragment_buildings, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.buildings_list);
        listView.setAdapter(buildingsAdapter);

        return rootView;
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.saveOnExit(BUILDINGS_FRAGMENT_SAVE);
        timestamp = BaseFragment.timestamp;
        super.onStop();
    }
}
