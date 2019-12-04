package com.example.randompeopledemo.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.randompeopledemo.serviceapi.PeopleServiceApi;
import com.example.randompeopledemo.serviceapi.PeopleServiceApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleRepositoryImplementation {
    private PeopleServiceApi peopleServiceApi;

    PeopleRepositoryImplementation() {
        peopleServiceApi = PeopleServiceApiClient.getInstance();
    }

    public LiveData<ResultsResponse> getResultsResponseLiveData(int numberPeople) {
        final MutableLiveData<ResultsResponse> data = new MutableLiveData<>();
        peopleServiceApi.getListOfPeople(10).enqueue(new Callback<ResultsResponse>() {
            @Override
            public void onResponse(Call<ResultsResponse> call, Response<ResultsResponse> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResultsResponse> call, Throwable t) {

            }
        });

        return data;
    }
}
