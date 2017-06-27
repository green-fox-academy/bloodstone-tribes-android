package com.greenfox.tribesoflagopusandroid.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
        Resource resource = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.resources_list, parent, false);
        }
        ImageView resourceImage = (ImageView) convertView.findViewById(R.id.resource_image);
        TextView type = (TextView) convertView.findViewById(R.id.type);
        if (resource.getType().equals("gold")) {
            resourceImage.setImageResource(R.drawable.gold);
        }
        if (resource.getType().equals("food")) {
            resourceImage.setImageResource(R.drawable.food);
        }
        type.setText(String.valueOf((resource.getAmount() + " ") + resource.getType()));
        return convertView;
    }
}
