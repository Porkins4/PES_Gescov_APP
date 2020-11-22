package com.example.gescov.ViewLayer.MainView;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.ViewLayer.PresentationControlFactory;

public class LoadingProfileController {

    public LoadingProfileController() {}

    public void checkLoginUser(MutableLiveData<TokenVerificationResult> r) {
        PresentationControlFactory.getViewLayerController().checkLoginUser(r);
    }

    public void updateUserId(String userId) {
        PresentationControlFactory.getViewLayerController().updateUserId(userId);
    }

    public void updateUserName(String userName) {
        PresentationControlFactory.getViewLayerController().updateUserName(userName);
    }

    public void getTypeProfile() {
        PresentationControlFactory.getViewLayerController().getTypeProfile();
    }
}
