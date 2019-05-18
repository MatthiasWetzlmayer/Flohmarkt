package com.example.matth.flohmarkt;

import android.app.Activity;
import android.content.Intent;
import android.drm.DrmStore;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.URI;

public class ShowArticle extends AppCompatActivity {
Article a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_article);
        int id=getIntent().getExtras().getInt("id");
        for(Article a: MainActivity.articles){
            if(a.id==id){
                this.a=a;
            }
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=getMenuInflater();
        if(a.y){
            mi.inflate(R.menu.deletemenu,menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DeleteTask t=new DeleteTask();
        t.execute(a,this);

        super.onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        DetailFragment df= (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.ac_detail);
        df.showDetail(a);
    }
}
