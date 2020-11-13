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

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private HomeController homeController;
    private Intent intent;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        Button takeTest = (Button) root.findViewById(R.id.takeTest);
        Button report = (Button) root.findViewById(R.id.report);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(),CovidNotificationActivity.class);
                intent.putExtra("NameInfected","Victor Martinez");
                intent.putExtra("School","FIB");
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


        return root;
    }
}