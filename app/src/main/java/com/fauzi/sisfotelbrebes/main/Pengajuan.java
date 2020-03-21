package com.fauzi.sisfotelbrebes.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fauzi.sisfotelbrebes.Login;
import com.fauzi.sisfotelbrebes.MainActivity;
import com.fauzi.sisfotelbrebes.R;
import com.fauzi.sisfotelbrebes.api.ApiClient;
import com.fauzi.sisfotelbrebes.api.ApiInterface;
import com.fauzi.sisfotelbrebes.model.PengajuanModel;
import com.fauzi.sisfotelbrebes.model.Upload;
import com.fauzi.sisfotelbrebes.model.User;
import com.fauzi.sisfotelbrebes.uploadBerkas.PengajuanBerkas;
import com.fauzi.sisfotelbrebes.uploadBerkas.UploadBerkas;
import com.fauzi.sisfotelbrebes.utility.PrefId;
import com.fauzi.sisfotelbrebes.utility.PrefUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pengajuan extends AppCompatActivity {

    ImageView imKembali;
    Spinner operator;
    EditText nomor,siteid,sitename,tinggi,alamat,latling;
    EditText dibangun,pemilik,batasut,batassel,batasbar,batastim,lamasewa;
    TextView lat,ling;
    Button btn_Next;

    Calendar myCalendar = Calendar.getInstance();
    ArrayAdapter<String> SpinnerAdapter;

    List<String> list;

    private ApiInterface apiInterface;

    String id_User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajuan);


        User user = PrefUtil.getUser(this, PrefUtil.USER_SESSION);
        id_User = user.getData().getId();

        nomor = findViewById(R.id.nomor);
        siteid = findViewById(R.id.siteid);
        sitename = findViewById(R.id.sitename);
        tinggi = findViewById(R.id.tinggi);
        alamat = findViewById(R.id.alamat);
        dibangun = findViewById(R.id.dibangun);
        pemilik = findViewById(R.id.pemilik);
        batasut = findViewById(R.id.batasutara);
        batassel = findViewById(R.id.batasselatan);
        batasbar = findViewById(R.id.batasbarat);
        batastim = findViewById(R.id.batastimur);
        lamasewa = findViewById(R.id.lamasewa);
        latling = findViewById(R.id.latling);
        lat = findViewById(R.id.lat);
        ling = findViewById(R.id.ling);
        btn_Next = findViewById(R.id.btn_next);

        operator = findViewById(R.id.provider);
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
        operator.setAdapter(SpinnerAdapter);

