package com.greenfox.tribesoflagopusandroid.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.greenfox.tribesoflagopusandroid.R;
import com.greenfox.tribesoflagopusandroid.api.model.User;

import java.util.List;

/**
 * Created by User on 2017. 06. 09..
 */

public class UserAdapter extends ArrayAdapter<User> {

    private Context context;
    private List<User> values;

    public UserAdapter(Context context, List<User> values) {
        super(context, R.layout.activity_main, values);

        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.activity_main, parent, false);
        }

        TextView textView = (TextView) row.findViewById(R.id.textView2);

        User user = values.get(position);
        String message = user.getUsername();
        textView.setText(message);

        return row;
    }
}
