package com.example.szymon.przewodnikturystyczny;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Szymon on 03.04.2016.
 */
public class Places {
    List<Place> places;
    public Places(){
        places= new ArrayList<>();
    }
    public void addPlace(Place place){
        places.add(place);
    }

    public List<Place> getPlaces() {
        return places;
    }
}