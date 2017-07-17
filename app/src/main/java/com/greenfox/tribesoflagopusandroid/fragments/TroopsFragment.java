package com.greenfox.tribesoflagopusandroid.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.TribesApplication;
import com.greenfox.tribesoflagopusandroid.adapter.TroopAdapter;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Troop;
import com.greenfox.tribesoflagopusandroid.api.model.response.TroopsResponse;
import com.greenfox.tribesoflagopusandroid.api.service.ApiService;
import com.greenfox.tribesoflagopusandroid.event.TroopsEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public TroopsFragment() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Troops");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TribesApplication.app().basicComponent().inject(this);
        editor = preferences.edit();
        View rootView = inflater.inflate(R.layout.fragment_troops, container, false);
        troopAdapter = new TroopAdapter(getContext(), new ArrayList<Troop>());
        listView = (ListView) rootView.findViewById(R.id.troops_listView);
        refreshActiveFragment();

        troopsFloatingActionMenu = (FloatingActionMenu) rootView.findViewById(R.id.add_troop_menu);
        addTroopsActionButton = (FloatingActionButton) rootView.findViewById(R.id.add_troop_menu_item);
        addTroopsActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService.postTroop(preferences.getString(USER_ACCESS_TOKEN, "")).enqueue(new Callback<Troop>() {
                    @Override
                    public void onResponse(Call<Troop> call, Response<Troop> response) {
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

    public void getTroopsFromAPI() {
        apiService.getTroops(preferences.getString(USER_ACCESS_TOKEN, "")).enqueue(new Callback<TroopsResponse>() {
            @Override
            public void onResponse(Call<TroopsResponse> call, Response<TroopsResponse> response) {
                EventBus.getDefault().post(new TroopsEvent(response.body().getTroops()));
                troopAdapter.clear();
                troopAdapter.addAll(response.body().getTroops());
                listView.setAdapter(troopAdapter);
                if (loadingViewListener != null) {
                    loadingViewListener.loadingFinished();
                }
            }

            @Override
            public void onFailure(Call<TroopsResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void refreshActiveFragment() {
        getTroopsFromAPI();
        super.refreshActiveFragment();
    }

  @Override
  public void onStop() {
    super.saveOnExit(TROOPS_FRAGMENT_SAVE);
    timestamp = BaseFragment.timestamp;
    super.onStop();
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onEventTroopAdded(TroopsEvent event) {
    troopAdapter.addAll(event.getTroops());
  }
}
