package com.example.myapplication;

import android.content.Intent;
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
import java.util.Scanner;


public class add_inventory extends AppCompatActivity {

    public Boolean isSuccess;
    ConnectionClass connectionClass;

    Button addToList, Cancel, Camera;
    public EditText itemName, itemDescription, itemSKU, itemQTY, itemUOM, itemCostpUnit, itemROP, itemMintoNot, itemRestock, itemMinRestock;
    public int userId;

    private String ScanCodeVal;

    /*
    public String getQRString(){

        return ScanCodeVal;
        //return QRCode;
    }

    public void setQRString(String QRCode ){
        //this.theQRCode = QRCode;
        ScanCodeVal = QRCode;
    }
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        connectionClass = new ConnectionClass();

        itemName = findViewById(R.id.item_Inventory_Name_Edit);
        itemDescription = findViewById(R.id.item_Inventory_Description_Edit);
        itemSKU = findViewById(R.id.item_Inventory_SKU_Edit);
        itemQTY = findViewById(R.id.item_Inventory_ItemQty_Edit);
        itemUOM = findViewById(R.id.item_Inventory_UOM_Edit);
        itemCostpUnit = findViewById(R.id.item_Inventory_CostPerUnit_Edit);
        itemROP = findViewById(R.id.item_Inventory_ROP_Edit);
        itemMintoNot = findViewById(R.id.item_Inventory_MinToNotify_Edit);
        itemRestock = findViewById(R.id.item_Inventory_Restock_Edit);
        itemMinRestock = findViewById(R.id.item_Inventory_MinToRestock_Edit);



        addToList = findViewById(R.id.button_add_commit);
        Cancel = findViewById(R.id.button_cancel_commit3);

        //String blahOne = "";

        addToList.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                String blah = "";

                String str_itemName = itemName.getText().toString();
                String str_itemDesc = itemDescription.getText().toString();
                String str_itemSKU = itemSKU.getText().toString();
                //String str_itemSKU = obj1.getQRCode();
                Float str_itemQTY = Float.parseFloat(itemQTY.getText().toString());
                String str_itemUOM = itemUOM.getText().toString();
                Float str_CostpUnit = Float.parseFloat(itemCostpUnit.getText().toString());
                byte str_Notify = 0;
                Float str_ROP = Float.parseFloat(itemROP.getText().toString());
                Float str_MinToNot = Float.parseFloat(itemMintoNot.getText().toString());
                Float str_itemRestock = Float.parseFloat(itemRestock.getText().toString());
                String str_itemMinRestock = itemMinRestock.getText().toString();
                int userId = 6;

                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        blah = "Error in connection with SQL server";
                    } else {
                        managesysteminventory scannedCode = new managesysteminventory();
                        // String inputCode = scannedCode.theQRCode;
                        String inputCode = scannedCode.QRCode;

                        scannedCode.QRCode = "NULL";


                        //str_itemSKU = scannedCode.QRCode;
                        Intent i = new Intent(add_inventory.this, managesysteminventory.class);
                        startActivity(i);
                        finish();
                        //String query = "INSERT INTO" + Refrence.ServerDatabaseInv + "(ScanCode) VALUES ('"+inputCode+"');";
                        //itemDescription = ""
                        //String query = "select * from " + Refrence.ServerDatabaseInv + "where SKU ='" + str_itemSKU + "' and itemName ='" + str_itemName + "'";
                        //String query = "INSERT INTO" + Refrence.ServerDatabaseInv + "(SKU, ItemName, itemDescrip, itemQuant, UOM, [Cost/Unit], MinToNoti, ReorderPoint, ScanCode) VALUES ('"+str_itemSKU+ "', '"+ str_itemName+ "', '"+str_itemDesc+ "', '"+str_itemQTY+ "', '"+str_itemUOM+"', '"+str_CostpUnit+"', '"+str_MinToNot+"', '"+str_ROP+"','"+inputCode+"')";



                        String query = "INSERT INTO" + Refrence.ServerDatabaseInv + "(SKU, ItemName, itemDescrip, itemQuant, UOM, [Cost/Unit], MinToNoti, ReorderPoint, ScanCode) VALUES ('"+str_itemSKU+ "', '"+ str_itemName+ "', '"+str_itemDesc+ "', '"+str_itemQTY+ "', '"+str_itemUOM+"', '"+str_CostpUnit+"', '"+str_MinToNot+"', '"+str_ROP+"', '"+inputCode+"')";

                        //String query = "INSERT INTO" + Refrence.ServerDatabaseInv + "(UOM) VALUES ('"+str_itemUOM+"');";
                        Statement stmt = con.createStatement();
                        boolean rs = stmt.execute(query);

                        Log.e("query", query);

                        isSuccess = true;

  //                    inputCode = "NULL";
        //              scannedCode.QRCode = "NULL";
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
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(add_inventory.this, managesysteminventory.class);
                startActivity(i);
                finish();
            }
        });

    }

}


