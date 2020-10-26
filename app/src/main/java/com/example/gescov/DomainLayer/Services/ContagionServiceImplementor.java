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
       // System.out.println("tesst");
        System.out.println(userId);
        System.out.println(schoolId);
        //call back-end Service
        String response = null;
       // hay que poner el identificador del usuario y el centro
        try {
            //now?id="+ userId + "&nameCen"
            System.out.println("llega");
            response = c.execute("https://gescov.herokuapp.com/api/contagion/now?nameCen="+schoolId).get();
            System.out.println(response + "holahola");
        } catch (ExecutionException  | InterruptedException e ){
            e.printStackTrace();
        }
        if (response == null) return "Hay fallo";

        return response;
    }
}
