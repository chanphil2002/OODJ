/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.*;
import java.util.List;
import static model.Supplier.*;
/**
 *
 * @author pc
 */
public class Item implements IFileFormattable, IDataParser<Item>, IDataSearchable, IFileStatus{
    private String itemCode;
    private String itemName;
    private int quantity;
    private float price;
    private Supplier supplier;
    private final String filePath = "resources/data/item.txt";
    private IdGenerator idGenerator = new IdGenerator(filePath);
    private boolean dataAvailable;
    List<Supplier> supplierList = FileOperations.readObjectsFromFile("resources/data/supplier.txt", new Supplier());
    
    public Item(){
        itemCode = idGenerator.generateID("I");
        dataAvailable = true;
    }
    
    public Item(String itemCode, String itemName, int quantityLeft, float price, Supplier supplier, boolean dataAvailable){
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantity = quantityLeft;
        this.price = price;
        this.supplier = supplier;
        this.dataAvailable = dataAvailable;
    }
    
    
    //Getter Methods
    @Override
    public String getCode() {
        return itemCode;
    }
   
    
    public String getItemName(){
        return itemName;
    }
    
    public int getItemQuantity(){
        return quantity;
    }
    
    public float getItemPrice(){
        return price;
    }
    
    public String getSupplierCode(){
        return supplier.getCode();
    }
    
    public String getSupplierName(){
        return supplier.getSupplierName();
    }
    
    public String getFilePath(){
        return filePath;
    }
    
    //Setter Methods
    public void setItemCode(String itemCode){
        this.itemCode = itemCode;
    }
    
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    
    public void setItemQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public void setPrice(float price){
        this.price = price;
    }
    
    public void setSupplier(Supplier supplier){
        this.supplier = supplier;
    }

    //Data Formatting for Writing
    @Override
    public String formatForFile() {
        return itemCode + "," + itemName + "," + quantity + "," + price + "," + supplier.getCode() + "," + dataAvailable;
    }
    
    //Data Parsing for Reading
    @Override
    public Item parseData(String line){
        String[] parts = line.split(",");
        if (parts.length == 6) { // Assuming 2 attributes in the data
            String itemCode = parts[0];
            String itemName = parts[1];
            int quantity = Integer.parseInt(parts[2]);
            float price = Float.parseFloat(parts[3]);
            String supplierID = parts[4];
            boolean dataAvailable = Boolean.parseBoolean(parts[5]);

            Supplier supplier = (Supplier) FileOperations.findDataByCode(supplierID, supplierList);

            return new Item(itemCode, itemName, quantity, price, supplier, dataAvailable);
        }
        return null;
    } 

    @Override
    public boolean getDataAvailable() {
        return dataAvailable;
    }

    @Override
    public void setDataAvailable(boolean deleted) {
        this.dataAvailable = deleted;
    }


}
