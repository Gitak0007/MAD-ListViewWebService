package academic.example.com.academicsfinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends Activity {


    Context ctx = MainActivity.this;
    Button btFavList;
    ListView lv;
    ArrayList<Academics> pArrayList = new ArrayList<Academics>();
    Adapter adt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btFavList = (Button) findViewById(R.id.btFavList);
        lv = (ListView) findViewById(R.id.lv);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(ctx, ActAcademicsDetails.class);
                intent.putExtra("Key_Academics", pArrayList.get(i));
                startActivity(intent);

            }
        });

        btFavList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ctx, ActFavList.class));
            }
        });


    }

    private void getData() {

        Lister ls = new Lister(ctx);
        ls.createAndOpenDB();


        try {

            Log.d("000999", "===");

            String[][] data = ActSearch.mFetchedData;
            ls.closeDB();

            try {

                //Log.d("000999", "=== "+data.length);


            }catch (Exception e){


                //data = ls.executeReader("Select *from Academics");

            }

            Log.d("000999", "---- ");


            pArrayList.clear();
            Academics academics;

            Log.d("000999", "Data fetch Length "+data.length);

            adt = new Adapter(ctx, pArrayList);
            lv.setAdapter(adt);


            for (int i = 0 ; i <data.length; i++){

                academics = new Academics();


                academics.id = Integer.parseInt(data[i][0]);
                academics.name = data[i][1];
                academics.contactInfo = data[i][2];
                academics.address = data[i][3];
                academics.mLat = data[i][4];
                academics.mLong = data[i][5];
                academics.type = data[i][6];
                academics.sector = data[i][7];
                academics.gender = data[i][8];
                academics.fee = data[i][9];
                academics.admissionDate =data[i][10];
                academics.isFav =data[i][11];

                pArrayList.add(academics);
            }

            adt.notifyDataSetChanged();



        } catch (Exception e) {
            Toast.makeText(ctx, "Exception "+e, Toast.LENGTH_SHORT).show();
            Log.d("000999", "===E "+e);
            adt.notifyDataSetChanged();

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    public class Adapter extends BaseAdapter {

        Context ctx;
        ArrayList<Academics> mArrayList;
        private LayoutInflater inflater = null;

        public Adapter(Context ctx, ArrayList<Academics> mArrayList) {

            this.ctx = ctx;
            this.mArrayList = mArrayList;

            inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {

            return mArrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return 0;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int position, View row, ViewGroup viewGroup) {

            final ViewHolder holder;
            if (row == null) {
                holder = new ViewHolder();
                row = inflater.inflate(R.layout.item_list_view_academic, null);

                holder.tvName = (TextView) row.findViewById(R.id.tvName);
                holder.ivFav = (ImageView) row.findViewById(R.id.ivFav);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            if(mArrayList.get(position).isFav.equals("0")){
                holder.ivFav.setBackgroundResource(R.mipmap.un_fav);

            }else {
                holder.ivFav.setBackgroundResource(R.mipmap.fav);

            }


            holder.tvName.setText(mArrayList.get(position).name);


            holder.ivFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        if(mArrayList.get(position).isFav.equals("0")){
                            holder.ivFav.setBackgroundResource(R.mipmap.fav);
                            Lister ls = new Lister(ctx);
                            ls.createAndOpenDB();
                            boolean mFlag = ls.executeNonQuery("update Academics set isFav = '1' where id = "+mArrayList.get(position).id);
                            Log.d("000999", "1 mFlag "+mFlag);
                            ls.closeDB();
                            mArrayList.get(position).isFav = "1";
                        }else {
                            holder.ivFav.setBackgroundResource(R.mipmap.un_fav);

                            Lister ls = new Lister(ctx);
                            ls.createAndOpenDB();
                            boolean mFlag = ls.executeNonQuery("update Academics set isFav = '0' where id = "+mArrayList.get(position).id);
                            Log.d("000999", "0 mFlag "+mFlag);
                            ls.closeDB();
                            mArrayList.get(position).isFav = "0";

                        }
                    }catch (Exception e){
                        Log.d("000999", "Exception "+e);
                    }

                }
            });

            return row;
        }
    }


    static class ViewHolder {
        TextView tvName;
        ImageView ivFav;

    }


}




