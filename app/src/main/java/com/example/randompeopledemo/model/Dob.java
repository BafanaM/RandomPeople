package com.example.randompeopledemo.model;

import com.google.gson.annotations.SerializedName;

public class Dob {
    @SerializedName("date")
    public String date;
    @SerializedName("age")
    public Integer age;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
