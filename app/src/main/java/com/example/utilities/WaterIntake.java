package com.example.utilities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Scanner;

public class WaterIntake extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_intake);
    }
}
    /*
        public static void main(String [] args) throws Exception {
            Scanner sc = new Scanner(System.in);

            System.out.print("Amount of water your bottle can hold: ");
            // entering amount of water bottle can hold
            double litre = sc.nextDouble();
            System.out.print("How many bottles have you drunk today? : ");
            // amount of bottles for today
            double bottles = sc.nextDouble();
            // calculating water drunk
            double total = (bottles * litre);

            // result
            if (bottles == 1) {
                System.out.print("You've only drunk one bottle today! :(" + "\n");
            }
            else {
                System.out.println("Today, you have drunk " + bottles + " bottles. Which equals to " + total + " liters of water.");
            }

            // advice
            if (total < 4) {
                System.out.println("You did not drink enough water, drink at least 4 liters to stay healthy!");
            }

            else if (total >= 4) {
                System.out.println("Good job! You've drunk enough water today! :)");}

        }}

     */