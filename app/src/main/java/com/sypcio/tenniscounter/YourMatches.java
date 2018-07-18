package com.sypcio.tenniscounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sypa1 on 18.06.2018.
 */

public class YourMatches extends AppCompatActivity {

    final List<String> nameList = new ArrayList<>();        //Tworzę nameList na nazwy past


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourmatches);
        final ListView listView = new ListView(this);
        setContentView(listView);

//podziel plik DATA na mecze kropką

        String filename = "DATA";
        File file = new File(this.getFilesDir(), filename);

        int length = (int) file.length();
        byte[] bytes = new byte[length];

        try {
            FileInputStream in = new FileInputStream(file);
            in.read(bytes);
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        String contents = new String(bytes);

        String[] matches = contents.split("\n");


        for (int i = 0; i < matches.length; i++) {          //w petli wpisuj kazdy zapisany mecz
            matches[i] = matches[i].replace(" ","\n");
            matches[i] = matches[i].replace("."," ");
            Log.i("costam",matches[i]);
            nameList.add(matches[i]);
            }

            ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(YourMatches.this, R.layout.activity_yourmatches, R.id.names, nameList);
            //pb.setVisibility(View.INVISIBLE);
            listView.setAdapter(namesAdapter);          //wyświetlam listę nazw past w adapterze
        }

}
