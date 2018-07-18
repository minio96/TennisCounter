package com.sypcio.tenniscounter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragAndDropPermissions;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by sypa1 on 21.06.2018.
 */

public class Match extends AppCompatActivity {
    int sety;
    int currentSet = 0;
    public int maxWonSet;
    public int[][] table;
    final int[] setWon = new int[2];
    public static Bundle mMyAppsBundle = new Bundle();

    @Override
    protected void onResume(){
        super.onResume();

        if (setWon[0] == maxWonSet){
            saveData(table);
            Toast.makeText(this, "You won", Toast.LENGTH_LONG).show();
            finish();
        }
        if (setWon[1] == maxWonSet){
            saveData(table);
            Toast.makeText(this, "You won", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                sety = 0;
            } else {
                sety = extras.getInt("sety");
            }
        } else {
            sety = (int) savedInstanceState.getSerializable("sety");
        }

        if(sety == 3) {
            setContentView(R.layout.activity_match_3set);
            table = new int[3][2];
            maxWonSet = 2;

        } else if(sety == 5) {
            setContentView(R.layout.activity_match_5set);
            table = new int[5][2];
            maxWonSet = 3;
        }

        String name1 = Players.mMyAppsBundle.getString("player1");
        String name2 = Players.mMyAppsBundle.getString("player2");

        Log.i("sety",name1);
        Log.i("sety",name2);   //przechodzimy do meczu______________________________________
        final TextView matchPlayer1 = findViewById(R.id.name1);
        final TextView matchPlayer2 = findViewById(R.id.name2);
        final Button point1 = findViewById(R.id.btn1);
        final Button point2 = findViewById(R.id.btn2);
        final Button save = findViewById(R.id.save_btn);
        final TextView score1 = findViewById(R.id.points1);
        final TextView score2 = findViewById(R.id.points2);
        final TextView set1a = findViewById(R.id.Set1a);
        final TextView set2a = findViewById(R.id.Set2a);
        final TextView set3a = findViewById(R.id.Set3a);
        final TextView set1b = findViewById(R.id.Set1b);
        final TextView set2b = findViewById(R.id.Set2b);
        final TextView set3b = findViewById(R.id.Set3b);
        final Game game1 = new Game();
        final Game game2 = new Game();
        final int playerId1 = 0;
        final int playerId2 = 1;


        matchPlayer1.setText(name1);
        matchPlayer2.setText(name2);
        point1.setText("Point " + name1);
        point2.setText("Point " + name2);


        if (setWon[playerId1] == maxWonSet){
            Toast.makeText(this, "You won", Toast.LENGTH_LONG).show();
            Log.i("setwon2",Integer.toString(setWon[playerId1]));
            //finish();
        }
        point1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game1.win();
                if (game1.getScore() >= 3 && game2.getScore() >= 3) {
                    if ((game1.getScore() - game2.getScore()) >= 2) { //wygrany gem
                        game1.setScore(0);
                        game2.setScore(0);
                        table[currentSet][playerId1] = addGame(table[currentSet][playerId1]);
                    }
                    if ((game1.getScore() == game2.getScore()) && game1.getScore() == 4) {//w przypadku wyrowniania
                        game1.setScore(3);
                        game2.setScore(3);
                    }
                }
                if (game1.getScore() >= 4 && (game1.getScore() -game2.getScore())>=2){ //wygrany gem
                    game1.setScore(0);
                    game2.setScore(0);
                    table[currentSet][playerId1] = addGame(table[currentSet][playerId1]);
                }

                int currentBefore = currentSet;
                if (currentSet > currentBefore){
                    table[currentSet][playerId2] = addGame(table[currentSet][playerId2]);
                }
                score1.setText(game1.getDescription());
                score2.setText(game2.getDescription());
                String set = String.valueOf(table[currentSet][playerId1]);
                if (currentSet == 0){
                    set1a.setText(set);
                }else if(currentSet == 1){
                    set2a.setText(set);
                }else if(currentSet == 2){
                    set3a.setText(set);
                }
                if (sety ==5){
                    final TextView set4a = findViewById(R.id.Set4a);
                    final TextView set5a = findViewById(R.id.Set5a);
                    if (currentSet == 3){
                        set4a.setText(set);
                    }else if(currentSet == 4){
                        set5a.setText(set);
                    }
                }
                currentSet = checkSet(table, currentSet, playerId1,setWon,playerId2);
            }
        });
        point2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game2.win();
                if (game1.getScore() >= 3 && game2.getScore() >= 3) {
                    if ((game2.getScore() - game1.getScore()) >= 2) {
                        game1.setScore(0);
                        game2.setScore(0);
                        table[currentSet][playerId2] = addGame(table[currentSet][playerId2]);
                    }
                    if ((game1.getScore() == game2.getScore()) && game2.getScore() == 4) {//w przypadku wyrowniania
                        game1.setScore(3);
                        game2.setScore(3);
                    }
                }
                if (game2.getScore() >= 4 && (game2.getScore() -game1.getScore())>=2){
                    game1.setScore(0);
                    game2.setScore(0);
                    table[currentSet][playerId2] = addGame(table[currentSet][playerId2]);
                }
                int currentBefore = currentSet;
                if (currentSet > currentBefore){
                    table[currentSet][playerId2] = addGame(table[currentSet][playerId2]);
                }
                score1.setText(game1.getDescription());
                score2.setText(game2.getDescription());
                String set = String.valueOf(table[currentSet][playerId2]);
                if (currentSet == 0){
                    set1b.setText(set);
                }else if(currentSet == 1){
                    set2b.setText(set);
                }else if(currentSet == 2){
                    set3b.setText(set);
                }
                if (sety ==5){
                    final TextView set4b = findViewById(R.id.Set4b);
                    final TextView set5b = findViewById(R.id.Set5b);
                    if (currentSet == 3){
                        set4b.setText(set);
                    }else if(currentSet == 4){
                        set5b.setText(set);
                    }
                }
                currentSet = checkSet(table, currentSet, playerId2,setWon,playerId1);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData(table);
            }
        });
    }

    public int checkSet(int[][] table, int currentSet, final int id1, final int[] setWon, final int id2){

        final Intent intent = new Intent(this, TieBreak.class);
        Match.mMyAppsBundle.putBoolean("over",false);


        if (table[currentSet][id1]>=6 && table[currentSet][id1] - table[currentSet][id2]>1){
            currentSet++;
            setWon[id1]++;
            Log.i("setwon1",Integer.toString(setWon[id1]));
        }

        if (setWon[id1] == maxWonSet){
            Toast.makeText(this, "You won", Toast.LENGTH_LONG).show();
            Log.i("setwon2",Integer.toString(setWon[id1]));
            saveData(table);
            finish();
        }

        if (table[currentSet][id1] == 6 && table[currentSet][id2] == table[currentSet][id1]) {
            startActivity(intent);
            setWon[Match.mMyAppsBundle.getInt("won")]++;
            currentSet++;
            Log.i("setwon3",Integer.toString(setWon[id1]));
        }
        return currentSet;
    }

    public void saveData(int[][] table){
        String filename = "DATA";
        File file = new File(this.getFilesDir(), filename);
        FileOutputStream outputStream;

        String data;
        String data1 = Players.mMyAppsBundle.getString("player1");
        String data2 = Players.mMyAppsBundle.getString("player2");
        for( int i =0; i < table.length; i++ ){
            data1 = data1 + "." + (Integer.toString(table[i][0]));
            data2 = data2 + "." + (Integer.toString(table[i][1]));
        }
        data = data1 + " " + data2+"\n";
        Log.i("data",data);

        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int length = (int) file.length();
        byte[] bytes = new byte[length];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(bytes);
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        Toast.makeText(this, "Score saved", Toast.LENGTH_LONG).show();
        String contents = new String(bytes);
        Log.i("dupaa",contents);
    }
    public int addGame(int score){
        score++;
    return score;
    }
}
