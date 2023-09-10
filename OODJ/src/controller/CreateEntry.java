/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.*;
import java.util.Scanner;
import java.util.List;
import java.util.InputMismatchException;
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
        List<Supplier> supplierList = FileOperations.readObjectsFromFile("resources/data/supplier.txt", new Supplier());
        
        System.out.println("Enter Item Name: ");
        item.setItemName(scanner.nextLine());
        
        boolean success = true;
        do {
            success = true;
            try {
                
                System.out.println("Enter Item quantity: ");
                item.setItemQuantity(scanner.nextInt());
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Integer, please try again.");
                success = false;
                scanner.nextLine();
            }
        } while(!success);
        float price = 0;
        do {
            try {
                success = true;
                System.out.println("Enter Item Price: ");
                price = scanner.nextFloat();
                scanner.nextLine();
                if (price < 0) {
                    System.out.println("Please input a positive price.");
                    success = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid number, please try again");
                scanner.nextLine();
                success = false;
            }
        } while(!success);
        item.setPrice(price);
        
        boolean continueEnteringSupplier = true;
        while (continueEnteringSupplier) {
            DisplayEntry.displaySupplierEntry();
            System.out.println("Enter Supplier ID (Can be multiple Supplier): ");
            String supplierID = scanner.nextLine();
            
            if (supplierID.equalsIgnoreCase("exit")) {
                continueEnteringSupplier = false;
                break;
            }
            Supplier supplier = null;
            try {
                supplier = (Supplier) FileOperations.findDataByCode(supplierID, supplierList);
            } catch (Exception Exception) {
                System.out.println("Supplier with ID: " + supplierID + "not found.");
                success = false;
            } finally {
                if (success) {
                    item.addSupplier(supplier); 
                }
            }
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
            Item foundItem = null;
            int itemQuantity = 0;
            boolean success = true;
            try {
                foundItem = (Item) FileOperations.findDataByCode(itemCode, itemList);
            } catch (Exception Exception) {
                System.out.println("Please input a correct item code.");
                success = false;
            } finally {
                if (success) {
                    itemQuantity = foundItem.getItemQuantity();
                }
            }
            int quantity = 0;
            do {
                try {
                    success = true;
                    System.out.println("Enter Quantity Sold: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine();
                    if(foundItem.getItemQuantity() < quantity || quantity == 0){
                        System.out.println("Invalid quantity, please try again.");
                        success = false;
                }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid quantity, please try again.");
                    scanner.nextLine();
                    success = false;
                }
            } while (!success);
            itemQuantity -= quantity;
            foundItem.setItemQuantity(itemQuantity);
            FileOperations.updateObjectInFile(foundItem, "resources/data/item.txt", itemList);
            sales.addItem(foundItem, quantity);
            salesAmount += Math.round(foundItem.getItemPrice() * quantity);
            sales.setTotalSalesAmount(salesAmount);
        }
        FileOperations.writeObjectToFile(sales, "resources/data/sales.txt");
    }
    
    public static void createPREntry(){
        PurchaseRequisition PR = new PurchaseRequisition();
        List<Item> itemList = FileOperations.readObjectsFromFile("resources/data/item.txt", new Item());
        List<Supplier> supplierList = FileOperations.readObjectsFromFile("resources/data/supplier.txt", new Supplier());
        
        boolean continueEnteringPR = true;
        while (continueEnteringPR) {
            System.out.print("Enter Item Code (or type 'exit' to finish): ");
            String itemCode = scanner.nextLine();
            
            if (itemCode.equalsIgnoreCase("exit")) {
                continueEnteringPR = false;
                break;
            }
            Item foundItem = null;
            boolean success = true;
            try {
                foundItem = (Item) FileOperations.findDataByCode(itemCode, itemList);
            } catch (Exception Exception) {
                System.out.println("Item not found.");
                success = false;
            } finally {
                if (success) {
                    int quantity = 0;
                    do {
                        success = true;
                        try {
                            System.out.println("Enter Quantity Requested: ");
                            quantity = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid quantity. Please input the correct quantity.");
                            scanner.nextLine();
                            success = false;
                        }
                    } while (!success);
                    
                    Supplier foundSupplier = null;
                    do { 
                        try {
                            success = true;
                            System.out.println("Enter Supplier ID: ");
                            String supplierID = scanner.nextLine();
                            foundSupplier = (Supplier) FileOperations.findDataByCode(supplierID, supplierList);
                        } catch (Exception Exception) {
                            System.out.println("Supplier not found.");
                            success = false;
                        }
                    } while (!success);
                    PurchaseItem purchaseItem = new PurchaseItem(foundItem, foundSupplier, quantity);
                    PR.addItemsRequested(purchaseItem);
                }
            }     
        }
            FileOperations.writeObjectToFile(PR, "resources/data/purchaserequisition.txt");
        }
    
    public static void createPOEntry(){
        List<PurchaseRequisition> prList = FileOperations.readObjectsFromFile("resources/data/purchaserequisition.txt", new PurchaseRequisition());
        boolean success = false;
        PurchaseRequisition foundPR = null;
        do {
            try {
                System.out.print("Select Purchase Requisition ID: ");
                String PRID = scanner.nextLine();
                foundPR = (PurchaseRequisition) FileOperations.findDataByCode(PRID, prList);
                success = true;
        }   catch (Exception Exception) {
            success = false;
            System.out.println("Please input a correct ID.");
        }
        } while (!success);
        System.out.println(foundPR.getCode());
        System.out.print("Do you want to accept this Purchase Requisition? (yes/no): ");
        String response = scanner.nextLine();
        
        if(response.equalsIgnoreCase("yes")){
            // Set the status to APPROVED if accepted
            foundPR.setStatus(PurchaseStatus.APPROVED);
            System.out.println(foundPR.getCode());
            FileOperations.updateObjectInFile(foundPR, "resources/data/purchaserequisition.txt", prList);
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
        

