package com.greenfox.tribesoflagopusandroid.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenfox.tribesoflagopusandroid.R;

import static com.greenfox.tribesoflagopusandroid.MainActivity.BATTLE_FRAGMENT_SAVE;

public class BattleFragment extends BaseFragment {

    String timestamp;

    public BattleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_battle, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Battle");
    }

    @Override
    public void onStop() {
        super.saveOnExit(BATTLE_FRAGMENT_SAVE);
        timestamp = BaseFragment.timestamp;
        super.onStop();
    }
}
