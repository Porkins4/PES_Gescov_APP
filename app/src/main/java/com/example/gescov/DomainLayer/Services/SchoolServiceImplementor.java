package com.example.gescov.DomainLayer.Services;

import com.example.gescov.DomainLayer.Conection;
import com.example.gescov.DomainLayer.SchoolServiceConnections;

import java.util.concurrent.ExecutionException;

public class SchoolServiceImplementor implements ISchoolService {
    private SchoolServiceConnections conection;
    private final String GET_CLASSROOM_DIMENSIONS_URI = "https://gescov.herokuapp.com/api/classroom/distribution";

    public SchoolServiceImplementor() { }

    @Override
    public String getDimensions(String schoolId, String classroomId) {
        conection = new SchoolServiceConnections();
        String response = null;
        try {
            response = conection.execute(GET_CLASSROOM_DIMENSIONS_URI+"?id=5f958acfebe981482ce5adf0").get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if (response == null) return "Failure at getting dimensions from the server";
        return response;
    }
}
