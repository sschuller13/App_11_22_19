package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.widget.Toast;
//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class managesysteminventory extends AppCompatActivity {

    ConnectionClass connectionClass;
    public Boolean isSuccess;
    //private static EditText userId, itemId, itemName, itemDescription, unitOfMeasure, SKU;
    private Button addItem, manualInput, list, scan_btn;
    //private String theQRCode;


    //public String theQRCode;
    static String QRCode;

    public String getQRString(){

        //return ScanCodeVal;
        return QRCode;
    }

    public void setQRString(String CopyQRCode ){
        QRCode = CopyQRCode;
        //this.theQRCode = QRCode;
        //ScanCodeVal = QRCode;
    }

    /*
    public String getQRString(){

        return theQRCode;
        //return QRCode;
    }

    public void setQRString(String QRCode ){
        //this.theQRCode = QRCode;
        theQRCode = QRCode;
    }
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managesysteminventory);
        connectionClass = new ConnectionClass();


        final Activity activity = this;

        scan_btn = findViewById(R.id.scan_btn);

        scan_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //String blah = "";
                //Intent Index = new Intent(managesysteminventory.this, add_inventory.class);
                //Intent i = new Intent(activity);
                IntentIntegrator intergrator = new IntentIntegrator(activity);
                intergrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                intergrator.setPrompt("Scan");
                intergrator.setCameraId(0);
                intergrator.setBeepEnabled(false);
                intergrator.setBarcodeImageEnabled(false);
                intergrator.initiateScan();

            }

        });

        //final Activity activity = this;
        addItem = findViewById(R.id.btn_add_inventory_items);


        addItem.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(managesysteminventory.this, add_inventory.class);
                startActivity(i);
                finish();
            }
        });
        list = findViewById(R.id.btn_inventory_list);

        list.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent i = new Intent(managesysteminventory.this, List1Activity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //Toast.makeText(managesysteminventory.this, "WORRRRRRRRRRRRRK",  Toast.LENGTH_LONG).show();

        String blah;
        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }

            else {

                QRCode = result.getContents();
                String theQR = QRCode;


                try {
                    //String blah = "";
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        blah = "Error in connection with SQL server";
                    } else {

                        Statement stmtOne = con.createStatement();
                        //Toast.makeText(managesysteminventory.this, "WORRRRRRRRRRRRRK",  Toast.LENGTH_LONG).show();
                        //Toast.makeText(managesysteminventory.this, theQR,  Toast.LENGTH_LONG).show();

                        String query = "select * from "+ Refrence.ServerDatabaseInv + " where ScanCode= '" + theQR + "'";

                        //Toast.makeText(managesysteminventory.this, "After query",  Toast.LENGTH_LONG).show();

                        ResultSet rs = stmtOne.executeQuery(query);
                        Log.e("query",query);
                        //Toast.makeText(managesysteminventory.this, "After resultset",  Toast.LENGTH_LONG).show();

                        if (rs.next()){

                            Toast.makeText(managesysteminventory.this, "Found a QR Code",  Toast.LENGTH_LONG).show();
                            //Intent i = new Intent(managesysteminventory.this, updateProductCount.class);
                            Intent i = new Intent(managesysteminventory.this, ScanCodeFound.class);
                            startActivity(i);
                            finish();

                        }



                        else {

                            //String query = "INSERT INTO" + Refrence.ServerDatabaseInv + "(SKU, ItemName, itemDescrip, itemQuant, UOM, [Cost/Unit], MinToNoti, ReorderPoint, ScanCode) VALUES ('" + str_itemSKU + "', '" + str_itemName + "', '" + str_itemDesc + "', '" + str_itemQTY + "', '" + str_itemUOM + "', '" + str_CostpUnit + "', '" + str_MinToNot + "', '" + str_ROP + "', '" + inputCode + "')";
                            //String queryhm = "INSERT INTO" + Refrence.ServerDatabaseInv + "(ScanCode) VALUES ('" + theQR + "')";

                            Toast.makeText(managesysteminventory.this, "Did not Find a QR Code",  Toast.LENGTH_LONG).show();


                            isSuccess = true;
                            //Intent i = new Intent(managesysteminventory.this, add_inventory.class);
                            Intent i = new Intent(managesysteminventory.this, CreateOrAppend.class);
                            startActivity(i);
                            finish();

                        }

                    }

                }

                catch (SQLException exc){
                    blah = "SQL Exception";
                    Log.e("exception", exc.toString());
                }

                catch (Exception ex) {
                    isSuccess = false;
                    blah = "Exceptions";
                    Log.e("exception", ex.toString());
                }


            }
        }

        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
        //super.onActivityResult(requestCode, resultCode, data);
    }
}
