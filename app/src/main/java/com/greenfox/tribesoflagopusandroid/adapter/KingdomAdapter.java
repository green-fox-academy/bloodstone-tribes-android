package com.greenfox.tribesoflagopusandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Kingdom;

import java.util.ArrayList;

/**
 * Created by georgezsiga on 6/28/17.
 */

public class KingdomAdapter extends ArrayAdapter<Kingdom> {

    public KingdomAdapter(@NonNull Context context, ArrayList<Kingdom> troops) {
        super(context, 0, troops);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Kingdom current = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

//        TextView hp = (TextView) convertView.findViewById(R.id.HP_amount);
//        hp.setText(Integer.toString(current.getHp()));
//        TextView ap = (TextView) convertView.findViewById(R.id.AP_amount);
//        ap.setText(Integer.toString(current.getAttack()));
//        TextView dp = (TextView) convertView.findViewById(R.id.DP_amount);
//        dp.setText(Integer.toString(current.getDefense()));

        return convertView;
    }
}

