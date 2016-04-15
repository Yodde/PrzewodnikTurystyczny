package com.example.szymon.przewodnikturystyczny;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity{
    static List<String> urls = Arrays.asList("http://wti.mikroprint.pl/get_places.php?number=8", "http://wti.mikroprint.pl/get_place_details.php?Id=7");
    static Places places;
    static boolean downloadedPlacessComplete;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main, new PlaceholderFragment())
                    .commit();
        }
        final Button downloadDataButton = (Button)findViewById(R.id.download_places_button);
        if (downloadDataButton != null) {
            downloadDataButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    new GetJson().execute(new GetJsonParameters(v.getContext(), urls));
                }
            });
        }
    }



    public static class PlaceholderFragment extends Fragment implements AsyncResponse<Place> {
        static View rootView;
        GetJsonDetails json = new GetJsonDetails();
        Place place = null;
        ArrayAdapter<Place> placeAdapter;
        ListView listView;
        public PlaceholderFragment() {
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_main,container,false);
            listView = (ListView) rootView.findViewById(R.id.list_of_places);
            json.delegate = this;
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Place p = (Place) parent.getItemAtPosition(position);
                    json.execute("http://wti.mikroprint.pl/get_place_details.php?Id="+p.getId());
                }
            });
            return rootView;
        }
        public void loadData(){
            try {
                placeAdapter = new ArrayAdapter<>(rootView.getContext(), R.layout.fragment_places, R.id.list_of_places_TextView, places.getPlaces());
                listView = (ListView) rootView.findViewById(R.id.list_of_places);
                listView.setAdapter(placeAdapter);
            }
            catch (java.lang.NullPointerException e){
                e.printStackTrace();
                e.getCause();
            }
        }

        @Override
        public void processFinish(Place output) {
            place = output;
            place.setAllInfoDownloaded(true);
            TextView tv = (TextView) rootView.findViewById(R.id.place_details);
            tv.setText(place.toString());
        }
    }

}
