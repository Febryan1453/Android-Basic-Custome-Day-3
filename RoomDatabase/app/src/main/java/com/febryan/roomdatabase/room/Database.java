package com.febryan.roomdatabase.room;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Note.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract  DaoNote getDao();
    private static Database instance;

    public static Database getDatabase(final Context context){
        if (instance == null){
            synchronized (Database.class){
                instance = Room.databaseBuilder(context, Database.class, "DATABASE")
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return instance;
    }

}
