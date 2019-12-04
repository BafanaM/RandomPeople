package com.example.randompeopledemo.serviceapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeopleServiceApiClient {
    private static final String BASE_URL = "https://api.randomuser.me/";
    public static PeopleServiceApi peopleServiceApi;

    private PeopleServiceApiClient() {

    }

    public static PeopleServiceApi getInstance() {
        Retrofit retrofit;
        if (peopleServiceApi == null) {
            Gson gson = new GsonBuilder().create();
            retrofit = new Retrofit.Builder().baseUrl(PeopleServiceApiClient.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();
            peopleServiceApi = retrofit.create(PeopleServiceApi.class);
        }
        return peopleServiceApi;
    }
}
