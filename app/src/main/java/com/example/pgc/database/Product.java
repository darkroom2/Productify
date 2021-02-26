package com.example.pgc.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "products", foreignKeys = @ForeignKey(entity = Store.class, parentColumns = {"id"}, childColumns = {"storeId"}, onDelete = ForeignKey.CASCADE), indices = {@Index(value = {"storeId", "nazwa", "producent"}, unique = true)})
public class Product {
    @PrimaryKey(autoGenerate = true)
    int id;
    String storeId;

    String nazwa;
    String producent;
    int ilosc_na_stanie;
    String info_dodatkowe;

    public Product(String storeId, String nazwa, String producent, int ilosc_na_stanie, String info_dodatkowe) {
        this.storeId = storeId;
        this.nazwa = nazwa;
        this.producent = producent;
        this.ilosc_na_stanie = ilosc_na_stanie;
        this.info_dodatkowe = info_dodatkowe;
    }

    @Ignore
    public Product(int id, String storeId, String nazwa, String producent, int ilosc_na_stanie, String info_dodatkowe) {
        this.id = id;
        this.storeId = storeId;
        this.nazwa = nazwa;
        this.producent = producent;
        this.ilosc_na_stanie = ilosc_na_stanie;
        this.info_dodatkowe = info_dodatkowe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public int getIlosc_na_stanie() {
        return ilosc_na_stanie;
    }

    public void setIlosc_na_stanie(int ilosc_na_stanie) {
        this.ilosc_na_stanie = ilosc_na_stanie;
    }

    public String getInfo_dodatkowe() {
        return info_dodatkowe;
    }

    public void setInfo_dodatkowe(String info_dodatkowe) {
        this.info_dodatkowe = info_dodatkowe;
    }
}
