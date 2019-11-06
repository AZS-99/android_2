package com.example.adam_7;

import android.content.Intent;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity3 extends AppCompatActivity {
    TextView name, department, year;
    Intent intent;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        linearLayout = (LinearLayout) findViewById(R.id.thirdLayout);
        name = (TextView) findViewById(R.id.name);
        department = (TextView) findViewById(R.id.department);
        year = (TextView) findViewById(R.id.year);



        intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        department.setText(intent.getStringExtra("department"));
        year.setText(intent.getStringExtra("year"));

        if (intent.getIntExtra("backgroundColour", 0) == 0)
            linearLayout.setBackgroundColor(Color.parseColor("#ff0000"));
        else
            linearLayout.setBackgroundColor(Color.parseColor("#ff00ff"));

    }
}
