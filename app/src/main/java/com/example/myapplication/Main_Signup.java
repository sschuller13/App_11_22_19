package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main_Signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText firstname, lastname, email, password;
    private String usertype1;
    private TextView errortext;
    private Button signup_btn;
    private Spinner spinner;
    ConnectionClass connectionClass;
    private boolean isSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__signup);
        connectionClass = new ConnectionClass();
     firstname = findViewById(R.id.main_signup_first_name);
     lastname = findViewById(R.id.main_signup_last_name);
     email = findViewById(R.id.main_signup_email);
     password = findViewById(R.id.main_signup_password);
     signup_btn = findViewById(R.id.main_signup_button_signup);
     errortext = findViewById(R.id.textView_error_main_signup);
     spinner = findViewById(R.id.spinner_main_signup);
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Owner");
        categories.add("Admin");
        categories.add("User");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String z = "";

                String first = firstname.getText().toString();
                String last = lastname.getText().toString();
                String email1 = email.getText().toString().replace(".","%");
                String password2 = password.getText().toString();
                String usertype = usertype1;


                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {
//                        String query = "select * from " + Refrence.ServerDatabaseUser + "where Email ='" + userid + "' and password ='" + password + "'";
                        String query = "INSERT INTO" + Refrence.ServerDatabaseUser + "( FirstName, LastName, Phone, Email, Password , AccessType) VALUES ('" + first +"', '" + last +"', ' ','" + email1 + "', '" +password2 +"','" + usertype+ "');";

                        Statement stmt = con.createStatement();
                        boolean rs = stmt.execute(query);


                        Log.e("query", query);

                            isSuccess = true;
                            Intent i = new Intent(Main_Signup.this, manage_inventory.class);
                            startActivity(i);
                            //finish();



                    }
                } catch (Exception ex) {
                    isSuccess = false;
                    z = "Exceptions";
                    Log.e("exceptioonnnnn", ex.toString());
                }


            }

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        usertype1 = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + usertype1, Toast.LENGTH_LONG).show();

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}


