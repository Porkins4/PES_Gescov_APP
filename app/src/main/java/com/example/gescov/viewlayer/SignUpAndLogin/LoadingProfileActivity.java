package com.example.gescov.viewlayer.SignUpAndLogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gescov.R;
import com.example.gescov.viewlayer.navigation.NavigationMenu;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class LoadingProfileActivity extends AppCompatActivity {

    private GoogleSignInClient googleSignInClient;
    private LoadingProfileViewModel loadingProfileViewModel;

    private GoogleSignInAccount account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        loadingProfileViewModel = new ViewModelProvider(this).get(LoadingProfileViewModel.class);
        setVerificationListener();
        //initGoogleClient();

    }

    private void setVerificationListener() {
        loadingProfileViewModel.getLoginResult(getString(R.string.server_client_id)).observe(this,
                new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean error) {
                        if (error) {//error while logging in
                            startLoginActivity();
                        } else {//User logged in
                            setIdentifierListener();
                        }
                    }
                }
        );
    }

    private void setIdentifierListener() {
        loadingProfileViewModel.getUserID().observe(this,
                new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean error) {
                        if (!error) {
                            retrieveUserInformation();
                        } else System.out.println("error with token verification");
                    }
                }
        );
    }

    private void retrieveUserInformation() {
        loadingProfileViewModel.getUserInformation().observe(this,
                new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean error) {
                        if (!error) {
                            succesfulLogin();
                        } else System.out.println("error while gettin user info");
                    }
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

    protected void onResume() {
        super.onResume();
        setVerificationListener();
    }
}