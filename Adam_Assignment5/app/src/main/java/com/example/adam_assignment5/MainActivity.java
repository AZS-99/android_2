package com.example.adam_assignment5;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/*******************************
CHECK RES/ANDROID_MANIFEST.XML
 *******************************/

public class MainActivity extends AppCompatActivity {

    static String json;
    static boolean dataFetched;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> merged, urls, places, magnitudes, dates;
    Intent intent;
    WebView webView;
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
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss z");
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                String utc = sdf.format(date);
//                Date date = originalFormat.parse(dateCode);
                urls.add(url);
                places.add(place);
                magnitudes.add(magnitude);
                merged.add("M " + magnitude + " - " + place + "\n" + utc);
            }

        } catch (JSONException  e) {
            e.printStackTrace();
        }

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_cell, R.id.cellTxt, merged) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the current item from ListView
                View view = super.getView(position,convertView,parent);
                if(position %2 == 1)
                {
                    // Set a background color for ListView regular row/item
                    view.setBackgroundColor(Color.parseColor("#00FF00"));
                }
                else
                {
                    // Set the background color for alternate row/item
                    view.setBackgroundColor(Color.parseColor("#FFFF00"));
                }
                return view;
            }
        };
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(MainActivity.this, webView.class);
                intent.putExtra("url", urls.get(position));
                startActivity(intent);
//                intent = new Intent("android.intent.action.VIEW", Uri.parse(urls.get(position)));
//                startActivity(intent);
            }
        });

    }

}
