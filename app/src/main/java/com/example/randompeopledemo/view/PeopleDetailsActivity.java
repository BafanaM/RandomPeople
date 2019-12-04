package com.example.randompeopledemo.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.randompeopledemo.R;
import com.example.randompeopledemo.databinding.ActivityPeopleDetailsBinding;
import com.example.randompeopledemo.model.Coordinates;
import com.example.randompeopledemo.model.Location;
import com.example.randompeopledemo.model.Result;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PeopleDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPeopleDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_people_details);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        String peopleResult = "people_entry";
        String location = "location";
        String imageUrl = "image_url";
        String coordinates = "coordinates";
        if (getIntent() != null && getIntent().hasExtra(peopleResult)
                && getIntent().hasExtra(imageUrl)
                && getIntent().hasExtra(location)) {

            Result result = getIntent().getParcelableExtra(peopleResult);
            Location locationObject = getIntent().getParcelableExtra(location);
            String thumbnailUrl = getIntent().getStringExtra(imageUrl);
            Coordinates coordinatesObject = getIntent().getParcelableExtra(coordinates);

            Glide.with(this)
                    .load(thumbnailUrl).dontAnimate().fitCenter().diskCacheStrategy(
                    DiskCacheStrategy.RESOURCE)
                    .placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder).into(binding.ivPerson);

            if (locationObject != null && result != null && coordinatesObject != null) {
                latitude = Double.parseDouble(coordinatesObject.getLatitude());
                longitude = Double.parseDouble(coordinatesObject.getLongitude());
                binding.tvStreetNumber.setText(String.format("%s%s", getString(R.string.street_number) + " ", String.valueOf(locationObject.getStreet().getNumber())));
                binding.tvStreetName.setText(String.format("%s%s", getString(R.string.street_name) + " ", locationObject.getStreet().getName()));
                binding.tvTel.setText(String.format("%s%s", getString(R.string.contact_number) + " ", result.getPhone()));
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Person is here"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
