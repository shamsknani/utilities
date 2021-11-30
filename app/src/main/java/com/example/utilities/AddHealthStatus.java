package com.example.utilities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AddHealthStatus extends AppCompatActivity {
    private String name;
    private String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_health_status);
    }
}