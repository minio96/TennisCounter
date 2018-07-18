package com.sypcio.tenniscounter;

import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sypa1 on 20.06.2018.
 */

public class Game {
    private String[] point = {"0","15","30","40","Adv"};
    public static final List<String> points = new ArrayList<String>();
    public int score;
    public int[] scoreTable;

    public Game(){
        for(int i = 0; i< point.length; i++) {
            points.add(point[i]);
        }
    }
    public void setScore(int i) {
        this.score = i;
    }
    public void gameWon(){

    }

    public int getScore(){
        return score;
    }

    public void win(){
        this.score = this.score + 1;
    }

    public String getDescription(){
        return points.get(this.score);
    }
    public void  gameLost(){this.score = 0;}

}
