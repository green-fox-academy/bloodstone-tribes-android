package com.greenfox.tribesoflagopusandroid.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.TribesApplication;

import javax.inject.Inject;

import static com.greenfox.tribesoflagopusandroid.MainActivity.MAIN_FRAGMENT_SAVE;

public class MainFragment extends Fragment {

    @Inject
    SharedPreferences preferences;

    SharedPreferences.Editor editor;

    String timestamp;

    public MainFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TribesApplication.app().basicComponent().inject(this);
        editor = preferences.edit();
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onStop() {
        super.onStop();
        timestamp = String.valueOf(System.currentTimeMillis());
        editor.putString(MAIN_FRAGMENT_SAVE, timestamp);
        editor.apply();
    }

}
