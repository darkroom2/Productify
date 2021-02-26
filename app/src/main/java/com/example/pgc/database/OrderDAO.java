package com.example.pgc.database;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface OrderDAO {
    @Insert
    void insert(Order order);
}