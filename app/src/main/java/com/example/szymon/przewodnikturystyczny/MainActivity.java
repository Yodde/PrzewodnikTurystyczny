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

public class MainActivity extends AppCompatActivity{
    static List<String> urls = Arrays.asList("http://wti.mikroprint.pl/get_places.php?number=8", "http://wti.mikroprint.pl/get_place_details.php?Id=7");
    static Places places;
    static boolean downloadedPlacessComplete;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main, new PlacesFragment())
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
}
