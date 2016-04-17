package com.example.szymon.przewodnikturystyczny;

/**
 * Created by Szymon on 15.04.2016.
 */
public interface AsyncResponse<T> {
    void processFinish(T output);
}
