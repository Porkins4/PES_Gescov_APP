package com.example.gescov.viewlayer.Singletons;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class LoggedInUser {

    private static GoogleSignInAccount account;

    public static void setCurrentLoggedUser(GoogleSignInAccount newAccount) {
        account = newAccount;
    }

    public static String getUserName() {
        return account.getDisplayName();
    }

    public static String getPhotoURL() {
        return account.getPhotoUrl().toString();
    }

    public static String getToken() {
        return account.getIdToken();
    }

    public static String getEmail() {return account.getEmail();}

}