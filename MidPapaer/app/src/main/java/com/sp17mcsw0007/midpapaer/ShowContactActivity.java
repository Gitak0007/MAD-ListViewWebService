package com.sp17mcsw0007.midpapaer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowContactActivity extends AppCompatActivity {
    TextView txtId,txtName;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);

         txtId = (TextView) findViewById(R.id.txtId);
         txtName = (TextView) findViewById(R.id.txtName);
         btnSave = (Button) findViewById(R.id.btnSave);

        txtId.setText(getIntent().getStringExtra("id"));
        txtName.setText(getIntent().getStringExtra("name"));

        btnSave.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                save();
            }

        });
    }
    public void save(){
        String id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");

    }
}
