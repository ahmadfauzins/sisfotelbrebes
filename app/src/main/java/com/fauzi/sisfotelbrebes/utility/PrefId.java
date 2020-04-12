package com.fauzi.sisfotelbrebes.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fauzi.sisfotelbrebes.model.PengajuanModel;
import com.google.gson.Gson;


public class PrefId {

    public static final String USER_SESSION = "id";

    public static SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void putID(Context context, String key, PengajuanModel data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        putString(context, key, json);
    }
    public static void updateID(Context context, String key, PengajuanModel data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        updateString(context, key, json);
    }

    public static PengajuanModel getID(Context context, String key) {
        Gson gson = new Gson();
        String json = getString(context, key);
        PengajuanModel data = gson.fromJson(json, PengajuanModel.class);
        return data;
    }

    public static void putString(Context context, String key, String value) {
        getSharedPreference(context).edit().putString(key, value).apply();
    }

    public static void updateString(Context context, String key, String value) {
        getSharedPreference(context).edit().putString(key, value).apply();
    }
    public static String getString(Context context, String key) {
        return getSharedPreference(context).getString(key, null);
    }

    public static void clear(Context context , String key, String value) {
        getSharedPreference(context).edit().putString(key, value).apply();
    }

}

