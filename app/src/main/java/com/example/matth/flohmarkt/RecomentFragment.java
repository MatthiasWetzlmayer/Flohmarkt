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
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;


public class RecomentFragment extends Fragment {
    ListView lv;
    ArticleAdapter aa;
    MasterFragment.MasterClicked mc;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_recoment,container);
        lv=v.findViewById(R.id.RF_lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Article> list=aa.getList();
                mc.onMasterClicked(list.get(position));
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MasterFragment.MasterClicked){
            mc= (MasterFragment.MasterClicked) context;
        }
    }

    public void setArticles(){
        ArrayList<Article> rand=new ArrayList<>();
        Random rn=new Random();
        for(int i=0;i<5;i++){
            int random=rn.nextInt();
            Article r=MainActivity.articles.get(rn.nextInt(MainActivity.articles.size()));
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
        }

    }
    public void update(){
        DownloadTask dt=new DownloadTask();
        dt.execute(aa,getActivity());



    }

    public void onMasterChanged() {
        if(MainActivity.articles!=null){
            setArticles();
        }
    }
}
