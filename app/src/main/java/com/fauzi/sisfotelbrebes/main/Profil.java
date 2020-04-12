package com.fauzi.sisfotelbrebes.main;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.fauzi.sisfotelbrebes.MainActivity;
import com.fauzi.sisfotelbrebes.R;
import com.fauzi.sisfotelbrebes.adapter.AdapterStatus;
import com.fauzi.sisfotelbrebes.api.ApiClient;
import com.fauzi.sisfotelbrebes.api.ApiInterface;
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
    EditText mUsername,mNama,mAlamat,mEmail,mNo,mIdentitas;
    Spinner provider;
    EditText mNamapem, mAlamatpem, mNo_telp;
    ArrayAdapter<String> SpinnerAdapter;
    List<String>list;
    ImageView imKembali;
    ApiInterface apiInterface;

    String id_User;
    int iduser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        User user = PrefUtil.getUser(this, PrefUtil.USER_SESSION);
        id_User = user.getData().getId();
        iduser = Integer.parseInt(user.getData().getId());

        mUsername= findViewById(R.id.username);
        mNama = findViewById(R.id.nama);
        mIdentitas= findViewById(R.id.identitas);
        mAlamat= findViewById(R.id.alamat);
        mEmail= findViewById(R.id.email);
        mNo = findViewById(R.id.no);
        mNamapem= findViewById(R.id.namapem);
        mAlamatpem= findViewById(R.id.alamatpem);
        mNo_telp= findViewById(R.id.no_telp);

        mFabedit = findViewById(R.id.fabedit);
        mFab = findViewById(R.id.fab);

        mFabedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogEdit();

            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readMode();
                showDialog();
            }
        });

        imKembali = findViewById(R.id.kembali);
        imKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profil.this, MainActivity.class);
                startActivity(intent);
                finish();
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
                       mUsername.setText(response.body().get(i).getUsername());
                       mNama.setText(response.body().get(i).getNama());
                       mIdentitas.setText(response.body().get(i).getIdentitas());
                       mAlamat.setText(response.body().get(i).getAlamat());
                       mEmail.setText(response.body().get(i).getEmail());
                       mNo.setText(response.body().get(i).getNo_hp());
                       mNamapem.setText(response.body().get(i).getNama_pt());
                       mAlamatpem.setText(response.body().get(i).getAlamat_pt());
                       mNo_telp.setText(response.body().get(i).getNo_telp());
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

        mUsername.setFocusableInTouchMode(false);
        mNama.setFocusableInTouchMode(false);
        mIdentitas.setFocusableInTouchMode(false);
        mAlamat.setFocusableInTouchMode(false);
        mEmail.setFocusableInTouchMode(false);
        mNo.setFocusableInTouchMode(false);
        mNamapem.setFocusableInTouchMode(false);
        mAlamatpem.setFocusableInTouchMode(false);
        mNo_telp.setFocusableInTouchMode(false);

        mFabedit.setVisibility(View.VISIBLE);
        mFab.setVisibility(View.INVISIBLE);

    }
    @SuppressLint("RestrictedApi")
    void editMode(){

        mUsername.setFocusableInTouchMode(true);
        mNama.setFocusableInTouchMode(true);
        mIdentitas.setFocusableInTouchMode(true);
        mAlamat.setFocusableInTouchMode(true);
        mEmail.setFocusableInTouchMode(true);
        mNo.setFocusableInTouchMode(true);
        mNamapem.setFocusableInTouchMode(true);
        mAlamatpem.setFocusableInTouchMode(true);
        mNo_telp.setFocusableInTouchMode(true);

        mFabedit.setVisibility(View.INVISIBLE);
        mFab.setVisibility(View.VISIBLE);

    }
    private void updateMode(final String key, final int id) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        readMode();

        String username = mUsername.getText().toString().trim();
        String namalengkap = mNama.getText().toString().trim();
        String ktp = mIdentitas.getText().toString().trim();
        String alamatlengkap = mAlamat.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        final String no_hp = mNo.getText().toString().trim();
        String nama_pem = mNamapem.getText().toString().trim();
        String alamat_pem = mAlamatpem.getText().toString().trim();
        String no_telp = mNo_telp.getText().toString().trim();
        String picture = null;

//        if (bitmap == null) {
//            picture = "";
//        } else {
//            picture = getStringImage(bitmap);
//        }

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Pengguna> call = apiInterface.updateUser(key, id, username,namalengkap,ktp,alamatlengkap,
                email,no_hp,nama_pem,alamat_pem,no_telp);

        call.enqueue(new Callback<Pengguna>() {
            @Override
            public void onResponse(Call<Pengguna> call, Response<Pengguna> response) {
                progressDialog.dismiss();

                String value = response.body().getValue();
                String message = response.body().getMessage();
                if (value.equals("1")){
                    Intent intent = new Intent(Profil.this,Profil.class);
                    Toast.makeText(Profil.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Profil.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pengguna> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Profil.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Ubah Data Profil Pemohon?");
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Update",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        updateMode("update",iduser);

                    }
                })
                .setNegativeButton("Batal",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }
    private void showDialogEdit(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Edit Data Profil Pemohon?");
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Edit",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        editMode();
                    }
                })
                .setNegativeButton("Batal",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }
}
