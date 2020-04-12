package com.fauzi.sisfotelbrebes.api;

import com.fauzi.sisfotelbrebes.model.PengajuanModel;
import com.fauzi.sisfotelbrebes.model.Pengguna;
import com.fauzi.sisfotelbrebes.model.User;
import com.fauzi.sisfotelbrebes.uploadBerkas.UploadBerkas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("register.php")
    Call<Pengguna>Register(
            @Field("key") String key,
            @Field("username") String username,
            @Field("nama") String nama,
            @Field("identitas") String identitas,
            @Field("alamat") String alamat,
            @Field("email") String email,
            @Field("no_hp") String no_hp,
            @Field("provider") String provider,
            @Field("nama_pt") String namapem,
            @Field("alamat_pt") String alamatpem,
            @Field("no_telp") String no_telp,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<Pengguna>Login(
            @Field("email") String email,
            @Field("password") String password);


    @FormUrlEncoded
    @POST("pengajuan.php")
    Call<PengajuanModel>Pengajuan(
            @Field("key") String key,
            @Field("user_id") String user_id,
            @Field("no_surat") String no_surat,
            @Field("operator") String username,
            @Field("site_id") String nama,
            @Field("site_name") String identitas,
            @Field("tinggi") String email,
            @Field("alamat") String alamat,
            @Field("berdiri") String berdiri,
            @Field("pemilik_tanah") String pemilik_tanah,
            @Field("batas_u") String batas_u,
            @Field("batas_s") String batas_s,
            @Field("batas_b") String batas_b,
            @Field("batas_t") String batas_t,
            @Field("lama_sewa") String lama_sewa,
            @Field("lat") String lat,
            @Field("ling") String ling);

    @FormUrlEncoded
    @POST("get_user.php")
    Call<List<Pengguna>> getUser(
            @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("update_user.php")
    Call<Pengguna>updateUser(
            @Field("key")String key,
            @Field("id")int id,
            @Field("username")String username,
            @Field("nama")String nama,
            @Field("identitas")String identitas,
            @Field("alamat")String alamat,
            @Field("email")String email,
            @Field("no_hp")String no_hp,
            @Field("namapem")String namapem,
            @Field("alamatpem")String alamatpem,
            @Field("no_telp")String no_telp);

    @FormUrlEncoded
    @POST("get_cell.php")
    Call<List<PengajuanModel>> getCellular(
            @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("get_status.php")
    Call<List<PengajuanModel>> getStatusPengajuan(
            @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("uploadberkas.php")
    Call<UploadBerkas> UploadData(
            @Field("key") String key,
            @Field("id") String id,
            @Field("kode") String kode,
            @Field("foto") String foto);

    @FormUrlEncoded
    @POST("uploadberkasall.php")
    Call<UploadBerkas> UploadDataAll(
            @Field("key") String key,
            @Field("kode") String kode,
            @Field("foto") String foto);


}


