package com.fauzi.sisfotelbrebes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imPengajuan, imStatus, imCell, imHistory, imPengaduan, imSyarat, imRegulasi, imLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imPengajuan = findViewById(R.id.pengajuan);
        imStatus = findViewById(R.id.status);
        imCell = findViewById(R.id.cell);
        imHistory = findViewById(R.id.history);
        imPengaduan = findViewById(R.id.pengaduan);
        imSyarat = findViewById(R.id.syarat);
        imRegulasi = findViewById(R.id.regulasi);
        imLogout= findViewById(R.id.logout);


        imPengajuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UploadPDF.class);
                startActivity(intent);
            }
        });
        imStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Status.class);
                startActivity(intent);
            }
        });
        imCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Maps.class);
                startActivity(intent);
            }
        });
        imHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Pengajuan.class);
                startActivity(intent);
            }
        });
        imPengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Pengajuan.class);
                startActivity(intent);
            }
        });
        imSyarat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Pengajuan.class);
                startActivity(intent);
            }
        });
        imRegulasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Pengajuan.class);
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
                        Intent intent = new Intent(MainActivity.this,Login.class);
                        startActivity(intent);
                        finish();
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

}

