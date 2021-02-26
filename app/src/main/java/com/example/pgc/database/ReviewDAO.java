package com.example.pgc.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReviewDAO {
    @Insert
    void insert(Review review);

    @Query("SELECT * FROM reviews WHERE autor = :autor")
    Review loadReviewByAuthor(String autor);

    @Query("SELECT * FROM reviews WHERE storeId=:storeId")
    List<Review> findReviewsForStore(int storeId);

}
