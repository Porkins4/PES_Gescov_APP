package com.example.gescov.viewlayer.Map;

import android.Manifest;

import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.R;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;


import java.util.List;


public class MapsFragment extends Fragment {
    private GoogleMap mMap;
    private MapVIewModel mapVIewModel;
    private Location userLocation;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {


        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            try {
                boolean success = mMap.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(
                                getContext(), R.raw.mapstyle));
                if (!success) {
                    Log.e("MapFragment", "Style parsing failed.");
                }
            } catch (Resources.NotFoundException e) {
                Log.e("MapFragment", "Can't find style. Error: ", e);
            }
            getSchools();
            LatLng locUser;
            locUser = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(locUser, 13), 2700, null);
            mMap.setMaxZoomPreference(17);
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);

        }

        private void getSchools() {
            mapVIewModel = new ViewModelProvider(getActivity()).get(MapVIewModel.class);
            mapVIewModel.getSchools().observe(getActivity(),
                    schools -> setMarkers(schools)
            );
        }


        private void setMarkers(List<Pair<School, Integer>> schools) {
            for (int i = 0; i < schools.size(); ++i) {
                Integer numContagion = schools.get(i).second;
                double longitude = schools.get(i).first.getLongitude();
                double latitude = schools.get(i).first.getLatitude();
                System.out.println(longitude + ',' + latitude);
                if (numContagion >= 7) {
                    mMap.addCircle(new CircleOptions().center(new LatLng(latitude, longitude)).radius(60.0).strokeColor(Color.argb(130, 150, 50, 50)).fillColor(Color.argb(130, 150, 50, 50)));
                } else if (numContagion == 0) {
                    mMap.addCircle(new CircleOptions().center(new LatLng(latitude, longitude)).radius(50.0).strokeColor(Color.argb(130, 50, 150, 50)).fillColor(Color.argb(130, 50, 150, 50)));
                } else {
                    System.out.println("putillllllaaa");
                    mMap.addCircle(new CircleOptions().center(new LatLng(latitude, longitude)).radius(40.0).strokeColor(Color.argb(130, 150, 150, 50)).fillColor(Color.argb(130, 150, 150, 50)));
                }
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userLocation = PresentationControlFactory.getMapController().getLocation();
        if ( userLocation == null) {
            userLocation = new Location("default");
            userLocation.setLatitude(41.38453674316406);
            userLocation.setLongitude(2.1137747764587402);
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_frag_id);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}