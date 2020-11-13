package com.example.gescov.ViewLayer.MainView;

import android.media.session.MediaSession;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.ViewLayer.PresentationControlFactory;

public class MainScreenViewModel extends ViewModel {
    private MutableLiveData<TokenVerificationResult> requestResult;

    public LiveData<TokenVerificationResult> getRequestResult() {
        checkUserInCloud();
        return requestResult;
    }

    private void checkUserInCloud() {
        PresentationControlFactory.getMainScreenController().checkLoginUser(requestResult);
    }

    public void setToken(String token) {
        requestResult = new MutableLiveData<>();
        TokenVerificationResult input = new TokenVerificationResult(token);
        requestResult.setValue(input);
    }
}
