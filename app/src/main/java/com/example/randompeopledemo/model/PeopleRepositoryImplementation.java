package com.example.randompeopledemo.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.randompeopledemo.serviceapi.PeopleServiceApi;
import com.example.randompeopledemo.serviceapi.PeopleServiceApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleRepositoryImplementation {
    private PeopleServiceApi peopleServiceApi;


    public PeopleRepositoryImplementation() {
        peopleServiceApi = PeopleServiceApiClient.getInstance();
    }

    public LiveData<ResultsResponse> getResultsResponseLiveData(int numberPeople) {
        final MutableLiveData<ResultsResponse> data = new MutableLiveData<>();
        peopleServiceApi.getListOfPeople(numberPeople).enqueue(new Callback<ResultsResponse>() {
            @Override
            public void onResponse(Call<ResultsResponse> call, Response<ResultsResponse> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                    Log.i("Passed", "Passed");
                }
            }

            @Override
            public void onFailure(Call<ResultsResponse> call, Throwable throwable) {
                Log.i("Failed", "failed");
                data.setValue(new ResultsResponse(throwable));
            }
        });

        return data;
    }
}
