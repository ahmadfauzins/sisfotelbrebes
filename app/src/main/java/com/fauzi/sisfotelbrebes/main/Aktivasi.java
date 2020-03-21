package com.fauzi.sisfotelbrebes.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fauzi.sisfotelbrebes.Login;
import com.fauzi.sisfotelbrebes.R;

public class Aktivasi extends AppCompatActivity {

    Button regis;
    TextView  tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktivasi);


        tvLogin = findViewById(R.id.tv_Login);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Aktivasi.this, Login.class);
                startActivity(intent);

                finish();
            }
        });

        regis = findViewById(R.id.btn_login);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Aktivasi.this,Login.class);
                startActivity(intent);

                finish();
            }
        });

    }
}
