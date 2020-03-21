package com.fauzi.sisfotelbrebes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.fauzi.sisfotelbrebes.main.Cell;
import com.fauzi.sisfotelbrebes.main.Maps;
import com.fauzi.sisfotelbrebes.main.Pengajuan;
import com.fauzi.sisfotelbrebes.main.Profil;
import com.fauzi.sisfotelbrebes.main.Regulasi;
import com.fauzi.sisfotelbrebes.main.Status;
import com.fauzi.sisfotelbrebes.main.Syarat;
import com.fauzi.sisfotelbrebes.main.UploadPDF;
import com.fauzi.sisfotelbrebes.utility.PrefId;
import com.fauzi.sisfotelbrebes.utility.PrefUtil;

public class MainActivity extends AppCompatActivity {

    ImageView imPengajuan, imStatus, imCell, imHistory, imPengaduan, imSyarat, imRegulasi, imLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imPengajuan = findViewById(R.id.datadiri);
        imStatus = findViewById(R.id.pengajuan);
        imCell = findViewById(R.id.status);
        imHistory = findViewById(R.id.history);
        imPengaduan = findViewById(R.id.lokasi);
        imSyarat = findViewById(R.id.syarat);
        imRegulasi = findViewById(R.id.regulasi);
        imLogout= findViewById(R.id.logout);


        imPengajuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Profil.class);
                startActivity(intent);
            }
        });
        imStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Pengajuan.class);
                startActivity(intent);
            }
        });
        imCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Status.class);
                startActivity(intent);
            }
        });
        imHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cell.class);
                startActivity(intent);
            }
        });
        imPengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Maps.class);
                startActivity(intent);
            }
        });
        imSyarat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Syarat.class);
                startActivity(intent);
            }
        });
        imRegulasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Regulasi.class);
                startActivity(intent);
            }
        });
        imLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDialog();
            }
        });


    }

    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Keluar dari Sisfotel Brebes?");
        alertDialogBuilder
                .setMessage("Sistem Informasi Pendaftaran Menara Telekomunikasi Brebes")
                .setIcon(R.drawable.logobrebes)
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        logout();
                    }
                })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    private void logout() {
        PrefId.clear(this);
        PrefUtil.clear(this);
        startActivity(new Intent(getBaseContext(),Splash.class));
        finish();
    }
}

