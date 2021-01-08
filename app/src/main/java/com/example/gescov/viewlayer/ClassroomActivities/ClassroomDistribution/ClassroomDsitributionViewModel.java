package com.example.gescov.viewlayer.ClassroomActivities.ClassroomDistribution;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.domainlayer.Classmodels.Assignment;
import com.example.gescov.domainlayer.Classmodels.Classroom;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class ClassroomDsitributionViewModel extends ViewModel {
    private MutableLiveData<ClassroomDistributionInfo> data;
    private MutableLiveData<ClassroomDistributionClassInfo> classInfo;

    //-----------------------------
    // Reservation request
    private MutableLiveData<Boolean> reservationResponse;
    private int requestResponse;
    private String classSessionID;
    private int row;
    private int col;

    public ClassroomDsitributionViewModel() {
        PresentationControlFactory.getStudentsInClassSessionController().setClassroomDsitributionViewModel(this);
    }

    public LiveData<ClassroomDistributionInfo> getData(String classSessionID) {
        if (data == null) {
            data = new MutableLiveData<>();
            getAssignmentsInfo(classSessionID);
        }
        return data;
    }

    public void getAssignmentsInfo(String classSessionID) {
        PresentationControlFactory.getStudentsInClassSessionController().getAssignmentsForClassSession(classSessionID);
    }

    public void setAssignmentsResponse(List<Assignment> r, boolean b) {
        ClassroomDistributionInfo classroomDistributionInfo = new ClassroomDistributionInfo(r,b);
        data.setValue(classroomDistributionInfo);
    }

    public LiveData<ClassroomDistributionClassInfo> getClassroom(String classroomID) {
        if (classInfo == null) {
            classInfo = new MutableLiveData<>();
            getClassromInfo(classroomID);
        }
        return classInfo;
    }

    private void getClassromInfo(String classroomID) {
        PresentationControlFactory.getStudentsInClassSessionController().getClassroomInfo(classroomID);
    }

    public void setClassInfo(Classroom c, boolean b) {
        ClassroomDistributionClassInfo classroomDistributionClassInfo = new ClassroomDistributionClassInfo(c,b);
        classInfo.setValue(classroomDistributionClassInfo);
    }

    public Classroom getClassroomInfo() {
        return classInfo.getValue().getClassroom();
    }

    public List<Assignment> getAssignmentsList() {
        return data.getValue().getStudents();
    }

    public void init(String classSessionID, int row, int col) {
        this.classSessionID = classSessionID;
        this.row = row;
        this.col = col;
    }

    public LiveData<Boolean> reservationRequest() {
        if (reservationResponse == null) reservationResponse = new MutableLiveData<>();
        PresentationControlFactory.getStudentsInClassSessionController().sendReservationRequest(classSessionID, row, col);
        return reservationResponse;
    }

    public void setSendReservationRequestResponse(boolean error, int errorCode) {
        requestResponse = errorCode;
        reservationResponse.setValue(error);
    }

    public int getErrorCode() {
        return requestResponse;
    }

    public User getUserName() {
        return PresentationControlFactory.getViewLayerController().getLoggedUserInfo();
    }
}
