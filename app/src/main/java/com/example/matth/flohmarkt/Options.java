package com.example.matth.flohmarkt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getSupportFragmentManager()
        .beginTransaction()
        .replace(android.R.id.content, new Prefs()).commit();

    }
}
