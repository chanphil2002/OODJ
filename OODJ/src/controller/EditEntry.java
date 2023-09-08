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
import java.util.Set;
import static model.Supplier.*;
/**
 *
 * @author pc
 */
public class EditEntry {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void editSupplierEntry(){
        DisplayEntry.displaySupplierEntry();
        List<Supplier> supplierList = FileOperations.readObjectsFromFile("resources/data/supplier.txt", new Supplier());
        System.out.print("Enter Supplier ID to Edit: ");
        String supplierID = scanner.nextLine();
        Supplier foundSupplier = (Supplier) FileOperations.findDataByCode(supplierID, supplierList);
        
        System.out.print("Enter Number: 1. Edit | 2. Delete: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch(choice){
            case 1:
                System.out.println("");
                System.out.println("Current Supplier ID " + foundSupplier.getCode());
                System.out.println("Current Supplier Name: " + foundSupplier.getSupplierName());
                System.out.print("Change Name: (or Press Enter to skip): ");
                String name = scanner.nextLine();
                if(name.isEmpty()){
                    System.out.println("You didn't change any name.");
                } else {
                    foundSupplier.setSupplierName(name);
                    
                }
                break;
            case 2:
                System.out.print("Are you sure you want to delete? yes/no: ");
                String confirmDelete = scanner.nextLine();
                if("yes".equals(confirmDelete)){
                    foundSupplier.setDataAvailable(false);
                }
                break;
        }
        FileOperations.updateObjectInFile(foundSupplier, foundSupplier.getFilePath(),supplierList);
        
    }
    
    public static void editItemEntry(){
        DisplayEntry.displayItemsEntry();
        List<Item> itemList = FileOperations.readObjectsFromFile("resources/data/item.txt", new Item());
        System.out.print("Enter Item ID: ");
        String itemID = scanner.nextLine();
        Item foundItem = (Item) FileOperations.findDataByCode(itemID, itemList);
        System.out.print("Enter Number: 1. Edit | 2. Delete: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch(choice){
            case 1:
                System.out.println("");
                System.out.printf("%-17s %-25s %-25s %-20s%n", "Current Item ID |", "Current Item Name       |", "Current Item Quantity |", "Current Item Price ");
                System.out.printf("%-17s %-25s %-25d %-20.2f%n", foundItem.getCode(), foundItem.getItemName(), foundItem.getItemQuantity(), foundItem.getItemPrice());
                System.out.print("Change Name: (or Press Enter to skip): ");
                String name = scanner.nextLine();
                if(name.isEmpty()){
                    System.out.println("You didn't change this name.");
                } else {
                    foundItem.setItemName(name);
                }
                System.out.print("Change Item Quantity: (or Press Enter to skip): ");
                String quantity = scanner.nextLine();
                if(quantity.isEmpty()){
                    System.out.println("You didn't change this item quantity.");
                } else {
                    int intValue = Integer.parseInt(quantity);
                    foundItem.setItemQuantity(intValue);
                    System.out.println(foundItem.getItemQuantity());
                }
                System.out.print("Change Item Price: (or Press Enter to skip): ");
                String price = scanner.nextLine();
                if(price.isEmpty()){
                    System.out.println("You didn't change this item quantity.");
                } else {
                    float floatValue = Float.parseFloat(price);
                    foundItem.setPrice(floatValue);
                }
                break;
            case 2:
                System.out.print("Are you sure you want to delete? yes/no: ");
                String confirmDelete = scanner.nextLine();
                if("yes".equals(confirmDelete)){
                    foundItem.setDataAvailable(false);
                }
                break;
        }
        
        FileOperations.updateObjectInFile(foundItem, foundItem.getFilePath(),itemList);
    }
    
