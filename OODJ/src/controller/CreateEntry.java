/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static model.Supplier.*;
/**
 *
 * @author pc
 */
public class CreateEntry {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void createSupplierEntry(){
        System.out.println("Enter Supplier Name: ");
        String supplierName = scanner.nextLine();
        
        Supplier supplier = new Supplier(supplierName);
        
        FileOperations.writeObjectToFile(supplier, supplier.getFilePath());
    }
    
    public static void createItemEntry(){
        Item item = new Item();
        System.out.println("Enter Item Name: ");
        item.setItemName(scanner.nextLine());
        
        System.out.println("Enter Item quantity: ");
        item.setItemQuantity(scanner.nextInt());
        
        System.out.println("Enter Item Price: ");
        item.setPrice(scanner.nextFloat());
        scanner.nextLine();
        
        System.out.println("Enter Supplier ID: ");
        String supplierID = scanner.nextLine();
        
        List<Supplier> supplierList = FileOperations.readObjectsFromFile("resources/data/supplier.txt", new Supplier());
        Supplier supplier = (Supplier) FileOperations.findDataByCode(supplierID, supplierList);
        
        if(supplier != null){
            item.setSupplier(supplier);
        } else {
            System.out.println("Supplier with ID: " + supplierID + "not found.");
            return;
        }
        FileOperations.writeObjectToFile(item, "resources/data/item.txt");
    }
    
    public static void createSalesEntry(){
        Sales sales = new Sales();
        List<Item> itemList = FileOperations.readObjectsFromFile("resources/data/item.txt", new Item());
        double salesAmount = 0;
        
        boolean continueEnteringSales = true;
        while (continueEnteringSales) {
            System.out.print("Enter Item Code (or type 'exit' to finish): ");
            String itemCode = scanner.nextLine();
            
            if (itemCode.equalsIgnoreCase("exit")) {
                continueEnteringSales = false;
                break;
            }
            
            Item foundItem = (Item) FileOperations.findDataByCode(itemCode, itemList);
            int itemQuantity = foundItem.getItemQuantity();
            
            while(true){
                System.out.println("Enter Quantity Sold: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();
                if(foundItem.getItemQuantity() < quantity || quantity == 0){
                    System.out.println("Invalid quantity, please try again.");
                } else {
                    itemQuantity -= quantity;
                    foundItem.setItemQuantity(itemQuantity);
                    FileOperations.updateObjectInFile(foundItem, "resources/data/item.txt", itemList);
                    sales.addItem(foundItem, quantity);
                    salesAmount += (foundItem.getItemPrice() * quantity);
                    break;
                }
            }
            
            sales.setTotalSalesAmount(salesAmount);
        }
        FileOperations.writeObjectToFile(sales, "resources/data/sales.txt");
    }
    
    public static void createPREntry(){
        PurchaseRequisition PR = new PurchaseRequisition();
        List<Item> itemList = FileOperations.readObjectsFromFile("resources/data/item.txt", new Item());
        Map<String, Integer> itemQuantities = new HashMap<>();
        
        boolean continueEnteringPR = true;
        while (continueEnteringPR) {
            System.out.print("Enter Item Code (or type 'exit' to finish): ");
            String itemCode = scanner.nextLine();
            
            if (itemCode.equalsIgnoreCase("exit")) {
                continueEnteringPR = false;
                break;
            }
            
            Item foundItem = (Item) FileOperations.findDataByCode(itemCode, itemList);
            
            if (foundItem != null) {
                System.out.println("Enter Quantity Requested: ");
                int quantity = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                // Check if the item code already exists in the map
                if (itemQuantities.containsKey(itemCode)) {
                    // If it exists, add the new quantity to the existing quantity
                    int existingQuantity = itemQuantities.get(itemCode);
                    itemQuantities.put(itemCode, existingQuantity + quantity);
                } else {
                    // If it doesn't exist, simply put the quantity in the map
                    itemQuantities.put(itemCode, quantity);
                }

                // Add the item to the PR
                PR.addItemsRequested(foundItem, quantity);
                
            } else {
                System.out.println("Item not found.");
            }
        }
            FileOperations.writeObjectToFile(PR, "resources/data/purchaserequisition.txt");
        }
    
    public static void createPOEntry(){
        List<PurchaseRequisition> prList = FileOperations.readObjectsFromFile("resources/data/purchaserequisition.txt", new PurchaseRequisition());
        System.out.println("Select Purchase Requisition ID: ");
        String PRID = scanner.nextLine();
        PurchaseRequisition foundPR = (PurchaseRequisition) FileOperations.findDataByCode(PRID, prList);
        System.out.println(foundPR.getCode());
        Map<Item, Integer> itemsRequested = foundPR.getItemsRequested();
            for (Map.Entry<Item, Integer> entry : itemsRequested.entrySet()) {
            Item key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key.getCode() + ": " + value);
        }
        System.out.println(itemsRequested);
        System.out.print("Do you want to accept this Purchase Requisition? (yes/no): ");
        String response = scanner.nextLine();
        
        if(response.equalsIgnoreCase("yes")){
            // Set the status to APPROVED if accepted
            foundPR.setStatus(PurchaseStatus.APPROVED);
            
            // Create a Purchase Order if the PR status is "APPROVED"
            if (foundPR.getStatus() == PurchaseStatus.APPROVED) {
                PurchaseOrder po = new PurchaseOrder(foundPR);
                System.out.println(po.getCode());
                System.out.println(po.getAssociatedRequisition().getCode());
                FileOperations.writeObjectToFile(po, "resources/data/purchaseorder.txt");
                System.out.println("Purchase Order " + po.getCode() + " created.");
            } else {
                System.out.println("Purchase Requisition is not approved. Cannot create Purchase Order.");
            }
        } else {
            System.out.println("Purchase Requisition is rejected.");
        } 
    }
}
        

