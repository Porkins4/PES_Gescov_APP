package com.example.gescov.ViewLayer.home;

import android.util.Pair;

public class ContagionRequestResult {
    //private Boolean error;
    private Pair<String,Boolean> error;

    public ContagionRequestResult (){}

    public Pair<String,Boolean> getError() {
        return error;
    }

    public void setError(Pair<String,Boolean> error) {
        this.error = error;
    }

}
