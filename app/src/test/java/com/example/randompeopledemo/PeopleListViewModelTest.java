package com.example.randompeopledemo;


import android.app.Application;

import androidx.lifecycle.Observer;

import com.example.randompeopledemo.viewModel.PeopleListViewModel;
import com.example.randompeopledemo.model.ResultsResponse;
import com.example.randompeopledemo.serviceapi.PeopleServiceApi;
import com.example.randompeopledemo.serviceapi.PeopleServiceApiClient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class PeopleListViewModelTest {

    @Mock
    private PeopleServiceApi peopleServiceApi;
    @Mock
    PeopleServiceApiClient peopleServiceApiClient;
    private PeopleListViewModel viewModel;
    @Mock
    Observer<ResultsResponse> observer;
    @Mock Application application;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        viewModel = new PeopleListViewModel(application);
        viewModel.getPeopleListLiveData().observeForever(observer);
    }

    @Test
    public void testNull() {
        when(peopleServiceApiClient).thenReturn(null);
        assertNotNull(viewModel.getPeopleListLiveData());
        assertTrue(viewModel.getPeopleListLiveData().hasObservers());
    }

    @Test
    public void testApiFetchDataSuccess() {
        when(peopleServiceApi.getListOfPeople(10)).getMock();
        viewModel.getPeopleListLiveData().getValue();

    }

    @Test
    public void testApiFetchDataError() {
        viewModel.getPeopleListLiveData().getValue();

    }

    @After
    public void tearDown() throws Exception {
        peopleServiceApiClient = null;
        viewModel = null;
    }
}
