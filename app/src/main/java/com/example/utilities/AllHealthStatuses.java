package com.example.utilities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllHealthStatuses extends AppCompatActivity {


    private RecyclerView rvHealthStatusesAll;
    AdapterHealthStatus adapter;
    FirebaseServices fbs;
    ArrayList<HealthStatus> healthStatuses;
    MyCallback myCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_health_statuses);

        fbs = FirebaseServices.getInstance();
        healthStatuses = new ArrayList<HealthStatus>();
        readData();
        myCallback = new MyCallback() {
            @Override
            public void onCallback(List<HealthStatus> healthStatusesList) {
                RecyclerView recyclerView = findViewById(R.id.rvHealthStatusesAll);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new AdapterHealthStatus(getApplicationContext(), healthStatuses);
                recyclerView.setAdapter(adapter);
            }
        };



    }

    private void readData() {
        try {

            fbs.getFirestore().collection("HealthStatuses")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    healthStatuses.add(document.toObject(HealthStatus.class));
                                }

                                myCallback.onCallback(healthStatuses);
                            } else {
                                Log.e("AllHealthStatuses: readData()", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "error reading!" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
