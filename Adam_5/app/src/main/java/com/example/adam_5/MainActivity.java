package com.example.adam_5;

import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView1, listView2;
    int[] flags = {R.drawable.egypt};
    ArrayList<Country> countries1, countries2;

    Adapter1 adapter1;
    Adapter2 adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView1 = (ListView) findViewById(R.id.listView1);
        listView2 = (ListView) findViewById(R.id.listView2);

        countries1 = new ArrayList<>();
        countries1.add(new Country("Argentina", "Buenos Aires", "drawable://" + R.drawable.argentina));
        countries1.add(new Country("Bahamas", "Nassau", "drawable://" + R.drawable.bahamas));
        countries1.add(new Country("Cameroon", "Yaounde", "drawable://" + R.drawable.cameroon));
        countries1.add(new Country("Denmark", "Copenhagen", "drawable://" + R.drawable.denmark));
        countries1.add(new Country("Egypt", "Cairo", "drawable://" + R.drawable.egypt));
        countries1.add(new Country("Finland", "Helsinki", "drawable://" + R.drawable.finland));
        countries1.add(new Country("Georgia", "Tbilisi", "drawable://" + R.drawable.georgia));
        countries1.add(new Country("Honduras", "Tegucigalpa", "drawable://" + R.drawable.honduras));
        countries1.add(new Country("Indonesia", "Jakarta", "drawable://" + R.drawable.indonesia));
        countries1.add(new Country("Jamaica", "Kingston", "drawable://" + R.drawable.jamaica));
        countries1.add(new Country("Kazakhstan", "Astana", "drawable://" + R.drawable.kazakhstan));
        countries1.add(new Country("Laos", "Vientiane", "drawable://" + R.drawable.laos));

        countries2 = new ArrayList<>();
        countries2.add(new Country("Australia", "Canberra", "drawable://" + R.drawable.australia));
        countries2.add(new Country("Bangladesh", "Dhaka", "drawable://" + R.drawable.bangladesh));
        countries2.add(new Country("Colombia", "Bogota", "drawable://" + R.drawable.colombia));
        countries2.add(new Country("Djibouti", "Djibouti", "drawable://" + R.drawable.djibouti));
        countries2.add(new Country("Ethiopia", "Addis Ababa", "drawable://" + R.drawable.ethiopia));
        countries2.add(new Country("France", "Paris", "drawable://" + R.drawable.france));
        countries2.add(new Country("Ghana", "Accra", "drawable://" + R.drawable.ghana));
        countries2.add(new Country("Hungary", "Budapest", "drawable://" + R.drawable.hungary));
        countries2.add(new Country("Iran", "Tehran", "drawable://" + R.drawable.iran));
        countries2.add(new Country("Japan", "Tokyo", "drawable://" + R.drawable.japan));
        countries2.add(new Country("Kuwait", "Kuwait", "drawable://" + R.drawable.kuwait));
        countries2.add(new Country("Lebanon", "Beirut", "drawable://" + R.drawable.lebanon));

        adapter1 = new Adapter1(this, R.layout.listview1, countries1);
        adapter2 = new Adapter2(this, R.layout.list_view2, countries2);
        listView1.setAdapter(adapter1);
        listView2.setAdapter(adapter2);

    }
}
