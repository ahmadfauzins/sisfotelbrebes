package com.fauzi.sisfotelbrebes.api;

import android.content.Context;
import android.widget.EditText;


import com.fauzi.sisfotelbrebes.api.config.RetrofitBuilder;

import retrofit2.Callback;

public class LoginService {

    private LoginInterface loginInterface;

    public LoginService(Context context) {
        loginInterface = RetrofitBuilder.builder(context)
                .create(LoginInterface.class);
    }

    public void doLogin(String email, String password, Callback callback) {
        loginInterface.login(email, password).enqueue(callback);
    }

}
