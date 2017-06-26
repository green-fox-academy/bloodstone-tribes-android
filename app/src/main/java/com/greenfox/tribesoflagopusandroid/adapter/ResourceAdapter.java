package com.greenfox.tribesoflagopusandroid.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Resource;

import java.util.List;

/**
 * Created by User on 2017. 06. 26..
 */

public class ResourceAdapter extends ArrayAdapter<Resource> {


    public ResourceAdapter(@NonNull Context context, @NonNull List<Resource> resources) {
        super(context, 0, resources);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Resource current = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_main, parent, false);
        }

        TextView gold = (TextView) convertView.findViewById(R.id.gold_amount);
        gold.setText(Integer.toString(current.getAmount()));
        TextView food = (TextView) convertView.findViewById(R.id.food_amount);
        food.setText(Integer.toString(current.getAmount()));

        return convertView;
    }
}
