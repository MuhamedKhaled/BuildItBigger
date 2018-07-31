package com.os.joker;

import java.util.Random;

public class Joker {
    private final String[] jokes = new String[]{
            "Q: What's the object-oriented way to become wealthy? \n A: Inheritance",
            "This is totally a funny joke",
            "This is another totally funny joke",
            "This is a wibbly wobbly timey wimey funny joke",
    };

    public String getJoke() {
        return jokes[new Random().nextInt(jokes.length)];
    }
}
