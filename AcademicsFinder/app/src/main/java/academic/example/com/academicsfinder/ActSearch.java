package academic.example.com.academicsfinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ActSearch extends Activity {

    TextView tvSchool, tvCollege, tvUniversity, tvType;
    Spinner spFeeRange, spLocation, spType;
    Button btSearch, btSync, btHome;

    public static String[][] mFetchedData;

    EditText etSearchText;
    Context ctx = ActSearch.this;

    int instituteType = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_search);

        tvSchool = findViewById(R.id.tvSchool);
        tvCollege = findViewById(R.id.tvCollege);
        tvUniversity = findViewById(R.id.tvUniversity);

        spType = findViewById(R.id.spType);
        spLocation = findViewById(R.id.spLocation);
        spFeeRange = findViewById(R.id.spFeeRange);

        tvType = findViewById(R.id.tvType);
        btSearch = findViewById(R.id.btSearch);
        btSync = findViewById(R.id.btSync);
        btHome = findViewById(R.id.btHome);

        btHome.setVisibility(View.GONE);

        etSearchText = findViewById(R.id.etSearchText);


        //startActivity(new Intent(ctx, ActNavigation.class));
        //startActivity(new Intent(ctx, ActivityTestMap.class));


        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ctx, MainActivity.class));

            }
        });





        tvSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvSchool.setBackgroundResource(R.drawable.tv_bg);
                tvCollege.setBackgroundResource(R.drawable.tv_bg_transparent);
                tvUniversity.setBackgroundResource(R.drawable.tv_bg_transparent);

                spType.setVisibility(View.VISIBLE);
                tvType.setVisibility(View.VISIBLE);
                instituteType = 1;

            }
        });


        tvCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvSchool.setBackgroundResource(R.drawable.tv_bg_transparent);
                tvCollege.setBackgroundResource(R.drawable.tv_bg);
                tvUniversity.setBackgroundResource(R.drawable.tv_bg_transparent);
                spType.setVisibility(View.GONE);
                tvType.setVisibility(View.GONE);
                instituteType = 2;
            }
        });

        tvUniversity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvSchool.setBackgroundResource(R.drawable.tv_bg_transparent);
                tvCollege.setBackgroundResource(R.drawable.tv_bg_transparent);
                tvUniversity.setBackgroundResource(R.drawable.tv_bg);
                spType.setVisibility(View.GONE);
                tvType.setVisibility(View.GONE);
                instituteType = 3;
            }
        });



        setSpinnerType();
        setSpinnerLocation();
        setSpinnerFeeRange();

        tvSchool.performClick();
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("000999", "spFeeRange "+spFeeRange.getSelectedItem());
                Log.d("000999", "spLocation "+spLocation.getSelectedItem());
                Log.d("000999", "spType "+spType.getSelectedItem());


                try {

                    Lister ls = new Lister(ctx);
                    ls.createAndOpenDB();
                    String mQuery = "Select *from Academics ";


                    if(instituteType == 1){
                        mQuery = mQuery+ " where type = '1'";
                    }else  if(instituteType == 2){
                        mQuery = mQuery+ " where type = '2'";
                    }else {
                        mQuery = mQuery+ " where type = '3'";
                    }


                    Log.e("000999", mQuery);

                    if(spFeeRange.getSelectedItem() != null){
                        mQuery = mQuery+ " and fee = '"+spFeeRange.getSelectedItem()+"' ";
                    }



                    if(etSearchText.getText().length() != 0){
                        //String[][] data = ls.executeReader("Select *from Patient where  LOWER(fname) LIKE ? and LOWER(lname) LIKE ? and LOWER(phone) LIKE ? and LOWER(mr_no) LIKE ?  order by fname",  new String[] { "%" + stFName.toLowerCase() + "%",  "%" + stLName.toLowerCase() + "%",  "%" + stPhone.toLowerCase() + "%",  "%" + stMR.toLowerCase() + "%"} );
                        mQuery = mQuery+ " and  LOWER(name) like '%"+etSearchText.getText().toString().toLowerCase()+"%' ";
                    }


                    String[][] mData = ls.executeReader(mQuery);
                    //Log.d("000999", "mQuery  "+mQuery);
                    Toast.makeText(ctx, mData.length+ " Record found!", Toast.LENGTH_SHORT).show();


                    if(mData != null){
                        if(mData.length > 0 ){
                            mFetchedData = mData;
                            startActivity(new Intent(ctx, MainActivity.class));

                        }
                    }


                }catch (Exception e){

                    Log.e("000999", "Exeption "+e);

                    Toast.makeText(ctx, "No record found!", Toast.LENGTH_SHORT).show();

                }


            }
        });


        btSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDataFromJson();
            }


        });


        //tvUniversity.performClick();
        //btSearch.performClick();


    }

    private void insertDataFromJson() {

        Lister ls = new Lister(ctx);
        ls.createAndOpenDB();


        try {
            boolean mFlagDelete = ls.executeNonQuery("delete from Academics");
            boolean mFlagReset = ls.executeNonQuery("delete from sqlite_sequence where name='Academics'");
            Log.d("000999", "mFlagDelete "+mFlagDelete);
            Log.d("000999", "mFlagReset "+mFlagReset);
        }catch (Exception e){

        }

        try {



            JSONArray jArray = new JSONArray(loadJSONFromAsset());


            for (int i = 0; i < jArray.length(); i++){

                boolean mFlag = ls.executeNonQuery("insert into Academics (" +
                        "name, " +
                        " contactInfo, " +
                        " address, " +
                        " mLat, " +
                        " mLong, " +
                        " type, " +
                        " sector, " +
                        " gender, " +
                        " fee, " +
                        " admissionDate, " +
                        " isFav, " +
                        " route " +
                        "" +
                        ") values " +
                        "(" +
                        "'"+jArray.getJSONObject(i).getString("name") +"',"+
                        "'"+jArray.getJSONObject(i).getString("contactInfo") +"',"+
                        "'"+jArray.getJSONObject(i).getString("address") +"',"+
                        "'"+jArray.getJSONObject(i).getString("mLat") +"',"+
                        "'"+jArray.getJSONObject(i).getString("mLong") +"',"+
                        "'"+jArray.getJSONObject(i).getString("type") +"',"+
                        "'"+jArray.getJSONObject(i).getString("sector") +"',"+
                        "'"+jArray.getJSONObject(i).getString("gender") +"',"+
                        "'"+jArray.getJSONObject(i).getString("fee") +"',"+
                        "'"+jArray.getJSONObject(i).getString("admissionDate") +"',"+
                        "'"+jArray.getJSONObject(i).getString("isFav") +"',"+
                        "'"+jArray.getJSONObject(i).getJSONArray("route") +"'"+
                        ")"
                );

                Log.d("000999", "mFlag "+mFlag);

            }

        }catch (Exception e){

            Toast.makeText(ctx, "Exception", Toast.LENGTH_SHORT).show();

            Log.e("000999", "Exception "+e);
        }
    }

    private void setSpinnerFeeRange() {
        List<String> list = new ArrayList<String>();
        list.add("100-1000");
        list.add("1000-5000");
        list.add("5000-10000");
        String[] area = (String[]) list.toArray(new String[0]);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_item, area); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFeeRange.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        spinnerArrayAdapter,
                        R.layout.sp_title,
                        ctx));


    }

    private void setSpinnerLocation() {
        List<String> list = new ArrayList<String>();
        list.add("DHA");
        list.add("Nazimabad");

        String[] area = (String[]) list.toArray(new String[0]);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_item, area); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLocation.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        spinnerArrayAdapter,
                        R.layout.sp_title,
                        ctx));
    }

    private void setSpinnerType() {

            List<String> list = new ArrayList<String>();
            list.add("Primary");
            list.add("Pre-Primary");
            list.add("Secondary");

        String[] area = (String[]) list.toArray(new String[0]);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_item, area); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        spinnerArrayAdapter,
                        R.layout.sp_title,
                        ctx));

    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = ctx.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }



}
