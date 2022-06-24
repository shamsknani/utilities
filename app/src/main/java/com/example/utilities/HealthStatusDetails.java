package com.example.utilities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class HealthStatusDetails extends AppCompatActivity {

    private TextView tvName, tvBirthdate, tvHeight, tvWeight, tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_status_details);

    connectComponents();
    Intent i = this.getIntent();
    HealthStatus healthstatus = (HealthStatus) i.getSerializableExtra("healthstatus");

        tvName.setText(healthstatus.getName());
        tvBirthdate.setText(healthstatus.getBirthdate());
        tvHeight.setText(healthstatus.getHeight());
        tvWeight.setText(healthstatus.getWeight());
        tvDate.setText(healthstatus.getDate());
}
    private void connectComponents() {
        tvName = findViewById(R.id.tvName);
        tvBirthdate = findViewById(R.id.tvBirthdate);
        tvHeight = findViewById(R.id.tvHeight);
        tvWeight = findViewById(R.id.tvWeight);
        tvDate = findViewById(R.id.tvDate);
    }

}