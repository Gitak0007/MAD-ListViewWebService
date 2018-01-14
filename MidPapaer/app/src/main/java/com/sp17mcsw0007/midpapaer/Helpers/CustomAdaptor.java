package com.sp17mcsw0007.midpapaer.Helpers;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sp17mcsw0007.midpapaer.Models.Contact;
import com.sp17mcsw0007.midpapaer.MainActivity;
import com.sp17mcsw0007.midpapaer.R;

import java.util.List;

/**
 * Created by admin on 10/15/2017.
 */

public class CustomAdaptor extends BaseAdapter {

    List<Contact> contacts;
    Context context;
    private static LayoutInflater inflater = null;

    public CustomAdaptor(MainActivity activity, List<Contact> _contacts) {
        context = activity;
        contacts = _contacts;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return contacts.size();
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
    public View getView(final int position, View view, ViewGroup viewGroup) {
        View rowView;

            rowView = inflater.inflate(R.layout.list_layout, null);
            TextView contactId = (TextView) rowView.findViewById(R.id.textView);
            TextView contactName = (TextView) rowView.findViewById(R.id.textView2);

            final Contact c = contacts.get(position);
             contactId.setText(String.valueOf(c.getId()));
             contactName.setText(c.getName());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage(c.getName());
                    builder.show();
                }
            });

        return rowView;
    }
}