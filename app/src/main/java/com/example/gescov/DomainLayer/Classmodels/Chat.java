package com.example.gescov.DomainLayer.Classmodels;

import org.json.JSONException;
import org.json.JSONObject;

public class Chat {
    private String id;
    private String partA;
    private String partB;


    public Chat(String id, String partA, String partB) {
        this.id = id;
        this.partA = partA;
        this.partB = partB;
    }

    public static Chat FromJSONtoChat(JSONObject response) {
        Chat result = new Chat("null","","");
        try {
            String id = response.getString("id");
            String partA = response.getString("partA");
            String partB = response.getString("partB");
            result = new Chat(id,partA,partB);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getID() {
        return id;
    }

    public void print() {
        System.out.println(id);
        System.out.println(partA);
        System.out.println(partB);
    }
}
