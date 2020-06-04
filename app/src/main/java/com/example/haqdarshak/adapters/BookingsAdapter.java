package com.example.haqdarshak.adapters;


import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.haqdarshak.DeleteEventListener;
import com.example.haqdarshak.R;
import com.example.haqdarshak.UpdateEventListner;

import com.example.haqdarshak.databinding.BookingsItemBinding;
import com.example.haqdarshak.roomdatabase.Booking;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class BookingsAdapter extends RecyclerView.Adapter <BookingsAdapter.BookingViewHolder>  {

    private ArrayList<Booking> bookings;
     private Context context;
     private  OnUpdateItemClick onUpdateItemClick;
    private OnDeleteItemClick onDeleteItemClick;

    public BookingsAdapter(ArrayList<Booking> bookings, Context context) {
        this.bookings = bookings;
        this.context = context;
        this.onDeleteItemClick = (OnDeleteItemClick) context;
        this.onUpdateItemClick = (OnUpdateItemClick) context;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         BookingsItemBinding bookingsItemBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.bookings_item, viewGroup, false);
        return new BookingViewHolder(bookingsItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder bookingViewHolder, int i) {
        Booking booking = bookings.get(i);
        bookingViewHolder.bookingsItemBinding.setBooking(booking);

    }

    @Override
    public int getItemCount() {
        if (bookings != null) {
            return bookings.size();
        } else {
            return 0;
        }
    }

    public void setBookingList(ArrayList<Booking> bookings) {
        this.bookings = bookings;
        notifyDataSetChanged();
    }




    class BookingViewHolder extends RecyclerView.ViewHolder  implements DeleteEventListener, UpdateEventListner {

       private BookingsItemBinding bookingsItemBinding;

       public BookingViewHolder(@NonNull BookingsItemBinding bookingsItemBinding) {
           super(bookingsItemBinding.getRoot());

           this.bookingsItemBinding = bookingsItemBinding;
           this.bookingsItemBinding.setItemDeleteClickListener(this);
           this.bookingsItemBinding.setItemUpdateClickListener(this);
       }


        @Override
        public void bookDetail(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Are you sure you want to delete booking?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Booking booking = bookings.get(getAdapterPosition());
                    onDeleteItemClick.onDeleteClick(booking);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            AlertDialog ad = builder.create();
            ad.show();
        }


        @Override
        public void updateDetail(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Update Status");
// add a list
            String[] statusarr = {"Open", "Close", "Converted to application", "Save Booking"};

            builder.setItems(statusarr, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:

                            Booking booking = bookings.get(getAdapterPosition());
                            onUpdateItemClick.onUpdateClick(booking,"Open");
                            break;
                        case 1:
                            Booking booking1 = bookings.get(getAdapterPosition());
                            onUpdateItemClick.onUpdateClick(booking1,"Close");
                            break;
                        case 2:
                            Booking booking2 = bookings.get(getAdapterPosition());
                            onUpdateItemClick.onUpdateClick(booking2,"Converted to application");
                            break;
                        case 3:
                            Booking booking3 = bookings.get(getAdapterPosition());
                            onUpdateItemClick.onUpdateClick(booking3,"Save Booking");
                            break;

                    }
                }
            });
// create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public interface OnDeleteItemClick {
        void onDeleteClick(Booking booking);
    }

    public interface OnUpdateItemClick{
        void onUpdateClick(Booking booking, String string);
    }
}