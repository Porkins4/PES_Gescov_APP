package com.example.gescov.ViewLayer.SchoolClassroomList;

import com.example.gescov.ViewLayer.SchoolsAdministration.SchoolListViewAdapter;

import java.util.List;

interface ISchoolClassroomsCrontroller {
    public void createClassroom(String schoolName, String schoolCode);

    public ClassroomListViewAdapter getClassroomListViewSchoolAdapter();
    public List getList();
}
