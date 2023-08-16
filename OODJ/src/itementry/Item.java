/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itementry;

import supplierentry.Supplier;
import utility.*;
import java.io.*;
import java.util.List;
/**
 *
 * @author pc
 */
public class Item implements FileFormattable, FileReadable<Item>{
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

    @Override
    public String formatForFile() {
        return itemCode + "," + itemName + "," + itemQuantity + "," + price + "," + supplierID;
    }

    @Override
    public List<Item> readFromFile(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Item parseLine(String line) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
