package com.example.gescov.ViewLayer.MainView;

import androidx.lifecycle.MutableLiveData;

import com.example.gescov.ViewLayer.PresentationControlFactory;

public class MainScreenController {

    public MainScreenController() {}

    public void checkLoginUser(MutableLiveData<TokenVerificationResult> r) {
        PresentationControlFactory.getViewLayerController().checkLoginUser(r);
    }

    public void updateUserId(String userId) {
        PresentationControlFactory.getViewLayerController().updateUserId(userId);
    }
}
