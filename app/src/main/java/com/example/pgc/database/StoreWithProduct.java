package com.example.pgc.database;

import androidx.room.Ignore;

public class StoreWithProduct {
    int id;
    String nazwa;
    String adres;
    float ocena_ogolna;
    int p_id;
    String storeId;
    String p_nazwa;
    String producent;
    int ilosc_na_stanie;
    String info_dodatkowe;
    @Ignore
    Store s;
    @Ignore
    Product p;

    public StoreWithProduct(int id, String nazwa, String adres, float ocena_ogolna, int p_id, String storeId, String p_nazwa, String producent, int ilosc_na_stanie, String info_dodatkowe) {
        this.id = id;
        this.nazwa = nazwa;
        this.adres = adres;
        this.ocena_ogolna = ocena_ogolna;
        this.p_id = p_id;
        this.storeId = storeId;
        this.p_nazwa = p_nazwa;
        this.producent = producent;
        this.ilosc_na_stanie = ilosc_na_stanie;
        this.info_dodatkowe = info_dodatkowe;
        this.s = new Store(id, nazwa, adres, ocena_ogolna);
        this.p = new Product(p_id, storeId, p_nazwa, producent, ilosc_na_stanie, info_dodatkowe);
    }


    public Store getS() {
        return s;
    }

    public void setS(Store s) {
        this.s = s;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }
}
