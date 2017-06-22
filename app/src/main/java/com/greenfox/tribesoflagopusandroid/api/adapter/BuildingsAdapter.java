package com.greenfox.tribesoflagopusandroid.api.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

        ImageView buildingImage = (ImageView) convertView.findViewById(R.id.building_image);
        ImageView upArrow = (ImageView) convertView.findViewById(R.id.up);
        TextView type = (TextView) convertView.findViewById(R.id.building_type);
        TextView buildingLevel = (TextView) convertView.findViewById(R.id.building_level);
        if (current.getType().equals("townhall")) {
            buildingImage.setImageResource(R.drawable.town);
        }
        if (current.getType().equals("mine")) {
            buildingImage.setImageResource(R.drawable.mine);
        }
        if (current.getType().equals("farm")) {
            buildingImage.setImageResource(R.drawable.farm);
        }
        if (current.getType().equals("barrack")) {
            buildingImage.setImageResource(R.drawable.troop);
        }
        type.setText(current.getType());
        buildingLevel.setText(String.valueOf("Level " + current.getLevel()));

        return convertView;
    }
}