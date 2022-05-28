package com.example.utilities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddHealthStatus extends AppCompatActivity {

    private static final String TAG = "AddHealthStatus";
    private EditText  etweight, etheight, etdate, etbirthdate, etName ;
    private Spinner spCatAddHealthStatus;
    private ImageView ivPhotoAddHealthStatus;
    private FirebaseServices fbs;
    private Uri filePath;
    private StorageReference storageReference;
    private String refAfterSuccessfullUpload = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_health_status);

        getSupportActionBar().hide();
        connectComponents();
    }

    private void connectComponents() {
        etName = findViewById(R.id.etNameAddHealthStatus);
        etheight = findViewById(R.id.etheightAddHealthStatus);
        etweight = findViewById(R.id.etweightAddHealthStatus);
        etbirthdate = findViewById(R.id.etageAddHealthStatus);
        etdate = findViewById(R.id.etdate);


        spCatAddHealthStatus = findViewById(R.id.spCatAddHealthStatus);
        ivPhotoAddHealthStatus = findViewById(R.id.ivPhotoAddHealthStatus);
        fbs = FirebaseServices.getInstance();
        spCatAddHealthStatus.setAdapter(new ArrayAdapter<Hhcategory>(this, android.R.layout.simple_list_item_1, Hhcategory.values()));
        storageReference = fbs.getStorage().getReference();
    }

    public void add(View view) {
        // check if any field is empty
        String weight, height, birthdate, date,name,category, photo;
        name = etName.getText().toString();
        height = etheight.getText().toString();
        weight = etweight.getText().toString();
        birthdate = etbirthdate.getText().toString();
        date = etdate.getText().toString();

        category = spCatAddHealthStatus.getSelectedItem().toString();
        if (ivPhotoAddHealthStatus.getDrawable() == null)
            photo = "no_image";
        else photo = storageReference.toString();

        if (weight.trim().isEmpty() || height.trim().isEmpty() ||  birthdate.trim().isEmpty() || date.trim().isEmpty() || name.trim().isEmpty() ||
                category.trim().isEmpty() || photo.trim().isEmpty())
        {
            Toast.makeText(this, R.string.err_fields_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        HealthStatus HealthStatus = new HealthStatus(Integer.parseInt(weight), Integer.parseInt(height), birthdate, date, name, Hhcategory.valueOf(category));
        fbs.getFirestore().collection("HealthStatuses")
                .add(HealthStatus)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void selectPhoto(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),40);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 40) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    filePath = data.getData();
                    ivPhotoAddHealthStatus.setBackground(null);
                    Picasso.get().load(filePath).into(ivPhotoAddHealthStatus);
                    uploadImage();
                }
            } else if (resultCode == Activity.RESULT_CANCELED)  {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void uploadImage()
    {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            String fileNameStr = filePath.toString().substring(filePath.toString().lastIndexOf("/")+1);
            StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + fileNameStr);

            filePath.toString().substring(filePath.toString().lastIndexOf("/")+1);
            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(AddHealthStatus.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                    refAfterSuccessfullUpload = ref.toString();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(AddHealthStatus.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }
    }

    public void gotowater(View view) {
        Intent i = new Intent(this,HeartRate.class);
        startActivity(i);
    }
    public void gotoSteps(View view) {
        Intent i = new Intent(this,Steps.class);
        startActivity(i);
    }
}