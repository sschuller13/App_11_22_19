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
import android.widget.TextView;
import android.widget.Button;

import com.example.myapplication.login.Login;

public class ownerpanel extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private static final String myprefrence = "data";
    private static final String myprefrencname = "name";
    private static final String myprefrenctype = "type";
    private String userfirstname, usertype;
    private TextView welcome;
    private Button manageCompany, manageAdmin, manageUsers, manageSystemInventory, managePhysicalCount, reports;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownerpanel);
        sharedPreferences = getSharedPreferences(myprefrence, Context.MODE_PRIVATE);
        userfirstname = sharedPreferences.getString("name","");
        usertype = sharedPreferences.getString("type","");
        welcome = findViewById(R.id.welcome_owner);
        welcome.setText("Welcome  " + userfirstname);

        manageSystemInventory = findViewById(R.id.btn_owner_manage_system_inventory);


        manageSystemInventory.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                Intent i = new Intent(ownerpanel.this, managesysteminventory.class);
                startActivity(i);
                finish();


                            }
        });

    }

}
