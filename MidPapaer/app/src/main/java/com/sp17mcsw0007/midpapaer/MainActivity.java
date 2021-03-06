package com.sp17mcsw0007.midpapaer;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sp17mcsw0007.midpapaer.Helpers.CustomAdaptor;
import com.sp17mcsw0007.midpapaer.Helpers.WebService;
import com.sp17mcsw0007.midpapaer.Models.Contact;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetData().execute();
        listView = (ListView) findViewById(R.id.list_view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this, ShowContactActivity.class);

                intent.putExtra("id", id);
                intent.putExtra("name", name);
                //intent.putExtra("Key_Academics", pArrayList.get(i));
                startActivity(intent);
            }
        });
    }

    public class GetData extends AsyncTask<Void, Void, Void> {

            List<Contact> list = new ArrayList<>();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    list = WebService.GetData("https://api.androidhive.info/contacts/");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                // Dismiss the progress dialog
//            if (pDialog.isShowing())
//                pDialog.dismiss();

                listView.setAdapter(new CustomAdaptor(MainActivity.this, list));
            }

    }
}
