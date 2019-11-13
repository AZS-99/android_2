package com.example.adam_assign_3;

import android.content.Intent;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    EditText cityET, nameET, sportET, mvpET, stadiumET;
    Button submit, exit;
    String city, name, sport, mvp, stadium;
    ArrayList<String> cities, names, sports, mvps, stadiums;
    Intent intent;

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
        cities = new ArrayList<>();
        names = new ArrayList<>();
        sports = new ArrayList<>();
        mvps = new ArrayList<>();
        stadiums = new ArrayList<>();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(cityET.getText())|| TextUtils.isEmpty(nameET.getText())) {
                    Toast.makeText(Main2Activity.this, "City and Name are required", Toast.LENGTH_LONG).show();
                }
                else {
                    city = cityET.getText().toString();
                    name = nameET.getText().toString();

                    if (TextUtils.isEmpty(sportET.getText()))
                        sport = "";
                    else
                        sport = sportET.getText().toString();

                    if (TextUtils.isEmpty(mvpET.getText()))
                        mvp = "";
                    else
                        mvp = mvpET.getText().toString();

                    if (TextUtils.isEmpty(stadiumET.getText()))
                        stadium = "";
                    else
                        stadium = stadiumET.getText().toString();

                    cityET.getText().clear();
                    nameET.getText().clear();
                    sportET.getText().clear();
                    mvpET.getText().clear();
                    stadiumET.getText().clear();

                }

                intent = new Intent();
                intent.putExtra("name", name);
                intent.putExtra("city", city);
                intent.putExtra("sport", sport);
                intent.putExtra("mvp", mvp);
                intent.putExtra("stadium", stadium);
                setResult(RESULT_OK, intent);
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
