package com.example.geeta.madpractise;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText txtName,txtEmail;
    Button btnRegister,refresh;
    ListView list_view;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = (EditText) findViewById(R.id.txtName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);

        list_view = (ListView)findViewById(R.id.list_view);
        // g = new getData();
        // g.execute();

        helper db = new helper(getApplicationContext());

        refresh = (Button) findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
               helper db = new helper(getApplicationContext());
               List<User> allUser = db.getAllUsers();
                list_view.setAdapter(new CustomAdaptor(MainActivity.this, allUser));
            }
        });

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtName.getText().toString();
                String email = txtEmail.getText().toString();
                Log.d("TextBoxes", "Both TextBoxes filled");
                saveData(name, email);
                Log.d("Method", "Data Saved in the DB");
            }
        });
    }

    public void saveData(String name, String email) {
        helper db = new helper(getApplicationContext());
        db.addStudent(name, email);
    }
}
