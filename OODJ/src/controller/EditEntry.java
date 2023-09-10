/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.*;
import view.OptionPicker;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
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
        Supplier foundSupplier = null;
        boolean success = true;
        do {
            try {
                success = true;
                System.out.print("Enter Supplier ID to Edit: ");
                String supplierID = scanner.nextLine();
                foundSupplier = (Supplier) FileOperations.findDataByCode(supplierID, supplierList);
            } catch (Exception Exception) {
                System.out.println("Invalid Supplier");
            }
        } while (!success);
        
        
        List<String> options = new ArrayList<String>();
        options.add("Edit");
        options.add("Delete");
        options.add("Return to previous menu");
        int option = OptionPicker.optionPicker(options);
        switch(option){
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
            default:
        }
        FileOperations.updateObjectInFile(foundSupplier, foundSupplier.getFilePath(),supplierList);
        
    }
    
    public static void editItemEntry(){
        DisplayEntry.displayItemsEntry();
        List<Item> itemList = FileOperations.readObjectsFromFile("resources/data/item.txt", new Item());
        Item foundItem = null;
        boolean success = true;
        do {
            try {
                success = true;
                System.out.print("Enter Item ID: ");
                String itemID = scanner.nextLine();
                foundItem = (Item) FileOperations.findDataByCode(itemID, itemList);
            } catch (Exception Exception) {
                System.out.println("Invalid item.");
                success = false;
            }
        } while (!success);
        List<String> options = new ArrayList<String>();
        options.add("Edit");
        options.add("Delete");
        options.add("Return to previous menu");
        int option = OptionPicker.optionPicker(options);
        switch(option){
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
                do {
                    try {
                        success = true;
                        System.out.print("Change Item Quantity: (or Press Enter to skip): ");
                        String quantity = scanner.nextLine();
                        if(quantity.isEmpty()){
                            System.out.println("You didn't change this item quantity.");
                        } else {
                            int intValue = Integer.parseInt(quantity);
                            foundItem.setItemQuantity(intValue);
                            System.out.println(foundItem.getItemQuantity());
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid quantity. Please input the correct quantity or press enter to skip.");
                        success = false;
                    }
                } while (!success);
                do {
                    try {
                        success = true;
                        System.out.print("Change Item Price: (or Press Enter to skip): ");
                        String price = scanner.nextLine();
                        if(price.isEmpty()){
                            System.out.println("You didn't change this item price.");
                        } else {
                            float floatValue = Float.parseFloat(price);
                            foundItem.setPrice(floatValue);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid price. Please input the correct price or press enter to skip.");
                        success = false;
                    }
                } while (!success);
                break;
            case 2:
                System.out.print("Are you sure you want to delete? yes/no: ");
                String confirmDelete = scanner.nextLine();
                if("yes".equals(confirmDelete)){
                    foundItem.setDataAvailable(false);
                }
                break;
            default:
        } 
        FileOperations.updateObjectInFile(foundItem, foundItem.getFilePath(),itemList);
    }
    
    public static void editSalesEntry(){
        DisplayEntry.displaySalesEntry();
        List<Sales> salesList = FileOperations.readObjectsFromFile("resources/data/sales.txt", new Sales());
        List<Item> itemList = FileOperations.readObjectsFromFile("resources/data/item.txt", new Item());
        Sales foundSales = null;
        boolean success = true;
        do {
            try {
                success = true;
                System.out.print("Enter Sales ID: ");
                String salesID = scanner.nextLine();
                foundSales = (Sales) FileOperations.findDataByCode(salesID, salesList);
            } catch (Exception Exception) {
                System.out.println("Invalid Sales");
                success = false;
            }
        } while (!success);
        List<String> options = new ArrayList<String>();
        options.add("Edit");
        options.add("Delete");
        options.add("Return to previous menu");
        int option = OptionPicker.optionPicker(options);
        switch(option){
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
                            int newQuantitySold = 0;
                            do {
                                try {
                                    success = true;
                                    newQuantitySold = Integer.parseInt(quantityStr);
                                } catch (Exception e) {
                                    System.out.println("Invalid quantity. Please input the correct quantity or enter to skip.");
                                    success = false;
                                }
                            } while (!success);
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
            default:
        }
        FileOperations.updateObjectInFile(foundSales, foundSales.getFilePath(),salesList);
    }
    
    public static void editPREntry(){
        DisplayEntry.displayPREntry();
        List<PurchaseRequisition> prList = FileOperations.readObjectsFromFile("resources/data/purchaserequisition.txt", new PurchaseRequisition());
        PurchaseRequisition foundPR = null;
        boolean success = true;
        do {
            try {
                success = true;
                System.out.print("Enter Purchase Requisition ID: ");
                String prID = scanner.nextLine();
                foundPR = (PurchaseRequisition) FileOperations.findDataByCode(prID, prList);
            } catch (Exception Exception) {
                System.out.println("Invalid Purchase Requisition");
                success = false;
            }
        } while (!success);
        List<String> options = new ArrayList<String>();
        options.add("Edit");
        options.add("Delete");
        options.add("Return to previous menu");
        int option = OptionPicker.optionPicker(options);
        switch(option){
            case 1:
                System.out.println("");
                System.out.println("Date: " + foundPR.getDate());
                System.out.println("Sales ID: " + foundPR.getCode());
                System.out.printf("%-17s %-25s %-25s %-20s%n", "Item ID |", "Current Item Name       |", "Current Quantity Sold |", "Total ");
                for(PurchaseItem p : foundPR.getItemsRequested()){
                   
                    System.out.printf("%-17s %-25s %-25d %-20.2f%n", p.getItem().getCode(),
                            p.getItem().getItemName(), p.getQuantityRequested(), p.getSupplier().getCode());
                }
                System.out.println("Select Item ID in which you want to change.: (or Press Enter to skip)");
                String itemPrompt = scanner.nextLine();
                
                if (!itemPrompt.isEmpty()) {
                    PurchaseItem selected = null;
                    for (PurchaseItem item : foundPR.getItemsRequested()) {
                        if (item.getItem().getCode().equalsIgnoreCase(itemPrompt)) {
                            selected = item;
                            break;
                        }
                    }
                    if (selected != null) {
                        System.out.println("Change Quantity to Request (or press Enter to skip):");
                        do {
                            try {
                                success = true;
                                String quantityStr = scanner.nextLine();
                                if (!quantityStr.isEmpty()) {
                                    int newQuantitySold = Integer.parseInt(quantityStr);
                                    selected.setQuantityRequested(newQuantitySold);
                                    int selectedIndex = foundPR.getItemsRequested().indexOf(selected);
                                    foundPR.getItemsRequested().set(selectedIndex, selected);
                                    break;
                        }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid quantity. Please input the correct quantity or enter to skip.");
                                success = false;
                            }
                        } while (!success);        
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
        PurchaseOrder foundPO = null;
        boolean success = true;
        do {
            try {
                success = true;
                System.out.print("Enter Purchase Order ID: ");
                String poID = scanner.nextLine();
                foundPO = (PurchaseOrder) FileOperations.findDataByCode(poID, poList);
            } catch (Exception Exception) {
                System.out.println("Invalid Purchase Order");
                success = false;
            }
        } while (!success);
        
        
        System.out.println("");
        System.out.println("Date: " + foundPO.getDate());
        System.out.println("Purchase Order ID: " + foundPO.getCode());
        System.out.printf("%-17s %-25s %-25s %-20s%n", "Item ID |", "Current Item Name       |", "Current Quantity Sold |", "Total ");
        for(PurchaseItem p : foundPO.getAssociatedRequisition().getItemsRequested()){
            System.out.printf("%-17s %-25s %-25d %-20.2f%n", p.getItem().getCode(),
                    p.getItem().getItemName(), p.getQuantityRequested(),p.getSupplier().getCode());
        }
        
        System.out.print("Purchase Order Completed (Y) / Purchase Order Cancelled (X): ");
        String itemPrompt = scanner.nextLine();
        if (!itemPrompt.isEmpty() && itemPrompt.equalsIgnoreCase("y")) {
            // Update item quantities in your inventory
            for (PurchaseItem p : foundPO.getAssociatedRequisition().getItemsRequested()) {
                String itemCode = p.getItem().getCode();
                Item correspondingItem = null;
                for (Item item : itemList) {
                    if (item.getCode().equalsIgnoreCase(itemCode)) {
                        correspondingItem = item;
                        break;
                    }
                }
                
                if (correspondingItem != null) {
                    // Update the quantity in the Item class
                    int newQuantity = correspondingItem.getItemQuantity() + p.getQuantityRequested();
                    correspondingItem.setItemQuantity(newQuantity);
                    FileOperations.updateObjectInFile(correspondingItem, correspondingItem.getFilePath(),itemList);
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
        }
                
        FileOperations.updateObjectInFile(foundPO, foundPO.getFilePath(),poList);
    }
}

    