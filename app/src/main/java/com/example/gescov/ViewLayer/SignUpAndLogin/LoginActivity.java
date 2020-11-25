package com.example.gescov.ViewLayer.SignUpAndLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gescov.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {

    private static final int SUCCESSDUL_SIGN_IN = 200;
    private GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initGoogleButton();
    }

    private void initGoogleButton() {
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button_login_activity);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = LoadingProfileController.getGoogleSignInClient(getString(R.string.server_client_id)).getSignInIntent();
                startActivityForResult(signInIntent, SUCCESSDUL_SIGN_IN);
            }

        });
        signInButton.setSize(SignInButton.SIZE_WIDE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SUCCESSDUL_SIGN_IN) {
            Intent i = new Intent(this, LoadingProfileActivity.class);
            startActivity(i);
        }
    }
}