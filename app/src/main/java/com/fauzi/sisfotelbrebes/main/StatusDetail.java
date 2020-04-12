package com.fauzi.sisfotelbrebes.main;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fauzi.sisfotelbrebes.R;


public class PengajuanDetail extends AppCompatActivity {


    TextView mStatus, mKet;
    EditText mNomor, mSiteId, mSiteNama, mOperator, mAlamat;

    private String nomor, siteid, sitename, operator, alamat,ket;
    private int id , status;

    ImageView tinjau, verif, sukses;

    ImageView imKembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajuan_detail);

        mStatus = findViewById(R.id.status);
        mKet= findViewById(R.id.ket);
        mNomor= findViewById(R.id.nomor);
        mSiteNama= findViewById(R.id.sitename);
        mSiteId= findViewById(R.id.siteid);
        mOperator = findViewById(R.id.operator);
        mAlamat = findViewById(R.id.alamatpem);

        tinjau = findViewById(R.id.pengajuan1);
        verif = findViewById(R.id.pengajuan2);
        sukses= findViewById(R.id.pengajuan3);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        nomor = intent.getStringExtra("nomor");
        siteid = intent.getStringExtra("siteid");
        sitename = intent.getStringExtra("sitename");
        operator = intent.getStringExtra("operator");
        alamat = intent.getStringExtra("alamat");
        ket = intent.getStringExtra("ket");
        status = intent.getIntExtra("status",0);

        imKembali = findViewById(R.id.kembali);
        imKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PengajuanDetail.this, Status.class);
                startActivity(intent);
                finish();
            }
        });

        readMode();
        setDataFromIntentExtra();
    }


    @SuppressLint("RestrictedApi")
    void readMode(){

        mNomor.setFocusableInTouchMode(false);
        mSiteId.setFocusableInTouchMode(false);
        mSiteNama.setFocusableInTouchMode(false);
        mAlamat.setFocusableInTouchMode(false);
        mOperator.setFocusableInTouchMode(false);

    }

    private void setDataFromIntentExtra() {

        if (id != 0) {
//            getSupportActionBar().setTitle("Nilai  " + matkul.toString());
            if (status==0){
                mStatus.setText("SEDANG DITINJAU");
                tinjau.setVisibility(View.VISIBLE);
                verif.setVisibility(View.GONE);

            }else{
                mStatus.setText("SEDANG DIVERIFIKASI");
                verif.setVisibility(View.VISIBLE);
            }
            mNomor.setText(nomor);
            mSiteId.setText(siteid);
            mSiteNama.setText(sitename);
            mOperator.setText(operator);
            mAlamat.setText(alamat);
            mKet.setText(ket);

        }

    }
}
