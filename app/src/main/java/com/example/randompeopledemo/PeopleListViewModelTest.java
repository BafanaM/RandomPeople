package com.example.randompeopledemo;


import android.app.Application;

import androidx.lifecycle.Observer;

import com.example.randompeopledemo.model.PeopleListViewModel;
import com.example.randompeopledemo.model.Result;
import com.example.randompeopledemo.model.ResultsResponse;
import com.example.randompeopledemo.serviceapi.PeopleServiceApi;
import com.example.randompeopledemo.serviceapi.PeopleServiceApiClient;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class PeopleListViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    PeopleServiceApi peopleServiceApi;
    @Mock
    PeopleServiceApiClient peopleServiceApiClient;
    private PeopleListViewModel viewModel;
    @Mock
    Observer<ResultsResponse> observer;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        viewModel = new PeopleListViewModel(this);
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
        when(peopleServiceApi.getListOfPeople(10)).thenReturn(Single.just(new ResultsResponse()));
        viewModel.getPeopleListLiveData();

    }

    @Test
    public void testApiFetchDataError() {
        when(peopleServiceApiClient).thenReturn(Single.error(new Throwable("Api error")));
        viewModel.getPeopleListLiveData();

    }

    @After
    public void tearDown() throws Exception {
        peopleServiceApiClient = null;
        viewModel = null;
    }
}
