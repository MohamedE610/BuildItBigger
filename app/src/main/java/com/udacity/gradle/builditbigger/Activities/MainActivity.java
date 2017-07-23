package com.udacity.gradle.builditbigger.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.Utils.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.Utils.NetworkResponse;
import com.udacity.gradle.builditbigger.Utils.NetworkState;

public class MainActivity extends AppCompatActivity  {

    EndpointsAsyncTask fetchJokeFromServer;
    EndpointsAsyncTask fetchAllJokesFromServer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        InitializeGlobals();
        FetchDataFromGCEndpoint();
        fetchJokeFromServer.setOnNetworkResponse((NetworkResponse) fragment);
        fetchAllJokesFromServer.setOnNetworkResponse((NetworkResponse) fragment);
    }

    private void InitializeGlobals() {

        fetchJokeFromServer=new EndpointsAsyncTask("getRandomJoke");
        fetchAllJokesFromServer=new EndpointsAsyncTask("getJokes");

    }

    private void FetchDataFromGCEndpoint() {

        if(NetworkState.ConnectionAvailable(this)) {

            fetchJokeFromServer.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            fetchAllJokesFromServer.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        else {
            Toast.makeText(MainActivity.this,"No Internet Available",Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this,"Please turn on WiFi or Connection Data",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void tellJoke(View view) {

    }


    public void showAllJokes(View view) {

    }


}



