package com.fauzi.sisfotelbrebes.model;

import com.fauzi.sisfotelbrebes.main.Pengajuan;
import com.fauzi.sisfotelbrebes.uploadBerkas.UploadBerkas;
import com.google.gson.annotations.SerializedName;

public class PengajuanModel {


    @SerializedName("id")
    private String id;
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("no_surat")
    private String no_surat;
    @SerializedName("operator")
    private String operator;
    @SerializedName("site_id")
    private String iste_id;
    @SerializedName("site_name")
    private String site_name;
    @SerializedName("tinggi")
    private String tinggi;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("berdiri")
    private String berdiri;
    @SerializedName("pemilik_tanah")
    private String pemilik_tanah;
    @SerializedName("batas_u")
    private String batas_u;
    @SerializedName("batas_s")
    private String batas_s;
    @SerializedName("batas_b")
    private String batas_b;
    @SerializedName("batas_t")
    private String batas_t;
    @SerializedName("lama_sewa")
    private String lama_sewa;
    @SerializedName("lat")
    private String lat;
    @SerializedName("ling")
    private String ling;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String message;

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

    @SerializedName("status")
    private String status;
    @SerializedName("is_approved")
    private String is_approved;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo_surat() {
        return no_surat;
    }

    public void setNo_surat(String no_surat) {
        this.no_surat = no_surat;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getIste_id() {
        return iste_id;
    }

    public void setIste_id(String iste_id) {
        this.iste_id = iste_id;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getTinggi() {
        return tinggi;
    }

    public void setTinggi(String tinggi) {
        this.tinggi = tinggi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getBerdiri() {
        return berdiri;
    }

    public void setBerdiri(String berdiri) {
        this.berdiri = berdiri;
    }

    public String getPemilik_tanah() {
        return pemilik_tanah;
    }

    public void setPemilik_tanah(String pemilik_tanah) {
        this.pemilik_tanah = pemilik_tanah;
    }

    public String getBatas_u() {
        return batas_u;
    }

    public void setBatas_u(String batas_u) {
        this.batas_u = batas_u;
    }

    public String getBatas_s() {
        return batas_s;
    }

    public void setBatas_s(String batas_s) {
        this.batas_s = batas_s;
    }

    public String getBatas_b() {
        return batas_b;
    }

    public void setBatas_b(String batas_b) {
        this.batas_b = batas_b;
    }

    public String getBatas_t() {
        return batas_t;
    }

    public void setBatas_t(String batas_t) {
        this.batas_t = batas_t;
    }

    public String getLama_sewa() {
        return lama_sewa;
    }

    public void setLama_sewa(String lama_sewa) {
        this.lama_sewa = lama_sewa;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLing() {
        return ling;
    }

    public void setLing(String ling) {
        this.ling = ling;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    PengajuanModel data;

    public PengajuanModel getData() {
        return data;
    }

    public void setData(PengajuanModel data) {
        this.data = data;
    }
        public PengajuanModel() {

    }
}