    public static void editSalesEntry(){
        DisplayEntry.displaySalesEntry();
        List<Sales> salesList = FileOperations.readObjectsFromFile("resources/data/sales.txt", new Sales());
        List<Item> itemList = FileOperations.readObjectsFromFile("resources/data/item.txt", new Item());
        System.out.print("Enter Sales ID: ");
        String salesID = scanner.nextLine();
        Sales foundSales = (Sales) FileOperations.findDataByCode(salesID, salesList);
        
        System.out.print("Enter Number: 1. Edit | 2. Delete: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch(choice){
            case 1:
                System.out.println("");
                System.out.println("Date: " + foundSales.getDate());
                System.out.println("Sales ID: " + foundSales.getCode());
                System.out.printf("%-17s %-25s %-25s %-20s%n", "Item ID |", "Current Item Name       |", "Current Quantity Sold |", "Total ");
                for(Map.Entry<Item, Integer> entry : foundSales.getItemsSold().entrySet()){
                    Item item = entry.getKey();
                    int quantity = entry.getValue();
                   
                    System.out.printf("%-17s %-25s %-25d %-20.2f%n", item.getCode(),
                            item.getItemName(), quantity, item.getItemPrice()*quantity);
                }
                System.out.println("Total Sales Amount: " + foundSales.getTotalSalesAmount());
                System.out.print("Select Item ID in which you want to change. (or Press Enter to skip): ");
                String itemPrompt = scanner.nextLine();
                
                if (!itemPrompt.isEmpty()) {
                    Item selected = null;
                    for (Item item : foundSales.getItemsSold().keySet()) {
                        if (item.getCode().equalsIgnoreCase(itemPrompt)) {
                            selected = item;
                            break;
                        }
                    }
                    
                    if (selected != null) {
                        System.out.print("Change Quantity Sold (or press Enter to skip): ");
                        String quantityStr = scanner.nextLine();
                        if (!quantityStr.isEmpty()) {
                            int newQuantitySold = Integer.parseInt(quantityStr);
                            
                            // Calculate the change in item quantity
                            int oldQuantitySold = foundSales.getItemsSold().get(selected);
                            int changeInQuantity = newQuantitySold - oldQuantitySold;
                            
                            foundSales.getItemsSold().put(selected, newQuantitySold);
                            selected.setItemQuantity(selected.getItemQuantity() - changeInQuantity);
                            FileOperations.updateObjectInFile(selected, "resources/data/item.txt", itemList);
                            double newTotalSalesAmount = 0.0;
                            for (Map.Entry<Item, Integer> entry : foundSales.getItemsSold().entrySet()) {
                                Item item = entry.getKey();
                                int quantitySold = entry.getValue();
                                newTotalSalesAmount += item.getItemPrice() * quantitySold;
                            }
                            foundSales.setTotalSalesAmount(newTotalSalesAmount);
                            break;
                        }
                    } else {
                        System.out.println("Item not found.");
                        break;
                    }
                } else {
                    System.out.println("You didn't make any changes this time.");
                    break;
                }
            case 2:
                System.out.print("Are you sure you want to delete? yes/no: ");
                String confirmDelete = scanner.nextLine();
                if("yes".equals(confirmDelete)){
                    foundSales.setDataAvailable(false);
                }
                break;
        }
        FileOperations.updateObjectInFile(foundSales, foundSales.getFilePath(),salesList);
    }
    
