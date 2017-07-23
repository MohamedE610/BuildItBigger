package com.example.displayjokeandroidlib.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.displayjokeandroidlib.Adapters.JokesAdapter;
import com.example.displayjokeandroidlib.R;

import java.util.ArrayList;

public class ShowAllJokesActivity extends AppCompatActivity implements JokesAdapter.RecyclerViewClickListener {

    final String JOKE_STR="JOKE_STR";
    final String JOKE_BUNDLE="JOKE_BUNDLE";
    final String JOKES="JOKES";

    RecyclerView jokesRecyclerView;
    Bundle bundle;
    ArrayList<String> jokes;
    JokesAdapter jokesAdapter;
    String jokeStr;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_jokes);

        bundle=getIntent().getBundleExtra(JOKE_BUNDLE);

        jokes = bundle.getStringArrayList(JOKES);
        jokesRecyclerView=(RecyclerView) findViewById(R.id.jokes_recyclerView);
        jokesAdapter=new JokesAdapter(jokes,this);
        jokesRecyclerView.setLayoutManager(new GridLayoutManager(this,1));
        jokesRecyclerView.setAdapter(jokesAdapter);
        jokesAdapter.setClickListener(this);

    }

    @Override
    public void ItemClicked(View v, int position) {
        StartJokeActivity(position);
    }

    private void StartJokeActivity(int position) {
        bundle = new Bundle();
        jokeStr=jokes.get(position);
        bundle.putString(JOKE_STR, jokeStr);

        intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JOKE_BUNDLE, bundle);
        startActivity(intent);

    }
}
