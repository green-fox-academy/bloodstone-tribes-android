package com.greenfox.tribesoflagopusandroid.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.TribesApplication;
import com.greenfox.tribesoflagopusandroid.adapter.BuildingsAdapter;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;
import com.greenfox.tribesoflagopusandroid.api.model.response.BuildingsResponse;
import com.greenfox.tribesoflagopusandroid.api.service.ApiService;
import com.greenfox.tribesoflagopusandroid.event.BuildingsEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.greenfox.tribesoflagopusandroid.MainActivity.BUILDINGS_FRAGMENT_SAVE;
import static com.greenfox.tribesoflagopusandroid.MainActivity.USER_ACCESS_TOKEN;

public class BuildingsFragment extends BaseFragment {

    @Inject
    SharedPreferences preferences;

    SharedPreferences.Editor editor;
    @Inject
    ApiService apiService;

    private final String createdBuilding = "Building created";

    private BuildingsAdapter buildingsAdapter;
    String timestamp;

    FloatingActionMenu buildingsFloatingMenu;
    FloatingActionButton addFarmFloatingButton, addMineFloatingButton, addBarrackFloatingButton;

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
        apiService.getBuildings(preferences.getString(USER_ACCESS_TOKEN, "")).enqueue(new Callback<BuildingsResponse>() {
            @Override
            public void onResponse(Call<BuildingsResponse> call, Response<BuildingsResponse> response) {
                EventBus.getDefault().post(new BuildingsEvent(response.body().getBuildings()));
            }

            @Override
            public void onFailure(Call<BuildingsResponse> call, Throwable t) {
            }
        });

        View rootView = inflater.inflate(R.layout.fragment_buildings, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.buildings_list);
        listView.setAdapter(buildingsAdapter);

        buildingsFloatingMenu = (FloatingActionMenu) rootView.findViewById(R.id.add_building_menu);

        addFarmFloatingButton = (FloatingActionButton) rootView.findViewById(R.id.add_farm_menu_item);
        addFarmFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Farm added", Toast.LENGTH_SHORT).show();
                apiService.postBuilding(preferences.getString(USER_ACCESS_TOKEN, ""), "farm").enqueue(new Callback<Building>() {
                    @Override
                    public void onResponse(Call<Building> call, Response<Building> response) {
                        apiService.addBuildingToList(response.body());
                        refresh();
                    }

                    @Override
                    public void onFailure(Call<Building> call, Throwable t) {

                    }
                });
            }
        });

        addMineFloatingButton = (FloatingActionButton) rootView.findViewById(R.id.add_mine_menu_item);
        addMineFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Toast.makeText(getContext(),"Mine added", Toast.LENGTH_SHORT).show();
                apiService.postBuilding(preferences.getString(USER_ACCESS_TOKEN, ""), "mine").enqueue(new Callback<Building>() {
                    @Override
                    public void onResponse(Call<Building> call, Response<Building> response) {
                        apiService.addBuildingToList(response.body());
                        refresh();
                    }

                    @Override
                    public void onFailure(Call<Building> call, Throwable t) {

                    }
                });
            }
        });

        addBarrackFloatingButton = (FloatingActionButton) rootView.findViewById(R.id.add_barrack_menu_item);
        addBarrackFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Barrack added", Toast.LENGTH_SHORT).show();
                apiService.postBuilding(preferences.getString(USER_ACCESS_TOKEN, ""), "barrack").enqueue(new Callback<Building>() {
                    @Override
                    public void onResponse(Call<Building> call, Response<Building> response) {
                        apiService.addBuildingToList(response.body());
                        refresh();
                    }

                    @Override
                    public void onFailure(Call<Building> call, Throwable t) {

                    }
                });
            }
        });
        return rootView;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.saveOnExit(BUILDINGS_FRAGMENT_SAVE);
        timestamp = BaseFragment.timestamp;
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBuildingAdded(BuildingsEvent event) {
        buildingsAdapter.addAll(event.getBuildings());
    }

    public void refresh() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.detach(this);
        transaction.attach(this);
        transaction.commit();
    }
}
