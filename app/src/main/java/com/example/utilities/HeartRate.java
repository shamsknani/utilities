package com.example.utilities;

import static com.example.utilities.Utilities.isNumeric;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HeartRate extends AppCompatActivity {

    EditText editTextNumber3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);
        editTextNumber3 = findViewById(R.id.editTextNumber3);

    }

    public void msg(View view)
    {
        String str = editTextNumber3.getText().toString();

        if (str.isEmpty() ||!isNumeric(str) ) {

            Toast.makeText(getApplicationContext(),"please enter a number", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
        int Hentered = Integer.parseInt(str);
        String Hrate = "";
        if (Hentered < 101 && Hentered > 59) {
            Hrate = "YOUR HEARTRATE IS STABLE";
        }
        else {
            Hrate = "YOUR HEARTRATE IS NOT STABLE";
        }
        Toast.makeText(getApplicationContext(), Hrate, Toast.LENGTH_SHORT).show();
    }
}}