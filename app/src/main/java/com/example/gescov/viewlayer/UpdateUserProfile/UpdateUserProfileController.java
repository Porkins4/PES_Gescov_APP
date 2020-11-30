package com.example.gescov.viewlayer.UpdateUserProfile;

import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

public class UpdateUserProfileController {

    public UpdateUserProfileController() {}


    public void changeUserProfile(String profile) {
        PresentationControlFactory.getViewLayerController().changeUserProfile(profile);
    }

    public String getUserType() {
        return PresentationControlFactory.getViewLayerController().getUserType();
    }
}