//        latling.setFocusableInTouchMode(false);
//        latling.setFocusable(false);
//        latling.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//             Intent intent  = new Intent(Pengajuan.this, Maps.class);
//             startActivity(intent);
//            }
//        });

        dibangun.setFocusableInTouchMode(false);
        dibangun.setFocusable(false);
        dibangun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Pengajuan.this, date , myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        imKembali = findViewById(R.id.kembali);
        imKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pengajuan.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanjut();
            }
        });

    }

    //PICKTANGGAL//
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setLahir();
        }
    };

    private void setLahir(){
        String myFormat = "dd MMMM yyyy";
        SimpleDateFormat sdf =new SimpleDateFormat(myFormat, Locale.US);

        dibangun.setText(sdf.format(myCalendar.getTime()));
    }

    private void lanjut() {
        nomor.setError(null);
        siteid.setError(null);
        sitename.setError(null);
        tinggi.setError(null);
        alamat.setError(null);
        dibangun.setError(null);
        pemilik.setError(null);
        batasut.setError(null);
        batassel.setError(null);
        batasbar.setError(null);
        batastim.setError(null);
        lamasewa.setError(null);
        lat.setError(null);
        ling.setError(null);

        View fokus = null;
        boolean cancel = false;

        String cekno = nomor.getText().toString();
        String ceksiteid = siteid.getText().toString();
        String ceksitename = sitename.getText().toString();
        String cektinggi = tinggi.getText().toString();
        String cekalamat= alamat.getText().toString();
        String cekth = dibangun.getText().toString();
        String cekpemilik = pemilik.getText().toString();
        String cekbatasu = batasut.getText().toString();
        String cekbatass = batassel.getText().toString();
        String cekbatasb = batasbar.getText().toString();
        String cekbatast = batastim.getText().toString();
        String ceklama = lamasewa.getText().toString();
        String ceklat = lat.getText().toString();
        String cekling = ling.getText().toString();


        if (TextUtils.isEmpty(cekno)){
            nomor.setError("This field is required");
            fokus = nomor;
            cancel = true;
        }
        if (TextUtils.isEmpty(ceksiteid)){
            siteid.setError("This field is required");
            fokus = siteid;
            cancel = true;
        }
        if (TextUtils.isEmpty(ceksitename)){
            sitename.setError("This field is required");
            fokus = sitename;
            cancel = true;
        }
        if (TextUtils.isEmpty(cektinggi)){
            tinggi.setError("This field is required");
            fokus = tinggi;
            cancel = true;
        }
        if (TextUtils.isEmpty(cekalamat)){
            alamat.setError("This field is required");
            fokus = alamat;
            cancel = true;
        }
        if (TextUtils.isEmpty(cekth)){
            dibangun.setError("This field is required");
            fokus = dibangun;
            cancel = true;
        }
        if (TextUtils.isEmpty(cekpemilik)){
            pemilik.setError("This field is required");
            fokus = pemilik;
            cancel = true;
        }
        if (TextUtils.isEmpty(cekbatasu)){
            batasut.setError("This field is required");
            fokus = batasut;
            cancel = true;
        }
        if (TextUtils.isEmpty(cekbatass)){
            batassel.setError("This field is required");
            fokus = batassel;
            cancel = true;
        }
        if (TextUtils.isEmpty(cekbatasb)){
            batasbar.setError("This field is required");
            fokus = batasbar;
            cancel = true;
        }
        if (TextUtils.isEmpty(cekbatast)){
            batastim.setError("This field is required");
            fokus = batastim;
            cancel = true;
        }
        if (TextUtils.isEmpty(ceklama)){
            lamasewa.setError("This field is required");
            fokus = lamasewa;
            cancel = true;
        }
        if (TextUtils.isEmpty(ceklat)){
            lat.setError("This field is required");
            fokus = lat;
            cancel = true;
        }
        if (TextUtils.isEmpty(cekling)){
            ling.setError("This field is required");
            fokus = ling;
            cancel = true;
        }

        if (cancel) fokus.requestFocus();
        else   postData("insert");
    }

    private void postData(String key) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        String cekno = nomor.getText().toString();
        String ceksiteid = siteid.getText().toString();
        String ceksitename = sitename.getText().toString();
        String cektinggi = tinggi.getText().toString();
        String cekalamat= alamat.getText().toString();
        String cekth = dibangun.getText().toString();
        String cekpemilik = pemilik.getText().toString();
        String cekbatasu = batasut.getText().toString();
        String cekbatass = batassel.getText().toString();
        String cekbatasb = batasbar.getText().toString();
        String cekbatast = batastim.getText().toString();
        String ceklama = lamasewa.getText().toString();
        String ceklat = lat.getText().toString();
        String cekling = ling.getText().toString();
        String cekpro = String.valueOf(operator.getSelectedItem());


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<PengajuanModel> call = apiInterface.Pengajuan(key, id_User, cekno, cekpro, ceksiteid, ceksitename,cektinggi, cekalamat,cekth,
                                    cekpemilik,cekbatasu,cekbatass,cekbatasb,cekbatast,ceklama,ceklat,cekling);

        call.enqueue(new Callback<PengajuanModel>() {
            @Override
            public void onResponse(Call<PengajuanModel> call, Response<PengajuanModel> response) {
                progressDialog.dismiss();

                Log.i(Pengajuan.class.getSimpleName(), response.toString());

                String message = response.body().getMessage();
                String valu = response.body().getValue();

                PengajuanModel data = (PengajuanModel) response.body();

                if (valu.equals("1")){
                    PrefId.putID(Pengajuan.this, PrefId.USER_SESSION, data);
                    Intent intent = new Intent(Pengajuan.this, PengajuanBerkas.class);
                    intent.putExtra("id", data.getId());
                    startActivity(intent);
                } else {
                    Toast.makeText(Pengajuan.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PengajuanModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Pengajuan.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private boolean isSessionDaftar() {
        return PrefId.getID(this, PrefId.USER_SESSION) != null;
    }

}

