package com.example.haqdarshak.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.haqdarshak.R;
import com.example.haqdarshak.roomdatabase.Booking;
import com.example.haqdarshak.viewmodel.BookingListViewModel;
import com.example.haqdarshak.adapters.BookingsAdapter;
import com.example.haqdarshak.databinding.ActivityBookingBinding;
import com.example.haqdarshak.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity implements BookingsAdapter.OnDeleteItemClick ,BookingsAdapter.OnUpdateItemClick{
    BookingListViewModel bookingListViewModel;
    private BookingsAdapter bookingsAdapter;
    private ArrayList<Booking> bookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          ActivityBookingBinding activityBookingBinding = DataBindingUtil.setContentView(this, R.layout.activity_booking);


        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "listdetails.json");
        Log.i("data", jsonFileString);
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<Booking>>() {
        }.getType();
        bookings = gson.fromJson(jsonFileString, listUserType);

        // bind RecyclerView
        RecyclerView recyclerView = activityBookingBinding.recyclerviewbookings;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);




        bookingListViewModel = ViewModelProviders.of(this).get(BookingListViewModel.class);
        bookingListViewModel.deleteBookings();
        bookingsAdapter = new BookingsAdapter(bookings, BookingActivity.this);

        recyclerView.setAdapter(bookingsAdapter);


        for (int i = 0; i < bookings.size(); i++) {

            bookingListViewModel.addBookings(new Booking(bookings.get(i).getBookingId(),
                    bookings.get(i).getName(),
                    bookings.get(i).getVillage(),
                    bookings.get(i).getDistrict(),
                    bookings.get(i).getState(),
                    bookings.get(i).getSchemeId(),
                    bookings.get(i).getSchemeName(),
                    bookings.get(i).getStatus()));
        }
        // observe for bookings data changes

        bookingListViewModel.getAllNotes().observe(this, new Observer<List<Booking>>() {
            @Override
            public void onChanged(@Nullable List<Booking> bookings) {
                //add bookings to adapter
                Log.e("bookinglist", String.valueOf(bookings.size()));

                bookingsAdapter.setBookingList((ArrayList<Booking>) bookings);
            }
        });


    }

//delete booking
    @Override
    public void onDeleteClick(Booking booking) {
        bookingListViewModel.deleteselectedBookings(booking);
    }


//update booking
    @Override
    public void onUpdateClick(final Booking booking, final String string) {

                     bookingListViewModel.updatebooking(booking,string);


    }
}