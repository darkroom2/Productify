package com.example.pgc.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDAO {
    @Insert
    void insert(Product product);

    @Query("SELECT * FROM products WHERE nazwa = :name")
    Product findProductByName(String name);

    @Query("SELECT * FROM products WHERE nazwa LIKE '%' || :name || '%' AND storeId = :storeId")
    List<Product> findProductByNameAndStoreId(String name, String storeId);

    @Query("SELECT * FROM products WHERE storeId = :storeId")
    List<Product> findProductsForStore(String storeId);

}
