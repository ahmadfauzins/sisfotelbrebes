package com.fauzi.sisfotelbrebes.uploadBerkas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.fauzi.sisfotelbrebes.R;
import com.fauzi.sisfotelbrebes.main.Status;
import com.fauzi.sisfotelbrebes.utility.PrefId;


public class PengajuanBerkasSelesai extends AppCompatActivity {

    Button regis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktivasi_verifikasi);


        regis = findViewById(R.id.btn_login);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               selesai();
               Intent intent = new Intent(PengajuanBerkasSelesai.this,Status.class);
               startActivity(intent);
               finish();
            }
        });

    }
    private void selesai() {
        PrefId.clear(this,"id","");
    }
}
