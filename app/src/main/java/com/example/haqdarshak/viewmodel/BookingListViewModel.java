package com.example.haqdarshak.viewmodel;

import android.app.Application;


import com.example.haqdarshak.repository.BookingRepository;
import com.example.haqdarshak.roomdatabase.Booking;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class BookingListViewModel extends AndroidViewModel {
    private LiveData<List<Booking>> mAllBookings;
    BookingRepository mbookingsRepository;

    public BookingListViewModel(@NonNull Application application) {
        super(application);
        mbookingsRepository = new BookingRepository(application);
        mAllBookings = mbookingsRepository.getAllNotes();


    }
    public LiveData<List<Booking>> getAllNotes() {
        return mAllBookings;
    }

    public void addBookings(Booking booking) {
        mbookingsRepository.addStudents(booking);
    }

    public void deleteBookings() {
        mbookingsRepository.deleteBookings();
    }


    public void deleteselectedBookings( Booking booking) {
        mbookingsRepository.deleteselectedbooking(booking);
    }

    public void updatebooking(Booking booking, String string) {
        mbookingsRepository.updatebooking(booking,string);
    }
}
