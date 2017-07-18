package com.greenfox.tribesoflagopusandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Troop;

import java.util.List;

/**
 * Created by hegyi on 2017-06-21.
 */

public class TroopAdapter extends ArrayAdapter<Troop> {

    public TroopAdapter(@NonNull Context context, List<Troop> troops) {
        super(context, 0, troops);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Troop current = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.troop_list_item, parent, false);
        }

        TextView hp = (TextView) convertView.findViewById(R.id.HP_amount);
        hp.setText(Integer.toString(current.getHp()));
        TextView ap = (TextView) convertView.findViewById(R.id.AP_amount);
        ap.setText(Integer.toString(current.getAttack()));
        TextView dp = (TextView) convertView.findViewById(R.id.DP_amount);
        dp.setText(Integer.toString(current.getDefence()));

        return convertView;
    }
}
