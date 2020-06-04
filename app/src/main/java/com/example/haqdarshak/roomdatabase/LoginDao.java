package com.example.haqdarshak.roomdatabase;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface LoginDao {

    @Insert
    void insertDetails(LoginTable data);

    @Query("select * from LoginDetails")
    LiveData<List<LoginTable>> getDetails();

    @Query("delete from LoginDetails")
    void deleteAllData();

}
