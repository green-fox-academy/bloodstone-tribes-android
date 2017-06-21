package com.greenfox.tribesoflagopusandroid;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import javax.inject.Inject;

import static com.greenfox.tribesoflagopusandroid.MainActivity.BACKGROUND_SYNC;
import static com.greenfox.tribesoflagopusandroid.MainActivity.NOTIFICATION;
import static com.greenfox.tribesoflagopusandroid.MainActivity.PASSWORD;

/**
 * Created by georgezsiga on 6/21/17.
 */

public class SettingsFragment extends Fragment {

    @Inject
    SharedPreferences preferences;

    SharedPreferences.Editor editor;

    TextView notification_status, background_sync_status;
    Switch notification, background_sync;

    public SettingsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        editor = preferences.edit();

        View rootView = inflater.inflate(R.layout.settings_fragment, container, false);
        notification_status = (TextView) rootView.findViewById(R.id.notification_status);
        notification = (Switch) rootView.findViewById(R.id.notification);
        background_sync_status = (TextView) rootView.findViewById(R.id.background_sync_status);
        background_sync = (Switch) rootView.findViewById(R.id.background_sync);

        notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    notification_status.setText("You will receive game notifications");
                    editor.putBoolean(String.valueOf(NOTIFICATION), true);
                    editor.apply();
                }else{
                    notification_status.setText("You don`t receive game notifications");
                    editor.putBoolean(String.valueOf(NOTIFICATION), false);
                    editor.apply();
                }
            }
        });

        background_sync.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    background_sync_status.setText("Background sync is ON");
                    editor.putBoolean(String.valueOf(BACKGROUND_SYNC), true);
                    editor.apply();
                }else{
                    background_sync_status.setText("Background sync is OFF");
                    editor.putBoolean(String.valueOf(BACKGROUND_SYNC), false);
                    editor.apply();
                }
            }
        });

        if(notification.isChecked()){
            notification_status.setText("You will receive game notifications");
        }
        else {
            notification_status.setText("You don`t receive game notifications");
        }

        if(background_sync.isChecked()){
            background_sync_status.setText("Background sync is ON");
        }
        else {
            background_sync_status.setText("Background sync is OFF");
        }

        return rootView;
    }

}
