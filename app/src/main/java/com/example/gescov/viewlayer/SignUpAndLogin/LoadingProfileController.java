package com.example.gescov.viewlayer.SignUpAndLogin;

import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class LoadingProfileController {

    private LoadingProfileViewModel loadingProfileViewModel;

    public LoadingProfileController() {}


    public void setLoadingProfileViewModel(LoadingProfileViewModel loadingProfileViewModel) {
        this.loadingProfileViewModel = loadingProfileViewModel;
    }

    //------------------------------------
    //login

    public void setUserLoggedIn(String serverClientID) {
        PresentationControlFactory.getViewLayerController().setUserLoggedIn(serverClientID);
    }

    public void setLoginResult(boolean result) {
        loadingProfileViewModel.setLoginResult(result);
    }

    public void getUserID() {
        PresentationControlFactory.getViewLayerController().getUserID();
    }

    public void setUserIDVerificationResult(boolean error) {
        loadingProfileViewModel.setUserIDVerificationResult(error);
    }

    public void refreshLoggedUser() {
        PresentationControlFactory.getViewLayerController().refreshLoggedUser();
    }

    public void setUserRetrieveResult(boolean error) {
        loadingProfileViewModel.setUserRetrieveResult(error);
    }

    public User getLoggedInUser() {
        return PresentationControlFactory.getViewLayerController().getUserLoggedIn();
    }

    public static GoogleSignInClient getGoogleSignInClient(String serverClientID) {
        return PresentationControlFactory.getViewLayerController().getGoogleSignInClient(serverClientID);
    }
}
