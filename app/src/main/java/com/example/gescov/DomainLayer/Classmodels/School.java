package com.example.gescov.DomainLayer.Classmodels;

import com.example.gescov.DomainLayer.Services.Volley.Interfaces.ISchoolService;
import com.example.gescov.DomainLayer.Singletons.ServicesFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class School {

    private String id;
    private String name;
    private String address;
    private String state;
    private String creatorID;
    private float latitude;
    private float longitude;
    private String telephoneNumber;
    private String webpage;
    private List<String> listAdministratorsID;
    private String email;

    public School(String id, String name, String address, String state, String creatorID, String email, List<String> listAdministratorsID) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.state = state;
        this.email = email;
        this.creatorID = creatorID;
        this.listAdministratorsID = listAdministratorsID;
        longitude = latitude = 0;
    }

    public School(String name) {
        this.name = name;
    }


    public School(){}

    public School(String id, String name, String address, String state, String creatorID, String email, String telephoneNumber, int longitude, int latitude, List<String> listAdministratorsID) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.state = state;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.creatorID = creatorID;
        this.listAdministratorsID = listAdministratorsID;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static School fromJsonToSchool(JSONObject response) {
        School res = new School();
        try {
            String idSchool = response.getString("id");
            String nameSchool = response.getString("name");
            String stateSchool = response.getString("state");
            String addressSchool = response.getString("address");
            String emailSchool = null;//aux.getString("email");
            String phone = response.getString("phone");
            int longitude = response.getInt("longitude");
            int latitude = response.getInt("longitude");
            List<String> listAdminsID = new ArrayList<>();
            JSONArray adminsArray = response.getJSONArray("administratorsID");
            for (int admin = 0; admin < adminsArray.length(); admin++) {
                listAdminsID.add(adminsArray.getString(admin));
            };
            String creatorSchoolID = response.getString("creatorID");
            res = new School(idSchool, nameSchool, addressSchool, stateSchool, creatorSchoolID, emailSchool, phone, longitude, latitude, listAdminsID);
        } catch (JSONException e) {
            res.setId("null"); //not received correctly
        }
        return res;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public String getClassroomDimensions(String schoolId, String classroomId) {
        ISchoolService iSchoolService = ServicesFactory.getSchoolService();
        return iSchoolService.getDimensions(schoolId,classroomId);
    }

    public String getStudentsInClassroom(String classroom) {
        ISchoolService iSchoolService = ServicesFactory.getSchoolService();
        return iSchoolService.getStudentsInClassroom(classroom);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCreatorID(String creatorID) {
        this.creatorID = creatorID;
    }

    public void createClassroom( String classroomName, int classroomRows, int classroomCols) {
        ISchoolService schoolService = ServicesFactory.getSchoolService();
        schoolService.createClassroomRequest(id, classroomName, classroomRows, classroomCols);
    }


    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getWebpage() {
        return webpage;
    }

    public String getEmail() {
        return email;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    public List<String> getAdministratorsList() {
        return listAdministratorsID;
    }

    public void print() {
        System.out.println(id);
        System.out.println(name);
        System.out.println(creatorID);
        System.out.println(telephoneNumber);
    }

    public String getCreator() {
        return creatorID;
    }
}