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



public class ScanCodeFound extends AppCompatActivity {
    Button updateCount;
    ConnectionClass connectionClass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_code_found);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        connectionClass = new ConnectionClass();

        //assignCode = findViewById(R.id.assign_btn);
        updateCount = findViewById(R.id.update_btn);



        updateCount.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent i = new Intent(ScanCodeFound.this, updateProductCount.class);
                startActivity(i);
                finish();
            }
        });
    }
}
