package com.example.matth.flohmarkt;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//Uname u Psw für webserver:
//WetzlmMt
// 17154

static ArrayList<Article> articles;
static ArrayList<Article> yours;
ListView lv;
ListView y;
static ArticleAdapter aa;
static ArticleAdapter aYours;
static SharedPreferences prefs;
static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        articles=new ArrayList<>();
        yours=new ArrayList<>();
        lv=findViewById(R.id.lv_All);
        y=findViewById(R.id.lv_yours);
        aa=new ArticleAdapter(this, articles);
        aYours=new ArticleAdapter(this,yours);
        lv.setAdapter(aa);
        y.setAdapter(aYours);
        prefs=PreferenceManager.getDefaultSharedPreferences(this);

        update();

       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Article a=articles.get(position);
               Intent i=new Intent(MainActivity.this,ShowArticle.class);
               i.putExtra("id",a.id);
               startActivity(i);

           }
       });
        y.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article a=yours.get(position);
                Intent i=new Intent(MainActivity.this,ShowArticle.class);
                i.putExtra("id",a.id);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.options){
            Intent i=new Intent(MainActivity.this,Options.class);
            startActivity(i);
        }else if(item.getItemId()==R.id.add){
            if(prefs.getString("uname","-").equals("-")||prefs.getString("psw","-").equals("-")){
                Intent i=new Intent(MainActivity.this,Options.class);
                startActivity(i);
            }else{
                AlertDialog.Builder b=new AlertDialog.Builder(this);
                final View v=getLayoutInflater().inflate(R.layout.adddialog,null);
                b.setTitle("Neuen Artikel hinzufügen")
                .setPositiveButton("Hinzufügen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText name=v.findViewById(R.id.add_Artname);
                        EditText price=v.findViewById(R.id.add_Price);
                        EditText email=v.findViewById(R.id.add_Email);
                        EditText tel=v.findViewById(R.id.add_Tel);

                        Article a=new Article(0,Integer.parseInt(price.getText().toString()),name.getText().toString(),email.getText().toString(),prefs.getString("uname",""),tel.getText().toString(),0.0,0.0);
                        UploadTask task=new UploadTask();
                        task.execute(a);
                    }
                })
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setView(v).create().show();
            }
        }

        return super.onOptionsItemSelected(item);

    }
    public static void update(){
        DownloadTask dt=new DownloadTask();
        dt.execute(articles);


    }

    @Override
    protected void onStart() {
        super.onStart();
        update();
    }
}
