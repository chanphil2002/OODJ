/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.FileOperations;
import controller.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import model.Item;
/**
 *
 * @author pc
 */

public class PurchaseRequisition implements IFileFormattable, IDataParser<PurchaseRequisition>, IDataSearchable, IFileStatus{
    private LocalDate PRDate;
    private String PRID;
    private Map<Item, Integer> itemsRequested;
    private PurchaseStatus status;
    private boolean dataAvailable;
    
    private final String filePath = "resources/data/purchaserequisition.txt";
    private IdGenerator idGenerator = new IdGenerator(filePath);
    
    public PurchaseRequisition(){
        PRDate = LocalDate.now();
        PRID = idGenerator.generateID("P");
        this.itemsRequested = new HashMap<>();
        status = PurchaseStatus.PENDING;
        dataAvailable = true;
    }
    
    public PurchaseRequisition(String PRID, Map<Item,Integer> itemsRequested, PurchaseStatus status, LocalDate date, boolean dataAvailable){
        this.PRDate = date;
        this.PRID = PRID;
        this.itemsRequested = itemsRequested;
        this.status = status;
        this.dataAvailable = dataAvailable;
    }
    
    public LocalDate getDate(){
        return PRDate;
    }
    
    @Override
    public String getCode() {
        return PRID;
    }
    
    public Map<Item, Integer> getItemsRequested(){
        return itemsRequested;
    }
    
    public void addItemsRequested(Item item, int quantity){
        System.out.println(itemsRequested);
        // Check if the item is already in the map
        if (itemsRequested.containsKey(item)) {
            // If it is, update the quantity
            int currentQuantity = itemsRequested.get(item);
            
            itemsRequested.put(item, currentQuantity + quantity);
        } else {
            // If it's not, add a new entry
            itemsRequested.put(item, quantity);
        }
    }
    
    public PurchaseStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseStatus status) {
        this.status = status;
    }
    
    public String getFilePath(){
        return filePath;
    }

    @Override
    public String formatForFile() {
        StringBuilder formattedData = new StringBuilder();
        formattedData.append(PRID).append(",[");
        
        boolean isFirstItem = true; // Track if it's the first item
        
        for (Map.Entry<Item, Integer> entry : itemsRequested.entrySet()) {
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
        formattedData.append("]").append(",");
        formattedData.append(status).append(",");
        formattedData.append(PRDate).append(",");
        formattedData.append(dataAvailable);
        
        return formattedData.toString();
    }

    @Override
    public PurchaseRequisition parseData(String line) {
        String[] parts = line.split(",");
       
        // Ensure that the formatted string has at least three parts: salesCode, items, and totalSales
        if (parts.length < 5) {
            throw new IllegalArgumentException("Invalid formatted data: " + line);
        }
        
        // Extract PR Code
        String PRID = parts[0];

        // Extract the items and quantities section (itemsStr)
        String itemsStr = parts[1];

        // Remove the square brackets
        itemsStr = itemsStr.substring(1, itemsStr.length() - 1);
        
        // Split itemsStr into individual item-quantity pairs
        String[] itemQuantityPairs = itemsStr.split("\\.");
        
        Map<Item, Integer> itemsRequested = new HashMap<>();
        
        for (String pair : itemQuantityPairs) {
            // Split each pair into item name and quantity
            String[] pairParts = pair.split(":");
  
            // Ensure that the pair has two parts
            if (pairParts.length != 2) {
                throw new IllegalArgumentException("Invalid item-quantity pair: " + pair);
            }
            
            List<Item> itemList = FileOperations.readObjectsFromFile("resources/data/item.txt", new Item());
            String itemCode = pairParts[0];
            int quantity = Integer.parseInt(pairParts[1]);
            Item item = (Item) FileOperations.findDataByCode(itemCode, itemList);
            itemsRequested.put(item, quantity);
        }
        
        // Extract date and file status
        PurchaseStatus readStatus = PurchaseStatus.valueOf(parts[2]);
        LocalDate date = LocalDate.parse(parts[3]);
        boolean dataAvailable = Boolean.parseBoolean(parts[4]);
        // Create and return a PR object
        PurchaseRequisition PR = new PurchaseRequisition(PRID, itemsRequested, readStatus, date, dataAvailable);

        return PR;
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
