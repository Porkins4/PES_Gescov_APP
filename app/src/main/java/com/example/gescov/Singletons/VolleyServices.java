package com.example.gescov.Singletons;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyServices {
    private static VolleyServices instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private VolleyServices() {
    }

    public static synchronized VolleyServices getInstance() {
        if (instance == null) instance = new VolleyServices();
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) requestQueue = Volley.newRequestQueue(ctx);
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public static void setContext(Context context) {
        ctx = context;
    }
}