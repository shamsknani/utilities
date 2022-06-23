package com.example.utilities;

import static com.example.utilities.Utilities.isNumeric;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Scanner;
import java.util.regex.Pattern;

public class WaterIntake extends AppCompatActivity {
    EditText editTextTextPersonName2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_intake);
        EditText editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
    }

    public void checkwater(View view) {

        String wrd = editTextTextPersonName2.getText().toString();

        if (wrd.isEmpty() ||!isNumeric(wrd) ) {
            Toast.makeText(getApplicationContext(), "please enter a number", Toast.LENGTH_SHORT).show();
            return; }
        else {
            int total = Integer.parseInt(wrd);
            String msg = "";
            if (total < 2000) {
                msg = "YOU DIDN'T DRINK ENOUGH WATER TODAY";
            } else {
                msg = "YOU DRANK ENOUGH WATER, GOOD JOB!";
            }

            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }
    }
