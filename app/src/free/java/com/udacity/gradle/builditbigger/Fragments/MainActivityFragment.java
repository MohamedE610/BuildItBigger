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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
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
    private InterstitialAd mInterstitialAd;
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

        // Code to get device id
        String androidIdDevice = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

        Log.d("androidIdDevice",androidIdDevice);

        Toast.makeText(getContext(),"Free", Toast.LENGTH_SHORT).show();

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(androidIdDevice)
                .build();

        mAdView.loadAd(adRequest);

        InitializeInterstitialAd();

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


    private void InitializeInterstitialAd() {

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();

                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                if(boolBtn)
                    StartJokeActivity();
                else
                    StartShowAllJokesActivity();

            }
        });
    }


    private void HandleClickButtonsEvent() {

        tellJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolBtn=true;

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                else {

                    StartJokeActivity();
                }

            }
        });

        showAllJokesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolBtn=false;

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                else {
                    StartShowAllJokesActivity();
                }


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
