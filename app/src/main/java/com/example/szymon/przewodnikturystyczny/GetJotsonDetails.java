package com.example.szymon.przewodnikturystyczny;

import android.os.AsyncTask;
import android.util.Log;
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

/**
 * Created by Szymon on 05.04.2016.
 */
class GetJsonDetails extends AsyncTask<String, Void, Place> {

    @Override
    protected Place doInBackground(String... param){
        Place place = null;
        HttpURLConnection connection = null;
        String json;
        try {
            URL url = new URL(param[0]);
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
                JSONArray jsonArray = jsonObject.getJSONArray("place");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    int id = object.getInt("id");
                    String shortDes = object.getString("shortDescription");
                    String description = object.getString("description");
                    String address = object.getString("address");
                    place = MainActivity.places.getPlace(id);
                    place.setAddress(address);
                    place.setDescription(description);
                    place.setShortDescription(shortDes);
                    MainActivity.places.fill(id,place);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return place;
    }
    @Override
    protected void onPostExecute(Place result){
        super.onPostExecute(result);
    }

}