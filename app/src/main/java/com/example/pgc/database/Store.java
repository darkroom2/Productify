package com.example.pgc.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "stores", indices = {@Index(value = {"nazwa", "adres"}, unique = true)})
public class Store {
    @PrimaryKey(autoGenerate = true)
    int id;
    String nazwa;
    String adres;
    float ocena_ogolna;

    public Store(String nazwa, String adres, float ocena_ogolna) {
        this.nazwa = nazwa;
        this.adres = adres;
        this.ocena_ogolna = ocena_ogolna;
    }

    @Ignore
    public Store(int id, String nazwa, String adres, float ocena_ogolna) {
        this.id = id;
        this.nazwa = nazwa;
        this.adres = adres;
        this.ocena_ogolna = ocena_ogolna;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public float getOcena_ogolna() {
        return ocena_ogolna;
    }

    public void setOcena_ogolna(float ocena_ogolna) {
        this.ocena_ogolna = ocena_ogolna;
    }
}

