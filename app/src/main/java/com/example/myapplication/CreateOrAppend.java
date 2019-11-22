package com.example.myapplication;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;


import android.view.View;
import android.widget.Button;


import android.widget.EditText;

//import managesysteminventory.java;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateOrAppend extends AppCompatActivity {

    Button createNewProduct, addToExistingItem;
    ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_append);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        connectionClass = new ConnectionClass();

        //assignCode = findViewById(R.id.assign_btn);
        createNewProduct = findViewById(R.id.create_btn);
        addToExistingItem = findViewById(R.id.append_btn);



        createNewProduct.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent i = new Intent(CreateOrAppend.this, add_inventory.class);
                startActivity(i);
                finish();
            }
        });

        addToExistingItem.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateOrAppend.this, AssignCode.class);
                startActivity(i);
                finish();

            }
        });
    }
}
