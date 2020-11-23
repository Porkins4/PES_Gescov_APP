package com.example.gescov.ViewLayer.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.example.gescov.ViewLayer.Singletons.LoggedInUser;
import com.example.gescov.ViewLayer.UpdateUserProfile.UpdateUserProfileActivity;
import com.example.gescov.ViewLayer.Singletons.PresentationControlFactory;

import java.io.IOException;
import java.io.InputStream;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Intent intent;
    private View root;
    private Button riskButton;
    private TextView nameText;
    private User user;
   
    
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        PresentationControlFactory.setViewModelProvider(new ViewModelProvider(this));
        homeViewModel = PresentationControlFactory.getViewModelProvider().get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        Button takeTest = (Button) root.findViewById(R.id.takeTest);
        Button report = (Button) root.findViewById(R.id.report);

        nameText = root.findViewById(R.id.home_user_name);
        nameText.setText("");
        riskButton = root.findViewById(R.id.home_risk_button);
        user = PresentationControlFactory.getViewLayerController().getLoggedUserInfo();

        homeViewModel.getRisk().observe((LifecycleOwner) getContext(), e -> {
            refreshActivity();
        });

        riskButton.setOnClickListener(e -> {
            PresentationControlFactory.getViewLayerController().updateLoggedUserRisk();
        });

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
        initViewComponents();
        LoadImage loadImage = new LoadImage((ImageView) root.findViewById(R.id.user_image_home));

        loadImage.execute(LoggedInUser.getPhotoURL());
        return root;
    }

    private void initViewComponents() {
        TextView userName = (TextView) root.findViewById(R.id.home_user_name);
        userName.setText(user.getName());
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
  
    public void refreshActivity() {
        riskButton.setText(getResources().getText(homeViewModel.getRisk().getValue() ? R.string.home_risk : R.string.home_not_risk));
        nameText.setText(user.getName());

    }

    private class LoadImage extends AsyncTask<String,Void, Bitmap> {

        ImageView imageView;
        public LoadImage(ImageView iv) {
            this.imageView = iv;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];
            Bitmap b = null;
            try {
                InputStream is = new java.net.URL(url).openStream();
                b = BitmapFactory.decodeStream(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return b;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }
    }
}