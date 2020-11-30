package com.example.gescov.viewlayer.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.DomainLayer.Classmodels.User;
import com.example.gescov.R;
import com.example.gescov.viewlayer.Singletons.LoggedInUser;
import com.example.gescov.viewlayer.UpdateUserProfile.UpdateUserProfileActivity;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Intent intent;
    private View root;
    private Button riskButton;
    private TextView nameText;
    private User user;
    private ImageView userImage;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        PresentationControlFactory.setViewModelProvider(new ViewModelProvider(this));
        homeViewModel = PresentationControlFactory.getViewModelProvider().get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        Button takeTest = root.findViewById(R.id.takeTest);
        String url = LoggedInUser.getPhotoURL();
        userImage = root.findViewById(R.id.profile_image);
        loadImageFromUrl(url);
        Button report = root.findViewById(R.id.report);

        nameText = root.findViewById(R.id.home_user_name);
        nameText.setText("");
        riskButton = root.findViewById(R.id.home_risk_button);
        user = PresentationControlFactory.getViewLayerController().getLoggedUserInfo();

        homeViewModel.getRisk().observe((LifecycleOwner) getContext(), e ->
            refreshActivity()
        );

        riskButton.setOnClickListener(e ->
            PresentationControlFactory.getViewLayerController().updateLoggedUserRisk()
        );

        report.setOnClickListener(v -> {
            intent = new Intent(getActivity(),CovidNotificationActivity.class);
            startActivity(intent);
        });

        takeTest.setOnClickListener(v -> {
            intent = new Intent(getActivity(),DailyTestActivity.class);
            startActivity(intent);
        });

        initUpdateUserProfileButton();
        initViewComponents();

        return root;
    }

    private void initViewComponents() {
        TextView userName = (TextView) root.findViewById(R.id.home_user_name);
        userName.setText(user.getName());
    }

    private void initUpdateUserProfileButton() {
        Button updateProfileButton = (Button) root.findViewById(R.id.update_profile_button);
        updateProfileButton.setOnClickListener(
                v -> {
                    Intent i = new Intent(getActivity(), UpdateUserProfileActivity.class);
                    startActivity(i);
                }
        );
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
               Log.i("loadingImage","error on loading imagge");
           }
       });
    }
}