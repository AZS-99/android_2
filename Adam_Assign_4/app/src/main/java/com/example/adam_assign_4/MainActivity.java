package com.example.adam_assign_4;

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
    ArrayList<String> cities, names, sports, mvps, stadiums, merged, imgPathes;
    ArrayList<Integer> ids;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    final int Add_TEAM = 1;
    final int EDIT_TEAM = 2;
    static boolean grantPhotoAccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addTeamBtn = (Button) findViewById(R.id.addTeamBtn);
        exitBtn = (Button) findViewById(R.id.exitBtn);
        listView = (ListView) findViewById(R.id.listView);

        database = new Database(this, "Teams");

        loadDataFromDatabase();
        createListView();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(MainActivity.this, Main3Activity.class);
                intent.putExtra("id", ids.get(position));
                intent.putExtra("name", names.get(position));
                intent.putExtra("city", cities.get(position));
                intent.putExtra("sport", sports.get(position));
                intent.putExtra("mvp", mvps.get(position));
                intent.putExtra("stadium", stadiums.get(position));
                intent.putExtra("imgPath", imgPathes.get(position));
                startActivityForResult(intent, EDIT_TEAM);
            }
        });


        addTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, Main2Activity.class);

                startActivityForResult(intent, Add_TEAM);
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
        if (resultCode != RESULT_CANCELED) {
            if(requestCode == Add_TEAM) {
                String name = intent.getStringExtra("name");
                String city = intent.getStringExtra("city");
                String sport = intent.getStringExtra("sport");
                String mvp = intent.getStringExtra("mvp");
                String stadium = intent.getStringExtra("stadium");
                String imgPath = intent.getStringExtra("imgPath");
                if(database.insert(city, name, sport, mvp, stadium, imgPath)) {
                    loadDataFromDatabase();
                }
                createListView();

            } else if (requestCode == EDIT_TEAM) {
                if(intent.getBooleanExtra("delete", false)) {
                    int id = intent.getIntExtra("id", 0);
                    String idString = String.valueOf(id);
                    database.delete(idString);
                }
                if(intent.getBooleanExtra("update", false)) {
                    String name = intent.getStringExtra("name");
                    String city = intent.getStringExtra("city");
                    String sport = intent.getStringExtra("sport");
                    String mvp = intent.getStringExtra("mvp");
                    String stadium = intent.getStringExtra("stadium");
                    String imgPath = intent.getStringExtra("imgPath");
                    int id = intent.getIntExtra("id", 0);
                    String idString = String.valueOf(id);
                    database.update(idString, city, name, sport, mvp, stadium, imgPath);
                }
                loadDataFromDatabase();
                createListView();
            }
        }

    }

    private void loadDataFromDatabase() {
        cities = new ArrayList<>();
        names = new ArrayList<>();
        sports = new ArrayList<>();
        mvps = new ArrayList<>();
        stadiums = new ArrayList<>();
        imgPathes = new ArrayList<>();
        ids = new ArrayList<>();

        Cursor allData = database.getAllData();
        StringBuffer buffer = new StringBuffer();
        while(allData.moveToNext()) {
            buffer.append(allData.getString(0));
            ids.add(Integer.parseInt(buffer.toString()));
            buffer.delete(0, buffer.length());

            buffer.append(allData.getString(1));
            cities.add(buffer.toString());
            buffer.delete(0, buffer.length());

            buffer.append(allData.getString(2));
            names.add(buffer.toString());
            buffer.delete(0, buffer.length());

            buffer.append(allData.getString(3));
            sports.add(buffer.toString());
            buffer.delete(0, buffer.length());

            buffer.append(allData.getString(4));
            mvps.add(buffer.toString());
            buffer.delete(0, buffer.length());

            buffer.append(allData.getString(5));
            stadiums.add(buffer.toString());
            buffer.delete(0, buffer.length());

            buffer.append(allData.getString(6));
            imgPathes.add(buffer.toString());
            buffer.delete(0, buffer.length());

            System.out.println("ids = " + ids);


        }
    }

    private ArrayList<String> merge(ArrayList<String> arrayList1, ArrayList<String> arrayList2) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < arrayList1.size(); ++i) {
            result.add(arrayList1.get(i) + " " + arrayList2.get(i));
        }
        return result;
    }

    private void createListView() {
        merged = merge(cities, names);
        arrayAdapter = new ArrayAdapter<>(this, R.layout.activity_cell, R.id.item, merged);
        listView.setAdapter(arrayAdapter);
    }

}