    public static void editPREntry(){
        DisplayEntry.displayPREntry();
        List<PurchaseRequisition> prList = FileOperations.readObjectsFromFile("resources/data/purchaserequisition.txt", new PurchaseRequisition());
        System.out.print("Enter Purchase Requisition ID: ");
        String prID = scanner.nextLine();
        PurchaseRequisition foundPR = (PurchaseRequisition) FileOperations.findDataByCode(prID, prList);
        
        System.out.print("Enter Number: 1. Edit | 2. Delete: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch(choice){
            case 1:
                System.out.println("");
                System.out.println("Date: " + foundPR.getDate());
                System.out.println("Sales ID: " + foundPR.getCode());
                System.out.printf("%-17s %-25s %-25s %-20s%n", "Item ID |", "Current Item Name       |", "Current Quantity Sold |", "Total ");
                for(Map.Entry<Item, Integer> entry : foundPR.getItemsRequested().entrySet()){
                    Item item = entry.getKey();
                    int quantity = entry.getValue();
                   
                    System.out.printf("%-17s %-25s %-25d %-20.2f%n", item.getCode(),
                            item.getItemName(), quantity, item.getItemPrice()*quantity);
                }
                System.out.println("Select Item ID in which you want to change.: (or Press Enter to skip)");
                String itemPrompt = scanner.nextLine();
                
                if (!itemPrompt.isEmpty()) {
                    Item selected = null;
                    for (Item item : foundPR.getItemsRequested().keySet()) {
                        if (item.getCode().equalsIgnoreCase(itemPrompt)) {
                            selected = item;
                            break;
                        }
                    }
                    
                    if (selected != null) {
                        System.out.println("Change Quantity to Request (or press Enter to skip):");
                        String quantityStr = scanner.nextLine();
                        if (!quantityStr.isEmpty()) {
                            int newQuantitySold = Integer.parseInt(quantityStr);
                            foundPR.getItemsRequested().put(selected, newQuantitySold);
                            break;
                        }
                    } else {
                        System.out.println("Item not found.");
                        break;
                    }
                } else {
                    System.out.println("You didn't make any changes this time.");
                    break;
                }
            case 2:
                System.out.println("Are you sure you want to delete?");
                String confirmDelete = scanner.nextLine();
                if("yes".equals(confirmDelete)){
                    foundPR.setDataAvailable(false);
                }
                break;
        }
        FileOperations.updateObjectInFile(foundPR, foundPR.getFilePath(),prList);
    }
    
    public static void editPOEntry(){
        DisplayEntry.displayPOEntry();
        List<PurchaseOrder> poList = FileOperations.readObjectsFromFile("resources/data/purchaseorder.txt", new PurchaseOrder());
        List<Item> itemList = FileOperations.readObjectsFromFile("resources/data/item.txt", new Item());
        System.out.print("Enter Purchase Order ID: ");
        String poID = scanner.nextLine();
        PurchaseOrder foundPO = (PurchaseOrder) FileOperations.findDataByCode(poID, poList);
        
        System.out.println("");
        System.out.println("Date: " + foundPO.getDate());
        System.out.println("Purchase Order ID: " + foundPO.getCode());
        System.out.printf("%-17s %-25s %-25s %-20s%n", "Item ID |", "Current Item Name       |", "Current Quantity Sold |", "Total ");
        for(Map.Entry<Item, Integer> entry : foundPO.getAssociatedRequisition().getItemsRequested().entrySet()){
            Item item = entry.getKey();
            int quantity = entry.getValue();

            System.out.printf("%-17s %-25s %-25d %-20.2f%n", item.getCode(),
                    item.getItemName(), quantity, item.getItemPrice()*quantity);
        }
        
        System.out.print("Purchase Order Completed (Y) / Purchase Order Cancelled (X): ");
        String itemPrompt = scanner.nextLine();
        if (!itemPrompt.isEmpty() && itemPrompt.equalsIgnoreCase("y")) {
            // Update item quantities in your inventory
            for (Map.Entry<Item, Integer> entry : foundPO.getAssociatedRequisition().getItemsRequested().entrySet()) {
                Item item = entry.getKey();
                int quantityRequested = entry.getValue();

                // Add the requested quantity to the item's quantity in your inventory
                int newQuantity = item.getItemQuantity() + quantityRequested;
                item.setItemQuantity(newQuantity);
                FileOperations.updateObjectInFile(item, item.getFilePath(),itemList);
            }

            FileOperations.updateObjectInFile(foundPO, foundPO.getFilePath(),poList);
            foundPO.setStatus(PurchaseStatus.COMPLETED);

            System.out.println("Purchase Order Completed. Your Item has Arrived!");

        } if (!itemPrompt.isEmpty() && itemPrompt.equalsIgnoreCase("x")) {
            foundPO.setStatus(PurchaseStatus.CANCELLED);

            System.out.println("Purchase Order Cancelled. Please Raise Requisition Again."); 
        } if (itemPrompt.isEmpty()) {
            System.out.println("Purchase Order for " + foundPO.getCode() + " is still Pending.");
        }
                
        FileOperations.updateObjectInFile(foundPO, foundPO.getFilePath(),poList);
    }
}
    