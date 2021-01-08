package com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList;

import android.content.Context;

import com.example.gescov.domainlayer.Classmodels.Classroom;
import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.ArrayList;
import java.util.List;

public class SchoolClassroomsCrontroller {

    private ClassroomListViewAdapter listViewAdapter;
    private List<Classroom> classroomsList;

    public void setListViewAdapter(Context context) {
        listViewAdapter = new ClassroomListViewAdapter(context, getList());
    }

    public void createClassroom(String classroomName, int classroomRows, int classroomCols) {
        School currentSchool = PresentationControlFactory.getSchoolsCrontroller().getCurrentSchool();
        PresentationControlFactory.getViewLayerController().createClassroom(currentSchool, classroomName, classroomRows, classroomCols);
    }

    public void updateClassroom(String classroomid, String classroomName, int numRows, int numCols) {
        PresentationControlFactory.getViewLayerController().updateClassroom(classroomid, classroomName, numRows, numCols);
    }

    public void deleteClassroom(String classroomId) {
        PresentationControlFactory.getViewLayerController().deleteClassroom(classroomId);
    }

    public ClassroomListViewAdapter getListViewAdapter() {
        return listViewAdapter;
    }

    public List<Classroom> getList() {
        if (classroomsList != null)
            return classroomsList;
        classroomsList = new ArrayList<>();
        return classroomsList;
    }

    public void refreshList() {
        PresentationControlFactory.getViewLayerController().getSchoolClassrooms(PresentationControlFactory.getSchoolsCrontroller().getCurrentSchool().getName());
    }

    public void refreshList(List<Classroom> classroomsList) {
        this.classroomsList = classroomsList;
        getListViewAdapter().setList(classroomsList);
        getListViewAdapter().notifyDataSetChanged();
    }


    public Classroom getClassroomByListPosition(int position) {
        return classroomsList.size() > position ? classroomsList.get(position) : null;
    }
    
}