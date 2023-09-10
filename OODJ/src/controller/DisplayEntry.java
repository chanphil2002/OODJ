/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import java.util.Map;
import model.*;

/**
 *
 * @author pc
 */
public class DisplayEntry {
    
    public static void displaySupplierEntry(){
        List<Supplier> supplierList = FileOperations.readObjectsFromFile(Supplier.filePath, new Supplier());
        
        System.out.printf("%-17s %-20s%n", "Supplier ID   |", "Supplier Name       ");
        for(Supplier s : supplierList){
            if(s.getDataAvailable()==true){
                System.out.printf("%-17s %-20s%n", s.getCode(), s.getSupplierName());
            }
        }
        System.out.println("");
    }
    
    public static void displayItemsEntry(){
        Item item = new Item();
        List<Item> itemList = FileOperations.readObjectsFromFile(Item.filePath,item);
        
        System.out.printf("%-17s %-25s %-25s %-20s%n", "Current Item ID |", "Current Item Name       |", "Current Item Quantity |", "Available Supplier ");
        for(Item i : itemList){
            if(i.getDataAvailable()==true){
                List<Supplier> supplierList = i.getSupplierList();
                StringBuilder supplierIds = new StringBuilder();
                for(Supplier supplier : supplierList){
                    if (supplierIds.length() > 0) {
                        supplierIds.append(", ");
                    }
                    supplierIds.append(supplier.getCode()).append(": ")
                            .append(supplier.getSupplierName());
                }
                System.out.printf("%-17s %-25s %-25s %-20s%n", i.getCode(), 
                        i.getItemName(), i.getItemQuantity(), supplierIds.toString());
            }
        }
        System.out.println("");
    }
    
    public static void displaySalesEntry(){
        Sales sales = new Sales();
        List<Sales> salesList = FileOperations.readObjectsFromFile("resources/data/sales.txt", sales);
        
        System.out.printf("%-15s %-15s %-15s %-10s%n", "Date        |", "Sales ID    |", "Total      |", "Items Sold");
        for(Sales s : salesList){
            if(s.getDataAvailable()==true){
                StringBuilder itemsSoldString = new StringBuilder();
                for (Map.Entry<Item, Integer> entry : s.getItemsSold().entrySet()) {
                    Item item = entry.getKey();
                    int quantity = entry.getValue();
                    
                    if (itemsSoldString.length() > 0) {
                        itemsSoldString.append(", ");
                    }
                    
                    itemsSoldString.append(item.getItemName()).append(": ")
                            .append(quantity);
                }
                
                System.out.printf("%-15s %-15s %-15s %-10s%n",s.getDate(), s.getCode(), s.getTotalSalesAmount(), itemsSoldString.toString());
            }
        }
        System.out.println("");
        
    }
    
    public static void displayPREntry(){
        List<PurchaseRequisition> prList = 
                FileOperations.readObjectsFromFile("resources/data/purchaserequisition.txt", new PurchaseRequisition());
        
        System.out.printf("%-15s %-15s %-15s %-15s%n", "Date        |", "PR ID       |", "Status      |", "Items Requested");
        for(PurchaseRequisition p : prList){
            if(p.getDataAvailable()==true){
                StringBuilder itemsRequestedStrings = new StringBuilder();
                for (PurchaseItem entry : p.getItemsRequested()) {
                    if (itemsRequestedStrings.length() > 0) {
                        itemsRequestedStrings.append(" || ");
                    }
                    itemsRequestedStrings.append(entry.getQuantityRequested())
                            .append("x ").append(entry.getItem().getItemName())
                            .append(" - ").append(entry.getSupplier().getSupplierName());
                }
                System.out.printf("%-15s %-15s %-15s %-15s%n", p.getDate(), 
                        p.getCode(), p.getStatus(), itemsRequestedStrings.toString());
            }
        }
        System.out.println("");
    }
    
    public static void displayPOEntry(){
        PurchaseOrder PO = new PurchaseOrder();
        List<PurchaseOrder> poList = 
                FileOperations.readObjectsFromFile("resources/data/purchaseorder.txt",PO);
        
        System.out.printf("%-15s %-15s %-15s %-15s%n", "Date        |", "PR ID       |", "Status      |", "Items Ordered");
        for(PurchaseOrder p : poList){
            if(p.getDataAvailable()==true){
                StringBuilder itemsOrderedStrings = new StringBuilder();
                
                for (PurchaseItem entry : p.getAssociatedRequisition().getItemsRequested()) {
                    if (itemsOrderedStrings.length() > 0) {
                        itemsOrderedStrings.append(" || ");
                    } 
                    itemsOrderedStrings.append(entry.getQuantityRequested())
                            .append("x ").append(entry.getItem().getItemName())
                            .append(" - ").append(entry.getSupplier().getSupplierName());
                
                }
                System.out.printf("%-15s %-15s %-15s %-15s%n", p.getDate(), 
                        p.getCode(), p.getStatus(), itemsOrderedStrings.toString());
            }
        }
        System.out.println("");
    }
    
    
}
