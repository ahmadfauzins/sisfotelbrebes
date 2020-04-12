package com.fauzi.sisfotelbrebes.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.fauzi.sisfotelbrebes.R;

public class CellDetail extends AppCompatActivity {

    EditText mSiteId, mSiteNama, mOperator, mAlamat,mStatus, mTinggi, mTahun, mLama, mPemilik, mLat,mLing;

    private String siteid, sitename, operator, alamat,tinggi,tahun,lama,pemilik,lat,ling;
    private int id , status;


    ImageView imKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell_detail);

        mStatus = findViewById(R.id.status);
        mSiteNama= findViewById(R.id.sitename);
        mSiteId= findViewById(R.id.siteid);
        mOperator = findViewById(R.id.operator);
        mAlamat = findViewById(R.id.alamatpem);
        mTinggi= findViewById(R.id.tinggi);
        mTahun= findViewById(R.id.dibangun);
        mLama= findViewById(R.id.lamasewa);
        mPemilik= findViewById(R.id.pemilik);
        mLat = findViewById(R.id.lat);
        mLing = findViewById(R.id.ling);


        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        siteid = intent.getStringExtra("siteid");
        sitename = intent.getStringExtra("sitename");
        operator = intent.getStringExtra("operator");
        alamat = intent.getStringExtra("alamat");
        tinggi = intent.getStringExtra("tinggi");
        tahun = intent.getStringExtra("tahun");
        lama = intent.getStringExtra("lama");
        pemilik = intent.getStringExtra("pemilik");
        lat = intent.getStringExtra("lat");
        ling = intent.getStringExtra("ling");
        status = intent.getIntExtra("status",0);

        imKembali = findViewById(R.id.kembali);
        imKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CellDetail.this, Cell.class);
                startActivity(intent);
                finish();
            }
        });

        readMode();
        setDataFromIntentExtra();
    }


    @SuppressLint("RestrictedApi")
    void readMode(){
        mStatus.setFocusableInTouchMode(false);
        mSiteId.setFocusableInTouchMode(false);
        mSiteNama.setFocusableInTouchMode(false);
        mAlamat.setFocusableInTouchMode(false);
        mOperator.setFocusableInTouchMode(false);
        mTinggi.setFocusableInTouchMode(false);
        mTahun.setFocusableInTouchMode(false);
        mLama.setFocusableInTouchMode(false);
        mPemilik.setFocusableInTouchMode(false);
        mLat.setFocusableInTouchMode(false);
        mLing.setFocusableInTouchMode(false);
    }

    private void setDataFromIntentExtra() {

        if (id != 0) {
//            getSupportActionBar().setTitle("Nilai  " + matkul.toString());
            if (status==0){
                mStatus.setText("OFFLINE");

            }else{
                mStatus.setText("EXISTING");
            }
            mSiteId.setText(siteid);
            mSiteNama.setText(sitename);
            mOperator.setText(operator);
            mAlamat.setText(alamat);
            mTinggi.setText(tinggi);
            mTahun.setText(tahun);
            mLama.setText(lama);
            mPemilik.setText(pemilik);
            mLat.setText(lat);
            mLing.setText(ling);

        }

    }
}
