package com.example.gescov.DomainLayer;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Conection extends AsyncTask<String,String,String> {
    @Override
    protected String doInBackground(String... strings) {
        URL url = null;
        try {
            System.out.println("im here ");
            url = new URL(strings[0]);
            System.out.println("aun no he fallado ");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = null;
        try {

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent","Mozilla/5.0");
            int response = connection.getResponseCode();
            System.out.println("Response status" + response);
            if (response == HttpURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer responseData = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    responseData.append(inputLine);
                }
                in.close();

                return responseData.toString();

            } else {
                return String.valueOf(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Fallo al crear URL o en Hacer la conexión";
    }
}
