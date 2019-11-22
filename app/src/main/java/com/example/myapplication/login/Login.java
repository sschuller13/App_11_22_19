package com.example.myapplication.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.ConnectionClass;
import com.example.myapplication.R;
import com.example.myapplication.Refrence;
import com.example.myapplication.adminpanel;
import com.example.myapplication.manage_inventory;
import com.example.myapplication.ownerpanel;
import com.example.myapplication.userpanel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.jar.Attributes;

public class Login extends AppCompatActivity {

    private EditText username, password1;
    private Button login_btn;
    ConnectionClass connectionClass;
    private ProgressBar pbbar;
    private String UserType, userfirstname;
    private SharedPreferences sharedPreferences;
    private static final String myprefrence = "data";
    private static final String myprefrencname = "name";
    private static final String myprefrenctype = "type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        connectionClass = new ConnectionClass();
        sharedPreferences = getSharedPreferences(myprefrence, Context.MODE_PRIVATE);

        pbbar = (ProgressBar) findViewById(R.id.pbbar);

        username = findViewById(R.id.login_username_edit);
        password1 = findViewById(R.id.login_password_edit);
        login_btn = findViewById(R.id.login_login_btn);
        pbbar.setVisibility(View.GONE);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoLogin doLogin = new DoLogin(); // this is the Asynctask
                doLogin.execute("");

            }
        });

    }

    public class DoLogin extends AsyncTask<String, String, String> {
        String z = "";
        Boolean isSuccess = false;


        String userid = username.getText().toString().replace(".","%");
        String password = password1.getText().toString();


        @Override
        protected void onPreExecute() {
            pbbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r) {
            pbbar.setVisibility(View.GONE);
            Toast.makeText(Login.this, r, Toast.LENGTH_SHORT).show();

            if (isSuccess) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(myprefrencname,userfirstname);
                editor.putString(myprefrenctype, UserType);
                editor.commit();

                if(UserType.equals("Owner")){

                    Intent i = new Intent(Login.this, ownerpanel.class);
                    startActivity(i);
                    finish();
                }
                else if(UserType.equals("Admin")){

                    Intent i = new Intent(Login.this, adminpanel.class);
                    startActivity(i);
                    finish();
                }
                else if(UserType.equals("User")) {

                    Intent i = new Intent(Login.this, userpanel.class);
                    startActivity(i);
                    finish();
                }
                else {
                    z = "Cant find Users";
                }

            }

        }

        @Override
        protected String doInBackground(String... params) {
            if (userid.trim().equals("") || password.trim().equals(""))
                z = "Please enter User Id and Password";
            else {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {
//                        String query = "select * from " + Refrence.ServerDatabaseUser + "where Email ='" + userid + "' and password ='" + password + "'";
                        String query = "select * from "+ Refrence.ServerDatabaseUser + " where Users.Email= '" + userid + "' and Users.Password= '" + password + "'";

                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);


                        Log.e("query",query);

                        if (rs.next()) {

                            z = "Login successfull";
                            isSuccess = true;
                            String query2 = "select * from Users where Users.Email = '"  + userid + "'";
                            Statement stmt2 = con.createStatement();
                            ResultSet rs2 = stmt2.executeQuery(query2);
                            if(rs2.next())
                            {
                                UserType =  rs2.getString(7);
                                userfirstname = rs2.getString(2);

                                Log.e("UserType", UserType);
                            }
                        } else {
                            z = "Invalid Credentials";
                            isSuccess = false;
                        }

                    }
                } catch (Exception ex) {
                    isSuccess = false;
                    z = "Exceptions";
                    Log.e("exception",ex.toString());
                }
            }
            return z;
        }


    }
}