package com.mafitness.hackiiitd;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mafitness.hackiiitd.Infracture.data;
import com.mafitness.hackiiitd.Infracture.marketdata;
import com.mafitness.hackiiitd.adapter.adapter;
import com.mafitness.hackiiitd.adapter.adapter2;

import java.util.ArrayList;

public class MarketActivity2 extends AppCompatActivity {


//    private EditText etIndustryName, etPhoneNum, etUdyogAdharNum, etEmail, etDetails, etLocationCity;
//    private Button btOrderPlace;
//    private String industryName, phoneNum, udyogAdNum, username, cluster, description, email, locationcity;
//    private data currentIndDetail;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    FirebaseAuth firebaseAuth;
    private adapter2 adapter;
    private ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            ArrayList<marketdata> marketlist = new ArrayList<>();
            for(DataSnapshot data : dataSnapshot.getChildren()){
                marketdata thisdata = new marketdata(data.child("industryname").getValue().toString(),
                        data.child("phonenum").getValue().toString(),
                        data.child("adharnum").getValue().toString(),
                        data.child("email").getValue().toString(),
                        data.child("city").getValue().toString(),
                        data.child("details").getValue().toString());
                marketlist.add(thisdata);
            }
            adapter.updatelist(marketlist);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabmarket);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MarketActivity2.this,MarketActivity.class));
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("orderdetails");
        storageReference = FirebaseStorage.getInstance().getReference();

        recyclerView = findViewById(R.id.rvMA);
        adapter = new adapter2(this, new ArrayList<marketdata>());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(valueEventListener);
    }

}
