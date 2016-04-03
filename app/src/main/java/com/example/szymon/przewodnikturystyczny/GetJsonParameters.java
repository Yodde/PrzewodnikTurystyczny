package com.example.szymon.przewodnikturystyczny;

import android.content.Context;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Szymon on 04.04.2016.
 * Class store parameters for GetJson:
 * context, url, mode
 */
public class GetJsonParameters {
    Context context;
    String url;
    /**
    * Mode:
     * 0- get all places
     * 1- get details about one place;
    */
    int mode;

    public GetJsonParameters() {
        context = null;
        url = null;
        mode = 0;
    }

    /**
     * @param context Current context.
     * @param  url JSON's URL as String.
     * @param mode Mode 0- for get all places, 1- for get detailed info abaut one place.
     */
    public GetJsonParameters(Context context, String url, int mode) {
        this.context = context;
        this.url = url;
        this.mode = mode;
    }
    /**
     * In this case mode is 0- get all places.
     * @param context Current context.
     * @param  url JSON's URL as String.
     */
    public GetJsonParameters(Context context, String url) {
        this.context = context;
        this.url = url;
        this.mode = 0;
    }

    public Context getContext() {
        return context;
    }

    public URL getUrl() {
        try {
            URL temp = new URL(url);
            return temp;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getMode() {
        return mode;
    }
}
