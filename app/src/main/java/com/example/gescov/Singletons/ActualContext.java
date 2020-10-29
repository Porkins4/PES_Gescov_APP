package com.example.gescov.Singletons;

import android.content.Context;

public class ActualContext {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        ActualContext.context = context;
    }
}
