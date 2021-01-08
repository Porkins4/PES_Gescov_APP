package com.example.gescov.viewlayer;

import android.content.Context;
import android.widget.Toast;

public class MessagesManager {
    private Context context;
    public MessagesManager(Context context) {
        this.context = context;
    }

    public void toastMessage(int resourceMessage) {
        Toast toast = Toast.makeText(context, context.getResources().getText(resourceMessage), Toast.LENGTH_SHORT);
        toast.show();
    }

    public void toastMessage(int resourceMessage, String endString) {
        Toast toast = Toast.makeText(context, context.getResources().getText(resourceMessage) + " " + endString, Toast.LENGTH_SHORT);
        toast.show();
    }
}
