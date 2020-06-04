package com.example.haqdarshak.roomdatabase;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Booking {

    public Booking(String bookingId , String name, String village, String district, String state,String schemeId, String schemeName, String status) {
        this.bookingId = bookingId;
        this.name = name;
        this.village = village;
        this.district = district;
        this.state = state;
        this.schemeId = schemeId;
        this.schemeName = schemeName;
        this.status = status;

    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "bookingId")
    private String bookingId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "village")
    private String village;

    @ColumnInfo(name = "district")
    private String district;

    @ColumnInfo(name = "state")
    private String state;

    @ColumnInfo(name = "schemeId")
    private String schemeId;

    @ColumnInfo(name = "schemeName")
    private String schemeName;


    @ColumnInfo(name = "status")
    private String status;


    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
