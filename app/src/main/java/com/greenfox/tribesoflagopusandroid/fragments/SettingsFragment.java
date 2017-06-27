package com.greenfox.tribesoflagopusandroid.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.TribesApplication;

import javax.inject.Inject;

import static com.greenfox.tribesoflagopusandroid.MainActivity.BACKGROUND_SYNC;
import static com.greenfox.tribesoflagopusandroid.MainActivity.MAIN_FRAGMENT_SAVE;
import static com.greenfox.tribesoflagopusandroid.MainActivity.NOTIFICATION;


/**
 * Created by georgezsiga on 6/21/17.
 */

public class SettingsFragment extends android.support.v4.app.Fragment {

    @Inject
    public SharedPreferences preferences;

    public SharedPreferences.Editor editor;

    public TextView notification_status, background_sync_status;
    public Switch notification, background_sync;

    String timestamp;

    public SettingsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TribesApplication.app().basicComponent().inject(this);
        editor = preferences.edit();

        View rootView = inflater.inflate(R.layout.settings_fragment, container, false);
        notification_status = (TextView) rootView.findViewById(R.id.notification_status);
        notification = (Switch) rootView.findViewById(R.id.notification);
        background_sync_status = (TextView) rootView.findViewById(R.id.background_sync_status);
        background_sync = (Switch) rootView.findViewById(R.id.background_sync);

        notification.setChecked(preferences.getBoolean(NOTIFICATION, false));
        background_sync.setChecked(preferences.getBoolean(BACKGROUND_SYNC, false));

        notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    notification.setChecked(true);
                    notification_status.setText(getContext().getString(R.string.notification_on));
                    editor.putBoolean(NOTIFICATION, true);
                    editor.apply();
                }else{
                    notification.setChecked(false);
                    notification_status.setText(getContext().getString(R.string.notification_off));
                    editor.putBoolean(NOTIFICATION, false);
                    editor.apply();
                }
            }
        });

        background_sync.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    background_sync.setChecked(true);
                    background_sync_status.setText(getContext().getString(R.string.background_sync_on));
                    editor.putBoolean(BACKGROUND_SYNC, true);
                    editor.apply();
                }else{
                    background_sync.setChecked(false);
                    background_sync_status.setText(getContext().getString(R.string.background_sync_off));
                    editor.putBoolean(BACKGROUND_SYNC, false);
                    editor.apply();
                }
            }
        });

        if(notification.isChecked()){
            notification_status.setText(getContext().getString(R.string.notification_on));
        }
        else {
            notification_status.setText(getContext().getString(R.string.notification_off));
        }

        if(background_sync.isChecked()){
            background_sync_status.setText(getContext().getString(R.string.background_sync_on));
        }
        else {
            background_sync_status.setText(getContext().getString(R.string.background_sync_off));
        }

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        timestamp = String.valueOf(System.currentTimeMillis());
        editor.putString(MAIN_FRAGMENT_SAVE, timestamp);
        editor.apply();
    }

}
