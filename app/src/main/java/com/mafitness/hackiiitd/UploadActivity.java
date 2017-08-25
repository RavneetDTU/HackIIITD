package com.mafitness.hackiiitd;

import android.app.Notification;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mafitness.hackiiitd.Infracture.data;
import com.squareup.picasso.Picasso;

public class UploadActivity extends AppCompatActivity {

    EditText etIndustryName, etPhoneNum, etUdyogAdharNum;
    ImageView ivIndImage;
    Button btUploadDetails;
    String industryName, phoneNum, udyogAdNum, username;
    data currentIndDetail;

    FirebaseDatabase firebaseDatabase;
    StorageReference storageReference;

    Uri downloadURL;

    int PICK_IMAGE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        username = getIntent().getStringExtra("username");

        etIndustryName = (EditText) findViewById(R.id.et_UA_industryName);
        etPhoneNum = (EditText) findViewById(R.id.et_UA_PhoneNum);
        etUdyogAdharNum = (EditText) findViewById(R.id.et_UA_UdyogAdharNum);
        btUploadDetails = (Button) findViewById(R.id.bt_UA_UploadDetails);
        ivIndImage = (ImageView) findViewById(R.id.iv_indimage);

        industryName = etIndustryName.getText().toString();
        phoneNum = etPhoneNum.getText().toString();
        udyogAdNum = etUdyogAdharNum.getText().toString();

        ivIndImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select industry image using:"), PICK_IMAGE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && data != null && data.getData() != null){
            if(requestCode == PICK_IMAGE) {
                Uri uri = data.getData();
                Picasso.with(getApplicationContext()).load(uri).into((ImageView)findViewById(R.id.iv_indimage));

                StorageReference filepath = storageReference.child("IndustryImage").child(uri.getLastPathSegment());
                filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override

                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //noinspection VisibleForTests
                        downloadURL = taskSnapshot.getDownloadUrl();

                        btUploadDetails.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(!anyFieldEmpty()){
                                    currentIndDetail = new data(
                                            username,
                                            industryName,
                                            phoneNum,
                                            udyogAdNum
                                    );
                                }
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

            }
        }

    }

    private boolean anyFieldEmpty() {
        boolean empty = false;

        if(industryName.isEmpty()){
            empty = true;
            etIndustryName.setError("This field cannot be empty!");
        }
        if(phoneNum.isEmpty()){
            empty = true;
            etPhoneNum.setError("This field cannot be empty!");
        }
        if(udyogAdNum.isEmpty()){
            empty = true;
            etUdyogAdharNum.setError("This field cannot be empty!");
        }

        return empty;
    }

}
