package com.example.myapplication;

public class InvListItems {
    public String itemName, itemROP;
    public int itemQTY;
    public InvListItems(String itemName, int itemQTY, String itemROP){
        this.itemName = itemName;
        this.itemQTY = itemQTY;
        this.itemROP = itemROP;
    }
    public String getItemName(){
        return itemName;
    }
    public int getItemQTY(){
        return itemQTY;
    }
    public String getItemQuant(){

        String itmQTY = Integer.toString(itemQTY);
        return itmQTY;
    }
    public String getItemROP(){
        return itemROP;
    }
}

