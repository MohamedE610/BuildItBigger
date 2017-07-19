package com.example.displayjokeandroidlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {


    final String JOKE_STR="JOKE_STR";
    final String JOKE_BUNDLE="JOKE_BUNDLE";

    Bundle bundle;
    String jokeStr;
    TextView jokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        bundle=getIntent().getBundleExtra(JOKE_BUNDLE);
        jokeStr=bundle.getString(JOKE_STR);

        jokeTextView=(TextView)findViewById(R.id.joke_text);
        jokeTextView.setText(jokeStr);


    }
}
