package com.example.pgc.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "reviews", foreignKeys = @ForeignKey(entity = Store.class, parentColumns = {"id"}, childColumns = {"storeId"}, onDelete = ForeignKey.CASCADE), indices = {@Index(value = {"storeId", "autor", "komentarz"}, unique = true)})
public class Review {
    @PrimaryKey(autoGenerate = true)
    int id;
    String storeId;

    String autor;
    String komentarz;
    float gwiazdki;

    public Review(String storeId, String komentarz, float gwiazdki, String autor) {
        this.storeId = storeId;
        this.komentarz = komentarz;
        this.gwiazdki = gwiazdki;
        this.autor = autor;
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

    public String getKomentarz() {
        return komentarz;
    }

    public void setKomentarz(String komentarz) {
        this.komentarz = komentarz;
    }

    public float getGwiazdki() {
        return gwiazdki;
    }

    public void setGwiazdki(float gwiazdki) {
        this.gwiazdki = gwiazdki;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
