package com.example.molytv.models;

import java.util.ArrayList;

public class User {
    private int uid;
    private String name;
    private String email;
    private String phone;
    private String password;
    private ArrayList wishList; //or List?
    public User(int uid, String name, String email, String phone, ArrayList wishList) {
        this.uid=uid;
        this.name=name;
        this.email=email;
        this.wishList=wishList;
    }

    public User(String name, String email, String password) {
        this.name=name;
        this.email=email;
        this.password=password;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList getWishList() {
        return wishList;
    }

    public void setWishList(ArrayList wishList) {
        this.wishList = wishList;
    }
}