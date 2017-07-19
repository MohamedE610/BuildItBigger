package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.Joke;
import com.example.TellMeJoke;
import com.example.displayjokeandroidlib.JokeActivity;


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
        bundle.putString(JOKE_STR,jokeStr);
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
