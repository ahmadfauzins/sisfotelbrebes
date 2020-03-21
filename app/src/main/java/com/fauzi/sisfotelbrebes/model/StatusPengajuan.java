package com.fauzi.sisfotelbrebes.model;

import com.google.gson.annotations.SerializedName;

public class StatusPengajuan {

    @SerializedName("id")
    private String id;
    @SerializedName("status")
    private String status;
    @SerializedName("is_approved")
    private String is_approved;
    @SerializedName("nama")
    private String nama;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIs_approved() {
        return is_approved;
    }

    public void setIs_approved(String is_approved) {
        this.is_approved = is_approved;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
