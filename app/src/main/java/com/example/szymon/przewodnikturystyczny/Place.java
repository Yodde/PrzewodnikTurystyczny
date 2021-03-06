package com.example.szymon.przewodnikturystyczny;

/**
 * Created by Szymon on 03.04.2016.
 */
public class Place {
    int id;
    String name;
    double longitude;
    double latitude;
    String shortDescription;
    String description;
    String address;
    boolean allInfoDownloaded;

    public Place() {

    }
    public void setAllInfoDownloaded(boolean bool){
        allInfoDownloaded = bool;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Place(int id, String name, double longitude, double latitude) {
        super();
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        shortDescription = null;
        description = null;
        address = null;
        allInfoDownloaded=false;
    }

    public Place(int id, String name, double longitude, double latitude, String shortDescription, String description, String address) {

        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.shortDescription = shortDescription;
        this.description = description;
        this.address = address;
        allInfoDownloaded = true;
    }
    @Override
    public String toString(){
            return this.id + " " + this.name + "\n";
    }
    public String allInfo(){
        if(allInfoDownloaded){
            return this.id + " " + this.name + "\n" + this.shortDescription + "\n" + this.address;
        }
        else
            return this.id + " " + this.name + "\n";
    }
}