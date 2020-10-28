package com.example.gescov.DomainLayer.Services;

import com.example.gescov.DomainLayer.Conection;

import java.util.concurrent.ExecutionException;

public class SchoolServiceImplementor implements ISchoolService{

    Conection conection;
    public SchoolServiceImplementor() {
        conection = new Conection();
    }

    @Override
    public String getAllSchools() {
        //call back-end Service
        conection = new Conection();
        String response = null;
        // hay que poner el identificador del usuario y el centro
        try {
            response = conection.execute("https://gescov.herokuapp.com/api/school").get();
        } catch (ExecutionException | InterruptedException e ){
            e.printStackTrace();
        }
        if (response == null) return "Hay fallo";//TODO
        return response;
    }
}
