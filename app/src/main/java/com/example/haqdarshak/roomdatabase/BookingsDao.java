package com.example.haqdarshak.roomdatabase;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BookingsDao {
    @Query("SELECT * FROM Booking")
    LiveData<List<Booking>> getAllBookings();

    @Insert
    void insert(Booking[] booking);

    @Query("DELETE FROM Booking")
    void delete();

    @Delete
    void deleteBooking(Booking booking);

    @Update
    void update(Booking booking);


}
