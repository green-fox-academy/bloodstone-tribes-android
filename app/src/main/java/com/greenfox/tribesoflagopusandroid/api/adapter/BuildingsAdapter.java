package com.greenfox.tribesoflagopusandroid.api.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Building;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Troop;

import java.util.ArrayList;



public class BuildingsAdapter extends ArrayAdapter<Building> {

    public BuildingsAdapter(@NonNull Context context, ArrayList<Building> buildings) {
        super(context, 0, buildings);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Building current = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView hp = (TextView) convertView.findViewById(R.id.building_type);
        hp.setText(current.getType() + " " + current.getId());
        TextView ap = (TextView) convertView.findViewById(R.id.AP_amount);
        ap.setText("level " + Integer.toString(current.getLevel()));

        return convertView;
    }
}