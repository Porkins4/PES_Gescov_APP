package com.example.gescov.DomainLayer.Services;

import com.example.gescov.DomainLayer.Conection;

import java.util.concurrent.ExecutionException;

public class ContagionServiceImplementor implements IContagionService {

    private Conection conection;
    public ContagionServiceImplementor() {
    }


    @Override
    public String getContagionList(String userId, String schoolId) {
        //call back-end Service
        conection = new Conection();
        String response = null;
       // hay que poner el identificador del usuario y el centro
        try {

            response = conection.execute("https://gescov.herokuapp.com/api/contagion/now?nameCen="+schoolId).get();

        } catch (ExecutionException  | InterruptedException e ){
            e.printStackTrace();
        }
        if (response == null) return "Hay fallo";//TODO
        return response;
    }
}
