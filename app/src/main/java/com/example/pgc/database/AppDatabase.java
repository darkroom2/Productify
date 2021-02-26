package com.example.pgc.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Store.class, Review.class, Product.class, Order.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabaseInstance(context);
        }
        return INSTANCE;
    }

    private static AppDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "pgc").build();
    }

    public abstract UserDAO userDAO();

    public abstract ProductDAO productDAO();

    public abstract ReviewDAO reviewDAO();

    public abstract StoreDAO storeDAO();

    public abstract OrderDAO orderDAO();
}
