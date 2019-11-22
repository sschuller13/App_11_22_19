package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class List1Activity extends AppCompatActivity {
    private ConnectionClass connectionClass;
    private ArrayList<InvListItems> itemArrayList;
    private MyAppAdapter myAppAdapter;
    private ListView listView;
    private boolean success = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list1);

        listView = (ListView) findViewById(R.id.listView);
        connectionClass = new ConnectionClass();
        itemArrayList = new ArrayList<InvListItems>();

        SyncData orderData = new SyncData();
        orderData.execute("");

        //try {
        // String query = "Select * from " + Refrence.ServerDatabaseInv + "where SKU = D12345";


        //String query = "INSERT INTO" + Refrence.ServerDatabaseInv
        //}
        //catch (Exception e){

        //}
        /*String [] foods = {"Here", "We", "Go"};
        ListAdapter InvAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, foods);
        ListView invListView = (ListView)findViewById(R.id.invListView);
        invListView.setAdapter(InvAdapter);

        invListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String food = String.valueOf(adapterView.getItemAtPosition(i));
                        Toast.makeText(InventoryList.this, food, Toast.LENGTH_LONG).show();

                    }
                }
        );
    */
    }
    private class SyncData extends AsyncTask<String, String, String>
    {
        String msg = "Internet/DB_Credentials/Windows_Firewall_TurnOn Error, See Android Monitor in the bottom for Details";
        ProgressDialog progress;

        @Override
        protected String doInBackground(String... strings) {
            try{
                Connection conn = connectionClass.CONN();
                if(conn == null){
                    success = false;
                }
                else{
                    String query = "SELECT ItemName, ItemQuant, ReorderPoint FROM INVENTORY";
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if(rs != null){
                        while(rs.next()){
                            try{
                                itemArrayList.add(new InvListItems(rs.getString("ItemName"),rs.getInt("ItemQuant"), rs.getString("ReorderPoint")));
                            }
                            catch (Exception ex){
                                ex.printStackTrace();
                            }
                        }
                        msg = "Found";
                        success = true;
                    }else{
                        msg = "No Data found!";
                        success = false;
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
                Writer writer = new StringWriter();
                e.printStackTrace(new PrintWriter(writer));
                msg = writer.toString();
            }
            return msg;
        }

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(List1Activity.this, "Synchonizing", "Listview Loading! Please Wait...", true);

        }
        protected void onPostExecute(String msg){
            progress.dismiss();
            Toast.makeText(List1Activity.this, msg + "", Toast.LENGTH_SHORT).show();
            if (success == false){
                Toast.makeText(List1Activity.this, msg + "False", Toast.LENGTH_LONG).show();

            }
            else{
                try{
                    myAppAdapter = new MyAppAdapter(itemArrayList, List1Activity.this);
                    listView.setChoiceMode(listView.CHOICE_MODE_SINGLE);
                    listView.setAdapter(myAppAdapter);
                    Toast.makeText(List1Activity.this, msg + "1", Toast.LENGTH_LONG).show();

                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }

    }
    public class MyAppAdapter extends BaseAdapter{
        public class ViewHolder{
            TextView itemName, itemQTY, itemROP;
        }
        public List<InvListItems> itemList;
        public Context context;
        ArrayList<InvListItems> arrayList;

        private MyAppAdapter(List<InvListItems> itemList2, Context context){
            this.itemList = itemList2;
            this.context = context;
            arrayList = new ArrayList<InvListItems>();
            arrayList.addAll(itemList);

        }
        public int getCount(){
            return itemList.size();
        }
        public Object getItem(int position)
        {
            return position;
        }
        public long getItemId(int position){
            return position;
        }
        public View getView(final int position, View convertView, ViewGroup parent){
            View rowView = convertView;
            ViewHolder viewHolder = null;
            if(rowView == null){
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.content_list1, parent, false);//****CHECK TO MAKE SURE THIS RIGHT
                viewHolder = new ViewHolder();
                viewHolder.itemName = (TextView) rowView.findViewById(R.id.itemName);
                viewHolder.itemQTY = (TextView) rowView.findViewById(R.id.itemQTY);
                viewHolder.itemROP = (TextView) rowView.findViewById(R.id.itemROP);

                rowView.setTag(viewHolder);
            }
            else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String itmQTY;
            viewHolder.itemName.setText(itemList.get(position).getItemName());
            viewHolder.itemQTY.setText(itemList.get(position).getItemQuant());
            viewHolder.itemROP.setText(itemList.get(position).getItemROP());

            return rowView;

        }
    }
}


