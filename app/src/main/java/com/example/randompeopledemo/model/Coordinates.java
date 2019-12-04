package com.example.randompeopledemo.model;

import com.google.gson.annotations.SerializedName;

public class Coordinates {
    @SerializedName("latitude")
    public String latitude;
    @SerializedName("longitude")
    public String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}