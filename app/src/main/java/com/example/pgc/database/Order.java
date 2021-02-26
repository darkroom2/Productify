package com.example.pgc.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders",
        foreignKeys = {@ForeignKey(entity = User.class, parentColumns = {"id"}, childColumns = {"userId"}, onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Product.class, parentColumns = {"id"}, childColumns = {"productId"}, onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Store.class, parentColumns = {"id"}, childColumns = {"storeId"}, onDelete = ForeignKey.CASCADE)},
        indices = {@Index(value = "userId"),
                @Index(value = "productId"),
                @Index(value = "storeId")})
public class Order {
    @PrimaryKey(autoGenerate = true)
    int id;
    String userId;
    String productId;
    String storeId;

    String imie;
    String nazwisko;
    String data_odbioru;
    int ilosc;
    String status;

    public Order(String userId, String productId, String storeId, String imie, String nazwisko, String data_odbioru, int ilosc, String status) {
        this.userId = userId;
        this.productId = productId;
        this.storeId = storeId;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.data_odbioru = data_odbioru;
        this.ilosc = ilosc;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getData_odbioru() {
        return data_odbioru;
    }

    public void setData_odbioru(String data_odbioru) {
        this.data_odbioru = data_odbioru;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
