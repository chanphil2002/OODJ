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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Item;
/**
 *
 * @author pc
 */

public class PurchaseRequisition implements IFileFormattable, IDataParser<PurchaseRequisition>, IDataSearchable, IFileStatus{
    private LocalDate PRDate;
    private String PRID;
    private List<PurchaseItem> purchaseItemsList;
    private PurchaseStatus status;
    private boolean dataAvailable;
    
    private final String filePath = "resources/data/purchaserequisition.txt";
    private IdGenerator idGenerator = new IdGenerator(filePath);
    
    public PurchaseRequisition(){
        PRDate = LocalDate.now();
        PRID = idGenerator.generateID("PR");
        this.purchaseItemsList = new ArrayList<>();
        status = PurchaseStatus.PENDING;
        dataAvailable = true;
    }
    
    public PurchaseRequisition(String PRID, List<PurchaseItem> purchaseItemsList, PurchaseStatus status, LocalDate date, boolean dataAvailable){
        this.PRDate = date;
        this.PRID = PRID;
        this.purchaseItemsList = purchaseItemsList;
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
    
    public List<PurchaseItem> getItemsRequested(){
        return purchaseItemsList;
    }
    
    public void addItemsRequested(PurchaseItem item){
        purchaseItemsList.add(item);
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
        
        for (PurchaseItem entry : purchaseItemsList) {
            // Append a comma before each item (except the first one)
            if (!isFirstItem) {
                formattedData.append(".");
            } else {
                isFirstItem = false;
            }
            // Append item and quantity to the formatted string
            formattedData.append(entry.getItem().getCode()).append(":")
                    .append(entry.getQuantityRequested()).append(":")
                    .append(entry.getSupplier().getCode());
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
        
        List<PurchaseItem> itemsRequestedList = new ArrayList<>();
        List<Item> itemList = FileOperations.readObjectsFromFile("resources/data/item.txt", new Item());
        List<Supplier> supplierList = FileOperations.readObjectsFromFile("resources/data/supplier.txt", new Supplier());
        
        for (String pair : itemQuantityPairs) {
            // Split each pair into item name and quantity
            String[] pairParts = pair.split(":");
            
            // Ensure that the pair has two parts
            if (pairParts.length != 3) {
                throw new IllegalArgumentException("Invalid item-quantity pair: " + pair);
            }
            
            String itemCode = pairParts[0];
            int quantity = Integer.parseInt(pairParts[1]);
            String supplierID = pairParts[2];
            Item item = null;
            Supplier supplier = null;
            try {
                item = (Item) FileOperations.findDataByCode(itemCode, itemList);
                supplier = (Supplier) FileOperations.findDataByCode(supplierID, supplierList);
            } catch (Exception ex) {
                Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            PurchaseItem purchaseItem = new PurchaseItem(item, supplier, quantity);
            itemsRequestedList.add(purchaseItem);
        }
        
        // Extract date and file status
        PurchaseStatus readStatus = PurchaseStatus.valueOf(parts[2]);
        LocalDate date = LocalDate.parse(parts[3]);
        boolean dataAvailable = Boolean.parseBoolean(parts[4]);
        // Create and return a PR object
        PurchaseRequisition PR = new PurchaseRequisition(PRID, itemsRequestedList, readStatus, date, dataAvailable);

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
