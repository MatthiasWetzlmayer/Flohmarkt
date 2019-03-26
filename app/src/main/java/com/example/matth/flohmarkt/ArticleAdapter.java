package com.example.matth.flohmarkt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ArticleAdapter extends BaseAdapter {
ArrayList<Article> list;
LayoutInflater li;
    public ArticleAdapter(Context ctx, ArrayList<Article> list){
        this.list=list;
        li= (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=li.inflate(R.layout.lv_layout,null);
        Article a=list.get(position);
        TextView name=v.findViewById(R.id.lvL_tvName);
        TextView price=v.findViewById(R.id.lvL_tvPrice);
        name.setText(a.name);
        price.setText(a.price+"");
        return v;
    }
}
