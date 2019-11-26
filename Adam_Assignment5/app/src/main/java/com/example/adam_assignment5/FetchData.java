package com.example.adam_assignment5;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchData extends AsyncTask <Void, Void, Void>{
    String allLines = "";

    @Override
    protected Void doInBackground(Void ... voids) {
        try {
            URL url = new URL("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&minmagnitude=6&limit=22");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                allLines += line;
            }
            MainActivity.json = allLines;
            MainActivity.dataFetched = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }


}
