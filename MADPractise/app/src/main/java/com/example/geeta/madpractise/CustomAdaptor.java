package com.example.geeta.madpractise;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Geeta on 09-Jan-18.
 */

public class CustomAdaptor extends BaseAdapter {
    Context context;

    LayoutInflater inflater;
    List<User> users;

    public CustomAdaptor(Context _context, List<User> _users) {
        context = _context;
        users = _users;

        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return users.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View rowView;
        if ( position == 0) {
            rowView = inflater.inflate(R.layout.list_header, null);
        } else {
            rowView = inflater.inflate(R.layout.list_view, null);
            TextView txtName = (TextView) rowView.findViewById(R.id.student_name);
            TextView txtEmail = (TextView) rowView.findViewById(R.id.student_email);

            final User p = users.get(position-1);
            txtName.setText(p._name);
            txtEmail.setText(p._email);

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage(p._name);
                    builder.show();
                }
            });

        }

        return rowView;
    }
}
