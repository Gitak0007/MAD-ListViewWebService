package academic.example.com.academicsfinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActAcademicsDetails extends Activity {

    TextView tvName, tvContactNo, tvAddress, admissionDate, sector, fee;
    Button btDirection, btClose;
    Context ctx = ActAcademicsDetails.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_academics_details);

        tvName = (TextView) findViewById(R.id.tvName);
        tvContactNo = (TextView) findViewById(R.id.tvContactNo);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        admissionDate = (TextView) findViewById(R.id.admissionDate);
        sector = (TextView) findViewById(R.id.sector);
        fee = (TextView) findViewById(R.id.fee);


        btDirection = (Button) findViewById(R.id.btDirection);
        btClose = (Button) findViewById(R.id.btClose);


        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ctx, ActNavigation.class));
            }
        });


        try{
            Academics academics = (Academics) getIntent().getExtras().getSerializable("Key_Academics");

            tvName.setText(Html.fromHtml(""+academics.name+""));
            tvContactNo.setText(Html.fromHtml("<b>Contact: </b>"+academics.contactInfo+""));
            tvAddress.setText(Html.fromHtml("<b>Address: </b>"+academics.address+""));
            if(academics.sector.equals("1")){
                sector.setText(Html.fromHtml("<b>Sector: </b>Private"));
            }else {
                sector.setText(Html.fromHtml("<b>Sector: </b>Government"));
            }


            admissionDate.setText(Html.fromHtml("<b>Date Admission: </b>"+academics.admissionDate+""));


            int fee1 = 0;
            int fee2 = 0;
            int fee3 = 0;

            if(academics.fee.contains("100")){

                fee1 = 12000;
                fee2 = 2000;
                fee3 = 2500;

            }else if(academics.fee.contains("100000")){

                fee1 = 3000;
                fee2 = 12000;
                fee3 = 9000;

            }else {

                fee1 = 1600;
                fee2 = 4000;
                fee3 = 3500;
            }

            if(academics.type.equals("1")){

                fee.setText(Html.fromHtmll("<b>Admission Fee: </b>"+fee1+
                        "<br>"+ "<b>Primary Fee: </b>"+fee2+""+
                        "<br>"+ "<b>Secondary Fee: </b>"+fee3+""));


            }else if(academics.type.equals("2")){

                fee.setText(Html.fromHtml("<b>Admission Fee: </b>"+fee1+
                        "<br>"+ "<b>Science : </b>"+fee2+""+
                                "<br>"+ "<b>Commerce : </b>"+fee3+""));

            }else {

                fee.setText(Html.fromHtml("<b>Admission Fee: </b>"+fee1+
                        "<br>"+ "<b>Engineering: </b>"+fee2+""+
                        "<br>"+ "<b>Bussiness: </b>"+fee3+""));


            }



            Log.d("000999", "name==="+academics.name);
            Log.d("000999", "contactInfo==="+academics.contactInfo);
            Log.d("000999", "address==="+academics.address);
            Log.d("000999", "mLat==="+academics.mLat);
            Log.d("000999", "mLong==="+academics.mLong);
            Log.d("000999", "type==="+academics.type);
            Log.d("000999", "sector==="+academics.sector);
            Log.d("000999", "gender==="+academics.gender);
            Log.d("000999", "fee==="+academics.fee);
            Log.d("000999", "admissionDate==="+academics.admissionDate);

        }catch (Exception e){
            Toast.makeText(ctx, "Data Not Found ", Toast.LENGTH_SHORT).show();
        }

    }
}
