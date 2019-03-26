package com.example.matth.flohmarkt;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UploadTask extends AsyncTask<Article,Integer,Integer> {
    @Override
    protected Integer doInBackground(Article... articles) {
        Article a=articles[0];
        String url="http://eaustria.no-ip.biz/flohmarkt/flohmarkt.php?operation=add&name="+
                a.name+"&price="+a.price+"&username="+MainActivity.prefs.getString("uname","") +
                "&password="+MainActivity.prefs.getString("psw","")+"&email="+a.email+
                "&phone="+a.phone;

        try {
            HttpURLConnection http= (HttpURLConnection) new URL(url).openConnection();
           http.getInputStream();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        MainActivity.update();
    }
}
