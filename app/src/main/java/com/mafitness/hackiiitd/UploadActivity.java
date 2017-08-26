package com.mafitness.hackiiitd;

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mafitness.hackiiitd.Infracture.data;
import com.squareup.picasso.Picasso;

public class UploadActivity extends AppCompatActivity {

    EditText etIndustryName, etPhoneNum, etUdyogAdharNum, etCluster, etDescription, etEmail, etLocationCity;
    ImageView ivIndImage;
    Button btUploadDetails;
    String industryName, phoneNum, udyogAdNum, username, cluster, description, email, locationcity;
    data currentIndDetail;

    private ProgressDialog progressDialog;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private StorageReference storageReference;

    public static final int PICK_POSTER = 101;

    Uri downloaduri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        progressDialog = new ProgressDialog(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Uploadeddata");
        firebaseAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        etIndustryName = (EditText) findViewById(R.id.et_UA_industryName);
        etPhoneNum = (EditText) findViewById(R.id.et_UA_PhoneNum);
        etUdyogAdharNum = (EditText) findViewById(R.id.et_UA_UdyogAdharNum);
        etCluster = (EditText) findViewById(R.id.et_UA_ClusterName);
        etEmail = (EditText) findViewById((R.id.et_UA_email));
        etDescription = (EditText) findViewById(R.id.et_UA_Description);
        etLocationCity = (EditText) findViewById(R.id.et_UA_city);
        btUploadDetails = (Button) findViewById(R.id.bt_UA_UploadDetails);
        ivIndImage = (ImageView) findViewById(R.id.iv_indimage);

        ivIndImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i,"Select Logo Using"),PICK_POSTER);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && data != null && data.getData() != null){
            if(requestCode == PICK_POSTER){

                Uri uri = data.getData();
                Picasso.with(getApplicationContext()).load(uri).into((ImageView) findViewById(R.id.iv_indimage));

                progressDialog.setMessage("Uploading....");
                progressDialog.show();

                StorageReference filepath = storageReference.child("EventPosters").child(uri.getLastPathSegment());

                filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();

                        downloaduri = taskSnapshot.getDownloadUrl();

                        btUploadDetails.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                data thisdata = new data(firebaseAuth.getCurrentUser().getDisplayName(),etIndustryName.getText().toString(),etPhoneNum.getText().toString(),etUdyogAdharNum.getText().toString(),etDescription.getText().toString(),etCluster.getText().toString(),downloaduri.toString(),firebaseAuth.getCurrentUser().getEmail(),etLocationCity.getText().toString());
                                databaseReference.push().setValue(thisdata);

                                Toast.makeText(UploadActivity.this, "Posting Your Data", Toast.LENGTH_SHORT).show();

                                etIndustryName.setText("");
                                etCluster.setText("");
                                etDescription.setText("");
                                etEmail.setText("");
                                etLocationCity.setText("");
                                etUdyogAdharNum.setText("");
                                etPhoneNum.setText("");
                            }
                        });

                    }
                });

            }
        }
    }
}
