package com.example.adam_assign_3;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Main3Activity extends AppCompatActivity {
    EditText nameET, cityET, sportET, mvpET, stadiumET;
    Button updateBtn, deleteBtn, exitBtn;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        nameET = (EditText) findViewById(R.id.nameEditText);
        cityET = (EditText) findViewById(R.id.cityEditText);
        sportET = (EditText) findViewById(R.id.sportEditText);
        mvpET = (EditText) findViewById(R.id.mvpEditText);
        stadiumET = (EditText) findViewById(R.id.stadiumEditText);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        exitBtn = (Button) findViewById(R.id.exitBtn);

        intent = getIntent();
        cityET.setText(intent.getStringExtra("city"));
        nameET.setText(intent.getStringExtra("name"));
        sportET.setText(intent.getStringExtra("sport"));
        mvpET.setText(intent.getStringExtra("mvp"));
        stadiumET.setText(intent.getStringExtra("stadium"));

        updateBtn.setEnabled(false);
        deleteBtn.setEnabled(false);

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
