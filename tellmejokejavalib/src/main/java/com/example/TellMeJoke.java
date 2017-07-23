package com.example;

import java.util.ArrayList;
import java.util.Random;

public class TellMeJoke {

    int randomIndex;
    String [] jokesArray= {"Two bytes meet.  The first byte asks, Are you ill? \n" +
            "The second byte replies, No, just feeling a bit off.",

             "Eight bytes walk into a bar.  The bartender asks, Can I get you anything?\n" +
                     "Yeah, reply the bytes.  Make us a double.",

              "Q. How did the programmer die in the shower?\n" +
                      "A. He read the shampoo bottle instructions: Lather. Rinse. Repeat.",

                "How many programmers does it take to change a light bulb?\n" +
                        "None – It’s a hardware problem",

                 "Why do programmers always mix up Halloween and Christmas?\n" +
            "Because Oct 31 equals Dec 25.",

            "There are only 10 kinds of people in this world: those who know binary and those who don’t.",

             "A programmer walks to the butcher shop and buys a kilo of meat.  An hour later he comes back upset that the butcher shortchanged him by 24 grams.",

              "Knock, knock.\n" +
                      "Who’s there?\n" +
                      "very long pause….\n" +
                      "Java.",
                "Programming is 10% science, 20% ingenuity, and 70% getting the ingenuity to work with the science.",

                "A man is smoking a cigarette and blowing smoke rings into the air.  His girlfriend becomes irritated with the smoke and says, Can’t you see the warning on the cigarette pack?  Smoking is hazardous to your health!\n" +
                        "\n" +
                        "To which the man replies, I am a programmer.  We don’t worry about warnings; we only worry about errors.",

                "There are three kinds of lies: Lies, damned lies, and benchmarks.",

                "Have you heard about the new Cray super computer?  It’s so fast, it executes an infinite loop in 6 seconds.",

                 "I just saw my life flash before my eyes and all I could see was a close tag…",

                  "Two strings walk into a bar and sit down. The bartender says, So what’ll it be?\n" +
                          "\n" +
                          "The first string says, I think I’ll have a beer quag fulk boorg jdk^CjfdLk jk3s d#f67howe%^U r89nvy~~owmc63^Dz x.xvcu\n" +
                          "\n" +
                          "Please excuse my friend, the second string says, He isn’t null-terminated." ,

                           "Why all Pascal programmers ask to live in Atlantis?\n" +
                           " Because it is below C level.",

                         "From the Random Shack Data Processing Dictionary:\n" +
                                 "\n" +
                                 "Endless Loop: n., see Loop, Endless.\n" +
                                 "Loop, Endless: n., see Endless Loop.",

                            "The three most dangerous things in the world are a programmer with a soldering iron, a hardware engineer with a software patch, and a user with an idea.  – The Wizardry Compiled by Rick Cook",

                        "One hundred little bugs in the code\n" +
                                "One hundred little bugs.\n" +
                                "Fix a bug, link the fix in,\n" +
                                "One hundred little bugs in the code.",

                    "Debugging: Removing the needles from the haystack.","The computer is mightier than the pen, the sword, and usually, the programmer."
                     ,"The generation of random numbers is too important to be left to chance","All programmers are playwrights, and all computers are lousy actors."
                    ,} ;

    ArrayList<Joke>  jokes;

    public TellMeJoke(){
        initializeJokes();
    }

    private void initializeJokes() {

        jokes =new ArrayList<>();

        for (String jokeStr :jokesArray ){
            Joke  joke=new Joke(jokeStr);
            jokes.add(joke);
        }

    }

    public Joke tell(){
        Random random =new Random();
        randomIndex=random.nextInt(jokes.size()-1);
        return jokes.get(randomIndex);
    }


/*
 this method was return  ArrayList<Joke> and I received this object an deal with it as ArrayList<Joke> like pass it
  through callbacks and casting it from Object to ArrayList<Joke>  but  when i  get any joke from it i get
  this error  " java.lang.ClassCastException: com.google.api.client.util.ArrayMap cannot be cast to com.example.Joke "
  and in other time get other "java.lang.ClassCastException: java.lang.String cannot be cast to com.example.Joke"
  from the same situation "when i try get joke from ArrayList<Joke>" and when i debug this i note that
  the actual type(heap) of  ArrayList<Joke> object  is ArrayMap with (key,value) and reference type (stack) is  ArrayList<Joke>
  I really don't understand what is the reason of this problem
  and when i change it to ArrayList<String> everything goes well
 please help me to understand the problem's reason and  if i said any wrong information please tell me  right one

 I appreciate that , thanks.

* */
    public ArrayList<String> getJokes(){

        ArrayList<String> strings=new ArrayList<>();

        for (String s:jokesArray)
          strings.add(s);

        return strings ;
    }



}
