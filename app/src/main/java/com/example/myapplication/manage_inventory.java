package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.myapplication.login.Login;

public class manage_inventory extends AppCompatActivity {

    Button login;
    Button Confirm_User;
    Button Add_Account;
    private PopupWindow POPUP_WINDOW_SCORE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        login = findViewById(R.id.btn_app_login);
        Confirm_User = findViewById(R.id.btn_app_conifrmuser);
        Add_Account = findViewById(R.id.btn_app_addaccount);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(manage_inventory.this, Login.class);
                startActivity(i);

            }
        });
        Confirm_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(manage_inventory.this, signupActivity.class);
                startActivity(i);
            }
        });
        Add_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  ShowPopup();
                Intent i = new Intent(manage_inventory.this, Main_Signup.class);
                startActivity(i);
            }
        });

    }
        private void ShowPopup()
        {


            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            int height = displayMetrics.heightPixels;

            // Inflate the popup_layout.xml
            LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.popup_addaccount, null);

            // Creating the PopupWindow
            POPUP_WINDOW_SCORE = new PopupWindow(this);
            POPUP_WINDOW_SCORE.setContentView(layout);
            POPUP_WINDOW_SCORE.setWidth((int) (width*.8));
            POPUP_WINDOW_SCORE.setHeight((int) (height*.6));
            POPUP_WINDOW_SCORE.setFocusable(true);

            // prevent clickable background
            POPUP_WINDOW_SCORE.setBackgroundDrawable(null);

            POPUP_WINDOW_SCORE.showAtLocation(layout, Gravity.CENTER, 1, 1);



            // Getting a reference to button one and do something
            Button butOne =  layout.findViewById(R.id.btn_popup_owner);
            butOne.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent i = new Intent(manage_inventory.this, personal_infoActivity.class);
                    startActivity(i);
                    //Do Something

                    //Close Window
                    POPUP_WINDOW_SCORE.dismiss();
                }
            });

            // Getting a reference to button two and do something
            Button butTwo =  layout.findViewById(R.id.btn_popup_user);
            butTwo.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //Do Something

                    //Close Window
                    POPUP_WINDOW_SCORE.dismiss();
                }
            });
        }
    }

