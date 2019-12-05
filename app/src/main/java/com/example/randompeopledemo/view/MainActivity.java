package com.example.randompeopledemo.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.randompeopledemo.R;
import com.example.randompeopledemo.databinding.ActivityMainBinding;
import com.example.randompeopledemo.model.Result;
import com.example.randompeopledemo.model.ResultsResponse;
import com.example.randompeopledemo.viewModel.PeopleListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PeopleListAdapter.ItemOnclickListener {

    private PeopleListAdapter peopleListAdapter;
    private ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        loadingDialog = new ProgressDialog(this);
        showProgressDialog();

        peopleListAdapter = new PeopleListAdapter(this);
        binding.peopleListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.peopleListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.peopleListRecyclerView.setAdapter(peopleListAdapter);
        setUpViewModelData();
    }

    @Override
    public void onItemClicked(Result result) {
        Intent intent = new Intent(this, PeopleDetailsActivity.class);
        intent.putExtra("people_entry", result);
        intent.putExtra("image_url", result.getPicture().getThumbnail());
        intent.putExtra("location", result.getLocation());
        intent.putExtra("coordinates", result.getLocation().getCoordinates());
        startActivity(intent);
    }


    private void setUpViewModelData() {
        PeopleListViewModel viewModel = ViewModelProviders.of(this).get(PeopleListViewModel.class);
        ResultsResponse resultsResponse = viewModel.getPeopleListLiveData().getValue();

        if (!isNetworkConnectionAvailable()) {
            dismissProgressDialog();
            showCustomDialog(getString(R.string.network_error));
        }

        if (resultsResponse != null) {
            if (resultsResponse.getThrowable() == null) {
                dismissProgressDialog();
            } else if (resultsResponse.getThrowable() != null) {
                dismissProgressDialog();
                showCustomDialog("Unable to ping server");
//                showAlertDialogWithAutoDismiss();
            }
        }

        viewModel.getPeopleListLiveData().observe(this, resultsResponse1 -> {

            List<Result> results = resultsResponse1.getResults();
            peopleListAdapter.setPeopleListItems(results);
            MainActivity.this.dismissProgressDialog();
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showProgressDialog() {
        if (loadingDialog != null) {
            loadingDialog.setTitle(getString(R.string.places_loading));
            loadingDialog.show();
            loadingDialog.setMessage(getString(R.string.please_wait_message));
            loadingDialog.setIndeterminate(true);
        }
    }

    public void dismissProgressDialog() {
        loadingDialog.dismiss();
    }

    public void showCustomDialog(String titleText) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titleText);
        builder.setIcon(R.drawable.ic_error_black_24dp);
        builder.setNegativeButton(R.string.cancel_text, (dialog, which) -> {
            MainActivity.this.finish();
            dialog.dismiss();
        })
                .setPositiveButton(R.string.retry, (dialog, which) -> setUpViewModelData());
        builder.show().setCancelable(false);
    }

    boolean isNetworkConnectionAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        assert connectivityManager != null;
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null) {
            return false;
        }
        NetworkInfo.State network = info.getState();
        return (network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING);
    }
}
