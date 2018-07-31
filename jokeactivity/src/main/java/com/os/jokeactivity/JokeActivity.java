package com.os.jokeactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    public static final String JOKE_EXTRA = "com.os.MainActivity.jokeExtra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView jokeTv = findViewById(R.id.jokeTv);
        jokeTv.setText(getIntent().getStringExtra(JOKE_EXTRA));
    }
}
