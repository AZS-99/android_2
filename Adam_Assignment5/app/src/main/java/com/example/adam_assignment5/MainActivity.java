package com.example.adam_assignment5;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*******************************
CHECK RES/ANDROID_MANIFEST.XML
 *******************************/

public class MainActivity extends AppCompatActivity {

    static String json;
    static boolean dataFetched;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> merged, urls, places, magnitudes, dates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataFetched = false;
        listView = (ListView)findViewById(R.id.listView);
        merged = new ArrayList<>();
        urls = new ArrayList<>();
        places = new ArrayList<>();
        magnitudes = new ArrayList<>();
        dates = new ArrayList<>();

        FetchData fetchData = new FetchData();
        fetchData.execute();

        do{
            if(!dataFetched){
                try{
                    Thread.sleep(1);
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        }while(!dataFetched);

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray features = jsonObject.getJSONArray("features");

            String url, magnitude, place, dateCode;
            for(int i = 0; i < features.length(); ++i) {
                url = features.getJSONObject(i).getJSONObject("properties").getString("url");
                magnitude = features.getJSONObject(i).getJSONObject("properties").getString("mag");
                place = features.getJSONObject(i).getJSONObject("properties").getString("place");
                dateCode = features.getJSONObject(i).getJSONObject("properties").getString("time");
                Date date = new Date(Long.parseLong(dateCode));
                urls.add(url);
                places.add(place);
                magnitudes.add(magnitude);
                merged.add("M " + magnitude + " - " + place + "\n" + date);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_cell, R.id.cellTxt, merged);
        listView.setAdapter(arrayAdapter);
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
