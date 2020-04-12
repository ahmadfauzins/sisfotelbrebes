package com.fauzi.sisfotelbrebes.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fauzi.sisfotelbrebes.MainActivity;
import com.fauzi.sisfotelbrebes.R;
import com.fauzi.sisfotelbrebes.adapter.AdapterStatus;
import com.fauzi.sisfotelbrebes.api.ApiClient;
import com.fauzi.sisfotelbrebes.api.ApiInterface;
import com.fauzi.sisfotelbrebes.model.PengajuanModel;
import com.fauzi.sisfotelbrebes.model.User;
import com.fauzi.sisfotelbrebes.utility.PrefUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cell extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterStatus adapterStatus;
    private List<PengajuanModel> mhsList;
    AdapterStatus.RecyclerViewClickListener listener;

    ImageView imKembali;
    ApiInterface apiInterface;
    TextView tvPt,tvPtAlamat;
    String id_User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell);
        tvPt = findViewById(R.id.pt);
        tvPtAlamat = findViewById(R.id.pt_alamat);

        User user = PrefUtil.getUser(this, PrefUtil.USER_SESSION);
        tvPt.setText(getResources().getString(R.string.ptsub, user.getData().getNama_pt()));
        tvPtAlamat.setText(getResources().getString(R.string.alamatpt, user.getData().getAlamat_pt()));
        id_User = user.getData().getId();

        imKembali = findViewById(R.id.kembali);
        imKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cell.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        recyclerView = findViewById(R.id.rv_buah);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listener = new AdapterStatus.RecyclerViewClickListener(){
            @Override
            public void onRowClick(View view, final int position) {

                Intent intent = new Intent(Cell.this, CellDetail.class);
                intent.putExtra("id", mhsList.get(position).getId());
                intent.putExtra("siteid", mhsList.get(position).getSite_id());
                intent.putExtra("sitename", mhsList.get(position).getSite_name());
                intent.putExtra("operator", mhsList.get(position).getOperator());
                intent.putExtra("alamat", mhsList.get(position).getAlamat());
                intent.putExtra("status", mhsList.get(position).getIs_approved());
                intent.putExtra("tinggi", mhsList.get(position).getTinggi());
                intent.putExtra("tahun", mhsList.get(position).getBerdiri());
                intent.putExtra("lama", mhsList.get(position).getLama_sewa());
                intent.putExtra("pemilik", mhsList.get(position).getPemilik_tanah());
                intent.putExtra("lat", mhsList.get(position).getLat());
                intent.putExtra("ling", mhsList.get(position).getLing());
                startActivity(intent);
                finish();
            }

            @Override
            public void onLoveClick(View view, int position) {

            }
        };



    }
//    public void getStatusPengajuan() {
//
//        String user_id = "2";
//
//        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//
//        Call<List<PengajuanModel>> call = apiInterface.getStatusPengajuan(user_id);
//
//        call.enqueue(new Callback<List<PengajuanModel>>() {
//            @Override
//            public void onResponse(Call<List<PengajuanModel>> call, Response<List<PengajuanModel>> response) {
////                progressBar.setVisibility(View.GONE);
//                mhsList = response.body();
//                Log.i(Status.class.getSimpleName(), response.body().toString());
//                adapterStatus = new AdapterStatus(mhsList, Status.this);
//                recyclerView.setAdapter(adapterStatus);
//                adapterStatus.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List<PengajuanModel>> call, Throwable t) {
//                Toast.makeText(Status.this, "rp :" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
        private void uploadFile() {

            //Memanggil Api Interface nya dan Api Client nya (BASE URL nya)
            apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            //masukan parameternya
            Call<List<PengajuanModel>> call = apiInterface.getCellular(id_User);
            //masukan dalam enqueue (antrian)
            call.enqueue(new Callback<List<PengajuanModel>>() {
                @Override
                public void onResponse(Call<List<PengajuanModel>> call, Response<List<PengajuanModel>> response) {

                        mhsList = response.body();
                        Log.i(Cell.class.getSimpleName(), response.body().toString());
                        adapterStatus = new AdapterStatus(mhsList, Cell.this,listener);
                        recyclerView.setAdapter(adapterStatus);
                        adapterStatus.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<List<PengajuanModel>> call, Throwable t) {
                    Toast.makeText(Cell.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();

                }
            });

    }

    @Override
    protected void onResume() {
        super.onResume();
        uploadFile();
    }
}
