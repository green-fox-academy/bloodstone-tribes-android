package com.greenfox.tribesoflagopusandroid.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.adapter.ResourceAdapter;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Resource;
import com.greenfox.tribesoflagopusandroid.api.model.response.ResourcesResponse;
import com.greenfox.tribesoflagopusandroid.api.service.MockApiService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {

    @Inject
    MockApiService mockApiService;

    ResourceAdapter resourceAdapter;
    ResourcesResponse response1;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mockApiService.getResource(1).enqueue(new Callback<ResourcesResponse>() {

            @Override
            public void onResponse(Call<ResourcesResponse> call, Response<ResourcesResponse> response) {
                response1 = response.body();
            }

            @Override
            public void onFailure(Call<ResourcesResponse> call, Throwable t) {

            }
        });
        resourceAdapter = new ResourceAdapter(this.getContext(), response1.getResources());

        View rootview = inflater.inflate(R.layout.fragment_main, container, false);

        TextView textView = (TextView) rootview.findViewById(R.id.resources_food);
        textView.setTag(resourceAdapter);

        return rootview;
    }

}
