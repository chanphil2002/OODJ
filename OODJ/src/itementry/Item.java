/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itementry;

import supplierentry.Supplier;
import java.io.*;
/**
 *
 * @author pc
 */
public class Item {
    private String itemCode;
    private String itemName;
    private int itemQuantity;
    private float price;
    private Supplier supplierID;
    
    public Item(){    
    }
    
    public Item(String itemCode, String itemName, int itemQuantity, float price, Supplier supplierID){
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.price = price;
        this.supplierID = supplierID;
    }
    
    //Getter Methods
    public String getItemCode(){
        return itemCode;
    }
    
    public String getItemName(){
        return itemName;
    }
    
    public int getItemQuantity(){
        return itemQuantity;
    }
    
    public float getPrice(){
        return price;
    }
    
    public Supplier getSupplierID(){
        return supplierID;
    }
    
    //Setter Methods
    public void setItemCode(String itemCode){
        this.itemCode = itemCode;
    }
    
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    
    public void setItemQuantity(int itemQuantity){
        this.itemQuantity = itemQuantity;
    }
    
    public void setPrice(float price){
        this.price = price;
    }
    
    public void setSupplierID(Supplier supplierID){
        this.supplierID = supplierID;
    }
}
