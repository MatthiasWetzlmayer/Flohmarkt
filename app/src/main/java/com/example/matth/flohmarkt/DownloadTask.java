package com.example.matth.flohmarkt;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

public class DownloadTask extends AsyncTask<ArrayList<Article>, Integer, ArrayList<Article>> {
    @Override
    protected ArrayList<Article> doInBackground(ArrayList<Article>... arrayLists) {
        ArrayList<Article> articles=arrayLists[0];


        try {
            HttpURLConnection http= (HttpURLConnection) new URL("http:\\\\eaustria.no-ip.biz\\flohmarkt\\flohmarkt.php?operation=get").openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type", "application/json");
            BufferedReader br=new BufferedReader(new InputStreamReader(http.getInputStream()));

            String json="";
            String s=null;
            while((s=br.readLine())!=null){
                json+=s+"\n";
            }
            articles.clear();

            JSONObject j=new JSONObject(json);
            JSONArray arr=j.getJSONArray("data");

            for(int i=0;i<arr.length();i++){
                try {
                    j = arr.getJSONObject(i);
                    Article a = new Article(j.getInt("id"), j.getInt("price"), j.getString("name"), j.getString("email"), j.getString("username"), j.getString("phone"),j.getDouble("lat"),j.getDouble("lon"));
                    a.setCreated(MainActivity.sdf.parse(j.getString("created")));
                    articles.add(a);
                }catch (JSONException ex){
                    ex.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }



        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Article> articles) {
        MainActivity.yours.clear();
        for(Article a:MainActivity.articles){
            if(a.username.equals(MainActivity.prefs.getString("uname",""))){
                MainActivity.yours.add(a);
                a.setY(true);
            }
        }
        MainActivity.aYours.notifyDataSetChanged();
        MainActivity.aa.notifyDataSetChanged();
        super.onPostExecute(articles);
    }
}
