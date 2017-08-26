package com.mafitness.hackiiitd;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mafitness.hackiiitd.Infracture.data;
import com.mafitness.hackiiitd.Infracture.marketdata;

public class MarketActivity extends AppCompatActivity {


    private EditText etIndustryName, etPhoneNum, etUdyogAdharNum, etEmail, etDetails, etLocationCity;
    private Button btOrderPlace;
    private String industryName, phoneNum, udyogAdNum, username, cluster, description, email, locationcity;
    private data currentIndDetail;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("orderdetails");
        storageReference = FirebaseStorage.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();
        etIndustryName = (EditText) findViewById(R.id.et_MA_industryName);
        etPhoneNum = (EditText) findViewById(R.id.et_MA_PhoneNum);
        etUdyogAdharNum = (EditText) findViewById(R.id.et_MA_UdyogAdharNum);
        etDetails = (EditText) findViewById(R.id.et_MA_details);
        etLocationCity = (EditText) findViewById(R.id.et_MA_city);
        etEmail = (EditText) findViewById(R.id.et_MA_email);
        btOrderPlace = findViewById(R.id.bt_MA_OrderPlace);


        btOrderPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                marketdata industryData = new marketdata(
                        etIndustryName.getText().toString(),
                        etPhoneNum.getText().toString(),
                        etUdyogAdharNum.getText().toString(),
                        etEmail.getText().toString(),
                        etLocationCity.getText().toString(),
                        etDetails.getText().toString()
                );
                databaseReference.push().setValue(industryData);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}
