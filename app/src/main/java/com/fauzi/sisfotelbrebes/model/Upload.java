package com.fauzi.sisfotelbrebes.model;


public class Upload extends BaseResponse {

    PengajuanModel data;

    public PengajuanModel getData() {
        return data;
    }

    public void setData(PengajuanModel data) {
        this.data = data;
    }

    public Upload() {
    }


}
