package com.mafitness.hackiiitd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.mafitness.hackiiitd.Infracture.data;

public class MarketActivity extends AppCompatActivity {


    private EditText etIndustryName, etPhoneNum, etUdyogAdharNum, etEmail, etDetails, etLocationCity;
    private Button btOrderPlace;
    private String industryName, phoneNum, udyogAdNum, username, cluster, description, email, locationcity;
    private data currentIndDetail;

    FirebaseDatabase firebaseDatabase;
    StorageReference storageReference;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        firebaseAuth = FirebaseAuth.getInstance();
        etIndustryName = (EditText) findViewById(R.id.et_MA_industryName);
        etPhoneNum = (EditText) findViewById(R.id.et_MA_PhoneNum);
        etUdyogAdharNum = (EditText) findViewById(R.id.et_MA_UdyogAdharNum);
        etDetails = (EditText) findViewById(R.id.et_MA_details);
        etLocationCity = (EditText) findViewById(R.id.et_MA_city);
        etEmail = (EditText) findViewById(R.id.et_MA_email);


    }
}
