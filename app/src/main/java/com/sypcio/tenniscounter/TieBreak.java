package com.sypcio.tenniscounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sypa1 on 16.07.2018.
 */

public class TieBreak extends AppCompatActivity {
    int tieScore1 = 0;
    int tieScore2 = 0;
    public static final String RESULT_KEY = "result";


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiebreak);

        final Button point1 = findViewById(R.id.btn1);
        final Button point2 = findViewById(R.id.btn2);
        final TextView score1 = findViewById(R.id.points1);
        final TextView score2 = findViewById(R.id.points2);


        point1.setText(Players.mMyAppsBundle.getString("player1"));
        point2.setText(Players.mMyAppsBundle.getString("player2"));

        point1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tieScore1++;
                score1.setText(Integer.toString(tieScore1));
                if (tieScore1 >= 7 && (tieScore1 - tieScore2) > 1) {
                    Match.mMyAppsBundle.putInt("won",0);
                    Match.mMyAppsBundle.putBoolean("over", true);
                    finish();
                }
            }
        });

        point2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tieScore2++;
                score2.setText(Integer.toString(tieScore2));
                if (tieScore2 >= 7 && (tieScore2 - tieScore1) > 1) {
                    Match.mMyAppsBundle.putInt("won",1);
                    Match.mMyAppsBundle.putBoolean("over", true);
                    finish();
                }
            }
        });


    }


}
