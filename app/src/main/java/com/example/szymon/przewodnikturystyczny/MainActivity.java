package com.example.szymon.przewodnikturystyczny;

import android.content.DialogInterface;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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
        downloadDataButton.setOnClickListener(this);
        final Button showMapButton = (Button) findViewById(R.id.map_button);
        showMapButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.download_places_button:{
                new GetJson().execute(new GetJsonParameters(v.getContext(), urls));
                break;
            }
            case R.id.map_button:{
                final Intent myIntent = new Intent(MainActivity.this,CityMap.class);
                startActivity(myIntent);
                break;
            }
            default:
                break;
        }
    }
}
