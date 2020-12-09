package com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList;

import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SchoolDetailsViewModel extends ViewModel {
    private MutableLiveData<SchoolRequestResult> result;

    public LiveData<SchoolRequestResult> getPutResult() {
        if (result == null) {
            result = new MutableLiveData<>();
        }
        return result;
    }

    public void getAddStudentToCenterResult(String schoolId) {
        PresentationControlFactory.getSchoolsCrontroller().addStudentToCenter(schoolId,result);
    }
}
