package com.example.adam_7;

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

public class MainActivity extends AppCompatActivity {
    Database employees;
    EditText name1, dept1, year1, name2, dept2, year2;
    Button addBtn1, addBtn2, viewBtn;
    Intent intent;
    ArrayList<String> names1, departments1, years1, names2, departments2, years2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name1 = (EditText) findViewById(R.id.nameEditText);
        dept1 = (EditText) findViewById(R.id.deptEditText);
        year1 = (EditText) findViewById(R.id.yearEditText);
        name2 = (EditText) findViewById(R.id.nameEditText2);
        dept2 = (EditText) findViewById(R.id.deptEditText2);
        year2 = (EditText)findViewById(R.id.yearEditText2) ;
        addBtn1 = (Button) findViewById(R.id.addButton1);
        addBtn2 = (Button) findViewById(R.id.addButton2);
        viewBtn = (Button) findViewById(R.id.viewBtn);

        employees = new Database(this);

        addBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name1.getText()) || TextUtils.isEmpty(dept1.getText()) || TextUtils.isEmpty(year1.getText()))
                    Toast.makeText(MainActivity.this, "Incomplete", Toast.LENGTH_SHORT).show();
                else {
                    employees.insert(Database.TABLE1, name1.getText().toString(), dept1.getText().toString(),
                            Integer.parseInt(year1.getText().toString()));
                    name1.getText().clear();
                    dept1.getText().clear();
                    year1.getText().clear();
                }

            }
        });

        addBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name2.getText()) || TextUtils.isEmpty(dept2.getText()) || TextUtils.isEmpty(year2.getText()))
                    Toast.makeText(MainActivity.this, "Incomplete", Toast.LENGTH_SHORT).show();
                else {
                    employees.insert(Database.TABLE2, name2.getText().toString(), dept2.getText().toString(),
                            Integer.parseInt(year2.getText().toString()));
                    name2.getText().clear();
                    dept2.getText().clear();
                    year2.getText().clear();
                }
            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names1 = new ArrayList();
                departments1 = new ArrayList();
                years1 = new ArrayList<>();
                names2 = new ArrayList<>();
                departments2 = new ArrayList<>();
                years2 = new ArrayList<>();

                Cursor rawData1 = employees.getAllData(Database.TABLE1);
                StringBuffer buffer = new StringBuffer();
                while(rawData1.moveToNext()) {
                    buffer.append(rawData1.getString(0));
                    names1.add(buffer.toString());
                    buffer.delete(0, buffer.length());

                    buffer.append(rawData1.getString(1));
                    departments1.add(buffer.toString());
                    buffer.delete(0, buffer.length());

                    buffer.append(rawData1.getString(2));
                    years1.add(buffer.toString());
                    buffer.delete(0, buffer.length());
                }

                Cursor rawData2 = employees.getAllData(Database.TABLE2);
                while(rawData2.moveToNext()) {
                    buffer.append(rawData2.getString(0));
                    names2.add(buffer.toString());
                    buffer.delete(0, buffer.length());

                    buffer.append(rawData2.getString(1));
                    departments2.add(buffer.toString());
                    buffer.delete(0, buffer.length());

                    buffer.append(rawData2.getString(2));
                    years2.add(buffer.toString());
                    buffer.delete(0, buffer.length());
                }

                intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putStringArrayListExtra("names1", names1);
                intent.putStringArrayListExtra("departments1", departments1);
                intent.putStringArrayListExtra("years1", years1);
                intent.putStringArrayListExtra("names2", names2);
                intent.putStringArrayListExtra("departments2", departments2);
                intent.putStringArrayListExtra("years2", years2);
                startActivity(intent);
            }
        });



    }
}
