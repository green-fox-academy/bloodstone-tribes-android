package com.greenfox.tribesoflagopusandroid;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by georgezsiga on 6/21/17.
 */

public class SettingsFragment extends Fragment {

    TextView notification_status;
    Switch notification;

    public SettingsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.settings_fragment, container, false);


        notification_status = (TextView) rootView.findViewById(R.id.notification_status);
        notification = (Switch) rootView.findViewById(R.id.notification);

        //set the switch to ON
        notification.setChecked(true);
        //attach a listener to check for changes in state
        notification.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    notification_status.setText("Switch is currently ON");
                }else{
                    notification_status.setText("Switch is currently OFF");
                }

            }
        });

        //check the current state before we display the screen
        if(notification.isChecked()){
            notification_status.setText("Switch is currently ON");
        }
        else {
            notification_status.setText("Switch is currently OFF");
        }

        return rootView;
    }

}
