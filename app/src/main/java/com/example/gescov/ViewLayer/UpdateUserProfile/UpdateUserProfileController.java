package com.example.gescov.ViewLayer.UpdateUserProfile;

import com.example.gescov.ViewLayer.ViewLayerSingletons.PresentationControlFactory;

public class UpdateUserProfileController {

    public UpdateUserProfileController() {}


    public void changeUserProfile(String profile) {
        PresentationControlFactory.getViewLayerController().changeUserProfile(profile);
    }

    public String getUserType() {
        return PresentationControlFactory.getViewLayerController().getUserType();
    }
}
