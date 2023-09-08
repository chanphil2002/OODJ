/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.*;
import controller.*;
import java.time.LocalDate;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */

public class Sales implements IFileFormattable, IDataParser<Sales>, IDataSearchable, IFileStatus{
    private LocalDate date;
    private String salesCode;
    private Map<Item, Integer> itemsSold;
    private double totalSalesAmount;
    private boolean dataAvailable;
    
    private final String filePath = "resources/data/sales.txt";
    private IdGenerator idGenerator = new IdGenerator(filePath);
    
    public Sales(){
        date = LocalDate.now();
        salesCode = idGenerator.generateID("S");
        this.itemsSold = new HashMap<>();
        dataAvailable = true;
    }
    
    public Sales(String salesCode, Map<Item, Integer> itemsSold, double totalSalesAmount, LocalDate date, boolean dataAvailable){
        this.date = date;
        this.salesCode = salesCode;
        this.itemsSold = itemsSold;
        this.totalSalesAmount = totalSalesAmount;
        this.dataAvailable = dataAvailable;
    }
    
    public LocalDate getDate(){
        return date;
    }
    
    @Override
    public String getCode() {
        return salesCode;
    }
    
    public Map<Item, Integer> getItemsSold(){
        return itemsSold;
    }
    
    public double getTotalSalesAmount(){
        return totalSalesAmount;
    }
    
    public String getFilePath(){
        return filePath;
    }
    
    public void addItem(Item item, int quantity) {
        System.out.println(itemsSold);
        // Check if the item is already in the map
        if (itemsSold.containsKey(item)) {
            // If it is, update the quantity
            int currentQuantity = itemsSold.get(item);
            
            itemsSold.put(item, currentQuantity + quantity);
        } else {
            // If it's not, add a new entry
            itemsSold.put(item, quantity);
        }

        // Update the total sales amount
        totalSalesAmount += item.getItemPrice() * quantity; // You need a method to get the price of the item
    }
    
    public void setTotalSalesAmount(double totalSalesAmount){
        this.totalSalesAmount = totalSalesAmount;
    }
    
    @Override
    public String formatForFile() {
        StringBuilder formattedData = new StringBuilder();
        formattedData.append(salesCode).append(",[");
        
        boolean isFirstItem = true; // Track if it's the first item
        
        for (Map.Entry<Item, Integer> entry : itemsSold.entrySet()) {
            Item item = entry.getKey();
            int quantity = entry.getValue();
            
            // Append a comma before each item (except the first one)
            if (!isFirstItem) {
                formattedData.append(".");
            } else {
                isFirstItem = false;
            }
            // Append item and quantity to the formatted string
            formattedData.append(item.getCode()).append(":").append(quantity);
        }
        
        formattedData.append("]").append(",").append(totalSalesAmount);
        formattedData.append(",").append(date);
        formattedData.append(",").append(dataAvailable);
        return formattedData.toString();
    }

    @Override
    public Sales parseData(String line) {
        String[] parts = line.split(",");
       
        // Ensure that the formatted string has at least three parts: salesCode, items, and totalSales
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid formatted data: " + line);
        }
        
        // Extract salesCode
        String salesCode = parts[0];

        // Extract the items and quantities section (itemsStr)
        String itemsStr = parts[1];

        // Remove the square brackets
        itemsStr = itemsStr.substring(1, itemsStr.length() - 1);
        
        // Split itemsStr into individual item-quantity pairs
        String[] itemQuantityPairs = itemsStr.split("\\.");
        
        Map<Item, Integer> itemsSold = new HashMap<>();
        
        List<Item> itemList = FileOperations.readObjectsFromFile("resources/data/item.txt", new Item());
        for (String pair : itemQuantityPairs) {
            // Split each pair into item name and quantity
            String[] pairParts = pair.split(":");

            // Ensure that the pair has two parts
            if (pairParts.length != 2) {
                throw new IllegalArgumentException("Invalid item-quantity pair: " + pair);
            }
            
            
            String itemCode = pairParts[0];
            int quantity = Integer.parseInt(pairParts[1]);
            Item item = null;
            try {
                item = (Item) FileOperations.findDataByCode(itemCode, itemList);
            } catch (Exception ex) {
                Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            itemsSold.put(item, quantity);

        }
        
        double totalSales = Double.parseDouble(parts[2]);
        LocalDate date = LocalDate.parse(parts[3]);
        boolean dataAvailable = Boolean.parseBoolean(parts[4]);
        return new Sales(salesCode, itemsSold, totalSales, date, dataAvailable); 
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