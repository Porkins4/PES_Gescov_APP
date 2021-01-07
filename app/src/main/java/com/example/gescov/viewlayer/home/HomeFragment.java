package com.example.gescov.viewlayer.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.Singletons.GescovApplication;
import com.example.gescov.viewlayer.Singletons.LoggedInUser;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Intent intent;
    private View root;
    private Button riskButton,takeTest;
    private TextView nameText;
    private User user;
    private CircleImageView userImage;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int SUCCESS_NOTIFICATION_COVID = 200;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        PresentationControlFactory.setApplicationContext(getContext().getApplicationContext());

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        checkPermision();
        PresentationControlFactory.setViewModelProvider(new ViewModelProvider(this));
        homeViewModel = PresentationControlFactory.getViewModelProvider().get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);


        takeTest = root.findViewById(R.id.takeTest);
        String url = LoggedInUser.getPhotoURL();
        userImage = root.findViewById(R.id.profile_image);
        loadImageFromUrl(url);
        Button report = root.findViewById(R.id.report);

        nameText = root.findViewById(R.id.home_user_name);
        nameText.setText("");
        riskButton = root.findViewById(R.id.home_risk_button);

        String contagionID = PresentationControlFactory.getContagionController().getIdContagion();
        takeTest.setEnabled(contagionID != null);
        user = PresentationControlFactory.getViewLayerController().getLoggedUserInfo();

        homeViewModel.getRisk().observe((LifecycleOwner) getContext(), e ->
                refreshActivity()
        );

        riskButton.setOnClickListener(e ->
                PresentationControlFactory.getViewLayerController().updateLoggedUserRisk()
        );

        report.setOnClickListener(v -> {
            intent = new Intent(getActivity(), CovidNotificationActivity.class);
            startActivityForResult(intent, SUCCESS_NOTIFICATION_COVID);
        });

        takeTest.setOnClickListener(v -> {
            intent = new Intent(getActivity(), DailyTestActivity.class);
            startActivity(intent);
        });

        initViewComponents();

        initNotification();


        return root;
    }

    private void initNotification() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    String token = task.getResult();
                    homeViewModel.setUserToken(token);
                    GescovApplication.setNotToken(token);
                });
    }

    private void checkPermision() {
        if (getActivity().getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)  {
            Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
            locationResult.addOnCompleteListener(getActivity(), task -> {
                if ( task.isSuccessful()) {
                    Location userLoc = task.getResult();
                    PresentationControlFactory.getHomeController().setLocation(userLoc);
                }
            });

        }else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
    }


    private void initViewComponents() {
        TextView userName = (TextView) root.findViewById(R.id.home_user_name);
        userName.setText(user.getName());
    }
  
    public void refreshActivity() {
        riskButton.setText(getResources().getText(homeViewModel.getRisk().getValue() ? R.string.home_risk : R.string.home_not_risk));
        nameText.setText(user.getName());

    }

    public void loadImageFromUrl(String url ) {
       Picasso.with(this.getContext()).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).noFade().into(userImage, new com.squareup.picasso.Callback() {
           public void onSuccess() {
               //it returns nothing
           }
           @Override
           public void onError() {
               Log.i("loadingImage","error on loading image");
           }
       });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if ( requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted. Continue the action or workflow
                // in your app.
                checkPermision();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SUCCESS_NOTIFICATION_COVID && resultCode == 0) {
            String contagionID = PresentationControlFactory.getContagionController().getIdContagion();
            takeTest.setEnabled(contagionID != null);
        }
    }
}