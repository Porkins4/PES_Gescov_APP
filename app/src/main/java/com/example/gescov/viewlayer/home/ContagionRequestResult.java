package com.example.gescov.viewlayer.home;

import android.util.Pair;

public class ContagionRequestResult {
    private Pair<String,Boolean> error;


    public Pair<String,Boolean> getError() {
        return error;
    }

    public void setError(Pair<String,Boolean> error) {
        this.error = error;
    }

}
