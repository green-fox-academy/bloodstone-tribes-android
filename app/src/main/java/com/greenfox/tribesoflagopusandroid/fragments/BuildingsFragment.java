package com.greenfox.tribesoflagopusandroid.fragments;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.greenfox.tribesoflagopusandroid.MainActivity;
import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.TribesApplication;
import com.greenfox.tribesoflagopusandroid.adapter.BuildingsAdapter;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.BuildingDTO;
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
  @Inject
  ApiService apiService;

  SharedPreferences.Editor editor;
  String timestamp;
  private BuildingsAdapter buildingsAdapter;
  FloatingActionMenu buildingsFloatingMenu;
  FloatingActionButton addFarmFloatingButton, addMineFloatingButton, addBarrackFloatingButton;
  ListView listView;

  public BuildingsFragment() {
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getActivity().setTitle("Buildings");
  }

  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    TribesApplication.app().basicComponent().inject(this);
    editor = preferences.edit();
    View rootView = inflater.inflate(R.layout.fragment_buildings, container, false);
    buildingsAdapter = new BuildingsAdapter(getContext(), new ArrayList<Building>());
    listView = (ListView) rootView.findViewById(R.id.buildings_list);
    refreshActiveFragment();

    buildingsFloatingMenu = (FloatingActionMenu) rootView.findViewById(R.id.add_building_menu);

    addFarmFloatingButton = (FloatingActionButton) rootView.findViewById(R.id.add_farm_menu_item);
    addFarmFloatingButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        apiService.postBuilding(preferences.getString(USER_ACCESS_TOKEN, ""), new BuildingDTO("farm")).enqueue(new Callback<Building>() {
          @Override
          public void onResponse(Call<Building> call, Response<Building> response) {
            sendNotification("farm");
            refreshActiveFragment();
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
      public void onClick(View v) {
        apiService.postBuilding(preferences.getString(USER_ACCESS_TOKEN, ""), new BuildingDTO("mine")).enqueue(new Callback<Building>() {
          @Override
          public void onResponse(Call<Building> call, Response<Building> response) {
            sendNotification("mine");
            refreshActiveFragment();
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
        apiService.postBuilding(preferences.getString(USER_ACCESS_TOKEN, ""), new BuildingDTO("barrack")).enqueue(new Callback<Building>() {
          @Override
          public void onResponse(Call<Building> call, Response<Building> response) {
            sendNotification("barrack");
            refreshActiveFragment();
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
  public void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  public void getBuildingsFromAPI() {
    apiService.getBuildings(preferences.getString(USER_ACCESS_TOKEN, "")).enqueue(new Callback<BuildingsResponse>() {
      @Override
      public void onResponse(Call<BuildingsResponse> call, Response<BuildingsResponse> response) {
        EventBus.getDefault().post(new BuildingsEvent(response.body().getBuildings()));
        buildingsAdapter.clear();
        buildingsAdapter.addAll(response.body().getBuildings());
        listView.setAdapter(buildingsAdapter);
        if (loadingViewListener != null) {
          loadingViewListener.loadingFinished();
        }
      }

      @Override
      public void onFailure(Call<BuildingsResponse> call, Throwable t) {
      }
    });
  }

  @Override
  public void refreshActiveFragment() {
    getBuildingsFromAPI();
    super.refreshActiveFragment();
  }

  @Override
  public void onStop() {
    super.saveOnExit(BUILDINGS_FRAGMENT_SAVE);
    timestamp = BaseFragment.timestamp;
    super.onStop();
    EventBus.getDefault().unregister(this);
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onEventBuildingAdded(BuildingsEvent event) {
    buildingsAdapter.addAll(event.getBuildings());
  }

  public void sendNotification(String building) {
    NotificationCompat.Builder mBuilder =
            new NotificationCompat.Builder(getActivity())
                    .setSmallIcon(R.drawable.tribes)
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.tribesbig))
                    .setContentTitle("New " + building + " has started!")
                    .setContentText("Your workers has started to work on your new " + building + ".");
    Intent resultIntent = new Intent(getContext(), MainActivity.class);
    TaskStackBuilder stackBuilder = TaskStackBuilder.create(getContext());
    stackBuilder.addParentStack(MainActivity.class);
    stackBuilder.addNextIntent(resultIntent);
    PendingIntent resultPendingIntent =
            stackBuilder.getPendingIntent(
                    0,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
    mBuilder.setContentIntent(resultPendingIntent).setAutoCancel(true);

    NotificationManager mNotificationManager =
            (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
    mNotificationManager.notify(001, mBuilder.build());
  }
}
