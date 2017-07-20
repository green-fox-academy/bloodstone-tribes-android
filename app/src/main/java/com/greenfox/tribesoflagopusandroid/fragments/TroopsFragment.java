package com.greenfox.tribesoflagopusandroid.fragments;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.greenfox.tribesoflagopusandroid.MainActivity;
import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.TribesApplication;
import com.greenfox.tribesoflagopusandroid.adapter.BuildingsAdapter;
import com.greenfox.tribesoflagopusandroid.adapter.TroopAdapter;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.BuildingDTO;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Kingdom;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Resource;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Troop;
import com.greenfox.tribesoflagopusandroid.api.model.response.TroopsResponse;
import com.greenfox.tribesoflagopusandroid.api.service.ApiService;
import com.greenfox.tribesoflagopusandroid.event.BuildingsEvent;
import com.greenfox.tribesoflagopusandroid.event.TroopsEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.greenfox.tribesoflagopusandroid.MainActivity.BUILDINGS_FRAGMENT_SAVE;
import static com.greenfox.tribesoflagopusandroid.MainActivity.NOTIFICATION;
import static com.greenfox.tribesoflagopusandroid.MainActivity.TROOPS_FRAGMENT_SAVE;
import static com.greenfox.tribesoflagopusandroid.MainActivity.USER_ACCESS_TOKEN;

public class TroopsFragment extends BaseFragment {

  @Inject
  SharedPreferences preferences;
  @Inject
  ApiService apiService;

  SharedPreferences.Editor editor;
  String timestamp;
  private TroopAdapter troopAdapter;
  FloatingActionMenu troopsFloatingActionMenu;
  FloatingActionButton addTroopsActionButton;
  ListView listView;
  List<Resource> resources;

  public TroopsFragment() {
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getActivity().setTitle("Troops");
  }


  @Override
  public void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Override
  public void onStop() {
    super.saveOnExit(TROOPS_FRAGMENT_SAVE);
    timestamp = BaseFragment.timestamp;
    super.onStop();
    EventBus.getDefault().unregister(this);
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onEventTroopAdded(TroopsEvent event) {
    troopAdapter.addAll(event.getTroops());
  }

  public void sendNotification() {
    if (preferences.getBoolean(NOTIFICATION, true)) {
      NotificationCompat.Builder mBuilder =
              new NotificationCompat.Builder(getActivity())
                      .setSmallIcon(R.drawable.tribes)
                      .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.tribesbig))
                      .setContentTitle("New training has started!")
                      .setContentText("Your new troops started their training.");
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



  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    TribesApplication.app().basicComponent().inject(this);
    editor = preferences.edit();

    troopAdapter = new TroopAdapter(getContext(), new ArrayList<Troop>());
    final View rootView = inflater.inflate(R.layout.fragment_troops, container, false);
    apiService.getKingdom(preferences.getString(USER_ACCESS_TOKEN, "")).enqueue(new Callback<Kingdom>() {
      @Override
      public void onResponse(Call<Kingdom> call, Response<Kingdom> response) {
        if (response.code() == 400) {
          ((MainActivity) getActivity()).logout();
          return;
        }
        resources = response.body().getResources();
        EventBus.getDefault().post(new TroopsEvent(response.body().getTroops()));
        ImageView goldImage = (ImageView) rootView.findViewById(R.id.gold_icon);
        ImageView foodImage = (ImageView) rootView.findViewById(R.id.food_icon);
        TextView gold = (TextView) rootView.findViewById(R.id.gold_amount);
        TextView food = (TextView) rootView.findViewById(R.id.food_amount);
        gold.setText(resources.get(0).getAmount() + " ");
        food.setText(resources.get(1).getAmount() + " ");
        goldImage.setImageResource(R.drawable.gold);
        foodImage.setImageResource(R.drawable.food);
      }

      @Override
      public void onFailure(Call<Kingdom> call, Throwable t) {
      }
    });
    ListView listView = (ListView) rootView.findViewById(R.id.troops_listView);
    listView.setAdapter(troopAdapter);

    troopsFloatingActionMenu = (FloatingActionMenu) rootView.findViewById(R.id.add_troop_menu);
    addTroopsActionButton = (FloatingActionButton) rootView.findViewById(R.id.add_troop_menu_item);
    addTroopsActionButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        apiService.postTroop(preferences.getString(USER_ACCESS_TOKEN, "")).enqueue(new Callback<Troop>() {
          @Override
          public void onResponse(Call<Troop> call, Response<Troop> response) {
            sendNotification();
            refreshActiveFragment();
          }

          @Override
          public void onFailure(Call<Troop> call, Throwable t) {

          }
        });
      }
    });
    return rootView;
  }

  public void refresh() {
    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
    transaction.detach(this);
    transaction.attach(this);
    transaction.commit();
  }
}
