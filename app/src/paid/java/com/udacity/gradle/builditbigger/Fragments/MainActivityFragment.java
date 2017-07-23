package com.udacity.gradle.builditbigger.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.displayjokeandroidlib.Activities.JokeActivity;
import com.example.displayjokeandroidlib.Activities.ShowAllJokesActivity;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.Utils.NetworkResponse;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements NetworkResponse {


    final String JOKE_STR="JOKE_STR";
    final String JOKES="JOKES";
    final String JOKE_BUNDLE="JOKE_BUNDLE";

    boolean boolBtn;  // true if  tellJokeButton clicked and false if  showAllJokesButton clicked

    Button tellJokeButton;
    Button showAllJokesButton;
    Intent intent;
    Bundle bundle;
    String jokeStr;
    ArrayList<String> jokes;
    ProgressBar progressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);


        InitializeGlobals(root);

        Toast.makeText(getContext(),"Paid", Toast.LENGTH_SHORT).show();

        HandleClickButtonsEvent();

        return root;
    }

    private void InitializeGlobals(View root) {
        tellJokeButton=(Button)root.findViewById(R.id.button_tells_joke);
        showAllJokesButton=(Button)root.findViewById(R.id.button_show_all_jokes);
        progressBar=(ProgressBar) root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        tellJokeButton.setClickable(false);
        tellJokeButton.setText(R.string.jokeLoading);
        showAllJokesButton.setClickable(false);
        showAllJokesButton.setText(R.string.jokesLoading);
    }

    private void HandleClickButtonsEvent() {

        tellJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    StartJokeActivity();

            }
        });

        showAllJokesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    StartShowAllJokesActivity();

            }
        });
    }


    private void StartJokeActivity() {
        bundle = new Bundle();
        bundle.putString(JOKE_STR, jokeStr);

        intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra(JOKE_BUNDLE, bundle);
        startActivity(intent);

    }

    private void StartShowAllJokesActivity() {

        bundle = new Bundle();
        bundle.putStringArrayList(JOKES, jokes);
        intent = new Intent(getActivity(), ShowAllJokesActivity.class);
        intent.putExtra(JOKE_BUNDLE, bundle);
        startActivity(intent);
    }

    @Override
    public void OnSuccess(String Data) {
        progressBar.setVisibility(View.GONE);
        tellJokeButton.setText(R.string.button_text);
        tellJokeButton.setClickable(true);
        jokeStr=Data;
        Toast.makeText(getContext(),jokeStr,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnFetch(ArrayList<String> jokesList) {
        progressBar.setVisibility(View.GONE);
        showAllJokesButton.setText(R.string.show_all_jokes);
        showAllJokesButton.setClickable(true);

        jokes=new ArrayList<>();
        for(int i=0;i<jokesList.size();i++)
        {
            /***********************************/
            //  java.lang.ClassCastException: com.google.api.client.util.ArrayMap cannot be cast to com.example.Joke
            //  java.lang.ClassCastException: java.lang.String cannot be cast to com.example.Joke

            jokes.add(jokesList.get(i));
        }

        Toast.makeText(getContext(),"Done! Fetch "+jokes.size()+" Joke",Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void OnFailure(boolean Failure) {
        tellJokeButton.setText(R.string.button_text);
        tellJokeButton.setClickable(true);
        progressBar.setVisibility(View.GONE);
        //Default Joke
        jokeStr=" Two bytes meet.  The first byte asks, Are you ill? \n " +
                " The second byte replies, No, just feeling a bit off." ;

        Toast.makeText(getContext(),"Time Out Error",Toast.LENGTH_SHORT).show();

    }
}
