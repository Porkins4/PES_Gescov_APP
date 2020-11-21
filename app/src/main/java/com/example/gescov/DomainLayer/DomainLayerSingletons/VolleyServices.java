package com.example.gescov.DomainLayer.DomainLayerSingletons;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

public class VolleyServices {
    private static VolleyServices instance;
    private static RequestQueue requestQueue;
    private static Context ctx;

    private VolleyServices() {
    }

    public static synchronized VolleyServices getInstance() {
        if (instance == null) instance = new VolleyServices();
        return instance;
    }

    public static RequestQueue getRequestQueue() {
        if (requestQueue == null) requestQueue = Volley.newRequestQueue(ctx);
        return requestQueue;
    }

    public static void addJSONArrayRequest(JsonArrayRequest request) {
        getRequestQueue().add(request);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public static void add(JsonArrayRequest req) {

    }

    public static void setContext(Context context) {
        ctx = context;
    }

    public static Context getCtx() {
        return ctx;
    }
}