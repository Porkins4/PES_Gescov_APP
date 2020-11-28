package com.example.gescov.ViewLayer.SignUpAndLogin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.DomainLayer.Classmodels.User;
import com.example.gescov.ViewLayer.Singletons.PresentationControlFactory;

public class LoadingProfileViewModel extends ViewModel {
    private MutableLiveData<Boolean> loginResult, userRetrieve, tokenVerificationResult;

    public LiveData<Boolean> getLoginResult(String serverClientID) {
        if (loginResult == null) {
            loginResult = new MutableLiveData<>();
            setUserLoggedIn(serverClientID);
        }
        return loginResult;
    }

    private void setUserLoggedIn(String serverClientID) {
        PresentationControlFactory.getLoadingProfileController().setLoadingProfileViewModel(this);
        PresentationControlFactory.getLoadingProfileController().setUserLoggedIn(serverClientID);
    }

    public void setLoginResult(boolean result) {
        loginResult.setValue(result);
    }

    public LiveData<Boolean> getUserID() {
        if (tokenVerificationResult == null) {
            tokenVerificationResult = new MutableLiveData<>();
            checkTokenWithServer();
        }
        return tokenVerificationResult;
    }

    private void checkTokenWithServer() {
        PresentationControlFactory.getLoadingProfileController().getUserID();
    }

    public void setUserIDVerificationResult(boolean error) {
        tokenVerificationResult.setValue(error);
    }

    public LiveData<Boolean> getUserInformation() {
        if (userRetrieve == null) {
            userRetrieve = new MutableLiveData<>();
            retrieveUserInfoFromServer();
        }
        return userRetrieve;
    }

    private void retrieveUserInfoFromServer() {
        PresentationControlFactory.getLoadingProfileController().retrieveUserInformation();
    }

    public void setUserRetrieveResult(boolean error) {
        userRetrieve.setValue(error);
    }

    public User getLoggedInUser() {
        return PresentationControlFactory.getLoadingProfileController().getLoggedInUser();
    }
}
