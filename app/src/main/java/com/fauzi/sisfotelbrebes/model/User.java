package com.fauzi.sisfotelbrebes.model;


public class User extends BaseResponse {

    Pengguna data;

    public User() {
    }

    public Pengguna getData() {
        return data;
    }

    public void setData(Pengguna data) {
        this.data = data;
    }
}
