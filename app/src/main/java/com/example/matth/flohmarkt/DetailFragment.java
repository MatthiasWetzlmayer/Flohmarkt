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
import android.widget.TextView;


public class DetailFragment extends Fragment {
    TextView tv;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_detail,container);
        tv=v.findViewById(R.id.DF_tv);
        v.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int width=v.getMeasuredWidth();
        int height=v.getMeasuredHeight();

        tv.setText(width+" W, "+height+" H");
        return v;
    }
    public void showDetail(Article a){
        tv.setText(a.toInfo());
    }
}
