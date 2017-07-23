package com.example;

import java.io.Serializable;

/**
 * Created by E610 on 7/19/2017.
 */
public class Joke {

    private String joke;

    public Joke(String joke){
        this.joke=joke;
    }

    public String getJokeString(){

        return this.joke;
    }
}

