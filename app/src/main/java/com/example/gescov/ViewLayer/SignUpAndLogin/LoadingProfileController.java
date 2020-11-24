package com.example.gescov.ViewLayer.SignUpAndLogin;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.DomainLayer.Classmodels.User;
import com.example.gescov.ViewLayer.Singletons.PresentationControlFactory;

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

    public void retrieveUserInformation() {
        PresentationControlFactory.getViewLayerController().retrieveUserInformation();
    }

    public void setUserRetrieveResult(boolean error) {
        loadingProfileViewModel.setUserRetrieveResult(error);
    }

    public User getLoggedInUser() {
        return PresentationControlFactory.getViewLayerController().getUserLoggedIn();
    }
}
