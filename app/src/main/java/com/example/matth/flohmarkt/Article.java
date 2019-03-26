package com.example.matth.flohmarkt;

import java.util.Date;

public class Article {
    int id,phone,price;
    String name,email,username;
    Date created;
    boolean y;
    public Article(int id,int price, String name, String email, String username, int phone){
        this.id=id;
        this.price=price;
        this.name=name;
        this.email=email;
        this.username=username;
        this.phone=phone;
        created=new Date();
        y=false;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setY(boolean y) {
        this.y = y;
    }
    public String toInfo(){
        return name+"\n"+price+"\n"+username+"\n"+email+"\n"+phone;
    }
}
