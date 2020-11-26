package com.example.gescov.ViewLayer.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.Volley;
import com.example.gescov.DomainLayer.Services.Volley.VolleyServices;
import com.example.gescov.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import java.util.List;

public class MapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
    private GoogleMap mMap;
  //  private List<ClusterMarker> markers;



        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
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
            getMarker();
        }


        private void getMarker() {
            LatLng fibMarker = new LatLng(41.38967, 2.11339);
            LatLng eetacMarker = new LatLng(41.2762922640011, 1.987153526989175);
            LatLng etsabMarker = new LatLng(41.38515737060989, 2.1138167576710107);
            mMap.addCircle(new CircleOptions().center(eetacMarker).radius(25.0).strokeColor(Color.RED).fillColor(Color.RED));
            mMap.addCircle(new CircleOptions().center(etsabMarker).radius(25.0).strokeColor(Color.RED).fillColor(Color.RED));
            mMap.addCircle(new CircleOptions().center(fibMarker).radius(50.0).strokeColor(Color.RED).fillColor(Color.RED));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(fibMarker,13),2000,null);
            mMap.setMaxZoomPreference(17);
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
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_frag_id);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}