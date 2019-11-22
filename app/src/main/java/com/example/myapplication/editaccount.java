package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class editaccount extends AppCompatActivity {
    private TextView editaccount;
    private ImageView image;
    private Button edit_info,notification, sign_out;
    ConnectionClass connectionClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editaccount);
         editaccount = findViewById(R.id.txt_edit_account);
         image = findViewById(R.id.imageView_editaccount);
         edit_info = findViewById(R.id.btn_edit_info);
         notification = findViewById(R.id.btn_edit_notification);
         sign_out = findViewById(R.id.btn_edit_signout);

    }
}
