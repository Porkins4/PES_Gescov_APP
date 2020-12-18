package com.example.gescov.viewlayer.SignUpAndLogin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;
import com.example.gescov.viewlayer.navigation.NavigationMenu;


public class LoadingProfileActivity extends AppCompatActivity {

    private LoadingProfileViewModel loadingProfileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        loadingProfileViewModel = new ViewModelProvider(this).get(LoadingProfileViewModel.class);
        setVerificationListener();

    }

    private void setVerificationListener() {
        loadingProfileViewModel.getLoginResult(getString(R.string.server_client_id)).observe(this,
                error -> {
                    if (error) {//error while logging in
                        startLoginActivity();
                    } else {//User logged in
                        setIdentifierListener();
                    }
                }
        );
    }

    private void setIdentifierListener() {
        loadingProfileViewModel.getUserID().observe(this,
                error -> {
                    if (!error) {
                        retrieveUserInformation();
                    } else loadingProfileViewModel.retryVerifyToken();
                }
        );
    }

    private void retrieveUserInformation() {
        loadingProfileViewModel.getUserInformation().observe(this,
                error -> {
                    if (!error) {
                        succesfulLogin();
                    }
                }
        );
    }

    public void succesfulLogin() {
        String welcome = getString(R.string.welcome) + " " + loadingProfileViewModel.getLoggedInUser().getName();
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, NavigationMenu.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    private void startLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setVerificationListener();
    }
}