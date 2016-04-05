package com.example.szymon.przewodnikturystyczny;

import android.app.Activity;
import android.content.Context;
import android.media.AudioTrack;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Szymon on 03.04.2016.
 * Context- one object
 */
class GetJson extends AsyncTask<GetJsonParameters, Void,Places> {
    Places places = new Places();
    ArrayAdapter<Place> placeAdapter;
    ListView listView;
    Context context;
    @Override
    protected Places doInBackground(GetJsonParameters... param){
        HttpURLConnection connection = null;
        String json;
        context = param[0].getContext();
        try {
            URL url = param[0].getUrl();
            connection = (HttpURLConnection) url.openConnection();//disconnect
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            if (connection.getResponseCode() == 200) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null)
                    sb.append(line + " ");
                br.close();
                json = sb.toString();
                JSONObject jsonObject = new JSONObject(json);
                Log.d("JSON OBJ", "JEST");
                JSONArray jsonArray = jsonObject.getJSONArray("places");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    int id = object.getInt("id");
                    String name = object.getString("name");
                    Log.d("NAME", name);
                    double longitude = object.getDouble("longitude");
                    double latitude = object.getDouble("latitude");
                    Place place = new Place(id, name, longitude, latitude);
                    places.addPlace(place);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return places;
    }
    @Override
    protected void onPostExecute(Places result){
        super.onPostExecute(result);
        MainActivity.places = result;
        MainActivity.downloadedPlacessComplete = true;
        MainActivity.PlaceholderFragment pf = new MainActivity.PlaceholderFragment();
        pf.loadData();

    }

}