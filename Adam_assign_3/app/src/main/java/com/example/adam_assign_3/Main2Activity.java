package com.example.adam_assign_3;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {
    EditText cityET, nameET, sportET, mvpET, stadiumET;
    Button submit, exit;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        cityET = (EditText) findViewById(R.id.cityEditText);
        nameET = (EditText) findViewById(R.id.nameEditText);
        sportET = (EditText) findViewById(R.id.sportEditText);
        mvpET = (EditText) findViewById(R.id.mvpEditText);
        stadiumET = (EditText) findViewById(R.id.stadiumEditText);
        submit = (Button) findViewById(R.id.submitBtn);
        exit = (Button) findViewById(R.id.exitBtn);
        database = new Database(Main2Activity.this, "TEAMS");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(cityET.getText())|| TextUtils.isEmpty(nameET.getText()))
                    Toast.makeText(Main2Activity.this, "City and Name are required", Toast.LENGTH_SHORT);
                else {
                    String city = cityET.getText().toString();
                    String name = nameET.getText().toString();
                    String sport = sportET.getText().toString();
                    String mvp = mvpET.getText().toString();
                    String stadium = stadiumET.getText().toString();
                    if (database.insert(city, name, sport, mvp, stadium)) {
                        cityET.getText().clear();
                        nameET.getText().clear();
                        sportET.getText().clear();
                        mvpET.getText().clear();
                        stadiumET.getText().clear();
                    }
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
