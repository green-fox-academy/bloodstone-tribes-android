package com.greenfox.tribesoflagopusandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.greenfox.tribesoflagopusandroid.adapter.KingdomAdapter;
import com.greenfox.tribesoflagopusandroid.api.model.response.KingdomResponse;
import com.greenfox.tribesoflagopusandroid.api.service.ApiService;

import javax.inject.Inject;

/**
 * Created by georgezsiga on 6/27/17.
 */

public class AlarmReceiver extends BroadcastReceiver {

    private KingdomAdapter kingdomAdapter;
    @Inject
    ApiService apiService;

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        TribesApplication.app().basicComponent().inject(this);

        apiService.getKingdom(1).enqueue(new Callback<KingdomResponse>() {

            @Override
            public void onResponse(Call<KingdomResponse> call, Response<KingdomResponse> response) {

            }

            @Override
            public void onFailure(Call<KingdomResponse> call, Throwable t) {

            }
        });
        kingdomAdapter = new KingdomAdapter(getContext(), new ArrayList<Troop>());

    }
}
