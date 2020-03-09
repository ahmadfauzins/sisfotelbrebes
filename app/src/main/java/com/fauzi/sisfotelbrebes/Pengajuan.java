package com.fauzi.sisfotelbrebes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Pengajuan extends AppCompatActivity {

    ImageView imKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajuan);

        imKembali = findViewById(R.id.kembali);
        imKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pengajuan.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
