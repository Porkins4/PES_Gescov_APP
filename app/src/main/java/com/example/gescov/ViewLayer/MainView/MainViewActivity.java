package com.example.gescov.ViewLayer.MainView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.example.gescov.LoggedInUser;
import com.example.gescov.R;
import com.example.gescov.ViewLayer.SignUpIn.LoginActivity;
import com.example.gescov.ViewLayer.navigation.NavigationMenu;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainViewActivity extends AppCompatActivity {

    private GoogleSignInClient googleSignInClient;

    private GoogleSignInAccount account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        /*CountDownTimer timer = new CountDownTimer(1500,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        }.start();*/
        initGoogleClient();
    }

    private void initGoogleClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this,gso);
        googleSignInClient.silentSignIn()
                .addOnCompleteListener(
                        this,
                        new OnCompleteListener<GoogleSignInAccount>() {
                            @Override
                            public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                                handleSignInResult(task);
                            }
                        });
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            LoggedInUser.setCurrentLoggedUser(account);
            succesfullLoginUI();
            // TODO(developer): send ID Token to server and validate
        } catch (ApiException e) {
            checkUserLoggedIn();
        }
    }

    private void startLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    private void checkUserLoggedIn() {
        account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) succesfullLoginUI();
        else startLoginActivity();
    }

    private void succesfullLoginUI() {
        LoggedInUser.setCurrentLoggedUser(account);
        String welcome = getString(R.string.welcome) + " " + LoggedInUser.getUserName();
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, NavigationMenu.class);
        startActivity(i);
    }
}