package com.example.gescov.viewlayer.SignUpAndLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.gescov.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.common.SignInButton;

public class LoginActivity extends AppCompatActivity {

    private static final int SUCCESSDUL_SIGN_IN = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initGoogleButton();
    }

    private void initGoogleButton() {
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button_login_activity);
        signInButton.setOnClickListener(v -> {
            Intent signInIntent = LoadingProfileController.getGoogleSignInClient(getString(R.string.server_client_id)).getSignInIntent();
            startActivityForResult(signInIntent, SUCCESSDUL_SIGN_IN);
        });
        signInButton.setSize(SignInButton.SIZE_WIDE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SUCCESSDUL_SIGN_IN && (GoogleSignIn.getLastSignedInAccount(this)) != null) {
            Intent i = new Intent(this, LoadingProfileActivity.class);
            startActivity(i);
        }
    }
}