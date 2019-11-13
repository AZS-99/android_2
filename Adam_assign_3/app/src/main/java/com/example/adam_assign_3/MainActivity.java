package com.example.adam_assign_3;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button addTeamBtn, exitBtn;
    Intent intent;
    Database database;
    ArrayList<String> cities, names, sports, mvps, stadiums, merged;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addTeamBtn = (Button) findViewById(R.id.addTeamBtn);
        exitBtn = (Button) findViewById(R.id.exitBtn);
        listView = (ListView) findViewById(R.id.listView);
        cities = new ArrayList<>();
        names = new ArrayList<>();
        sports = new ArrayList<>();
        mvps = new ArrayList<>();
        stadiums = new ArrayList<>();
        database = new Database(this, "Teams");

        loadDataFromDatabase();
        merged = merge(cities, names);

        arrayAdapter = new ArrayAdapter<>(this, R.layout.activity_cell, R.id.item, merged);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(MainActivity.this, Main3Activity.class);
                intent.putExtra("name", names.get(position));
                intent.putExtra("city", cities.get(position));
                intent.putExtra("sport", sports.get(position));
                intent.putExtra("mvp", mvps.get(position));
                intent.putExtra("stadium", stadiums.get(position));
                startActivity(intent);
            }
        });


        addTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivityForResult(intent, 1);
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                startActivity(intent);
                finish();
                System.exit(0);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String name = intent.getStringExtra("name");
                String city = intent.getStringExtra("city");
                String sport = intent.getStringExtra("sport");
                String mvp = intent.getStringExtra("mvp");
                String stadium = intent.getStringExtra("stadium");
                if(database.insert(city, name, sport, mvp, stadium)) {
                    cities.add(city);
                    names.add(name);
                    sports.add(sport);
                    mvps.add(mvp);
                    stadiums.add(stadium);
                }
                merged.add(city + " " + name);
                arrayAdapter = new ArrayAdapter<>(this, R.layout.activity_cell, R.id.item, merged);
                listView.setAdapter(arrayAdapter);
            }
        }
    }

    public void loadDataFromDatabase() {
        Cursor allData = database.getAllData();
        StringBuffer buffer = new StringBuffer();
        while(allData.moveToNext()) {
            buffer.append(allData.getString(0));
            cities.add(buffer.toString());
            buffer.delete(0, buffer.length());

            buffer.append(allData.getString(1));
            names.add(buffer.toString());
            buffer.delete(0, buffer.length());

            buffer.append(allData.getString(2));
            sports.add(buffer.toString());
            buffer.delete(0, buffer.length());

            buffer.append(allData.getString(3));
            mvps.add(buffer.toString());
            buffer.delete(0, buffer.length());

            buffer.append(allData.getString(4));
            stadiums.add(buffer.toString());
            buffer.delete(0, buffer.length());
        }
    }

    public ArrayList<String> merge(ArrayList<String> arrayList1, ArrayList<String> arrayList2) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < arrayList1.size(); ++i) {
            result.add(arrayList1.get(i) + " " + arrayList2.get(i));
        }
        return result;
    }


}
