package com.example.haqdarshak.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;


import com.example.haqdarshak.MainListner;
import com.example.haqdarshak.R;

import com.example.haqdarshak.databinding.ActivityLoginBinding;
import com.example.haqdarshak.roomdatabase.LoginTable;
import com.example.haqdarshak.viewmodel.LoginViewModel;


public class LoginActivity extends AppCompatActivity implements MainListner {

    private LoginViewModel loginViewModel;

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);

        binding.setClickListener((LoginActivity) this);


        loginViewModel = ViewModelProviders.of(LoginActivity.this).get(LoginViewModel.class);



    }

    @Override
    public void onClick(View view) {

        String strEmail = binding.txtEmailAddress.getText().toString().trim();
        String strPassword = binding.txtPassword.getText().toString().trim();

        LoginTable data = new LoginTable();

        if (TextUtils.isEmpty(strEmail)) {
            binding.txtEmailAddress.setError("Please Enter Your E-mail Address");
        }
        else if (TextUtils.isEmpty(strPassword)) {
            binding.txtPassword.setError("Please Enter Your Password");
        }
        else {

            data.setEmail(strEmail);
            data.setPassword(strPassword);
            loginViewModel.insert(data);

        }

    }
}