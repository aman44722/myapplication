package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class login_form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().setTitle("LOGIN");
    }
    public void btn_videoItem(View view){
        startActivity(new Intent(getApplicationContext(),videoPlay.class));
    }
    public void btn_register(View view){
        startActivity(new Intent(getApplicationContext(),signup_Form.class));
    }
}
