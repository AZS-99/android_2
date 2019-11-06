package com.example.adam_7;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    ListView listView1, listView2;
    List<String> names1, departments1, years1, names2, departments2, years2;
    ArrayAdapter<String> arrayAdapter1, arrayAdapter2;
    Intent intent, intent2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        listView1 = (ListView) findViewById(R.id.listView1);
        listView2 = (ListView) findViewById(R.id.listView2);

        intent = getIntent();
        names1 = intent.getStringArrayListExtra("names1");
        departments1 = intent.getStringArrayListExtra("departments1");
        years1 = intent.getStringArrayListExtra("years1");

        names2 = intent.getStringArrayListExtra("names2");
        departments2 = intent.getStringArrayListExtra("departments2");
        years2 = intent.getStringArrayListExtra("years2");


        arrayAdapter1 = new ArrayAdapter<>(this, R.layout.activity_cell, R.id.item, names1);
        listView1.setAdapter(arrayAdapter1);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent2 = new Intent(MainActivity2.this, MainActivity3.class);
                intent2.putExtra("name", names1.get(position));
                intent2.putExtra("department", departments1.get(position));
                intent2.putExtra("year", years1.get(position));
                intent2.putExtra("backgroundColour", 0);
                startActivity(intent2);
            }
        });

        arrayAdapter2 = new ArrayAdapter<>(this, R.layout.activity_cell, R.id.item, names2);
        listView2.setAdapter(arrayAdapter2);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent2 = new Intent(MainActivity2.this, MainActivity3.class);
                intent2.putExtra("name", names2.get(position));
                intent2.putExtra("department", departments2.get(position));
                intent2.putExtra("year", years2.get(position));
                intent2.putExtra("backgroundColour", 1);
                startActivity(intent2);
            }
        });


    }
}
