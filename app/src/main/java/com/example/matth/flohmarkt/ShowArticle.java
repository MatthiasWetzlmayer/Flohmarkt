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
        TextView tv=findViewById(R.id.show_tv);
        tv.setText(a.toInfo());

        Button b=findViewById(R.id.call);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s="tel:(+43) "+a.phone;
                Uri u=Uri.parse(s);
                Intent i=new Intent(Intent.ACTION_DIAL,u);
                startActivity(i);
            }
        });
        Button email=findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_SEND,Uri.parse("mailto"));

                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_EMAIL,a.email);
                i.putExtra(Intent.EXTRA_CC,a.email);
                i.putExtra(Intent.EXTRA_SUBJECT, a.name+" kaufen");
                startActivity(Intent.createChooser(i, "Send Email"));
            }
        });
        Button card=findViewById(R.id.card);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pos="geo:"+a.lat+","+a.lon;
                Uri uri = Uri.parse(pos);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                startActivity(intent);
            }
        });

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

}
