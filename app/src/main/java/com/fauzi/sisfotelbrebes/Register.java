package com.fauzi.sisfotelbrebes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fauzi.sisfotelbrebes.api.ApiClient;
import com.fauzi.sisfotelbrebes.api.ApiInterface;
import com.fauzi.sisfotelbrebes.main.Aktivasi;
import com.fauzi.sisfotelbrebes.model.Pengguna;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    TextView tvLogin;
    EditText username,nama,alamat,email,no,password,repassword, identitas;
    Spinner provider;
    EditText namapem, alamatpem, no_telp;
    Button register;
    ArrayAdapter<String>SpinnerAdapter;

    List<String>list;

    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvLogin = findViewById(R.id.login);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);

                finish();
            }
        });

        username = findViewById(R.id.username);
        nama = findViewById(R.id.nama);
        identitas = findViewById(R.id.identitas);
        alamat = findViewById(R.id.alamat);
        email = findViewById(R.id.email);
        no = findViewById(R.id.no);
        namapem = findViewById(R.id.namapem);
        alamatpem = findViewById(R.id.alamatpem);
        no_telp = findViewById(R.id.no_telp);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);

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

        register = findViewById(R.id.btn_reg);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daftar();
            }
        });

    }

    private void daftar() {
        username.setError(null);
        nama.setError(null);
        identitas.setError(null);
        alamat.setError(null);
        email.setError(null);
        no.setError(null);
        namapem.setError(null);
        alamatpem.setError(null);
        no_telp.setError(null);
        password.setError(null);
        repassword.setError(null);

        View fokus = null;
        boolean cancel = false;

        String cekuser = username.getText().toString();
        String ceknama = nama.getText().toString();
        String cekalamat = alamat.getText().toString();
        String cekemail= email.getText().toString();
        String cekno = no.getText().toString();
        String ceknamapem = namapem.getText().toString();
        String cekalamatpem = alamatpem.getText().toString();
        String cekno_telp = no_telp.getText().toString();
        String cekpass = password.getText().toString();
        String cekrepass = repassword.getText().toString();


        if (TextUtils.isEmpty(cekuser)){
                username.setError("This field is required");
                fokus = username;
                cancel = true;
            }
        if (TextUtils.isEmpty(ceknama)){
            nama.setError("This field is required");
            fokus = nama;
            cancel = true;
        }
        if (TextUtils.isEmpty(cekalamat)){
            alamat.setError("This field is required");
            fokus = alamat;
            cancel = true;
        }
        if (TextUtils.isEmpty(cekemail)){
            email.setError("This field is required");
            fokus = email;
            cancel = true;
        }
        if (TextUtils.isEmpty(cekno)){
            no.setError("This field is required");
            fokus = no;
            cancel = true;
        }
        if (TextUtils.isEmpty(ceknamapem)){
            namapem.setError("This field is required");
            fokus = namapem;
            cancel = true;
        }
        if (TextUtils.isEmpty(cekalamatpem)){
            alamatpem.setError("This field is required");
            fokus = alamatpem;
            cancel = true;
        }
        if (TextUtils.isEmpty(cekno_telp)){
            no_telp.setError("This field is required");
            fokus = no_telp;
            cancel = true;
        }
        if (TextUtils.isEmpty(cekpass)){
            password.setError("This field is required");
            fokus = password;
            cancel = true;
            }else if (!cekPassword(cekpass,cekrepass)){
            repassword.setError("This password is incorrect");
            fokus = repassword;
            cancel = true;
        }

        if (cancel) fokus.requestFocus();
        else   postData("insert");
    }

        private void postData(final String key) {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Saving...");
            progressDialog.show();

            String cekuser = username.getText().toString();
            String ceknama = nama.getText().toString();
            String cekid = identitas.getText().toString();
            String cekalamat = alamat.getText().toString();
            String cekemail= email.getText().toString();
            String ceknamapem = namapem.getText().toString();
            String cekalamatpem = alamatpem.getText().toString();
            String cekno_telp = no_telp.getText().toString();
            String cekpro = String.valueOf(provider.getSelectedItem());
            String cekno = no.getText().toString();
            String cekpass = password.getText().toString();

            apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

            Call<Pengguna> call = apiInterface.Register(key, cekuser, ceknama, cekid, cekalamat,cekemail,cekno,cekpro,
                                ceknamapem, cekalamatpem, cekno_telp, cekpass);

            call.enqueue(new Callback<Pengguna>() {
                @Override
                public void onResponse(Call<Pengguna> call, Response<Pengguna> response) {
                    progressDialog.dismiss();

                    Log.i(Register.class.getSimpleName(), response.toString());

                    String value = response.body().getValue();
                    String message = response.body().getMessage();

                    if (value.equals("1")) {
                        finish();
                        Intent intent = new Intent(Register.this, Aktivasi.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Pengguna> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(Register.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();

                }
            });
        }

    private boolean cekPassword(String password, String repassword){
            return password.equals(repassword);
        }
}

