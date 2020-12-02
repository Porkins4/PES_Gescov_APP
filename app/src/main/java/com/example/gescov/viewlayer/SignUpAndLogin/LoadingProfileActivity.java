package com.example.gescov.viewlayer.SignUpAndLogin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gescov.R;
import com.example.gescov.viewlayer.navigation.NavigationMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


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
                    } else System.out.println("error with token verification");
                }
        );
    }

    private void retrieveUserInformation() {
        loadingProfileViewModel.getUserInformation().observe(this,
                error -> {
                    if (!error) {
                        succesfulLogin();
                    } else System.out.println("error while gettin user info");
                }
        );
    }

    public void succesfulLogin() {
        String welcome = getString(R.string.welcome) + " " + loadingProfileViewModel.getLoggedInUser().getName();
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, NavigationMenu.class);
        startActivity(i);
    }

    private void startLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setVerificationListener();
    }
}