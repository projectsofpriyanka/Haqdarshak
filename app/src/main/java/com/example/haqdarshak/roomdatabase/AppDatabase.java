package com.example.haqdarshak.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Booking.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase mInstance;

    //method to get room database
    public static AppDatabase getDatabase(Context context) {

        if (mInstance == null)
            mInstance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "Booking")
                    .fallbackToDestructiveMigration()
                    .build();

        return mInstance;
    }

    //method to remove instance
    public static void closeDatabase() {
        mInstance = null;
    }

    public abstract BookingsDao bookingsDao();
}

