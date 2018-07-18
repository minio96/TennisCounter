package com.sypcio.tenniscounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button newMatch = findViewById(R.id.newMatch);
        final Button yourMatches = findViewById(R.id.yourMatches);
        final Intent intentMatch = new Intent(this, Players.class);
        final Intent intentYour = new Intent(this, YourMatches.class);

        newMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentMatch);
            }
        });

        yourMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentYour);
            }
        });
    }
}
