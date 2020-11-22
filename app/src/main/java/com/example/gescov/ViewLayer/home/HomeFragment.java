package com.example.gescov.ViewLayer.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;
import com.example.gescov.ViewLayer.UpdateUserProfile.UpdateUserProfileActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Intent intent;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        Button takeTest = (Button) root.findViewById(R.id.takeTest);
        Button report = (Button) root.findViewById(R.id.report);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(),CovidNotificationActivity.class);
                startActivity(intent);
            }
        });

        takeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(),DailyTestActivity.class);
                startActivity(intent);
            }
        });

        initUpdateUserProfileButton();
        return root;
    }

    private void initUpdateUserProfileButton() {
        Button updateProfileButton = (Button) root.findViewById(R.id.update_profile_button);
        updateProfileButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), UpdateUserProfileActivity.class);
                        startActivity(i);
                    }
                }
        );
    }
}