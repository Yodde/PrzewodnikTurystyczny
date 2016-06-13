package com.example.szymon.przewodnikturystyczny;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Szymon on 17.04.2016.
 */
public class PlacesFragment extends Fragment {
    static View rootView;
    final String url = "http://wti.mikroprint.pl/get_place_details.php?Id=";
    //GetJsonDetails json = new GetJsonDetails();
    Place place = null;
    ArrayAdapter<Place> placeAdapter;
    ListView listView;
    public PlacesFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main,container,false);
        //json.delegate = this;
        listView = (ListView) rootView.findViewById(R.id.list_of_places);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GetJsonDetails gjd = new GetJsonDetails();
                Place p = (Place) parent.getItemAtPosition(position);
                gjd.execute(new GetJsonParameters(view.getContext(),url+p.getId()));
            }
        });
        return rootView;
    }
    public void loadData(){
        try {
            placeAdapter = new ArrayAdapter<>(rootView.getContext(), R.layout.fragment_places, R.id.list_of_places_TextView, MainActivity.places.getPlaces());
            listView = (ListView) rootView.findViewById(R.id.list_of_places);
            listView.setAdapter(placeAdapter);
        }
        catch (java.lang.NullPointerException e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void loadDataDetails(Place place){
        try {
            TextView tv = (TextView) rootView.findViewById(R.id.place_details);
            tv.setMovementMethod(new ScrollingMovementMethod());
            tv.setText(place.allInfo());
        }
        catch (java.lang.NullPointerException e){
            e.printStackTrace();
            e.getCause();
        }
    }

//    @Override
//    public void processFinish(Place output) {
//        place = output;
//        place.setAllInfoDownloaded(true);
//        TextView tv = (TextView) rootView.findViewById(R.id.place_details);
//        tv.setText(place.toString());
//    }
}