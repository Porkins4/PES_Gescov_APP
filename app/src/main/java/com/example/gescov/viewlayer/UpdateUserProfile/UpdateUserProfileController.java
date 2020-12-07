package com.example.gescov.viewlayer.UpdateUserProfile;

import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

public class UpdateUserProfileController {

    public UpdateUserProfileController() {}


    public void changeUserProfile(boolean isStudent) {
        PresentationControlFactory.getViewLayerController().changeUserProfile(isStudent);
    }

    public User.UserProfileType getUserType() {
        return PresentationControlFactory.getViewLayerController().getUserType();
    }
}
