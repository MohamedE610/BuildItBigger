package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.Joke;
import com.example.TellMeJoke;
import com.example.displayjokeandroidlib.JokeActivity;
import com.example.e610.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

class c{ static String str;}

public class MainActivity extends AppCompatActivity {

    final String JOKE_STR="JOKE_STR";
    final String JOKE_BUNDLE="JOKE_BUNDLE";

    Intent intent;
    Bundle bundle;
    String jokeStr;
    Joke joke;
    TellMeJoke tellMeJoke;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //new EndpointsAsyncTask().execute(new Pair<Context, String>(this, " Eslam ^_^ "));
        new EndpointsAsyncTask().execute(this);
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

        tellMeJoke=new TellMeJoke();
        joke=tellMeJoke.tell();
        jokeStr=joke.getJokeString();
        // Toast.makeText(this, "derp", Toast.LENGTH_SHORT).show();
        // Toast.makeText(this, jokeStr , Toast.LENGTH_SHORT).show();

        bundle=new Bundle();
        bundle.putString(JOKE_STR,c.str);
        intent=new Intent(this,JokeActivity.class);
        intent.putExtra(JOKE_BUNDLE,bundle);
        startActivity(intent);


    }

//Errors
//Warning:[options] bootstrap class path not set in conjunction with -source 1.7
/*
Error:Execution failed for task ':app:processDebugManifest'.
> Manifest merger failed : Attribute application@allowBackup value=(false) from AndroidManifest.xml:11:9-36
	is also present at [Build It Bigger:displayjokeandroidlib:unspecified] AndroidManifest.xml:13:9-35 value=(true).
	Suggestion: add 'tools:replace="android:allowBackup"' to <application> element at AndroidManifest.xml:10:5-34:19 to override.
 */

}

/*class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://192.168.1.3:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            Log.d("zzz",e.getMessage());
            Log.d("zzzz",e.getLocalizedMessage());
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}*/

class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://192.168.1.3:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0];


        try {
            return myApiService.returnJokeFromServer().execute().getData();
        } catch (IOException e) {
            Log.d("zzz",e.getMessage());
            Log.d("zzzz",e.getLocalizedMessage());
            Log.e("asd",e.getMessage(),e);
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
       c.str=result;
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}