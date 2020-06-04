package com.example.haqdarshak.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;


import com.example.haqdarshak.roomdatabase.AppDatabase;
import com.example.haqdarshak.roomdatabase.Booking;
import com.example.haqdarshak.roomdatabase.BookingsDao;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class BookingRepository {
    //Live Data of List of all bookings
    private LiveData<List<Booking>> mAllBookings;
    //Define booking Dao
    BookingsDao mBookingDao;

    public BookingRepository(@NonNull Application application) {
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        //init booking Dao
        mBookingDao = appDatabase.bookingsDao();
        //get all bookings
        mAllBookings = mBookingDao.getAllBookings();
    }
    //method to get all bookings
    public LiveData<List<Booking>> getAllNotes() {
        return mAllBookings;
    }

    //method to add bookings
    public void addStudents(Booking booking) {
        new AddBooking().execute(booking);
    }




    //Async task to add booking
    public class AddBooking extends AsyncTask<Booking, Void, Void> {
        @Override
        protected Void doInBackground(Booking... booking) {
            mBookingDao.insert(booking);
            return null;
        }
    }

    public void deleteBookings() {
        new DeleteBook().execute();
    }
//Async task to delete booking
    public class DeleteBook extends AsyncTask<Booking, Void, Void> {
        @Override
        protected Void doInBackground(Booking... booking) {

            mBookingDao.delete();

            return null;
        }
    }

    public void deleteselectedbooking(final Booking booking) {
        class DeleteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
             mBookingDao.deleteBooking(booking);
                return null;
            }


        }

        DeleteTask dt = new DeleteTask();
        dt.execute();
    }

    public void updatebooking(final Booking booking, final String string) {
        class UpdateTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                booking.setBookingId(booking.getBookingId());
                booking.setName(booking.getName());
                booking.setVillage(booking.getVillage());
                booking.setDistrict(booking.getDistrict());
                booking.setState(booking.getState());
                booking.setSchemeId(booking.getSchemeId());
                booking.setSchemeName(booking.getSchemeName());
                booking.setStatus(string);

                      mBookingDao.update(booking);
                return null;
            }


        }

        UpdateTask ut = new UpdateTask();
        ut.execute();
    }
}
