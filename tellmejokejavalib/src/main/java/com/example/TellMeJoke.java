package com.example;

import java.util.ArrayList;

public class TellMeJoke {

    //ArrayList<String> jokes;
    //String joke;
    ArrayList<Joke>  jokes;

    public TellMeJoke(){
        initializeJokes();
    }

    private void initializeJokes() {

        jokes =new ArrayList<>();
        Joke  joke=new Joke(" That's so funny ");
        jokes.add(joke);

    }

    public Joke tell(){

        return jokes.get(0);

    }

}
