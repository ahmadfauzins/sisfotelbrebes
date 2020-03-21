package com.fauzi.sisfotelbrebes.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fauzi.sisfotelbrebes.MainActivity;
import com.fauzi.sisfotelbrebes.R;
import com.fauzi.sisfotelbrebes.adapter.AdapterStatus;
import com.fauzi.sisfotelbrebes.api.ApiClient;
import com.fauzi.sisfotelbrebes.api.ApiInterface;
import com.fauzi.sisfotelbrebes.model.PengajuanModel;
import com.fauzi.sisfotelbrebes.model.Pengguna;
import com.fauzi.sisfotelbrebes.model.User;
import com.fauzi.sisfotelbrebes.utility.PrefUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profil extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterStatus adapterStatus;
    private List<User> mhsList;
    private FloatingActionButton mFab, mFabedit;
    EditText username,nama,alamat,email,no,identitas;
    Spinner provider;
    EditText namapem, alamatpem, no_telp;
    ArrayAdapter<String> SpinnerAdapter;
    List<String>list;
    ImageView imKembali;
    ApiInterface apiInterface;

    String id_User;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        User user = PrefUtil.getUser(this, PrefUtil.USER_SESSION);
        id_User = user.getData().getId();

        username = findViewById(R.id.username);
        nama = findViewById(R.id.nama);
        identitas = findViewById(R.id.identitas);
        alamat = findViewById(R.id.alamat);
        email = findViewById(R.id.email);
        no = findViewById(R.id.no);
        namapem = findViewById(R.id.namapem);
        alamatpem = findViewById(R.id.alamatpem);
        no_telp = findViewById(R.id.no_telp);

        mFabedit = findViewById(R.id.fabedit);
        mFab = findViewById(R.id.fab);

        mFabedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editMode();

            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readMode();
            }
        });

        imKembali = findViewById(R.id.kembali);
        imKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Profil.this, MainActivity.class);
                startActivity(intent);
            }
        });

        provider = findViewById(R.id.provider);
        list = new ArrayList<String >();
        list.add("INDOSAT");
        list.add("TELKOMSEL");
        list.add("XL AXIATA");
        list.add("AXIS");
        list.add("THREE");
        list.add("Lain nya");

        SpinnerAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return super.getView(position, convertView, parent);
            }
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return super.getDropDownView(position, convertView, parent);
            }
        };
        SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provider.setAdapter(SpinnerAdapter);


        readMode();
//        setData();
//        postData(ID_Mhs);

//        recyclerView = findViewById(R.id.rv_buah);
//
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//
//
    }
    public void getUser() {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Pengguna>> call = apiInterface.getUser(id_User);

        call.enqueue(new Callback<List<Pengguna>>() {
            @Override
            public void onResponse(Call<List<Pengguna>> call, Response<List<Pengguna>> response) {
               if (response.isSuccessful()){
                   for (int i = 0; i < response.body().size();i++){
                       username.setText(response.body().get(i).getUsername());
                       nama.setText(response.body().get(i).getNama());
                       identitas.setText(response.body().get(i).getIdentitas());
                       alamat.setText(response.body().get(i).getAlamat());
                       email.setText(response.body().get(i).getEmail());
                       no.setText(response.body().get(i).getNo_hp());
                       namapem.setText(response.body().get(i).getNama_pt());
                       alamatpem.setText(response.body().get(i).getAlamat_pt());
                       no_telp.setText(response.body().get(i).getNo_telp());
                   }
               }
            }

            @Override
            public void onFailure(Call<List<Pengguna>> call, Throwable t) {
                Toast.makeText(Profil.this, "rp :" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUser();
    }

    @SuppressLint("RestrictedApi")
    void readMode(){

        username.setFocusableInTouchMode(false);
        nama.setFocusableInTouchMode(false);
        identitas.setFocusableInTouchMode(false);
        alamat.setFocusableInTouchMode(false);
        email.setFocusableInTouchMode(false);
        no.setFocusableInTouchMode(false);
        namapem.setFocusableInTouchMode(false);
        alamatpem.setFocusableInTouchMode(false);
        no_telp.setFocusableInTouchMode(false);

        mFabedit.setVisibility(View.VISIBLE);
        mFab.setVisibility(View.INVISIBLE);

    }
    @SuppressLint("RestrictedApi")
    void editMode(){

        username.setFocusableInTouchMode(true);
        nama.setFocusableInTouchMode(true);
        identitas.setFocusableInTouchMode(true);
        alamat.setFocusableInTouchMode(true);
        email.setFocusableInTouchMode(true);
        no.setFocusableInTouchMode(true);
        namapem.setFocusableInTouchMode(true);
        alamatpem.setFocusableInTouchMode(true);
        no_telp.setFocusableInTouchMode(true);

        mFabedit.setVisibility(View.INVISIBLE);
        mFab.setVisibility(View.VISIBLE);

    }
}
