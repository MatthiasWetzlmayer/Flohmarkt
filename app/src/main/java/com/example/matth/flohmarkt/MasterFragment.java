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


public class MasterFragment extends Fragment {
    ListView lv;
    ArticleAdapter aa;
    MasterClicked mc;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_master,container);
        lv=v.findViewById(R.id.lv_All);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mc.onMasterClicked(MainActivity.articles.get(position));
            }
        });

        return v;
    }
    public interface MasterClicked{
        public void onMasterClicked(Article a);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MasterClicked){
            mc= (MasterClicked) context;
        }

    }

    public void update(){
        DownloadTask dt=new DownloadTask();
        dt.execute(aa,getActivity());



    }

    @Override
    public void onStart() {
        super.onStart();

        if(MainActivity.articles==null){
            MainActivity.articles=new ArrayList<>();
            aa=new ArticleAdapter(getActivity(),MainActivity.articles);
            lv.setAdapter(aa);
            update();
        }else{
            aa=new ArticleAdapter(getActivity(),MainActivity.articles);
            lv.setAdapter(aa);
        }



    }
}
