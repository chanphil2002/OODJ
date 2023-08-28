/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itementry;

import supplierentry.Supplier;
import utility.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author pc
 */
public class Item implements IFileFormattable, IDataParser<Item>, IDataSearchable{
    private String itemCode;
    private String itemName;
    private int itemQuantity;
    private float price;
    private Supplier supplierID;
    
    public Item(){    
    }
    
    public Item(String itemCode, String itemName){
        this.itemCode = itemCode;
        this.itemName = itemName;
    }
    
    public Item(String itemCode, String itemName, int itemQuantity){
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
    }
    
    public Item(String itemCode, String itemName, int itemQuantity, float price, Supplier supplierID){
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.price = price;
        this.supplierID = supplierID;
    }
    
    //Getter Methods
    @Override
    public String getCode() {
        return itemCode;
    }
    
    public static Item getItemCode(List<Item> itemList, String itemId) {
        for (Item item : itemList) {
            if (item.getCode().equals(itemId)) {
                return item;
            }
        }
        return null; // Item with specified ID not found
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

    //Data Formatting for Writing
    @Override
    public String formatForFile() {
        return itemCode + "," + itemName /* + "," + itemQuantity + "," + price + "," + supplierID */;
    }
    
    //Data Parsing for Reading
    @Override
    public Item parseData(String line){
        String[] parts = line.split(",");
        if (parts.length == 2) { // Assuming 2 attributes in the data
            String itemCode = parts[0];
            String itemName = parts[1];
//            int itemQuantity = Integer.parseInt(parts[2]);
//            float price = Float.parseFloat(parts[3]);

            return new Item(itemCode, itemName);
        }
        return null;
    } 


}
