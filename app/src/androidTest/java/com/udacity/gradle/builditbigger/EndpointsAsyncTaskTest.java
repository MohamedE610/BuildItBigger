package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.udacity.gradle.builditbigger.Utils.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.Utils.NetworkResponse;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by E610 on 7/22/2017.
 */

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest implements NetworkResponse {

    EndpointsAsyncTask fetchJokeFromServer;
    EndpointsAsyncTask fetchAllJokesFromServer;

    @Test
    public void gcEndpointsAsyncTaskTest() throws Exception{
        try {
            fetchJokeFromServer=new EndpointsAsyncTask("getRandomJoke");
            fetchAllJokesFromServer=new EndpointsAsyncTask("getJokes");
            fetchJokeFromServer.setOnNetworkResponse(this);
            fetchAllJokesFromServer.setOnNetworkResponse(this);

            fetchJokeFromServer.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            String jokeStr =(String) fetchJokeFromServer.get();
            Log.d("asd",jokeStr);
            assertNotNull(jokeStr);
            assertTrue(jokeStr.length() > 0);


            fetchAllJokesFromServer.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            ArrayList<String> jokes =(ArrayList<String>) fetchAllJokesFromServer.get(25,TimeUnit.SECONDS);
            Log.d("asd",jokes.get(0));
            assertNotNull(jokes);
            assertTrue(jokes.size() > 0);
            assertEquals(22,jokes.size());

        } catch (Exception e){
            Log.e("asd", "Time Out Error",e);
        }
    }

    @Override
    public void OnSuccess(String Data) {

    }

    @Override
    public void OnFetch(ArrayList<String> jokes) {

    }

    @Override
    public void OnFailure(boolean Failure) {

    }
}
