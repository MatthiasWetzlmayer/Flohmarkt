package com.example.matth.flohmarkt;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;


public class RecomentFragment extends Fragment {
    ListView lv;
    ArticleAdapter aa;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_recoment,container);
        lv=v.findViewById(R.id.RF_lv);
        return v;
    }

    public void setArticles(){
        ArrayList<Article> rand=new ArrayList<>();
        Random rn=new Random(MainActivity.articles.size());
        for(int i=0;i<5;i++){
            Article r=MainActivity.articles.get(rn.nextInt());
            rand.add(r);
        }
        aa=new ArticleAdapter(getActivity(),rand);
        lv.setAdapter(aa);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(MainActivity.articles==null){
            MainActivity.articles=new ArrayList<>();
            aa=new ArticleAdapter(getActivity(),MainActivity.articles);
            update();
        }else{
            aa=new ArticleAdapter(getActivity(),MainActivity.articles);
        }
    }
    public void update(){
        DownloadTask dt=new DownloadTask();
        dt.execute(aa,getActivity());



    }
}
