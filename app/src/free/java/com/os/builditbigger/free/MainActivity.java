package com.os.builditbigger.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.os.builditbigger.JokerApiAsyncTask;
import com.os.builditbigger.R;
import com.os.jokeactivity.JokeActivity;

public class MainActivity extends AppCompatActivity implements JokerApiAsyncTask.OnJokerAsyncTaskCompleted {
    private static final String TEST_DEVICE_KEY = "YOUR_TEST_DEVICE";

    private ProgressBar progressBar;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-7006018109785103/7789292047");

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);


        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(TEST_DEVICE_KEY)
                .build();
        adView.loadAd(adRequest);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-7006018109785103/7789292047");

        Button tellAJokeButton = findViewById(R.id.tellAJokeButton);
        tellAJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                new JokerApiAsyncTask(MainActivity.this).execute();
            }
        });
    }

    @Override
    public void onTaskCompleted(final String result) {
        if (result == null) {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(MainActivity.this, "Error while getting your joke!", Toast.LENGTH_SHORT).show();
            return;
        }

        interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(TEST_DEVICE_KEY).build());
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                progressBar.setVisibility(View.INVISIBLE);
                interstitialAd.show();
            }

            @Override
            public void onAdClosed() {
                Intent intent = new Intent(MainActivity.this, JokeActivity.class);
                intent.putExtra(JokeActivity.JOKE_EXTRA, result);
                startActivity(intent);
            }
        });
    }
}
