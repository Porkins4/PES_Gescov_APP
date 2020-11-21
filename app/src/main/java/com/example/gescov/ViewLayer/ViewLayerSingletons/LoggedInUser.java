package com.example.gescov.ViewLayer.ViewLayerSingletons;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class LoggedInUser {

    private static GoogleSignInAccount account;

    public static void setCurrentLoggedUser(GoogleSignInAccount newAccount) {
        account = newAccount;
    }

    public static String getUserName() {
        return account.getDisplayName();
    }
}