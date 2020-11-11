package com.example.gescov.ViewLayer.MainView;

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

public class MainViewActivity extends AppCompatActivity {

    private GoogleSignInAccount account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        CountDownTimer timer = new CountDownTimer(1500,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                checkUserLoggedIn();
            }
        }.start();
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