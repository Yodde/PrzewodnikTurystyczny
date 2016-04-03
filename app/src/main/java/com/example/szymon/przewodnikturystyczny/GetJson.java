package com.example.szymon.przewodnikturystyczny;

import android.app.Activity;
import android.content.Context;
import android.media.AudioTrack;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
class GetJson extends AsyncTask<GetJsonParameters, Void,Void> {
    Places places = new Places();
    List<String> listofPlaces = new ArrayList<>();
    ArrayAdapter<String> placeAdapter;
    ListView listView;
    Context context;
    @Override
    protected Void doInBackground(GetJsonParameters... param){
        HttpURLConnection connection = null;
        String json = null;
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
                    listofPlaces.add(id + " " + name);
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
        return null;
    }
    @Override
    protected void onPostExecute(Void result){
        super.onPostExecute(result);
        View rootView = ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);
        placeAdapter = new ArrayAdapter<String>(context,R.layout.fragment_places, R.id.list_of_places_TextView,listofPlaces);
        listView = (ListView) rootView.findViewById(R.id.list_of_places);
        listView.setAdapter(placeAdapter);

    }

}