package com.example.randompeopledemo.serviceapi;

import com.example.randompeopledemo.model.ResultsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PeopleServiceApi {

    @GET("?")
    Call<ResultsResponse> getListOfPeople(@Query("results") int resultsPerPage);
}
