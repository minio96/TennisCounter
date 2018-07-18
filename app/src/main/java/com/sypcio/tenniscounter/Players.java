package com.sypcio.tenniscounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sypa1 on 18.06.2018.
 */


public class Players extends AppCompatActivity{
    public String name1 = "";
    public String name2 = "";
    public static Bundle mMyAppsBundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        final EditText player1 = findViewById(R.id.player1);
        final EditText player2 = findViewById(R.id.player2);
        final Button confirm3 = findViewById(R.id.confirm3_btn);
        final Button confirm5 = findViewById(R.id.confirm5_btn);
        final Intent intent = new Intent(this, Match.class);

        player1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                name1 = player1.getText().toString();
                Players.mMyAppsBundle.putString("player1",name1);
            }
        });

        player2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                name2 = player2.getText().toString();
                Players.mMyAppsBundle.putString("player2",name2);
            }
        });

        confirm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name1.equals("") && name2.equals("")){
                    error();
                }else {
                    intent.putExtra("sety", 3);
                    startActivity(intent);
                }
            }
        });
        confirm5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name1.equals("") && name2.equals("")){
                    error();
                }else {
                    intent.putExtra("sety", 5);
                    startActivity(intent);
                }
            }
        });


    }

    public void error(){
        Toast.makeText(this, "Input name", Toast.LENGTH_LONG).show();
    }
}
