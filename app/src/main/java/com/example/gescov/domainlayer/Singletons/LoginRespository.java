package com.example.gescov.domainlayer.Singletons;

import androidx.annotation.NonNull;

import com.example.gescov.domainlayer.Singletons.DomainControlFactory;
import com.example.gescov.viewlayer.Singletons.GescovApplication;
import com.example.gescov.viewlayer.Singletons.LoggedInUser;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class LoginRespository {

    private static GoogleSignInClient client;
    private static GoogleSignInOptions gso;
    private static GoogleSignInAccount googleSignInAccount;

    public LoginRespository() {}

    public static GoogleSignInClient getGoogleSignInClient(String serverClientID) {
        initClientAndGso(serverClientID);
        return client;
    }

    public static void checkuserAlreadyLoggedInOnthisDevice(String serverClientID) {
        initClientAndGso(serverClientID);
        Task<GoogleSignInAccount> task = client.silentSignIn();
        if (task.isSuccessful()) {
            googleSignInAccount = task.getResult();
            LoggedInUser.setCurrentLoggedUser(googleSignInAccount);
            DomainControlFactory.getModelController().setLoginResul(false);
        } else {
            task.addOnCompleteListener(new OnCompleteListener<GoogleSignInAccount>() {
                @Override
                public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                    try {
                        googleSignInAccount = task.getResult(ApiException.class);
                        LoggedInUser.setCurrentLoggedUser(googleSignInAccount);
                        DomainControlFactory.getModelController().setLoginResul(false);
                    } catch (ApiException apiException) {
                        //no user logged in
                        DomainControlFactory.getModelController().setLoginResul(true);
                    }
                }
            });
        }
    }

    private static void initClientAndGso(String serverClientID) {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(serverClientID)
                .requestEmail()
                .build();
        client = GoogleSignIn.getClient(GescovApplication.getContext(),gso);
    }

    public static String getToken() {
        return googleSignInAccount.getIdToken();
    }

    public static String getUserEmail() {
        return googleSignInAccount.getEmail();
    }
}
