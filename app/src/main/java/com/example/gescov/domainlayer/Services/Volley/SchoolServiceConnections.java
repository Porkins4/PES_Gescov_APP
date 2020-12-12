package com.example.gescov.domainlayer.Services.Volley;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SchoolServiceConnections extends AsyncTask<String,String,String> {

    @Override
    protected String doInBackground(String... strings) {
        URL uri = null;
        try {
            uri = new URL(strings[0]);
        } catch (MalformedURLException e) {
            System.out.println("Error while creating URL");
        }

        HttpURLConnection connection = null;
        try {

            connection = (HttpURLConnection) uri.openConnection();
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
                throw new IOException();
            }
        } catch (IOException e) {
            System.out.println("Error while making HTTP request");
        }
        return null;
    }
}
