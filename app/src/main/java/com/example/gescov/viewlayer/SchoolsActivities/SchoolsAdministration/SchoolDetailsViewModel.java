package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.SchoolRequestResult;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

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
