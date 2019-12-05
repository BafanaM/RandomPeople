package com.example.randompeopledemo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.randompeopledemo.model.PeopleRepositoryImplementation;
import com.example.randompeopledemo.model.ResultsResponse;

public class PeopleListViewModel extends AndroidViewModel {
    private LiveData<ResultsResponse> resultsResponseLiveData;

    public PeopleListViewModel(@NonNull Application application) {
        super(application);
        PeopleRepositoryImplementation catRepositoryImplementation = new PeopleRepositoryImplementation();
        int TOTAL_RESULTS = 10;
        resultsResponseLiveData = catRepositoryImplementation.getResultsResponseLiveData(TOTAL_RESULTS);
    }

    public LiveData<ResultsResponse> getPeopleListLiveData() {
        return resultsResponseLiveData;
    }
}
