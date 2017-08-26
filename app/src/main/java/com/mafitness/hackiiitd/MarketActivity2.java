package com.mafitness.hackiiitd;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mafitness.hackiiitd.Infracture.data;
import com.mafitness.hackiiitd.Infracture.marketdata;

public class MarketActivity2 extends AppCompatActivity {


//    private EditText etIndustryName, etPhoneNum, etUdyogAdharNum, etEmail, etDetails, etLocationCity;
//    private Button btOrderPlace;
//    private String industryName, phoneNum, udyogAdNum, username, cluster, description, email, locationcity;
//    private data currentIndDetail;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    FirebaseAuth firebaseAuth;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("orderdetails");
        storageReference = FirebaseStorage.getInstance().getReference();

        recyclerView = findViewById(R.id.rvMA);

    }

}
