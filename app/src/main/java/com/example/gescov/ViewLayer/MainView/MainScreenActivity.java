package com.example.gescov.ViewLayer.MainView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.gescov.LoggedInUser;
import com.example.gescov.R;
import com.example.gescov.ViewLayer.PresentationControlFactory;
import com.example.gescov.ViewLayer.SignUpIn.LoginActivity;
import com.example.gescov.ViewLayer.navigation.NavigationMenu;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainScreenActivity extends AppCompatActivity {

    private GoogleSignInClient googleSignInClient;
    private MainScreenViewModel mainScreenViewModel;

    private GoogleSignInAccount account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        initGoogleClient();
    }

    private void setResponseListener(String token) {
        mainScreenViewModel = new ViewModelProvider(this).get(MainScreenViewModel.class);
        mainScreenViewModel.setToken(token);
        mainScreenViewModel.getRequestResult().observe(this, new Observer<TokenVerificationResult>() {
            @Override
            public void onChanged(TokenVerificationResult tokenVerificationResult) {
                if (tokenVerificationResult.getSuccess()) {
                    updateUserId();
                    updateUserName();
                    getTypeProfile();
                    showMenu();
                }
            }
        });
    }

    private void getTypeProfile() {
        PresentationControlFactory.getMainScreenController().getTypeProfile();
    }

    private void updateUserName() {
        PresentationControlFactory.getMainScreenController().updateUserName(LoggedInUser.getUserName());
    }


    private void updateUserId() {
        PresentationControlFactory.getMainScreenController().updateUserId(mainScreenViewModel.getRequestResult().getValue().getUserId());
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
            this.account = account;
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
        else {
            logout();
        }
    }

    private void logout() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this,gso);
        googleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        startLoginActivity();
                    }
                });
    }

    private void succesfullLoginUI() {
        LoggedInUser.setCurrentLoggedUser(account);
        setResponseListener(account.getIdToken());
    }

    private void showMenu() {
        String welcome = getString(R.string.welcome) + " " + LoggedInUser.getUserName();
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, NavigationMenu.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initGoogleClient();
    }
}