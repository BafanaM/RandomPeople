package com.example.randompeopledemo.model;

import com.google.gson.annotations.SerializedName;

public class Id {
    @SerializedName("name")
    public String name;
    @SerializedName("value")
    public Object value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
