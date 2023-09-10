/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private List<Supplier> itemSupplierList;
    public static final String filePath = "resources/data/item.txt";
    private IdGenerator idGenerator = new IdGenerator(filePath);
    private boolean dataAvailable;
    
    public Item(){
        itemCode = idGenerator.generateID("I");
        dataAvailable = true;
    }
    
    public Item(String itemCode, String itemName, int quantityLeft, float price, List<Supplier> itemSupplierList, boolean dataAvailable){
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantity = quantityLeft;
        this.price = price;
        this.itemSupplierList = itemSupplierList;
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
    
    public List<Supplier> getSupplierList(){
        return itemSupplierList;
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
    
    public void addSupplier(Supplier supplier){
        if(itemSupplierList == null){
            itemSupplierList = new ArrayList<>();
        }
        itemSupplierList.add(supplier);
    }

    //Data Formatting for Writing
    @Override
    public String formatForFile() {
        StringBuilder formattedData = new StringBuilder();
        formattedData.append(itemCode).append(",").append(itemName)
                .append(",").append(quantity)
                .append(",").append(price)
                .append(",[");
                
        boolean isFirstItem = true;
        
        for(Supplier s : itemSupplierList){
            // Append a comma before each item (except the first one)
            if (!isFirstItem) {
                formattedData.append(":");
            } else {
                isFirstItem = false;
            }
            formattedData.append(s.getCode());
        }
        formattedData.append("]").append(",");
        formattedData.append(dataAvailable);
        return formattedData.toString();
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
            
            String suppliersStr = parts[4];
            suppliersStr = suppliersStr.substring(1, suppliersStr.length() - 1);
            String[] supplierQuantityPairs = suppliersStr.split("\\:");
            
            List<Supplier> itemSupplierList = new ArrayList<>();
            List<Supplier> supplierList = FileOperations.readObjectsFromFile(Supplier.filePath, new Supplier());
            for(String s : supplierQuantityPairs){
                Supplier supplier = null;
                try {
                    supplier = (Supplier) FileOperations.findDataByCode(s, supplierList);
                } catch (Exception ex) {
                    Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                itemSupplierList.add(supplier);
            }
            
            boolean dataAvailable = Boolean.parseBoolean(parts[5]);

            return new Item(itemCode, itemName, quantity, price, itemSupplierList, dataAvailable);
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
