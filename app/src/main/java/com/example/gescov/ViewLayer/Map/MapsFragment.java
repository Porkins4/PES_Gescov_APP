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
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.Volley;
import com.example.gescov.DomainLayer.Services.Volley.VolleyServices;
import com.example.gescov.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import java.util.List;

public class MapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
    private LatLng plazacataluna = new LatLng(41.3870154, 2.1700471);
    private GoogleMap mMap;
  //  private List<ClusterMarker> markers;
    private ClusterManager mClusterManager;
    private MyClusterManagerRenderer mClusterManagerRenderer;



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
            try {

                mMap = googleMap;
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
            //mMap.addMarker(new MarkerOptions().position(plazacataluna).title("Marker"));
           // mMap.moveCamera(CameraUpdateFactory.newLatLng(plazacataluna));


        }


        private void getMarker() {
            // aqui hago la llamada al backend para obtener las coordenadas de los centros que tenemos en la base de datos(Utiliza List markers)
            /*LatLng fibMarker = new LatLng(41.38967, 2.11339);
            LatLng eetacMarker = new LatLng(41.27571169603351, 1.987292999720925);
            LatLng eetsabMarker = new LatLng(41.38450532434546, 2.113934772737321);
            mMap.addMarker(new MarkerOptions().position(fibMarker).title("Marker"));
            mMap.addMarker(new MarkerOptions().position(eetacMarker).title(""));
            mMap.addMarker(new MarkerOptions().position(eetsabMarker).title(""));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(fibMarker));*/

            if(mMap != null) {

                if (mClusterManager == null) {
                    System.out.println("iniiiiiiiii");
                    mClusterManager = new ClusterManager<ClusterMarker>(getActivity().getApplicationContext(), mMap);
                    //markers = new List<ClusterMarker>();

                }
                if (mClusterManagerRenderer == null) {
                    mClusterManagerRenderer = new MyClusterManagerRenderer(
                            getActivity(),
                            mMap,
                            mClusterManager
                    );
                    mClusterManager.setRenderer(mClusterManagerRenderer);
                }
                int image = R.drawable.red;
                ClusterMarker newClusterMarker = new ClusterMarker(new LatLng(41.38967, 2.11339),"FIB","",image);
                mClusterManager.addItem(newClusterMarker);
              //  markers.add(newClusterMarker);
                mClusterManager.cluster();
            }


        }
    };



    private String fineLocation = Manifest.permission.ACCESS_FINE_LOCATION;
    private String courseLocation = Manifest.permission.ACCESS_COARSE_LOCATION;
    private final int locationPermissionRequestCode = 1234;
    private boolean mLoactionPermissionsGranted;

    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(getContext(),fineLocation) == PackageManager.PERMISSION_GRANTED) {
            if ( ContextCompat.checkSelfPermission(getContext(),courseLocation) == PackageManager.PERMISSION_GRANTED ) {
                System.out.println("we have permission");
                mLoactionPermissionsGranted = true;
            } else {
                ActivityCompat.requestPermissions(getActivity(),permissions,locationPermissionRequestCode);
            }
        }
        System.out.println("fuuuuuuuuuuuu");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLoactionPermissionsGranted = false;
        switch ( requestCode) {
            case locationPermissionRequestCode :{
                if ( grantResults.length > 0 ) {
                    for ( int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLoactionPermissionsGranted = false;
                            return;
                        }
                    }
                    mLoactionPermissionsGranted = true;
                    //initialize our map
                }

            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getLocationPermission();
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