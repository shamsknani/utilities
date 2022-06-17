package com.example.utilities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AllActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
    }
    public void gotowater(View view) {
        Intent i = new Intent(this,WaterIntake.class);
        startActivity(i);
    }
    public void gotoSteps(View view) {
        Intent i = new Intent(this,Steps.class);
        startActivity(i);
    }

    public void gotoHeartrate(View view) {  Intent i = new Intent(this,HeartRate.class);
        startActivity(i);
    }

}