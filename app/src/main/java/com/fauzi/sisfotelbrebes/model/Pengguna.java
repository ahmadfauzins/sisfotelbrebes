package com.fauzi.sisfotelbrebes.model;

import com.google.gson.annotations.SerializedName;

public class Pengguna {

    @SerializedName("id")
    private String id;
    @SerializedName("username")
    private String username;
    @SerializedName("nama")
    private String nama;
    @SerializedName("identitas")
    private String identitas;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("email")
    private String email;
    @SerializedName("no_hp")
    private String no_hp;
    @SerializedName("provider")
    private String provider;
    @SerializedName("nama_pt")
    private String nama_pt;
    @SerializedName("alamat_pt")
    private String alamat_pt;
    @SerializedName("no_telp")
    private String no_telp;
    @SerializedName("foto")
    private String foto;
    @SerializedName("password")
    private String password;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIdentitas() {
        return identitas;
    }

    public void setIdentitas(String identitas) {
        this.identitas = identitas;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getNama_pt() {
        return nama_pt;
    }

    public void setNama_pt(String nama_pt) {
        this.nama_pt = nama_pt;
    }

    public String getAlamat_pt() {
        return alamat_pt;
    }

    public void setAlamat_pt(String alamat_pt) {
        this.alamat_pt = alamat_pt;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Pengguna() {
    }
}
