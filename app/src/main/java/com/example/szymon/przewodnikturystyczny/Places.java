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

    /**
     *
     * @param i Position of place in places list form 1 to x.
     * @return Place at position i-1.
     *
     */
    public Place getPlace(int i){
        return places.get(i-1);
    }

    /**
     * Fills detailed parameters of place at position i-1 in table.
     * @param i     * @param place
     */
    public void fill(int i, Place place){
        places.set(i-1,place);
    }
    public List<Place> getPlaces() {
        return places;
    }
}