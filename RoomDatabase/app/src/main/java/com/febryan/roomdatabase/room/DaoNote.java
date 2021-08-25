package com.febryan.roomdatabase.room;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoNote {

    @Insert
    void  insertAllData(Note model);

    // Select All Data
    @Query("SELECT * FROM note ORDER BY `key` DESC")
    List<Note> getAllData();

    // Delete Data
    @Query("DELETE from note WHERE `key`= :id")
    void deleteData(int id);

    // Update Data
    @Query("UPDATE note SET title= :title, `desc`= :desc WHERE `key`= :key")
    void updateData(String title, String desc, int key);

}
