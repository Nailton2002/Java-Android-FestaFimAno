package com.example.festafimano.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {

    //SharedPreferences é como se fosse um banco de dados
    private SharedPreferences mSharedPreferences;

    //construtor
    public SecurityPreferences(Context mContext) {
        this.mSharedPreferences = mContext.getSharedPreferences("FestaFimAno", Context.MODE_PRIVATE);

    }
    //sera usado para armazenar string ou seja salvar dados
    public  void  storeString(String key, String value) {
        this.mSharedPreferences.edit().putString(key, value).apply();
    }
    //serve recupera os dados
    public String getStoredString(String key) {
        return this.mSharedPreferences.getString(key, "") ;
    }

}
