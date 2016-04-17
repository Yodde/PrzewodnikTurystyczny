package com.example.szymon.przewodnikturystyczny;

/**
 * Created by Szymon on 16.04.2016.
 */
public class JsonInvalidParameters extends Exception {
    public JsonInvalidParameters(String message){
        super(message);
    }
    public JsonInvalidParameters(){
    }
    public String message(){
        return "Invaild address. No such parameters. Success = 0";
    }
}
