package com.example.szymon.przewodnikturystyczny;

import android.os.AsyncTask;
import android.util.Log;
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
import java.util.Arrays;
import java.util.List;

/**
 * Created by Szymon on 05.04.2016.
 */
class GetJsonDetails extends AsyncTask<GetJsonParameters, Void, Place> {
    //public AsyncResponse delegate = null;
    @Override
    protected Place doInBackground(GetJsonParameters... param){
        Place place = null;
        HttpURLConnection connection = null;
        String json;
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
                Log.d("DD","Pobiera");
                JSONObject jsonObject = new JSONObject(json);
                if(jsonObject.getInt("success")==1) {
                    JSONArray jsonArray = jsonObject.getJSONArray("place");
                    int i = 0;
                    JSONObject object = jsonArray.getJSONObject(i);
                    int id = object.getInt("id");
                    String shortDes = object.getString("shortDescription");
                    String description = object.getString("description");
                    String address = object.getString("address");
                    place = MainActivity.places.getPlace(id);
                    place.setAddress(address);
                    place.setDescription(description);
                    place.setShortDescription(shortDes);
                    MainActivity.places.fill(id, place);
                }
                else{
                    throw new JsonInvalidParameters();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JsonInvalidParameters jsonInvalidParameters) {
            Log.e("Error",jsonInvalidParameters.message()+"\n"+param[0]);
            jsonInvalidParameters.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return place;
    }
    @Override
    protected void onPostExecute(Place result){
        PlacesFragment pf = new PlacesFragment();
        result.setAllInfoDownloaded(true);
        pf.loadDataDetails(result);
        //delegate.processFinish(result);
        //TextView tv =
    }

}