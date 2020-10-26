package com.example.gescov.DomainLayer.Services;

import com.example.gescov.DomainLayer.Conection;

import java.util.concurrent.ExecutionException;

public class ContagionServiceImplementor implements IContagionService {
    Conection c;
    public ContagionServiceImplementor() {
        c = new Conection();
    }

    @Override
    public String getContagionList(String userId, String schoolId) {
        //call back-end Service
        c = new Conection();
        String response = null;
       // hay que poner el identificador del usuario y el centro
        try {
            System.out.println("otra llamada");
            response = c.execute("https://gescov.herokuapp.com/api/contagion/now?nameCen="+schoolId).get();
            System.out.println("he pasado el response");
        } catch (ExecutionException  | InterruptedException e ){
            e.printStackTrace();
        }
        if (response == null) return "Hay fallo";
        return response;
    }
}
