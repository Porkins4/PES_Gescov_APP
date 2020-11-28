package com.example.gescov.ViewLayer.Map;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.R;
import com.example.gescov.ViewLayer.Exceptions.AdapterNotSetException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;

import org.json.JSONException;

import java.util.List;


public class MapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
    private GoogleMap mMap;



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
            } catch (Resources.NotFoundException e ) {
                Log.e("MapFragment", "Can't find style. Error: ", e);
            }
            getSchools();
        }

        private void getSchools() {
            mapVIewModel = new ViewModelProvider(getActivity()).get(MapVIewModel .class);
            mapVIewModel.getSchools().observe(getActivity(),
                    schools -> setMarkers(schools)
            );


        }

        private void setMarkers(List<Pair<School, Integer>> schools) {
            for (int i = 0; i < schools.size(); ++i) {
                Integer numContagion = schools.get(i).second;
                double longitude = schools.get(i).first.getLongitude();
                double latitude = schools.get(i).first.getLatitude();
                if ( numContagion >= 7 ) {

                    mMap.addCircle(new CircleOptions().center(new LatLng(latitude,longitude)).radius(40.0).strokeColor(Color.RED).fillColor(Color.RED));
                }else if (numContagion == 0 ) {
                    mMap.addCircle(new CircleOptions().center(new LatLng(latitude,longitude)).radius(40.0).strokeColor(Color.GREEN).fillColor(Color.GREEN));
                }
                else {
                    mMap.addCircle(new CircleOptions().center(new LatLng(latitude,longitude)).radius(40.0).strokeColor(Color.YELLOW).fillColor(Color.YELLOW));
                }
            }
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(41.38967, 2.11339),13),2000,null);
            mMap.setMaxZoomPreference(17);
        }
    };

    private MapVIewModel mapVIewModel;






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