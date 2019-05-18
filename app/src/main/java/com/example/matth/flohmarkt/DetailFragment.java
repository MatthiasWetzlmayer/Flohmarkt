package com.example.matth.flohmarkt;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class DetailFragment extends Fragment {
    TextView tv;
    Button b,email,card;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_detail,container);
        tv=v.findViewById(R.id.DF_tv);

        b=v.findViewById(R.id.call);
        email=v.findViewById(R.id.email);
        card=v.findViewById(R.id.card);

        b.setVisibility(View.INVISIBLE);
        email.setVisibility(View.INVISIBLE);
        card.setVisibility(View.INVISIBLE);
        return v;
    }
    public void showDetail(final Article a){
        tv.setText(a.toInfo());
        b.setVisibility(View.VISIBLE);
        email.setVisibility(View.VISIBLE);
        card.setVisibility(View.VISIBLE);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s="tel:(+43) "+a.phone;
                Uri u=Uri.parse(s);
                Intent i=new Intent(Intent.ACTION_DIAL,u);
                startActivity(i);
            }
        });
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
}
