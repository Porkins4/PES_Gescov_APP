package com.example.gescov.ViewLayer.SignUpIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gescov.LoggedInUser;
import com.example.gescov.R;
import com.example.gescov.ViewLayer.navigation.NavigationMenu;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 200;
    private GoogleSignInClient googleSignInClient;
    private GoogleSignInAccount account;
    private ImageView appLogo;
    private TextView loginMessage;
    private SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        initView();
        initGoogleClient();
        initGoogleButton();
    }

    private void initView() {
        signInButton = (SignInButton) findViewById(R.id.sign_in_button_login_activity);
        appLogo = (ImageView) findViewById(R.id.app_logo_login_activity);
        loginMessage = (TextView) findViewById(R.id.login_message_login_activity);
    }

    private void initGoogleButton() {
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }

        });
        signInButton.setSize(SignInButton.SIZE_STANDARD);
    }

    private void initGoogleClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this,gso);
    }

    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            account = task.getResult(ApiException.class);
            succesfullLoginUI();
        } catch (ApiException e) {
            System.out.println("bruh");
        }
    }

    private void succesfullLoginUI() {
        LoggedInUser.setCurrentLoggedUser(account);
        String welcome = getString(R.string.welcome) + " " + LoggedInUser.getUserName();
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, NavigationMenu.class);
        startActivity(i);
    }
}