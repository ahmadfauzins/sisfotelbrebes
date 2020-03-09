package com.fauzi.sisfotelbrebes.api;

import com.fauzi.sisfotelbrebes.model.Pengguna;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


import retrofit2.http.Query;

public interface ApiInterface {

    @POST("get_mhs.php")
    Call<List<Pengguna>> getMahasiswa();

    @FormUrlEncoded
    @POST("get_mhs_id.php")
    Call<List<Pengguna>> getMahasiswaId(
            @Field("nim") String nim);

    @POST("get_nilai_id.php")
    Call<List<Pengguna>> getNilaiId(
            @Query("id") String id
    );

    @FormUrlEncoded
    @POST("add_pet.php")
    Call<Pengguna>insertMahasiswa(
            @Field("key") String key,
            @Field("nama") String nama,
            @Field("nim") String nim,
            @Field("tempat_lahir") String tempat_lahir,
            @Field("tanggal_lahir") String tanggal_lahir,
            @Field("alamat") String alamat,
            @Field("nama_ibu") String nama_ibu,
            @Field("nik") String nik,
            @Field("foto") String foto);

    @FormUrlEncoded
    @POST("register.php")
    Call<Pengguna>Register(
            @Field("key") String key,
            @Field("nama") String nama,
            @Field("nim") String nim,
            @Field("tempat_lahir") String tempat_lahir,
            @Field("tanggal_lahir") String tanggal_lahir,
            @Field("alamat") String alamat,
            @Field("nama_ibu") String nama_ibu,
            @Field("nik") String nik,
            @Field("foto") String foto,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("update_pet.php")
    Call<Pengguna>updateMahasiswa(
            @Field("key") String key,
            @Field("id") int id,
            @Field("nama") String nama,
            @Field("nim") String nim,
            @Field("tempat_lahir") String tempat_lahir,
            @Field("tanggal_lahir") String tanggal_lahir,
            @Field("alamat") String alamat,
            @Field("nama_ibu") String nama_ibu,
            @Field("nik") String nik,
            @Field("foto") String foto);

    @FormUrlEncoded
    @POST("login_pet.php")
    Call<Pengguna>loginMahasiswa(
            @Field("email") String email,
            @Field("password") String password);

}

