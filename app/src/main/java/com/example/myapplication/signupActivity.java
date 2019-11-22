package com.example.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signupActivity extends AppCompatActivity {

    private EditText firstname, lastname, companyname, companyid, companyemail;
    private Button signup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firstname = findViewById(R.id.signup_first_name);
        lastname = findViewById(R.id.signup_last_name);
        companyname = findViewById(R.id.signup_company_name);
        companyid = findViewById(R.id.signup_company_id);
        companyemail = findViewById(R.id.signup_company_email);
        signup = findViewById(R.id.signup_btn_signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String first = firstname.getText().toString();
                String last = lastname.getText().toString();
                String coname = companyname.getText().toString();
                String coid = companyid.getText().toString();
                String coemail = companyemail.getText().toString();





            }
        });

    }

}
