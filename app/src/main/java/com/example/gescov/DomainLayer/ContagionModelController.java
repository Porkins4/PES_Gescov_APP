package com.example.gescov.DomainLayer;

import java.util.concurrent.ExecutionException;

public class ContagionModelController implements  IContagionModelControles {
    Conection c;

    public ContagionModelController() {
        c = new Conection();
    }

    @Override
    public String getAllContagions() throws ExecutionException, InterruptedException {
        String response =  c.execute("https://gescov.herokuapp.com/api/contagion").get();// hay que poner el identificador del usuario para
        if (response == null) return "Hay fallo";
        return response;
    }

}
