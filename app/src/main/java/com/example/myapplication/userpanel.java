package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class userpanel extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private static final String myprefrence = "data";
    private static final String myprefrencname = "name";
    private static final String myprefrenctype = "type";
    private String userfirstname, usertype;
    private TextView welcome;
    private Button UserManageSystemInventory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpanel);
        sharedPreferences = getSharedPreferences(myprefrence, Context.MODE_PRIVATE);
        userfirstname = sharedPreferences.getString("name","");
        usertype = sharedPreferences.getString("type","");
        welcome = findViewById(R.id.welcome_userpanel);
        welcome.setText("Welcome  " + userfirstname);


        UserManageSystemInventory = findViewById(R.id.btn_owner_manage_system_inventory);


        UserManageSystemInventory.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                Intent i = new Intent(userpanel.this, managesysteminventory.class);
                startActivity(i);
                //finish();


            }
        });
    }

}
