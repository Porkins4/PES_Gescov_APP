package com.example.gescov.ViewLayer.SchoolClassroomList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.ViewLayer.PresentationControlFactory;

public class SchoolDetailsViewModel extends ViewModel {
    private MutableLiveData<SchoolRequestResult> result;

    public LiveData<SchoolRequestResult> getPutResult() {
        if (result == null) {
            result = new MutableLiveData<>();
        }
        return result;
    }

    public void getAddStudentToCenterResult(String schoolName) {
        PresentationControlFactory.getSchoolsCrontroller().addStudentToCenter(schoolName,result);
    }



}