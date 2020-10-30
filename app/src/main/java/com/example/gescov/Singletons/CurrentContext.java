package com.example.gescov.Singletons;

import android.content.Context;

public class CurrentContext {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        CurrentContext.context = context;
    }
}
