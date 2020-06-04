package com.example.haqdarshak.viewmodel;

import android.app.Application;
import android.content.Intent;


import com.example.haqdarshak.repository.LoginRepository;
import com.example.haqdarshak.roomdatabase.LoginTable;
import com.example.haqdarshak.view.BookingActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository repository;
    private LiveData<List<LoginTable>> getAllData;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        repository = new LoginRepository(application);
        getAllData = repository.getAllData();

    }

    public void insert(LoginTable data) {
        repository.insertData(data);
        Intent intent = new Intent(getApplication(), BookingActivity.class);

        getApplication().startActivity(intent);
    }

    public LiveData<List<LoginTable>> getGetAllData() {
        return getAllData;
    }

}

