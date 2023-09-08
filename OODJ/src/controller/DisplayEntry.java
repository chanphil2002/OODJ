/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.*;

/**
 *
 * @author pc
 */
public class DisplayEntry {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void displaySupplierEntry(){
        List<Supplier> supplierList = FileOperations.readObjectsFromFile("resources/data/supplier.txt", new Supplier());
        
        System.out.printf("%-17s %-20s%n", "Supplier ID   |", "Supplier Name       ");
        for(Supplier s : supplierList){
            if(s.getDataAvailable()==true){
                System.out.printf("%-17s %-20s%n", s.getCode(), s.getSupplierName());
            }
        }
    }
    
    public static void displayItemsEntry(){
        Item item = new Item();
        List<Item> itemList = FileOperations.readObjectsFromFile("resources/data/item.txt",item);
        
        System.out.printf("%-17s %-25s %-25s %-20s%n", "Current Item ID |", "Current Item Name       |", "Current Item Quantity |", "Current Item Price ");
        for(Item i : itemList){
            if(i.getDataAvailable()==true){
                System.out.printf("%-17s %-25s %-25s %-20s%n", i.getCode(), 
                        i.getItemName(), i.getItemQuantity(),  
                        i.getSupplierCode(), i.getSupplierName());
            }
        }
    }
    
    public static void displaySalesEntry(){
        Sales sales = new Sales();
        List<Sales> salesList = FileOperations.readObjectsFromFile("resources/data/sales.txt", sales);
        
        System.out.printf("%-17s %-20s %-25s%n", "Date          |", "Sales ID       |", "Total ");
        for(Sales s : salesList){
            if(s.getDataAvailable()==true){
                System.out.printf("%-17s %-20s %-25s%n",s.getDate(), s.getCode(), s.getTotalSalesAmount());
            }
        }
        
    }
    
    public static void displayPREntry(){
        List<PurchaseRequisition> prList = 
                FileOperations.readObjectsFromFile("resources/data/purchaserequisition.txt", new PurchaseRequisition());
        
        System.out.printf("%-17s %-30s %-25s%n", "Date          |", "Purchase Requisition ID    |", "Status ");
        for(PurchaseRequisition p : prList){
            if(p.getDataAvailable()==true){
                System.out.printf("%-17s %-30s %-25s%n",  p.getDate(),p.getCode(), p.getStatus());
            }
        }
        
    }
    
    public static void displayPOEntry(){
        PurchaseOrder PO = new PurchaseOrder();
        List<PurchaseOrder> poList = 
                FileOperations.readObjectsFromFile("resources/data/purchaseorder.txt",PO);
        
        System.out.printf("%-17s %-22s %-20s%n", "Date          |", "Purchase Order ID   |", "Status ");
        for(PurchaseOrder p : poList){
            if(p.getDataAvailable()==true){
                System.out.printf("%-17s %-22s %-20s%n",  p.getDate(),p.getCode(), 
            p.getStatus());
            }
        }
    }
    
    
}
