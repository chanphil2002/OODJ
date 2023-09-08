/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.*;
import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author pc
 */
public class PurchaseOrder implements IFileFormattable, IDataParser<PurchaseOrder>, IDataSearchable, IFileStatus{
    private LocalDate PODate;
    private String POID;
    private PurchaseRequisition associatedRequisition;
    private PurchaseStatus status;
    private boolean dataAvailable;
    
    private final String filePath = "resources/data/purchaseorder.txt";
    private IdGenerator idGenerator = new IdGenerator(filePath);
    
    public PurchaseOrder(){
    }
    
    public PurchaseOrder(PurchaseRequisition PR){
        this.PODate = LocalDate.now();
        POID = idGenerator.generateID("K");
        this.status = PurchaseStatus.PENDING;
        this.associatedRequisition = PR;
        dataAvailable = true;
    }
    
    public PurchaseOrder(LocalDate PODate, String POID, PurchaseRequisition requisition, PurchaseStatus status, boolean dataAvailable) {
        this.PODate = PODate;
        this.POID = POID;
        this.associatedRequisition = requisition;
        this.status = status;
        this.dataAvailable = dataAvailable;
    }
    
    public LocalDate getDate(){
        return PODate;
    }
    
    @Override
    public String getCode() {
        return POID;
    }
    
    public PurchaseRequisition getAssociatedRequisition(){
        return associatedRequisition;
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
    
    // Method to check if the associated Purchase Requisition is approved
    public boolean isRequisitionApproved() {
        return associatedRequisition != null && associatedRequisition.getStatus() == PurchaseStatus.APPROVED;
    }
    
    @Override
    public String formatForFile() {
        StringBuilder formattedData = new StringBuilder();
        formattedData.append(POID).append(",");
        formattedData.append(associatedRequisition.getCode()).append(",[");
        
        boolean isFirstItem = true; // Track if it's the first item
        
        for (Map.Entry<Item, Integer> entry : associatedRequisition.getItemsRequested().entrySet()) {
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
        formattedData.append(PODate).append(",");
        formattedData.append(dataAvailable);
        
        return formattedData.toString();
    }

    @Override
    public PurchaseOrder parseData(String line) {
        String[] parts = line.split(",");
       
        // Ensure that the formatted string has at least three parts: salesCode, items, and totalSales
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid formatted data: " + line);
        }
        
        // Extract PO and PR Code
        String POID = parts[0];
        String PRID = parts[1];

        // Extract the items and quantities section (itemsStr)
        String itemsStr = parts[2];

//        // Remove the square brackets
//        itemsStr = itemsStr.substring(1, itemsStr.length() - 1);
        
        List<PurchaseRequisition> prList = FileOperations.readObjectsFromFile("resources/data/purchaserequisition.txt", new PurchaseRequisition());
        PurchaseRequisition pr = (PurchaseRequisition) FileOperations.findDataByCode(PRID, prList);
        
//        // Split itemsStr into individual item-quantity pairs
//        String[] itemQuantityPairs = itemsStr.split("\\.");
        
//        for (String pair : itemQuantityPairs) {
//            // Split each pair into item name and quantity
//            String[] pairParts = pair.split(":");
//            
//            // Ensure that the pair has two parts
//            if (pairParts.length != 2) {
//                throw new IllegalArgumentException("Invalid item-quantity pair: " + pair);
//            }
//            List<Item> itemList = FileOperations.readObjectsFromFile("resources/data/item.txt", new Item());
//            String itemCode = pairParts[0];
//            int quantity = Integer.parseInt(pairParts[1]);
//            Item item = Item.getItemByCode(itemList, itemCode);
//            System.out.println(itemCode + quantity);
//            
//            // Add the item and quantity to the map
//            associatedRequisition.getItemsRequested().put(item, quantity);
//        }
        
        // Extract date
        PurchaseStatus p = PurchaseStatus.valueOf(parts[3]);
        LocalDate date = LocalDate.parse(parts[4]);
        boolean dataAvailable = Boolean.parseBoolean(parts[5]);
        
        // Create and return a PR object
        PurchaseOrder PO = new PurchaseOrder(date, POID, pr, p, dataAvailable);
        
        return PO;
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
