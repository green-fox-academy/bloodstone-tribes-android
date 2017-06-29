package com.greenfox.tribesoflagopusandroid.event;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by User on 2017. 06. 29..
 */

public class EventReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        new BuildingsEvent().setMessage("Building created");
    }

}

