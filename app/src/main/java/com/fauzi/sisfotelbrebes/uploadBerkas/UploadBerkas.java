package com.fauzi.sisfotelbrebes.uploadBerkas;

import com.google.gson.annotations.SerializedName;

public class UploadBerkas {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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
    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }
    public String getId_pengajuan() {
        return id_pengajuan;
    }

    public void setId_pengajuan(String id_pengajuan) {
        this.id_pengajuan = id_pengajuan;
    }
    @SerializedName("id")
    private String id;
    @SerializedName("kode")
    private String kode;
    @SerializedName("id_pengajuan")
    private String id_pengajuan;
    @SerializedName("foto")
    private String foto;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String message;
}
