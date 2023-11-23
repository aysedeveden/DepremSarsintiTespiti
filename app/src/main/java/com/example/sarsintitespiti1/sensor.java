package com.example.sarsintitespiti1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class sensor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}