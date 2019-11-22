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
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AssignCode extends AppCompatActivity {
    public Boolean isSuccess;
    public EditText inputSKU;
    ConnectionClass connectionClass;
    Button assign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_code);

        connectionClass = new ConnectionClass();

        inputSKU = findViewById(R.id.theSKU);

        assign = findViewById(R.id.Assign_Code_btn);

        assign.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String blah = "";
                String str_SKU = inputSKU.getText().toString();




                try{

                    //managesysteminventory scannedCode = new managesysteminventory();
                    // String inputCode = scannedCode.theQRCode;
                    //String inputCode = scannedCode.QRCode;
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        blah = "Error in connection with SQL server";
                    } else {
                        managesysteminventory scannedCode = new managesysteminventory();
                        // String inputCode = scannedCode.theQRCode;
                        String inputCode = scannedCode.QRCode;
                        scannedCode.QRCode = "NULL";

                        //Toast.makeText(updateProductCount.this, "After Query",  Toast.LENGTH_LONG).show();

                        //String updateQuery = "update "  + Refrence.ServerDatabaseInv + "(itemQuant) set (ScanCode) = updated_Product_Count where ScanCode= '" + inputCode + "')";
                        //String updateQuery = "update "  + Refrence.ServerDatabaseInv + "set (itemQusnt) VALUES('"+updated_Product_Count+ "')";
                        //UPDATE ORDERTABLE SET QUANTITY = (INSERT VALUE OF YOUR EDIT TEXT) WHERE NAME =   'Order2

                        Intent i = new Intent(AssignCode.this, managesysteminventory.class);
                        startActivity(i);
                        finish();
                       // Toast.makeText(updateProductCount.this, "Before Query", Toast.LENGTH_LONG).show();
                        //String updateQuery = "INSERT INTO" + Refrence.ServerDatabaseInv + "(SKU, ItemName) VALUES ('" + inputCode + "' + '" + inputCode + "')";
                        //String query = "select * from "+ Refrence.ServerDatabaseInv + " where ScanCode= '" + inputCode+ "'";
                        String query = "update "  + Refrence.ServerDatabaseInv + " set ScanCode= '" + inputCode + "'" + " where SKU= '" + str_SKU + "'";

                        //String query = "INSERT INTO" + Refrence.ServerDatabaseInv + "(SKU, ItemName) VALUES ('" + inputCode + "' + '" + inputCode + "')";
                        //String query = "select * from "+ Refrence.ServerDatabaseInv + " where ScanCode= '" + inputCode + "'";


                        //Toast.makeText(updateProductCount.this, "After Query", Toast.LENGTH_LONG).show();
                        Statement stmtOne = con.createStatement();
                        ResultSet rs = stmtOne.executeQuery(query);

                        //boolean rs = stmtOne.execute(updateQuery);
                        //Toast.makeText(updateProductCount.this, "After Result Set", Toast.LENGTH_LONG).show();
                        Log.e("query", query);
                        //Toast.makeText(updateProductCount.this, "After log.e", Toast.LENGTH_LONG).show();

                        /*
                        Intent i = new Intent(updateProductCount.this, managesysteminventory.class);
                        startActivity(i);
                        finish();

                         */

                        isSuccess = true;
                        //scannedCode.QRCode = "NULL";

                    }


                } catch (SQLException exc){
                    blah = "SQL Exception";
                    Log.e("exception", exc.toString());
                }

                catch (Exception ex) {
                    isSuccess = false;
                    blah = "Exceptions";
                    Log.e("exception", ex.toString());
                }


            }

        });
    }
}
