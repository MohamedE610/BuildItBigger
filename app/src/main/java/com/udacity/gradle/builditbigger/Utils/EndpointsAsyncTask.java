package com.udacity.gradle.builditbigger.Utils;

import android.os.AsyncTask;
import android.util.Log;

import com.example.e610.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.Utils.NetworkResponse;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by E610 on 7/23/2017.
 */
public class EndpointsAsyncTask extends AsyncTask<Void, Void, Object> {

    private String typeOfApiMethod;
    private NetworkResponse networkResponse;
    private static MyApi myApiService = null;


    public EndpointsAsyncTask (String typeOfApiMethod){
        this.typeOfApiMethod=typeOfApiMethod;
    }

    public void setOnNetworkResponse(NetworkResponse networkResponse) {
        this.networkResponse = networkResponse;
    }

    @Override
    protected Object doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once

            String localServerAddress="http://192.168.1.6:8080/_ah/api/";

            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(localServerAddress)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            if(typeOfApiMethod.equals("getRandomJoke"))
                 return myApiService.getJokeFromServer().execute().getData();
            else
                return myApiService.getAllJokesFromServer().execute().getData();

        } catch (IOException e) {
            Log.e("EndpointsAsyncTaskLog",e.getMessage(),e);
            return "Time Out Error";
        }
    }

    String jokeStr;

    ArrayList<String>jokes;
    @Override
    protected void onPostExecute(Object result) {

        if(result.equals("Time Out Error"))
            networkResponse.OnFailure(true);
        else {
            if (typeOfApiMethod.equals("getRandomJoke")) {
                jokeStr = (String) result;
                networkResponse.OnSuccess(jokeStr);
            }
            else {
                jokes = (ArrayList<String>) result;
                networkResponse.OnFetch(jokes);
            }
        }

        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
