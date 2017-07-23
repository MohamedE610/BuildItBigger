package com.udacity.gradle.builditbigger.Utils;

import com.example.Joke;

import java.util.ArrayList;

public interface NetworkResponse {


    void OnSuccess(String Data);
    void OnFetch(ArrayList<String> jokes);
    void OnFailure(boolean Failure);
}
