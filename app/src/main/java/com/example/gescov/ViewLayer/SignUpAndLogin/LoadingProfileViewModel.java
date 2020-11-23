package com.example.gescov.ViewLayer.SignUpAndLogin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.ViewLayer.Singletons.PresentationControlFactory;

public class LoadingProfileViewModel extends ViewModel {
    private MutableLiveData<TokenVerificationResult> requestResult;

    public LiveData<TokenVerificationResult> getRequestResult() {
        checkUserInCloud();
        return requestResult;
    }

    private void checkUserInCloud() {
        PresentationControlFactory.getLoadingProfileController().checkLoginUser(requestResult);
    }

    public void setToken(String token) {
        requestResult = new MutableLiveData<>();
        TokenVerificationResult input = new TokenVerificationResult(token);
        requestResult.setValue(input);
    }
}
