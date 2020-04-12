package com.fauzi.sisfotelbrebes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.opengl.ETC1;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fauzi.sisfotelbrebes.api.ApiClient;
import com.fauzi.sisfotelbrebes.api.ApiInterface;
import com.fauzi.sisfotelbrebes.api.LoginService;
import com.fauzi.sisfotelbrebes.model.Pengguna;
import com.fauzi.sisfotelbrebes.model.User;
import com.fauzi.sisfotelbrebes.utility.PrefUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    TextView tvRegist;
    Button bLogin;
    EditText memail,mpassword;
    private ApiInterface apiInterface;
    private LoginService loginService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvRegist = findViewById(R.id.regis);
        bLogin = findViewById(R.id.btn_login);
        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        
        if(isSessionLogin()) {
            startActivity(new Intent(getBaseContext(),MainActivity.class));
            finish();
        }

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAct();
            }
        });

        tvRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);

            }
        });


    }

    void loginAct() {

            memail.setError(null);
            mpassword.setError(null);
            View fokus = null;
            boolean cancel = false;

            String email = memail.getText().toString().trim();
            String password = mpassword.getText().toString().trim();

            if(TextUtils.isEmpty(email)) {
                memail.setError("Email cannot be empty!");
                fokus = memail;
                cancel = true;
            }

            if(TextUtils.isEmpty(password)) {
                mpassword.setError("Password cannot be empty");
                fokus = mpassword;
                cancel = true;
            }

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Login...");
            progressDialog.show();

            loginService = new LoginService(this);

            loginService.doLogin(email, password, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                progressDialog.dismiss();

                User user = (User) response.body();
                String valu = user.getValue();

                if(!user.isError()) {
                    if (valu.equals("1")) {
                        PrefUtil.putUser(Login.this, PrefUtil.USER_SESSION, user);
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    Toast.makeText(Login.this, user.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Login.this, "Koneksi Tidak Ada!", Toast.LENGTH_SHORT).show();
            }
        });

//
//        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//
//        Call<Pengguna> call = apiInterface.Login(cekemail,cekpass);
//
//        call.enqueue(new Callback<Pengguna>() {
//            @Override
//            public void onResponse(Call<Pengguna> call, Response<Pengguna> response) {
//                progressDialog.dismiss();
//
//                String value = response.body().getValue();
//                String message = response.body().getMessage();
//
//
//                if (value.equals("1")) {
//                    finish();
//                    Intent intent = new Intent(Login.this, MainActivity.class);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Pengguna> call, Throwable t) {
//                progressDialog.dismiss();
//                Toast.makeText(Login.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }


    private boolean isSessionLogin() {
        return PrefUtil.getUser(this, PrefUtil.USER_SESSION) != null;
    }

}
