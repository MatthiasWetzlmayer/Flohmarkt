package com.example.matth.flohmarkt;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteTask extends AsyncTask<Object,Object,Object> {


    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        ShowArticle sa= (ShowArticle) o;
        sa.onBackPressed();
    }

    @Override
    protected Object doInBackground(Object... objects) {
        Article a= (Article) objects[0];

        String url="http://eaustria.no-ip.biz/flohmarkt/flohmarkt.php?operation=delete&id="+a.id+
                "&username="+MainActivity.prefs.getString("uname","")+
                "&password="+MainActivity.prefs.getString("psw","");
        try {
            HttpURLConnection http= (HttpURLConnection) new URL(url).openConnection();
            http.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objects[1];
    }
}
