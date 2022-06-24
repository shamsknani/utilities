package com.example.utilities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class AllActivity extends AppCompatActivity {

    FirebaseServices fbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("HealthyHearts");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        fbs = FirebaseServices.getInstance();

    }

    public void gotowater(View view) {
        Intent i = new Intent(this, WaterIntake.class);
        startActivity(i);
    }

    public void gotoSteps(View view) {
        Intent i = new Intent(this, Steps.class);
        startActivity(i);
    }

    public void gotoHeartrate(View view) {
        Intent i = new Intent(this, HeartRate.class);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //case R.id.miSearch:
            // User chose the "Settings" item, show the app settings UI...
            //return true;

            case R.id.misignin:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                gotoLogin();
                return true;

            case R.id.misignout:
                fbs.getAuth().signOut();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

    }

    private void gotoLogin() {

    }

}