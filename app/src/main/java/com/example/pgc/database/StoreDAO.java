package com.example.pgc.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StoreDAO {
    @Insert
    void insert(Store store);

    @Query("SELECT * FROM stores WHERE nazwa = :name")
    Store loadStoreByName(String name);

    //           int s_id, String s_nazwa, String s_adres, float s_ocena_ogolna, int p_id, String p_storeId, String p_nazwa, String p_producent, int p_ilosc_na_stanie, String p_info_dodatkowe
    @Query("SELECT stores.id, stores.nazwa, stores.adres, stores.ocena_ogolna, products.id as p_id, products.storeId, products.nazwa as p_nazwa, products.producent, products.ilosc_na_stanie, products.info_dodatkowe FROM stores LEFT OUTER JOIN products ON products.storeId = stores.id WHERE products.nazwa LIKE :productName || '%'")
    List<StoreWithProduct> findStoresByProduct(String productName);
}
